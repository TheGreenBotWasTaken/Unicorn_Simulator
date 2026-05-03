package de.infokurs.Info_Projekt_12_2_2026.unicorns;


import javafx.scene.paint.Color;

public enum Rarity {
    COMMON(Color.GRAY),
    UNCOMMON(Color.LIGHTGREEN),
    RARE(Color.LIGHTBLUE),
    EPIC(Color.PURPLE),
    LEGENDARY(Color.GOLD),
    MYTHIC(Color.LIGHTPINK),
    DIVINE(Color.WHITE); //placeholder, not used (rainbow instead)

    private final Color color;

    private Rarity(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public boolean isRainbow() {
        return this == DIVINE;
    }
}

