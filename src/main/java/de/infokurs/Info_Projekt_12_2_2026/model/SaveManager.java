package de.infokurs.Info_Projekt_12_2_2026.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import de.infokurs.Info_Projekt_12_2_2026.model.unicorns.Unicorn;
import de.infokurs.Info_Projekt_12_2_2026.model.unicorns.UnicornTypeAdapter;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SaveManager {
    private static final String SAVE_FILE = "savestate.json";
    private static final Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .registerTypeAdapter(Unicorn.class, new UnicornTypeAdapter())
            .create();
    private static SaveData current = load();

    public static SaveData getSaveData() {
        return current;
    }

    public static void save(SaveData data) {
        List<Unicorn> unicorns = new ArrayList<>(Stable.getInstance().getUnicorns());
        data.setUnicorns(unicorns);

        try (Writer writer = new FileWriter(SAVE_FILE)) {
            gson.toJson(data, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Lädt SaveData von der Festplatte und stellt zusätzlich die im Stall
     * gehaltenen Unicorn-Instanzen direkt aus den gespeicherten Objekten wieder her.
     */
    public static SaveData load() {
        File file = new File(SAVE_FILE);
        if (!file.exists()) return new SaveData();

        try (Reader reader = new FileReader(file)) {
            SaveData data = gson.fromJson(reader, SaveData.class);
            if (data == null) {
                return new SaveData();
            }

            if (data.getUnicorns() != null) {
                for (Unicorn u : data.getUnicorns()) {
                    Stable.getInstance().addUnicorn(u);
                }
            }

            return data;
        } catch (IOException e) {
            e.printStackTrace();
            return new SaveData();
        }
    }
}