package de.infokurs.Info_Projekt_12_2_2026.model.unicorns;

public class CommonUnicorn extends Unicorn {
    private String displayName; // HERE SPACES
    private int strength;
    private int speed;
    private int intelligence;
    private boolean shiny;
    public CommonUnicorn(int strength, int speed, int intelligence, boolean shiny) {
        super("common_unicorn", Rarity.COMMON, 1);
        this.strength = strength;
        this.speed = speed;
        this.intelligence = intelligence;
        this.displayName = "Common Unicorn";
        this.shiny = shiny;
    }

    @Override
    public boolean isShiny() {
        return shiny;
    }

    @Override
    public int getBasePrice() {
        return 50;
    }

    @Override
    public int getRps() {
        return strength * speed * 1 / 5000;
    }

    @Override
    public String getTexturePath() {
        return "assets/textures/unicorns/unicorn_common.png";
    }
    @Override
    public int getIntelligence() {
        return intelligence;
    }

    @Override
    public int getSpeed() {
        return speed;
    }

    @Override
    public int getStrength() {
        return strength;
    }

    @Override
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String getDisplayName() {
        return displayName;
    }
}