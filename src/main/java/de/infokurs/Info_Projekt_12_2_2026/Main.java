package de.infokurs.Info_Projekt_12_2_2026;

import de.infokurs.Info_Projekt_12_2_2026.model.*;
import de.infokurs.Info_Projekt_12_2_2026.model.unicorns.Rarity;
import de.infokurs.Info_Projekt_12_2_2026.view.GuiManager;
import javafx.application.Application;

public class Main {

    public static void main(String[] args) {

        Application.launch(GuiManager.class, args);

    }


    public static void setSaveManager(SaveManager saveManager) {
        saveManager = saveManager;
    }

}
