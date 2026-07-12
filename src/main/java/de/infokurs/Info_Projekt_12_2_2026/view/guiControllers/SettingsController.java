package de.infokurs.Info_Projekt_12_2_2026.view.guiControllers;

import de.infokurs.Info_Projekt_12_2_2026.model.SaveData;
import de.infokurs.Info_Projekt_12_2_2026.model.SaveManager;
import de.infokurs.Info_Projekt_12_2_2026.util.TextureCache;
import de.infokurs.Info_Projekt_12_2_2026.view.GuiManager;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class SettingsController implements Initializable {

    @FXML
    private ImageView backButton;

    @FXML
    private Slider volumeSlider;
    double volume;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        volumeSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                volume = volumeSlider.getValue() / 100;
                GuiManager.getBackgroundMusicPlayer().setVolume(volume);
                SaveManager.getSaveData().setVolume(volume);
            }
        });
        volumeSlider.setValue(SaveManager.getSaveData().getVolume() * 100);
        TextureCache.applyScaled(backButton, "/assets/textures/gui/back.png", 5);
    }

    @FXML
    void backButtonPressed(MouseEvent event) {
        GuiManager.scale((ImageView) event.getSource(), GuiManager.PRESS_SCALE);
    }

    @FXML
    void backButtonReleased(MouseEvent event) {
        GuiManager.restore((ImageView) event.getSource());
        GuiManager.switchToScene(event, "title_screen");
    }
}
