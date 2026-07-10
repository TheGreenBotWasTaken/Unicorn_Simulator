package de.infokurs.Info_Projekt_12_2_2026.model;

import de.infokurs.Info_Projekt_12_2_2026.model.unicorns.Unicorn;

import java.util.List;

public class SaveData {

    public final transient float VERSION = 1.0f;
    public final transient String NAME = "LOV PADDOCK V. " + (2000 + VERSION);




    private double spawnUpgrade = 1.0;
    private int despawnUpgrade = 10;
    private double luckUpgrade = 1.0;
    private int moneten = 0;
    private long rainbows = 0;
    private double volume;

    private List<Unicorn> unicorns;

    public List<Unicorn> getUnicorns() {
        return unicorns;
    }

    public void setUnicorns(List<Unicorn> unicorns) {
        this.unicorns = unicorns;
    }

    public double getSpawnUpgrade() {
        return spawnUpgrade;
    }

    public void setSpawnUpgrade(double timeUpgrade) {
        this.spawnUpgrade = timeUpgrade;
    }

    public double getLuckUpgrade() {
        return luckUpgrade;
    }

    public void setLuckUpgrade(double luckUpgrade) {
        this.luckUpgrade = luckUpgrade;
    }

    public int getDespawnUpgrade() {
        return despawnUpgrade;
    }

    public void setDespawnUpgrade(int despawnUpgrade) {
        this.despawnUpgrade = despawnUpgrade;
    }

    public long getRainbows() { return rainbows; }
    public void setRainbows(long rainbows) { this.rainbows = rainbows; }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public int getMoneten() {
        return moneten;
    }

    public void setMoneten(int moneten) {
        this.moneten = moneten;
    }
}