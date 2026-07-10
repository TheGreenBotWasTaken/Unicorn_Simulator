package de.infokurs.Info_Projekt_12_2_2026.view.guiControllers;

import de.infokurs.Info_Projekt_12_2_2026.model.unicorns.Unicorn;
import de.infokurs.Info_Projekt_12_2_2026.util.TextureCache;
import de.infokurs.Info_Projekt_12_2_2026.view.GuiManager;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class UnicornProfileController {

    @FXML
    private ImageView backButton;

    @FXML
    private TextField nameField;

    @FXML
    private ImageView unicornView;

    private Unicorn unicorn;

    public void setUnicorn(Unicorn unicorn) {
        this.unicorn = unicorn;
        if (nameField != null) {
            nameField.setText(unicorn.getDisplayName());
        }
        TextureCache.applyScaled(unicornView, unicorn.getTexturePath(), 5);
    }

    @FXML
    public void initialize() {
        nameField.textProperty().addListener((obs, oldText, newText) -> {
            if (unicorn != null) {
                unicorn.setDisplayName(newText);
            }
        });
    }

    @FXML
    void backButtonPressed(MouseEvent event) {
        GuiManager.scale((ImageView) event.getSource(), GuiManager.PRESS_SCALE);
    }

    @FXML
    void backButtonReleased(MouseEvent event) {
        GuiManager.restore((ImageView) event.getSource());
        GuiManager.switchToScene(event, "stall");
    }

}
