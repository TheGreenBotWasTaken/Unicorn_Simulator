package de.infokurs.Info_Projekt_12_2_2026.model.unicorns;

public class EpicUnicorn extends Unicorn {
    private String displayName;
    private int strength;
    private int speed;
    private int intelligence;

    public EpicUnicorn(int strength, int speed, int intelligence) {
        super("epic_unicorn", Rarity.EPIC, 1);
        this.strength = strength;
        this.speed = speed;
        this.intelligence = intelligence;
        this.displayName = "Epic Unicorn";
    }

    @Override
    public int getBasePrice() {
        return 10;
    }

    @Override
    public int getRps() {
        return 1;
    }

    @Override
    public String getTexturePath() {
        return "assets/textures/unicorns/unicorn_epic.png";
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