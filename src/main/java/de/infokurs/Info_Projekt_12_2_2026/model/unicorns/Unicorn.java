package de.infokurs.Info_Projekt_12_2_2026.model.unicorns;

import de.infokurs.Info_Projekt_12_2_2026.model.Egg;
import de.infokurs.Info_Projekt_12_2_2026.model.BuyableItem;

public abstract class Unicorn implements BuyableItem { // future unicorns should extend this
    private final String ID; // NO SPACES
    private final String DISPLAY_NAME; // HERE SPACES
    private String name;
    private final Rarity RARITY;
    private int level;

    public Unicorn(String id, String displayName, Rarity rarity, int baseLevel) {
        this.ID = id;
        this.DISPLAY_NAME = displayName;
        this.RARITY = rarity;
        this.level = baseLevel;
    }
    public abstract int    getBasePrice();
    public abstract int getRps();

    public Rarity getRarity() {
        return RARITY;
    }

    public String getDisplayName() {
        return DISPLAY_NAME;
    }

    public String getId() {
        return ID;
    }
    public Egg layEgg() {
        return new Egg(RARITY, level, ID);
    }
    @Override
    public int weight() {
        return RARITY.weight();
    }

    public abstract Unicorn newInstance();
    @Override
    public double mult() {
        return RARITY.mult();
    }

    public abstract String getTexturePath();
}
