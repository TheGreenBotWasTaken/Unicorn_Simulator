package de.infokurs.Info_Projekt_12_2_2026;

import de.infokurs.Info_Projekt_12_2_2026.model.*;
import de.infokurs.Info_Projekt_12_2_2026.model.unicorns.Rarity;
import de.infokurs.Info_Projekt_12_2_2026.view.GuiManager;
import javafx.application.Application;

public class Main {

    public static void main(String[] args) {
        Thread.setDefaultUncaughtExceptionHandler((thread, ex) -> {
            ex.printStackTrace();
            try (java.io.PrintWriter pw = new java.io.PrintWriter(new java.io.FileWriter("crash.log", true))) {
                pw.println("Uncaught on thread: " + thread.getName());
                ex.printStackTrace(pw);
            } catch (Exception ignored) {}
        });
        Application.launch(GuiManager.class, args);
    }


    public static void setSaveManager(SaveManager saveManager) {
        saveManager = saveManager;
    }

}
