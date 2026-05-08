package de.infokurs.Info_Projekt_12_2_2026.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;

public class SaveManager {
    private static final String SAVE_FILE = "savestate.json";
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private static SaveData current = load();


    public static SaveData getCurrent() {
        return current;
    }
    public static void save(SaveData data) {
        try (Writer writer = new FileWriter(SAVE_FILE)) {
            gson.toJson(data, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static SaveData load() {
        File file = new File(SAVE_FILE);
        if (!file.exists()) return new SaveData();

        try (Reader reader = new FileReader(file)) {
            return gson.fromJson(reader, SaveData.class);
        } catch (IOException e) {
            e.printStackTrace();
            return new SaveData();
        }
    }
}