package de.infokurs.Info_Projekt_12_2_2026;

import de.infokurs.Info_Projekt_12_2_2026.model.Forest;
import de.infokurs.Info_Projekt_12_2_2026.model.SaveManager;
import de.infokurs.Info_Projekt_12_2_2026.model.Temple;
import de.infokurs.Info_Projekt_12_2_2026.model.unicorns.UnicornInit;
import de.infokurs.Info_Projekt_12_2_2026.view.GuiManager;
import javafx.application.Application;

public class Main {
    static SaveManager saveManager;
    static Temple temple;
    static Forest forest;

    public static void main(String[] args) {
        SaveManager saveManager = new SaveManager();
        Temple temple = new Temple();
        Forest forest = new Forest();
        UnicornInit.init();
        Application.launch(GuiManager.class, args);
    }

    public static Forest getForest() {
        return forest;
    }

    public static void setForest(Forest forest) {
        forest = forest;
    }

    public static Temple getTemple() {
        return temple;
    }

    public static void setTemple(Temple temple) {
        temple = temple;
    }

    public static SaveManager getSaveManager() {
        return saveManager;
    }

    public static void setSaveManager(SaveManager saveManager) {
        saveManager = saveManager;
    }

}
