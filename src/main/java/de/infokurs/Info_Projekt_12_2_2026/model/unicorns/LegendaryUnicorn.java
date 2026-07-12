package de.infokurs.Info_Projekt_12_2_2026.model.unicorns;

public class LegendaryUnicorn extends Unicorn {
    private final int strength;
    private final int speed;
    private final int intelligence;
    private final boolean shiny;

    public LegendaryUnicorn(int strength, int speed, int intelligence, boolean shiny) {
        super("legendary_unicorn", Rarity.LEGENDARY, 1, "Legendary Unicorn!!");
        this.strength = strength;
        this.speed = speed;
        this.intelligence = intelligence;
        this.shiny = shiny;
    }

    @Override
    public boolean isShiny() {
        return shiny;
    }

    @Override
    public int getBasePrice() {
        return 2000;
    }

    @Override
    public int getRps() {
        return Math.max(1, strength * speed * 100 / 5000);
    }

    @Override
    public String getTexturePath() {
        return "assets/textures/unicorns/unicorn_legendary.png";
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

}