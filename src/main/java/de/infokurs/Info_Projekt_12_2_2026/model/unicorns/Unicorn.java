package de.infokurs.Info_Projekt_12_2_2026.model.unicorns;

public abstract class Unicorn { // future unicorns should extend this, be singletons, register in UnicornRegistry
    private final String ID; // NO SPACES
    private final String DISPLAY_NAME; // HERE SPACES
    private final Rarity RARITY;

    public Unicorn(String id, String displayName, Rarity rarity) {
        this.ID = id;
        this.DISPLAY_NAME = displayName;
        this.RARITY = rarity;
    }

    public Rarity getRarity() {
        return RARITY;
    }

    public String getDisplayName() {
        return DISPLAY_NAME;
    }

    public String getId() {
        return ID;
    }
}
