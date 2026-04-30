package de.infokurs.Info_Projekt_12_2_2026.guiControllers;

import de.infokurs.Info_Projekt_12_2_2026.Properties;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class MainPageController {

    @FXML
    private Button unicornButton;

    @FXML
    private Label rainbowLabel;

    @FXML
    void unicornButtonMouseClicked(MouseEvent event) {
        Properties.setRainbows(Properties.getRainbows() + 1);
        rainbowLabel.setText("current reighnbowes: " + Properties.getRainbows());
        System.out.println(Properties.getRainbows());
    }

}
