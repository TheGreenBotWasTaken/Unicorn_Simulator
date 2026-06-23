package de.infokurs.Info_Projekt_12_2_2026.model.unicorns;

import de.infokurs.Info_Projekt_12_2_2026.model.registries.UnicornRegistry;

public class UnicornInit {
// DEPRECATED DONT USE
    public static void init() {
        UnicornRegistry registry = UnicornRegistry.getInstance();

        registry.register(FireUnicornExample.getInstance());
        System.out.println(UnicornRegistry.getInstance().getAll().size() + " unicorns intiialized!");
    }
}
