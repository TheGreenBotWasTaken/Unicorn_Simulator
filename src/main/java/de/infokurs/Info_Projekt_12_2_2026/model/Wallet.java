package de.infokurs.Info_Projekt_12_2_2026.model;

public class Wallet {

    private static Wallet instance;


    private Wallet() {
    }

    public static synchronized Wallet getInstance() {
        if (instance == null) {
            instance = new Wallet();
        }
        return instance;
    }

    public int getBalance() {
        return SaveManager.getSaveData().getMoneten();
    }

    public void add(int amount) {
        SaveManager.getSaveData().setMoneten(SaveManager.getSaveData().getMoneten() + amount);
    }

    public boolean spend(int amount) {

        int current = SaveManager.getSaveData().getMoneten();

        if (current < amount) {
            return false;
        }
        SaveManager.getSaveData().setMoneten(current - amount);
        return true;

    }
}