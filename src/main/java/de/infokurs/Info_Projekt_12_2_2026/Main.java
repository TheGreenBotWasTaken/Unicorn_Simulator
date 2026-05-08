package de.infokurs.Info_Projekt_12_2_2026;

import de.infokurs.Info_Projekt_12_2_2026.model.SaveManager;
import de.infokurs.Info_Projekt_12_2_2026.model.unicorns.UnicornInit;
import de.infokurs.Info_Projekt_12_2_2026.view.GuiManager;
import javafx.application.Application;

public class Main {
    SaveManager saveManager;
    public static void main(String[] args) {
        SaveManager saveManager = new SaveManager();
        UnicornInit.init();
        Application.launch(GuiManager.class, args);
    }

}
