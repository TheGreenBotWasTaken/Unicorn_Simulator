package de.infokurs.Info_Projekt_12_2_2026.model.unicorns;

import java.util.Random;

public class UnicornFactory {

    public static Unicorn createById(String id) {
        Random random = new Random();
        int intelligence = random.nextInt(100) + 1;
        int strength = random.nextInt(100) + 1;
        int speed = random.nextInt(100) + 1;
        return switch (id) {
            case "common_unicorn" ->    new CommonUnicorn(strength, speed, intelligence);
            case "unusual_unicorn" ->   new UnusualUnicorn(strength, speed, intelligence);
            case "rare_unicorn" ->      new RareUnicorn(strength, speed, intelligence);
            case "epic_unicorn" ->      new EpicUnicorn(strength, speed, intelligence);
            case "legendary_unicorn" -> new LegendaryUnicorn(strength, speed, intelligence);
            case "ultra_unicorn" ->     new UltraUnicorn(strength, speed, intelligence);
            // wenn weitere unicorns cases add
            default -> throw new IllegalArgumentException("Unbekannte Unicorn-ID: " + id);
        };
    }
}