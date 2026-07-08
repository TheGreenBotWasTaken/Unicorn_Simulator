package de.infokurs.Info_Projekt_12_2_2026.model.unicorns;

public class UnicornFactory {

    public static Unicorn createById(String id) {
        return switch (id) {
            case "fire_unicorn" -> new FireUnicornExample();
            // Hier weitere Einhörner ergänzen, z.B.:
            // case "ice_unicorn" -> new IceUnicorn();
            default -> throw new IllegalArgumentException("Unbekannte Unicorn-ID: " + id);
        };
    }
}