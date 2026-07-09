package de.infokurs.Info_Projekt_12_2_2026.view.guiControllers;

import de.infokurs.Info_Projekt_12_2_2026.model.SaveManager;
import de.infokurs.Info_Projekt_12_2_2026.util.TextureCache;
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
    private ImageView logo;

    @FXML
    private ImageView sponsorButton;

    @FXML
    public void initialize() {
        TextureCache.applyScaled(startButton, "/assets/textures/gui/start_button.png", 4);
        TextureCache.applyScaled(settingsButton, "/assets/textures/gui/settings_button.png", 4);
        TextureCache.applyScaled(quitButton, "/assets/textures/gui/save_and_quit_button.png", 4);
        TextureCache.applyScaled(logo, "/assets/textures/gui/logo.png", 6);
        TextureCache.applyScaled(sponsorButton, "/assets/textures/gui/sponsor_button.png", 5);

    }

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
    void sponsorButtonPressed(MouseEvent event) {
        GuiManager.scale((ImageView) event.getSource(), GuiManager.PRESS_SCALE);
    }

    @FXML
    void sponsorButtonReleased(MouseEvent event) {
        GuiManager.restore((ImageView) event.getSource());
        GuiManager.getInstance().getHostServices().showDocument( "https://www.patreon.com/LovPardock" );
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
