package de.infokurs.Info_Projekt_12_2_2026.view;

import de.infokurs.Info_Projekt_12_2_2026.model.SaveData;
import de.infokurs.Info_Projekt_12_2_2026.model.SaveManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class GuiManager extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/de/infokurs/Info_Projekt_12_2_2026/screens/main_page.fxml"));

        Scene scene = new Scene(root, 700, 400);
        stage.setScene(scene);

        stage.getIcons().add(new Image(getClass().getResource("/assets/icon.png").toExternalForm()));
        stage.setTitle(SaveManager.getCurrent().NAME);
        stage.setResizable(false);
        stage.show();
    }
    @Override
    public void stop() {
        SaveManager.save(SaveManager.getCurrent());
        System.out.println("close");
    }

}
