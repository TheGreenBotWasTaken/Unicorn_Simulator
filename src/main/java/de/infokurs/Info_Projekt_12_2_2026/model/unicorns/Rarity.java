package de.infokurs.Info_Projekt_12_2_2026.model.unicorns;


import javafx.scene.paint.Color;

public enum Rarity {
    COMMON(Color.GRAY, 1, 5),
    UNUSUAL(Color.LIGHTGREEN, 1, 10),
    RARE(Color.LIGHTBLUE, 5, 15),
    EPIC(Color.PURPLE, 10, 30),
    LEGENDARY(Color.GOLD, 20, 50),
    ULTRA(Color.WHITE, 100, 100);

    private final Color color;

    Rarity(Color color, int minLevel, int maxLevel) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public boolean isRainbow() {
        return this == ULTRA;
    }
}