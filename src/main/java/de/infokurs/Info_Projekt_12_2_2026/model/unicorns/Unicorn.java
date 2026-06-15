package de.infokurs.Info_Projekt_12_2_2026.model.unicorns;

import de.infokurs.Info_Projekt_12_2_2026.model.Egg;

public abstract class Unicorn { // future unicorns should extend this, be singletons, register in UnicornRegistry
    private final String ID; // NO SPACES
    private final String DISPLAY_NAME; // HERE SPACES
    private static String name;
    private final Rarity RARITY;
    private final int LEVEL;

    public Unicorn(String id, String displayName, Rarity rarity, int level) {
        this.ID = id;
        this.DISPLAY_NAME = displayName;
        this.RARITY = rarity;
        LEVEL = level;
    }
    public abstract int    getBaseCost();
    public abstract double getRps();

    public Rarity getRarity() {
        return RARITY;
    }

    public String getDisplayName() {
        return DISPLAY_NAME;
    }

    public String getId() {
        return ID;
    }
    //lay egg method with level & rarity
    public Egg layEgg() {
        return new Egg(RARITY, LEVEL);
    }
}
