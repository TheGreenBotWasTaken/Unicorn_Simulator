package de.infokurs.Info_Projekt_12_2_2026.model.unicorns;

public class FireUnicornExample extends Unicorn{
    private static final FireUnicornExample INSTANCE = new FireUnicornExample();
    public FireUnicornExample() {
        super("fire_unicorn", "Fire Unicorn Example", Rarity.ULTRA, 1);

    }
    public static FireUnicornExample getInstance() {
        return INSTANCE;
    }

    @Override
    public int getBasePrice() {
        return 10;
    }

    @Override
    public int getRps() {
        return 12345;
    }

    @Override
    public Unicorn newInstance() {
        return new FireUnicornExample();
    }

    @Override
    public String getTexturePath() {
        return "assets/textures/unicorns/unicorn_1.png";
    }

}