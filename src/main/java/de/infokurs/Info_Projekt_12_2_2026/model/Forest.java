package de.infokurs.Info_Projekt_12_2_2026.model;

import de.infokurs.Info_Projekt_12_2_2026.model.unicorns.FireUnicornExample;
import de.infokurs.Info_Projekt_12_2_2026.model.unicorns.Unicorn;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class Forest {

    private final Temple temple;
    private final RandomGenerator<Unicorn> randomGenerator;
    private final ArrayList<Unicorn> unicorns;
    private final Map<Unicorn, Long> spawnTimes;

    private int maxSlots = 5;
    private int despawnTime;

    public Forest(Temple temple) {
        System.out.println("[Forest] constructing...");

        this.temple = temple;
        randomGenerator = new RandomGenerator<>(temple.getLuck());
        unicorns = new ArrayList<>();
        spawnTimes = new LinkedHashMap<>();
        despawnTime = temple.getDespawnTime();

        System.out.println("[Forest] initial luck=" + temple.getLuck()
                + " despawnTime=" + despawnTime
                + " maxSlots=" + maxSlots);

        setupGenerator();
        spawnThread.start();
        despawnThread.start();

        System.out.println("[Forest] spawnThread and despawnThread started");
    }

    private void setupGenerator() {
        randomGenerator.add(new FireUnicornExample());
        //ALLE SPAWNABLE EINHÖRNER HIER EINFÜGEN

        System.out.println("[Forest] generator set up");
    }

    void upgradeLuck() {
        temple.luckUp();
        randomGenerator.setLuck(temple.getLuck());
        System.out.println("[Forest] luck upgraded -> " + temple.getLuck());
    }

    void upgradeTime() {
        temple.remainTimeUp();
        despawnTime = temple.getDespawnTime();
        System.out.println("[Forest] despawnTime upgraded -> " + despawnTime);
    }

    void upgradeCD() {
        temple.spawnRateUp();
        System.out.println("[Forest] spawnRate upgraded -> " + temple.getSpawnRate());
    }

    Unicorn spawn() throws InterruptedException {
        int seconds = (int) (60f / temple.getSpawnRate());
        System.out.println("[Forest] waiting " + seconds + "s before next spawn roll...");
        Thread.sleep(seconds * 1000L);

        Unicorn template = randomGenerator.roll();
        Unicorn unicorn = template.newInstance();
        System.out.println("[Forest] rolled " + unicorn);
        return unicorn;
    }

    private final Thread spawnThread = new Thread(() -> {
        try {
            spawnLoop();
        } catch (InterruptedException e) {
            System.out.println("[Forest] spawnThread interrupted, shutting down");
            Thread.currentThread().interrupt();
        }
    });

    private final Thread despawnThread = new Thread(() -> {
        try {
            despawnLoop();
        } catch (InterruptedException e) {
            System.out.println("[Forest] despawnThread interrupted, shutting down");
            Thread.currentThread().interrupt();
        }
    });

    void spawnLoop() throws InterruptedException {
        while (true) {
            if (unicorns.size() < maxSlots) {
                Unicorn unicorn = spawn();
                addUnicorn(unicorn);
            } else {
                System.out.println("[Forest] slots full (" + unicorns.size() + "/" + maxSlots + "), waiting...");
                Thread.sleep(100);
            }
        }
    }

    void despawnLoop() throws InterruptedException {
        while (true) {
            long now = System.currentTimeMillis();

            synchronized (unicorns) {
                int before = unicorns.size();
                unicorns.removeIf(u -> {
                    long expiry = spawnTimes.get(u) + despawnTime * 1000L;
                    boolean expired = now >= expiry;
                    if (expired) {
                        System.out.println("[Forest] despawning " + u);
                        spawnTimes.remove(u);
                    }
                    return expired;
                });
                int after = unicorns.size();
                if (before != after) {
                    System.out.println("[Forest] despawn pass removed " + (before - after)
                            + " unicorn(s), remaining=" + after);
                }
            }

            Thread.sleep(200); // check a few times a second
        }
    }

    private void addUnicorn(Unicorn unicorn) {
        synchronized (unicorns) {
            unicorns.add(unicorn);
            spawnTimes.put(unicorn, System.currentTimeMillis());
            System.out.println("[Forest] spawned " + unicorn + " (slots now " + unicorns.size() + "/" + maxSlots + ")");
        }
    }

    /**
     * Called by the UI when the player clicks/catches a unicorn.
     */
    public boolean catchUnicorn(Unicorn unicorn) {
        synchronized (unicorns) {
            spawnTimes.remove(unicorn);
            boolean removed = unicorns.remove(unicorn);
            System.out.println("[Forest] catchUnicorn(" + unicorn + ") -> " + removed);
            return removed;
        }
    }

    public ArrayList<Unicorn> getUnicorns() {
        synchronized (unicorns) {
            return new ArrayList<>(unicorns); // defensive copy for UI thread safety
        }
    }

    public void shutdown() {
        System.out.println("[Forest] shutdown requested");
        spawnThread.interrupt();
        despawnThread.interrupt();
    }
}