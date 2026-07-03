package de.infokurs.Info_Projekt_12_2_2026.model;

public class SaveData {

    public final float VERSION = 1.0f;
    public final String NAME = "UNICORN CLICKING SIMULATOR DELUXE ULTIMATE V. " + (2000 + VERSION);
    private long rainbows = 0;
    private double volume;
    private int money;

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
}
