package de.infokurs.Info_Projekt_12_2_2026.model;

public class RainbowManager {
    private static SaveData current = SaveManager.getCurrent();
    public static void addRainbows(int amount) {
        current.setRainbows(current.getRainbows() + amount);
    }

    public static long getRainbows() {
        return current.getRainbows();
    }

    public static void setRainbows(int rainbows) {
        current.setRainbows(rainbows);
    }
}
