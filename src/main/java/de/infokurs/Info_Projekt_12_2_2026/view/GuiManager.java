package de.infokurs.Info_Projekt_12_2_2026.view;

import de.infokurs.Info_Projekt_12_2_2026.model.BackgroundMusicPlayer;
import de.infokurs.Info_Projekt_12_2_2026.model.SaveData;
import de.infokurs.Info_Projekt_12_2_2026.model.SaveManager;
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
    public static BackgroundMusicPlayer backgroundMusicPlayer;
    private static Stage stage;
    private static Scene scene;
    public static final double PRESS_SCALE = 0.95;
    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/de/infokurs/Info_Projekt_12_2_2026/screens/title_screen.fxml"));

        Scene scene = new Scene(root, 700, 400);
        stage.setScene(scene);

        stage.getIcons().add(new Image(getClass().getResource("/assets/icon.png").toExternalForm()));
        stage.setTitle(SaveManager.getCurrent().NAME);
        stage.setResizable(false);
        stage.show();

         backgroundMusicPlayer =
                new BackgroundMusicPlayer("/assets/music");

        backgroundMusicPlayer.setVolume(SaveManager.getSaveData().getVolume());
        backgroundMusicPlayer.play();
    }
    @Override
    public void stop() {
        SaveManager.save(SaveManager.getCurrent());
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
