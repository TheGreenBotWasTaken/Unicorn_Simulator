package de.infokurs.Info_Projekt_12_2_2026.view.guiControllers;

import de.infokurs.Info_Projekt_12_2_2026.model.Forest;
import de.infokurs.Info_Projekt_12_2_2026.model.Temple;
import de.infokurs.Info_Projekt_12_2_2026.util.TextureCache;
import de.infokurs.Info_Projekt_12_2_2026.view.GuiManager;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class TempleController {

    @FXML
    private ImageView backButton;

    @FXML
    private Label luckLevelText;

    @FXML
    private ImageView luckTemple;

    @FXML
    private ImageView luckUpgrade;

    @FXML
    private Label luckUpgradeText;

    @FXML
    private Label spawnLevelText;

    @FXML
    private ImageView spawnTemple;

    @FXML
    private ImageView spawnUpgrade;

    @FXML
    private Label spawnUpgradeText;

    @FXML
    private Label timeLevelText;

    @FXML
    private ImageView timeTemple;

    @FXML
    private ImageView timeUpgrade;

    @FXML
    private Label timeUpgradeText;

    @FXML
    public void initialize() {
        String path = "/assets/textures/gui/sponsor_button.png";
        TextureCache.applyScaled(backButton, "/assets/textures/gui/back.png", 5);
        TextureCache.applyScaled(luckUpgrade, path, 3);
        TextureCache.applyScaled(spawnUpgrade, path, 3);
        TextureCache.applyScaled(timeUpgrade, path, 3);
        TextureCache.applyScaled(luckTemple, "/assets/textures/gui/temple_luck.png", 3);
        TextureCache.applyScaled(spawnTemple, "/assets/textures/gui/temple_spawn_rate.png", 3);
        TextureCache.applyScaled(timeTemple, "/assets/textures/gui/temple_time.png", 3);
        update();

    }

    public void update() {
        luckLevelText.setText("Luck level: " + (int) Temple.getInstance().getLuck());
        luckUpgradeText.setText("Upgrade cost: " + (int) Math.pow(Temple.getInstance().getLuck(), 2));
        spawnLevelText.setText("Spawn level: " + (int) Temple.getInstance().getSpawnRate());
        spawnUpgradeText.setText("Upgrade cost: " + (int) Math.pow(Temple.getInstance().getSpawnRate(), 2));
        timeLevelText.setText("Despawn time: " + (int) Temple.getInstance().getDespawnTime());
        timeUpgradeText.setText("Upgrade cost: " + (int) Math.pow(Temple.getInstance().getDespawnTime()/10, 2));




    }

    @FXML
    void backButtonReleased(MouseEvent event) {
        GuiManager.restore((ImageView) event.getSource());
        GuiManager.switchToScene(event, "main_page");
    }

    @FXML
    void buttonPressed(MouseEvent event) {
        GuiManager.scale((ImageView) event.getSource(), GuiManager.PRESS_SCALE);
    }

    @FXML
    void luckUpgradeReleased(MouseEvent event) {
        GuiManager.restore((ImageView) event.getSource());
        Forest.getInstance().upgradeLuck();
        update();
    }

    @FXML
    void spawnUpgradeReleased(MouseEvent event) {
        GuiManager.restore((ImageView) event.getSource());
        Forest.getInstance().upgradeCD();
        update();
    }

    @FXML
    void timeUpgradeReleased(MouseEvent event) {
        GuiManager.restore((ImageView) event.getSource());
        Forest.getInstance().upgradeTime();
        update();
    }
}
