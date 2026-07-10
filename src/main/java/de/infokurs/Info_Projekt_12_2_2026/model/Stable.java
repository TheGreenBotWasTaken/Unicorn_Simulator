package de.infokurs.Info_Projekt_12_2_2026.model;

import de.infokurs.Info_Projekt_12_2_2026.model.unicorns.Unicorn;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Stable {
    private static volatile Stable INSTANCE;

    private static final int MAX_CAPACITY = 10000; // upgrades? später mit research update

    private final List<Unicorn> unicorns;

    private final ScheduledExecutorService rainbowExecutor;

    private Stable() {
        this.unicorns = new CopyOnWriteArrayList<>();
        this.rainbowExecutor = Executors.newSingleThreadScheduledExecutor(r -> {
            Thread t = new Thread(r, "rainbow-generator");
            t.setDaemon(true);
            return t;
        });

        rainbowExecutor.scheduleAtFixedRate(this::generateRainbows, 1, 1, TimeUnit.SECONDS);
    }

    private void generateRainbows() {
        int totalRps = 0;
        for (Unicorn u : unicorns) {
            totalRps += u.getRps();
        }
        if (totalRps > 0) {
            RainbowManager.getInstance().addRainbows(totalRps);
        }
    }

    public static Stable getInstance() {
        Stable result = INSTANCE;
        if (result == null) {
            synchronized (Stable.class) {
                result = INSTANCE;
                if (result == null) {
                    INSTANCE = result = new Stable();
                }
            }
        }
        return result;
    }


    public boolean addUnicorn(Unicorn unicorn) {
        if (unicorns.size() >= MAX_CAPACITY) {
            System.out.println("Der Stall ist voll! " + unicorn.getDisplayName()
                    + " kann nicht mehr hinzugefügt werden.");
            return false;
        }

        unicorns.add(unicorn);
        System.out.println(unicorn.getDisplayName() + " wurde zum Stall hinzugefügt.");
        return true;
    }

    public boolean removeUnicorn(String name) {
        Unicorn unicorn = findUnicornByName(name);

        if (unicorn == null) {
            System.out.println("Das Einhorn namens " + name + " ist nicht im Stall vorhanden.");
            return false;
        }

        unicorns.remove(unicorn);
        System.out.println(unicorn.getDisplayName() + " wurde aus dem Stall entfernt.");
        return true;
    }

    public Unicorn findUnicornByName(String name) {
        for (Unicorn unicorn : unicorns) {
            if (unicorn.getDisplayName().equalsIgnoreCase(name)) {
                return unicorn;
            }
        }
        return null;
    }

    public List<Unicorn> getUnicorns() {
        return List.copyOf(unicorns);
    }

    public int getCurrentSize() {
        return unicorns.size();
    }

    public boolean isFull() {
        return unicorns.size() >= MAX_CAPACITY;
    }

    public void shutdown() {
        rainbowExecutor.shutdownNow();
    }
}