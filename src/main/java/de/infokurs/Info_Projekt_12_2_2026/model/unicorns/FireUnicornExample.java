package de.infokurs.Info_Projekt_12_2_2026.model.unicorns;

public class FireUnicornExample extends Unicorn{
    private static final FireUnicornExample INSTANCE = new FireUnicornExample();
    public FireUnicornExample() {
        super("fire_unicorn", "Fire Unicorn Example", Rarity.DIVINE);

    }
    public static FireUnicornExample getInstance() {
        return INSTANCE;
    }
}