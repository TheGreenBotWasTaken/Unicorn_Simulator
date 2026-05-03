package de.infokurs.Info_Projekt_12_2_2026;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class HelloApplication extends Application {
    private double hue = 0;
    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/de/infokurs/Info_Projekt_12_2_2026/screens/main_page.fxml"));

        Scene scene = new Scene(root, 300, 275);
        stage.setScene(scene);

        stage.getIcons().add(new Image(getClass().getResource("/assets/icon.png").toExternalForm()));
        stage.setTitle(Properties.NAME);
        stage.show();
    }

}
