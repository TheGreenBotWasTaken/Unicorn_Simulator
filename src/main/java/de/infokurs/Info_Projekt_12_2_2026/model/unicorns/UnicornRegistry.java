package de.infokurs.Info_Projekt_12_2_2026.model.unicorns;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class UnicornRegistry {

    private static UnicornRegistry instance;

    // Alle bekannten Unicorn-IDs - muss synchron mit UnicornFactory gehalten werden.
    private static final String[] ALL_UNICORN_IDS = {
            "common_unicorn",
            "unusual_unicorn",
            "rare_unicorn",
            "epic_unicorn",
            "legendary_unicorn",
            "ultra_unicorn"
            // "ice_unicorn",
            // weitere IDs hier ergänzen, sobald UnicornFactory sie kennt
    };

    private final Map<Rarity, List<String>> idsByRarity = new EnumMap<>(Rarity.class);

    private UnicornRegistry() {
        buildIndex();
    }

    public static synchronized UnicornRegistry getInstance() {
        if (instance == null) {
            instance = new UnicornRegistry();
        }
        return instance;
    }

    private void buildIndex() {
        for (Rarity rarity : Rarity.values()) {
            idsByRarity.put(rarity, new ArrayList<>());
        }

        for (String id : ALL_UNICORN_IDS) {
            Unicorn instance = UnicornFactory.createById(id);
            idsByRarity.get(instance.getRarity()).add(id);
        }
    }


    public List<String> getIdsByRarity(Rarity rarity) {
        return Collections.unmodifiableList(idsByRarity.get(rarity));
    }

    public List<String> getAllIds() {
        return List.of(ALL_UNICORN_IDS);
    }
}