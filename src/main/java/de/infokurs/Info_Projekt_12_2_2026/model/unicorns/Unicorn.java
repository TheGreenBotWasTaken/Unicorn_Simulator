package de.infokurs.Info_Projekt_12_2_2026.model.unicorns;

import de.infokurs.Info_Projekt_12_2_2026.model.Egg;
import de.infokurs.Info_Projekt_12_2_2026.model.BuyableItem;

public abstract class Unicorn implements BuyableItem { // future unicorns should extend this
    private final String ID; // NO SPACES
    private final Rarity RARITY;
    private int level;

    public Unicorn(String id, Rarity rarity, int baseLevel) {
        this.ID = id;
        this.RARITY = rarity;
        this.level = baseLevel;
    }
    public abstract boolean isShiny();
    public abstract int getBasePrice();

    public abstract int getRps();

    public Rarity getRarity() {
        return RARITY;
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

    @Override
    public double mult() {
        return RARITY.mult();
    }

    public abstract String getTexturePath();

    public abstract int getIntelligence();

    public abstract int getSpeed();

    public abstract int getStrength();

    public abstract void setDisplayName(String displayName);

    public abstract String getDisplayName();
}
