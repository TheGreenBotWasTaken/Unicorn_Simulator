package de.infokurs.Info_Projekt_12_2_2026.model;

public class RainbowManager {
    private static RainbowManager INSTANCE;
    private final SaveData current;

    private RainbowManager() {
        this.current = SaveManager.getSaveData();
    }

    public static synchronized RainbowManager getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new RainbowManager();
        }
        return INSTANCE;
    }

    public synchronized void addRainbows(long amount) {
        current.setRainbows(current.getRainbows() + amount);
    }

    public synchronized long getRainbows() {
        return current.getRainbows();
    }

    public synchronized void setRainbows(long rainbows) {
        current.setRainbows(rainbows);
    }
}