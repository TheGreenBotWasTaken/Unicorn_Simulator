package de.infokurs.Info_Projekt_12_2_2026.model;

public class RainbowManager {
    private static RainbowManager INSTANCE;
    private static SaveData current = SaveManager.getCurrent();
    public void addRainbows(int amount) {
        current.setRainbows(current.getRainbows() + amount);
    }

    public long getRainbows() {
        return current.getRainbows();
    }
    public static RainbowManager getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new RainbowManager();
        }

        return INSTANCE;
    }
    public void setRainbows(int rainbows) {
        current.setRainbows(rainbows);
    }
}
