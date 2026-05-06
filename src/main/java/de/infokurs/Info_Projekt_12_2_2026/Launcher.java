package de.infokurs.Info_Projekt_12_2_2026;

import de.infokurs.Info_Projekt_12_2_2026.model.unicorns.UnicornInit;
import javafx.application.Application;

public class Launcher {
    public static void main(String[] args) {
        UnicornInit.init();
        Application.launch(HelloApplication.class, args);
    }

}
