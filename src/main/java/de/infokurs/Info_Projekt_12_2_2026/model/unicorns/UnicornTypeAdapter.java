package de.infokurs.Info_Projekt_12_2_2026.model.unicorns;

import com.google.gson.*;

import java.lang.reflect.Type;

public class UnicornTypeAdapter implements JsonSerializer<Unicorn>, JsonDeserializer<Unicorn> {

    private static final String CLASS_NAME = "className";
    private static final String DATA = "data";

    @Override
    public JsonElement serialize(Unicorn src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject result = new JsonObject();
        result.addProperty(CLASS_NAME, src.getClass().getName());
        // Wichtig: mit src.getClass() serialisieren, nicht mit Unicorn.class,
        // sonst gehen genau die Subklassen-Felder verloren, um die es hier geht.
        result.add(DATA, context.serialize(src, src.getClass()));
        return result;
    }

    @Override
    public Unicorn deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        String className = jsonObject.get(CLASS_NAME).getAsString();
        JsonElement data = jsonObject.get(DATA);

        try {
            Class<?> clazz = Class.forName(className);
            return context.deserialize(data, clazz);
        } catch (ClassNotFoundException e) {
            throw new JsonParseException("Unbekannte Unicorn-Klasse beim Laden: " + className, e);
        }
    }
}