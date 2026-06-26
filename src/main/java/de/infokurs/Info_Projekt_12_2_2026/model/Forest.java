//package de.infokurs.Info_Projekt_12_2_2026.model;
//
//
//import de.infokurs.Info_Projekt_12_2_2026.model.unicorns.Unicorn;
//
//import java.util.ArrayList;
//
//public class Forest {
//
//    private Temple temple;
//    private RandomGenerator gen;
//    private ArrayList<Unicorn> unicornsToSpawn;
//    private int despawnTime;
//    private int spawnTime
//    public Forest() throws InterruptedException {
//        temple = new Temple();
//        gen = new RandomGenerator(temple.getLuck());
//        forestThread.start();
//    }
//
//    Thread forestThread = new Thread(() -> {
//        try {
//            forrestSlots();
//        } catch (InterruptedException e) {
//            Thread.currentThread().interrupt();
//        }
//    });
//
//    void upgradeLuck() {
//        temple.luckUp();
//        gen.setLuck(temple.getLuck());
//    }
//
//    void upgradeTime() {
//        temple.remainTimeUp();
//        stable.setTime(temple.getRemainTime());
//    }
//
//    void upgradeCD() {
//        temple.spawnRateUp();
//    }
//
//
//    String spawn() throws InterruptedException {
//        int seconds = Math.toIntExact(Math.round(60 / temple.getSpawnRate()));
//        for (int i = seconds; i >= 0; i--) {
//            Thread.sleep(1000);
//        }
//
//        return gen.roll();
//
//    }
//
//    Unicorn unicornSpawn(String r) {
//
//        return new Unicorn(r);
//    }
//
//    void forrestSlots() throws InterruptedException {
//        int slots = stable.giveSlots();
//
//        if (slots > 0) {
//            slots--;
//            stable.setSlots(slots);
//            stable.addStables(unicornSpawn(spawn())); //has to create a random unicorn
//            forrestSlots();
//        }
//        Thread.sleep(1000);
//        forrestSlots();
//
//    }
//}
package de.infokurs.Info_Projekt_12_2_2026.model;

import de.infokurs.Info_Projekt_12_2_2026.Main;
import de.infokurs.Info_Projekt_12_2_2026.model.unicorns.Rarity;
import de.infokurs.Info_Projekt_12_2_2026.model.unicorns.Unicorn;

import java.util.ArrayList;

public class Forest {

    private Temple temple;
    private RandomGenerator gen;
    private ArrayList<Unicorn> unicorns;

    private int maxSlots = 5;
    private int despawnTime;
    private int spawnTime;

    public Forest() {
        temple = Main.getTemple();
        gen = new RandomGenerator(temple.getLuck());

        unicorns = new ArrayList<>();

        despawnTime = temple.getDespawnTime();

        forestThread.start();
    }

    Thread forestThread = new Thread(() -> {
        try {
            forestLoop();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    });

    void upgradeLuck() {
        temple.luckUp();
        gen.setLuck(temple.getLuck());
    }

    void upgradeTime() {
        temple.remainTimeUp();
        despawnTime = temple.getDespawnTime();
    }

    void upgradeCD() {
        temple.spawnRateUp();
    }

    String spawn() throws InterruptedException {
        int seconds = (int) (60f / temple.getSpawnRate());

        Thread.sleep(seconds * 1000L);

        return gen.roll();
    }

    Unicorn unicornSpawn(Rarity rarity) {
        return new Unicorn("dummy", "dummy", rarity, 1) {
            @Override
            public int getBaseCost() {
                return 0;
            }

            @Override
            public double getRps() {
                return 0;
            }
        };
    }

    void forestLoop() throws InterruptedException {

        while (true) {

            if (unicorns.size() < maxSlots) {

                Unicorn unicorn = unicornSpawn(Rarity.COMMON);//refactor spawn();
                unicorns.add(unicorn);

                System.out.println(unicorn + " spawned.");
            }

            Thread.sleep(100);
        }
    }

    public ArrayList<Unicorn> getUnicorns() {
        return unicorns;
    }
}
