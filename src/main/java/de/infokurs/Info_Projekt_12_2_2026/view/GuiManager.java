package de.infokurs.Info_Projekt_12_2_2026.view;

import de.infokurs.Info_Projekt_12_2_2026.model.*;
import de.infokurs.Info_Projekt_12_2_2026.model.unicorns.Rarity;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class GuiManager extends Application {

    private static final GuiManager INSTANCE = new GuiManager();
    public static BackgroundMusicPlayer backgroundMusicPlayer;
    private static Stage stage;
    private static Scene scene;
    public static final double PRESS_SCALE = 0.95;

    public static GuiManager getInstance() {

        return INSTANCE;
    }


    @Override
    public void start(Stage stage) throws IOException {
        Forest.getInstance().getClass(); //um forestloop zu starten
        Parent root = FXMLLoader.load(getClass().getResource("/de/infokurs/Info_Projekt_12_2_2026/screens/title_screen.fxml"));

        Scene scene = new Scene(root, 700, 400);
        stage.setScene(scene);

        stage.getIcons().add(new Image(getClass().getResource("/assets/textures/gui/icon.png").toExternalForm()));
        stage.setTitle(SaveManager.getSaveData().NAME);
        stage.setResizable(false);
        stage.show();

         backgroundMusicPlayer =
                new BackgroundMusicPlayer("/assets/music");

        backgroundMusicPlayer.setVolume(SaveManager.getSaveData().getVolume());
        backgroundMusicPlayer.play();
        DebugConsole.start();
    }
    @Override
    public void stop() {
        SaveManager.save(SaveManager.getSaveData());
        System.exit(0);
    }
    public static BackgroundMusicPlayer getBackgroundMusicPlayer() {
        return backgroundMusicPlayer;
    }
    public static void switchToScene(MouseEvent event, String sceneName)  {
        Parent root = null;
        try {
            root = FXMLLoader.load(GuiManager.class.getResource("/de/infokurs/Info_Projekt_12_2_2026/screens/" + sceneName + ".fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public static void  scale(ImageView iv, double factor) {
        iv.setUserData(new double[]{iv.getFitWidth(), iv.getFitHeight()});
        iv.setFitWidth(iv.getFitWidth() * factor);
        iv.setFitHeight(iv.getFitHeight() * factor);
    }

    public static void restore(ImageView iv) {
        double[] natural = (double[]) iv.getUserData();
        if (natural != null) {
            iv.setFitWidth(natural[0]);
            iv.setFitHeight(natural[1]);
        }
    }

}
