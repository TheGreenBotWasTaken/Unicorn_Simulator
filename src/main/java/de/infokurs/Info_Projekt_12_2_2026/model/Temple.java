package de.infokurs.Info_Projekt_12_2_2026.model;

public class Temple {
    private static Temple INSTANCE;
    SaveData saveData = SaveManager.getSaveData();

    public static Temple getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new Temple();
        }
        return INSTANCE;
    }

    public double getSpawnRate() {
        return saveData.getSpawnUpgrade();
    }

    public void setSpawnRate(double spawnRate) {
        saveData.setSpawnUpgrade(spawnRate);
    }

    public double getLuck() {
        return saveData.getLuckUpgrade();
    }

    public void setLuck(double luck) {
        saveData.setLuckUpgrade(luck);
    }

    public int getDespawnTime() {
        return saveData.getDespawnUpgrade();
    }

    public void setDespawnTime(int despawnTime) {
        saveData.setDespawnUpgrade(despawnTime);
    }

    void luckUp() {
        setLuck(getLuck() + 0.1);
    }

     void spawnRateUp() {
        setSpawnRate(getSpawnRate() + 0.1);
    }

     void remainTimeUp() {
        setDespawnTime(getDespawnTime() + 1);
    }
}
//Preis muss noch hinzugefügt werden (done)
