package de.infokurs.Info_Projekt_12_2_2026.model.unicorns;

public class FireUnicornExample extends Unicorn{
    private static final FireUnicornExample INSTANCE = new FireUnicornExample();
    public FireUnicornExample() {
        super("fire_unicorn", "Fire de.infokurs.Info_Projekt_12_2_2026.model.Unicorn Example", Rarity.ULTRA, 1);

    }
    public static FireUnicornExample getInstance() {
        return INSTANCE;
    }

    @Override
    public int getBaseCost() {
        return 10;
    }

    @Override
    public double getRps() {
        return 12345;
    }
}