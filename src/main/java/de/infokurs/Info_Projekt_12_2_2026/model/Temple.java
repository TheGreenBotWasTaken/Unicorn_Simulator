package de.infokurs.Info_Projekt_12_2_2026.model;

public class Temple {
    private static Temple INSTANCE;
    double luck;
    double spawnRate;
    int despawnTime;

    public Temple() {

        luck = 1.0;
        spawnRate = 4.0;
        despawnTime = 10;

    }
    public static Temple getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new Temple();
        }

        return INSTANCE;
    }
    public double getSpawnRate() {
        return spawnRate;
    }

    public void setSpawnRate(double spawnRate) {
        this.spawnRate = spawnRate;
    }

    public double getLuck() {
        return luck;
    }

    public void setLuck(double luck) {
        this.luck = luck;
    }

    public int getDespawnTime() {
        return despawnTime;
    }

    public void setDespawnTime(int despawnTime) {
        this.despawnTime = despawnTime;
    }





    void luckUp() {
        setLuck(luck + 0.1);
    }

    void spawnRateUp() {
        setSpawnRate(spawnRate + 0.1);
    }

    void remainTimeUp() {
        setDespawnTime(despawnTime + 1);
    }
}
//Preis muss noch hinzugefügt werden
