package de.infokurs.Info_Projekt_12_2_2026.model.unicorns;

public class UltraUnicorn extends Unicorn {
    private String displayName; // HERE SPACES
    private int strength;
    private int speed;
    private int intelligence;
    public UltraUnicorn(int strength, int speed, int intelligence) {
        super("ultra_unicorn", Rarity.ULTRA, 1);
        this.strength = strength;
        this.speed = speed;
        this.intelligence = intelligence;
        this.displayName = "Ultra Unicorn";
    }

    @Override
    public int getBasePrice() {
        return 10000;
    }

    @Override
    public int getRps() {
        return 10000;
    }


    @Override
    public String getTexturePath() {
        return "assets/textures/unicorns/unicorn_ultra.png";
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