package de.infokurs.Info_Projekt_12_2_2026.model.unicorns;

import de.infokurs.Info_Projekt_12_2_2026.model.registries.UnicornRegistry;

public class UnicornInit {

    public static void init() {
        UnicornRegistry registry = UnicornRegistry.getInstance();
        //register unicorns here like this:
        // registry.register(SomeUnicorn.getInstance());
        registry.register(FireUnicornExample.getInstance());
        System.out.println(UnicornRegistry.getInstance().getAll().size() + " unicorns intiialized!");
    }
}
