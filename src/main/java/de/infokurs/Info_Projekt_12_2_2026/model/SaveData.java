package de.infokurs.Info_Projekt_12_2_2026.model;

import de.infokurs.Info_Projekt_12_2_2026.model.unicorns.Unicorn;

import java.util.List;

public class SaveData {

    public final transient float VERSION = 1.0f;
    public final transient String NAME = "UNICORN CLICKING SIMULATOR DELUXE ULTIMATE V. " + (2000 + VERSION);



    private int moneten = 0;
    private long rainbows = 0;
    private double volume;
    private int money;

    private List<Unicorn> unicorns;

    public List<Unicorn> getUnicorns() {
        return unicorns;
    }

    public void setUnicorns(List<Unicorn> unicorns) {
        this.unicorns = unicorns;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
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