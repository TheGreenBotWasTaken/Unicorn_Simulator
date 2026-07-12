package de.infokurs.Info_Projekt_12_2_2026.model.unicorns;

import java.util.Random;

public class UnicornFactory {
    private static final Random RANDOM = new Random();
    public static Unicorn createById(String id) {
        int intelligence = RANDOM.nextInt(100) + 1;
        int strength = RANDOM.nextInt(100) + 1;
        int speed = RANDOM.nextInt(100) + 1;
        boolean shiny = RANDOM.nextInt(100) == 0;
        return switch (id) {
            case "common_unicorn" ->    new CommonUnicorn(strength, speed, intelligence, shiny);
            case "unusual_unicorn" ->   new UnusualUnicorn(strength, speed, intelligence, shiny);
            case "rare_unicorn" ->      new RareUnicorn(strength, speed, intelligence, shiny);
            case "epic_unicorn" ->      new EpicUnicorn(strength, speed, intelligence, shiny);
            case "legendary_unicorn" -> new LegendaryUnicorn(strength, speed, intelligence, shiny);
            case "ultra_unicorn" ->     new UltraUnicorn(strength, speed, intelligence, shiny);
            // wenn weitere unicorns cases add
            default -> throw new IllegalArgumentException("Unbekannte Unicorn-ID: " + id);
        };
    }
}