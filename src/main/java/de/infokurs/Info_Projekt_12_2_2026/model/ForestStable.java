package de.infokurs.Info_Projekt_12_2_2026.model;

import de.infokurs.Info_Projekt_12_2_2026.model.unicorns.Unicorn;

public class ForestStable {


    static Unicorn start;
    int slots;
    int time;
    Thread t1 = new Thread(() -> {
        try {
            timeLimit();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    });


    public ForestStable() {
        start = null;
        slots = 5;
        time = 10;
        t1.start();
    }

    void spawnLoop() throws InterruptedException {
        while (true) {

            if (start != null) {

                Thread.sleep(time * 1000L);

                start = start.giveNextTo();
                slots++;
            } else {
                Thread.sleep(1000);
            }
        }
    }


}

