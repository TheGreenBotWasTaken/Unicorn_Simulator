package de.infokurs.Info_Projekt_12_2_2026.view.guiControllers;

import de.infokurs.Info_Projekt_12_2_2026.model.SaveManager;
import de.infokurs.Info_Projekt_12_2_2026.view.GuiManager;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class TitleScreenController {

    @FXML
    private ImageView quitButton;

    @FXML
    private ImageView settingsButton;

    @FXML
    private ImageView startButton;

    @FXML
    void quitButtonPressed(MouseEvent event) {
        GuiManager.scale((ImageView) event.getSource(), GuiManager.PRESS_SCALE);
    }

    @FXML
    void quitButtonReleased(MouseEvent event) {
        GuiManager.restore((ImageView) event.getSource());
        SaveManager.save(SaveManager.getSaveData());
        System.exit(0);
    }

    @FXML
    void settingButtonReleased(MouseEvent event) {
        GuiManager.restore((ImageView) event.getSource());
        GuiManager.switchToScene(event, "settings");
    }

    @FXML
    void settingsButtonPressed(MouseEvent event) {
        GuiManager.scale((ImageView) event.getSource(), GuiManager.PRESS_SCALE);
    }

    @FXML
    void startButtonPressed(MouseEvent event) {
        GuiManager.scale((ImageView) event.getSource(), GuiManager.PRESS_SCALE);
    }

    @FXML
    void startButtonReleased(MouseEvent event) {
        GuiManager.restore((ImageView) event.getSource());
        GuiManager.switchToScene(event, "main_page");
    }

}
