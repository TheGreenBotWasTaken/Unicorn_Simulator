package de.infokurs.Info_Projekt_12_2_2026.model.unicorns;

import de.infokurs.Info_Projekt_12_2_2026.model.BuyableItem;
import de.infokurs.Info_Projekt_12_2_2026.model.Egg;

public abstract class Unicorn implements BuyableItem { // future unicorns should extend this
    private final String id; // NO SPACES
    private final Rarity rarity;
    private int level;
    private String displayName;

    public Unicorn(String id, Rarity rarity, int baseLevel, String displayName) {
        this.id = id;
        this.rarity = rarity;
        this.level = baseLevel;
        this.displayName = displayName;
    }

    public abstract boolean isShiny();

    public abstract int getBasePrice();

    public abstract int getRps();

    public Rarity getRarity() {
        return rarity;
    }

    public String getId() {
        return id;
    }

    public Egg layEgg() {
        return new Egg(rarity, level, id);
    }

    @Override
    public int weight() {
        return rarity.weight();
    }

    @Override
    public double mult() {
        return rarity.mult();
    }

    public abstract String getTexturePath();

    public abstract int getIntelligence();

    public abstract int getSpeed();

    public abstract int getStrength();

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        if (displayName == null || displayName.isBlank()) return; // guard against empty rename
        this.displayName = displayName.trim();
    }
}