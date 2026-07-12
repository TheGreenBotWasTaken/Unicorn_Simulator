package de.infokurs.Info_Projekt_12_2_2026.model.unicorns;


import javafx.scene.paint.Color;

public enum Rarity {
    COMMON(Color.WHITE, 1, 5, 10, "Common", 500, 0),
    UNUSUAL(Color.PURPLE, 1, 10, 20, "Unusual", 250, 0),
    RARE(Color.LIGHTBLUE, 5, 15, 30, "Rare", 150, 1),
    EPIC(Color.YELLOW, 10, 30, 50, "Epic", 89, 2),
    LEGENDARY(Color.PINK, 20, 50, 100, "Legendary", 10, 2),
    ULTRA(Color.WHITE, 100, 100, 1000, "ULTRA SONIC SUPER INFINITE MEGA GIGA EXTREMELY COOL RAINBOW", 1, 4);

    Rarity(Color color, int minLevel, int maxLevel, int hatchFactor, String name, int weight, double multi) {
        this.weight = weight;
        this.multi = multi;
        this.color = color;
        this.minLevel = minLevel;
        this.maxLevel = maxLevel;
        this.hatchFactor = hatchFactor;
        this.name = name;
    }

    private final Color color;
    private final int minLevel;
    private final int maxLevel;
    private final int weight;
    private final double multi;
    private final String name;
    private final int hatchFactor;

    public String getName() {
        return name;
    }


    public int weight() {
        return weight;
    }

    public double mult() {
        return multi;
    }

    public int getHatchFactor() {
        return hatchFactor;
    }

    public int getMaxLevel() {
        return maxLevel;
    }

    public int getMinLevel() {
        return minLevel;
    }

    public Color getColor() {
        return color;
    }

    public boolean isRainbow() {
        return this == ULTRA;
    }
}