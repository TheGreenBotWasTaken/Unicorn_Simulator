package de.infokurs.Info_Projekt_12_2_2026.model;

public class UnicornNumberFormatter {

    private static final String[] SUFFIXES = {
            "", "k", "M", "B", "T",
            "Qd", "Qn", "Sx", "Sp",
            "Oc", "No", "De"
    };

    public static String format(long value) {
        if (value < 0) return "-" + format(-value);
        if (value < 1_000) return String.valueOf((int) value);

        double d = (double) value;
        int suffixIndex = 0;

        while (d >= 1_000 && suffixIndex < SUFFIXES.length - 1) {
            d /= 1_000;
            suffixIndex++;
        }

        String formatted;
        if (d >= 100) {
            formatted = String.format("%.0f", d);
        } else if (d >= 10) {
            formatted = String.format("%.1f", d);
        } else {
            formatted = String.format("%.2f", d);
        }

        return formatted + SUFFIXES[suffixIndex];
    }
}