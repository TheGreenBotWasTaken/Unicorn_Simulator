package de.infokurs.Info_Projekt_12_2_2026.model.unicorns;


import javafx.scene.paint.Color;

public enum Rarity {
    COMMON(Color.GRAY, 1, 5, 1, "Common"),
    UNUSUAL(Color.LIGHTGREEN, 1, 10, 2, "Unusual"),
    RARE(Color.LIGHTBLUE, 5, 15, 3, "Rare"),
    EPIC(Color.PURPLE, 10, 30, 5, "Epic"),
    LEGENDARY(Color.GOLD, 20, 50, 10, "Legendary"),
    ULTRA(Color.WHITE, 100, 100, -1, "ULTRA");

    private final Color color;
    private final int minLevel;
    private final int maxLevel;

    public String getName() {
        return name;
    }

    private final String name;

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

    Rarity(Color color, int minLevel, int maxLevel, int hatchFactor, String name) {
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