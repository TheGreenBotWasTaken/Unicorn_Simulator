package de.infokurs.Info_Projekt_12_2_2026;

import de.infokurs.Info_Projekt_12_2_2026.model.Forest;
import de.infokurs.Info_Projekt_12_2_2026.model.SaveManager;
import de.infokurs.Info_Projekt_12_2_2026.model.Shop;
import de.infokurs.Info_Projekt_12_2_2026.model.Temple;
import de.infokurs.Info_Projekt_12_2_2026.view.GuiManager;
import javafx.application.Application;

public class Main {
    static SaveManager saveManager;


    public static void main(String[] args) {
        saveManager = new SaveManager();

        Application.launch(GuiManager.class, args);
    }


    public static SaveManager getSaveManager() {
        return saveManager;
    }

    public static void setSaveManager(SaveManager saveManager) {
        saveManager = saveManager;
    }

}
