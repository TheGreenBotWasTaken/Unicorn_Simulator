package de.infokurs.Info_Projekt_12_2_2026.model.registries;

import de.infokurs.Info_Projekt_12_2_2026.model.unicorns.Unicorn;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

public class UnicornRegistry {

    private static final UnicornRegistry INSTANCE = new UnicornRegistry();
    private final Map<String, Unicorn> unicorns = new LinkedHashMap<>();



    public static UnicornRegistry getInstance() {
        return INSTANCE;
    }

    public void register(Unicorn unicorn) {
        if (unicorns.containsKey(unicorn.getId())) {
            throw new IllegalArgumentException(
                    "Unicorn with id \"" + unicorn.getId() + "'\" is already registered."
            );
        }
        unicorns.put(unicorn.getId(), unicorn);
    }

    public Optional<Unicorn> get(String id) { //fancy optionals for returning null? done
        
        return Optional.ofNullable(unicorns.get(id));
    }

    public Map<String, Unicorn> getAll() {
        return Collections.unmodifiableMap(unicorns);
    }
}