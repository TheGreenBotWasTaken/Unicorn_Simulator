package de.infokurs.Info_Projekt_12_2_2026;

public class Properties {
    public static final float VERSION = 1.0f;
    public static final String NAME = "UNICORN CLICKING SIMULATOR DELUXE ULTIMATE V. " + (2000 + VERSION);
    private static int rainbows = 0;

    public static int getRainbows() {
        return rainbows;
    }

    public static void setRainbows(int rainbows) {
        Properties.rainbows = rainbows;
    }
}
