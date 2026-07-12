package de.infokurs.Info_Projekt_12_2_2026.view.guiControllers;

import de.infokurs.Info_Projekt_12_2_2026.model.RainbowManager;
import de.infokurs.Info_Projekt_12_2_2026.model.SaveData;
import de.infokurs.Info_Projekt_12_2_2026.model.UnicornNumberFormatter;
import de.infokurs.Info_Projekt_12_2_2026.util.TextureCache;
import de.infokurs.Info_Projekt_12_2_2026.view.GuiManager;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;

public class MainPageController {
//    scale logic too complicated wont implement


    private double hue = 0;
    private int speed = 16;
    private ArrayList<ImageView> buttonList;

    //private double initialWindowWidth;
    //private double initialWindowHeight;
    @FXML
    private ImageView forestButton;
    @FXML
    private Label rainbowLabel;
    @FXML
    private ImageView shopButton;
    @FXML
    private ImageView unicornButton;
    @FXML
    private ImageView breedingButton;
    @FXML
    private ImageView stallButton;
    @FXML
    private ImageView templeButton;
    @FXML
    private GridPane gridPane;

    @FXML
    private ImageView backButton;

    @FXML
    private ImageView rainbowBackdrop;


    @FXML
    public void initialize() {
        buttonList = new ArrayList<>();
        buttonList.add(unicornButton);
        buttonList.add(shopButton);
        buttonList.add(breedingButton);
        buttonList.add(stallButton);
        buttonList.add(templeButton);
        buttonList.add(forestButton);

        Timeline timeline = new Timeline(
                new KeyFrame(Duration.millis(speed), e -> {
                    hue = (hue + 1) % 360;
                    rainbowLabel.setTextFill(Color.hsb(hue, 1.0, 1.0));
                    rainbowLabel.setText(
                            "Current rainbows: " +
                                    UnicornNumberFormatter.format(RainbowManager.getInstance().getRainbows()));
                })
        );
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
        TextureCache.applyScaled(backButton, "/assets/textures/gui/back.png", 5);
        TextureCache.applyScaled(rainbowBackdrop, "/assets/textures/gui/wood.png", 4);
    }

    @FXML
    void breedingButtonPressed(MouseEvent event) {
        GuiManager.scale((ImageView) event.getSource(), GuiManager.PRESS_SCALE);
    }

    @FXML
    void backButtonReleased(MouseEvent event) {
        GuiManager.restore((ImageView) event.getSource());
        GuiManager.switchToScene(event, "title_screen");
    }

    @FXML
    void buttonPressed(MouseEvent event) {
        GuiManager.scale((ImageView) event.getSource(), GuiManager.PRESS_SCALE);

    }

    @FXML
    void breedingButtonReleased(MouseEvent event) {
        GuiManager.restore((ImageView) event.getSource());
        GuiManager.switchToScene(event, "breeding");
    }


    @FXML
    void stallButtonPressed(MouseEvent event) {
        GuiManager.scale((ImageView) event.getSource(), GuiManager.PRESS_SCALE);

    }

    // maybe alle in eine methode?
    @FXML
    void stallButtonReleased(MouseEvent event) {
        GuiManager.restore((ImageView) event.getSource());
        GuiManager.switchToScene(event, "stall");
    }

    @FXML
    void templeButtonPressed(MouseEvent event) {
        GuiManager.scale((ImageView) event.getSource(), GuiManager.PRESS_SCALE);
    }

    @FXML
    void templeButtonReleased(MouseEvent event) {
        GuiManager.restore((ImageView) event.getSource());
        GuiManager.switchToScene(event, "temple");
    }

    @FXML
    void unicornButtonMousePressed(MouseEvent event) {
        RainbowManager.getInstance().addRainbows(1);
        updateLabel();
        System.out.println(RainbowManager.getInstance().getRainbows());
        GuiManager.scale((ImageView) event.getSource(), GuiManager.PRESS_SCALE);
    }

    public void updateLabel() {
        rainbowLabel.setText("current reighnbowes: " + UnicornNumberFormatter.format(RainbowManager.getInstance().getRainbows()));
    }

    @FXML
    void unicornButtonMouseReleased(MouseEvent event) {
        GuiManager.restore((ImageView) event.getSource());
    }

    @FXML
    void shopButtonPressed(MouseEvent event) {
        GuiManager.scale((ImageView) event.getSource(), GuiManager.PRESS_SCALE);
    }

    @FXML
    void shopButtonReleased(MouseEvent event) {
        GuiManager.restore((ImageView) event.getSource());
        GuiManager.switchToScene(event, "shop");
    }

    @FXML
    void forestButtonPressed(MouseEvent event) {
        GuiManager.scale((ImageView) event.getSource(), GuiManager.PRESS_SCALE);
    }

    @FXML
    void forestButtonReleased(MouseEvent event) {
        GuiManager.restore((ImageView) event.getSource());
        GuiManager.switchToScene(event, "forest");
    }


//    for (ImageView iv : buttonList) {
//        iv.setUserData(new double[]{iv.getFitWidth(), iv.getFitHeight()});
//    }
//
//    gridPane.widthProperty().addListener((obs, o, newVal) -> {
//        if (initialWindowWidth == 0) {
//            initialWindowWidth = newVal.doubleValue();
//            return; // skip first fire, this IS the initial size
//        }
//        updateImageSize(newVal.doubleValue(), gridPane.getHeight());
//    });
//
//    gridPane.heightProperty().addListener((obs, o, newVal) -> {
//        if (initialWindowHeight == 0) {
//            initialWindowHeight = newVal.doubleValue();
//            return;
//        }
//        updateImageSize(gridPane.getWidth(), newVal.doubleValue());
//    });
//
//    private void updateImageSize(double currentWidth, double currentHeight) {
//        double scaleX = currentWidth  / initialWindowWidth;
//        double scaleY = currentHeight / initialWindowHeight;
//
//        for (ImageView iv : buttonList) {
//            double[] natural = (double[]) iv.getUserData();
//            if (natural == null) return;
//            iv.setFitWidth (natural[0] * scaleX);
//            iv.setFitHeight(natural[1] * scaleY);
//        }
//    }
//

}