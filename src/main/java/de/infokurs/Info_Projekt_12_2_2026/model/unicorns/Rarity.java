package de.infokurs.Info_Projekt_12_2_2026.model.unicorns;


import javafx.scene.paint.Color;

public enum Rarity {
    COMMON(Color.GRAY, 1, 5, 1, "Common", 500, 0),
    UNUSUAL(Color.LIGHTGREEN, 1, 10, 2, "Unusual", 250, 0),
    RARE(Color.LIGHTBLUE, 5, 15, 3, "Rare", 150, 1),
    EPIC(Color.PURPLE, 10, 30, 5, "Epic", 89, 2),
    LEGENDARY(Color.GOLD, 20, 50, 10, "Legendary", 10, 3),
    ULTRA(Color.WHITE, 100, 100, -1, "ULTRA SONIC SUPER INFINITE MEGA GIGA EXTREM COOL RAINBOW", 1, 4);

    private final Color color;
    private final int minLevel;
    private final int maxLevel;
    private final int weight;
    private final double multi;

    public String getName() {
        return name;
    }

    private final String name;

    public int weight() { return weight; }
    public double mult() { return multi; }
    public int getHatchFactor() {
        return hatchFactor;
    }

    public int getMaxLevel() {
        return maxLevel;
    }

    public int getMinLevel() {
        return minLevel;
    }

    private final int hatchFactor;

    Rarity(Color color, int minLevel, int maxLevel, int hatchFactor, String name, int weight, double multi) {
        this.weight = weight;
        this.multi = multi;
        this.color = color;
        this.minLevel = minLevel;
        this.maxLevel = maxLevel;
        this.hatchFactor = hatchFactor;
        this.name = name;
    }

    public Color getColor() {
        return color;
    }

    public boolean isRainbow() {
        return this == ULTRA;
    }
}