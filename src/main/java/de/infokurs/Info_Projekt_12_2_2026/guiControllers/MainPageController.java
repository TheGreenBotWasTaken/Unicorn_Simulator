package de.infokurs.Info_Projekt_12_2_2026.guiControllers;

import de.infokurs.Info_Projekt_12_2_2026.Properties;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.util.ArrayList;

public class MainPageController {
//    scale logic too complicated wont implement
    private static final double PRESS_SCALE = 0.90;

    private double hue = 0;
    private int speed = 16;
    private ArrayList<ImageView> buttonList;

    //private double initialWindowWidth;
    //private double initialWindowHeight;
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
    void breedingButtonPressed(MouseEvent event) {
        scale((ImageView) event.getSource(), PRESS_SCALE);
    }

    @FXML
    void breedingButtonReleased(MouseEvent event) {
        restore((ImageView) event.getSource());
    }


    @FXML
    void stallButtonPressed(MouseEvent event) {
        scale((ImageView) event.getSource(), PRESS_SCALE);
    }

    @FXML
    void stallButtonReleased(MouseEvent event) {
        restore((ImageView) event.getSource());
    }

    @FXML
    void templeButtonPressed(MouseEvent event) {
        scale((ImageView) event.getSource(), PRESS_SCALE);
    }

    @FXML
    void templeButtonReleased(MouseEvent event) {
        restore((ImageView) event.getSource());
    }
    @FXML
    void unicornButtonMousePressed(MouseEvent event) {
        Properties.setRainbows(Properties.getRainbows() + 1);
        rainbowLabel.setText("current reighnbowes: " + Properties.getRainbows());
        System.out.println(Properties.getRainbows());
        scale((ImageView) event.getSource(), PRESS_SCALE);
    }

    @FXML
    void unicornButtonMouseReleased(MouseEvent event) {
        restore((ImageView) event.getSource());
    }

    @FXML
    void shopButtonPressed(MouseEvent event) {
        scale((ImageView) event.getSource(), PRESS_SCALE);
    }

    @FXML
    void shopButtonReleased(MouseEvent event) {
        restore((ImageView) event.getSource());
    }


@FXML
public void initialize() {
    buttonList = new ArrayList<>();
    buttonList.add(unicornButton);
    buttonList.add(shopButton);
    buttonList.add(breedingButton);
    buttonList.add(stallButton);
    buttonList.add(templeButton);

    Timeline timeline = new Timeline(
            new KeyFrame(Duration.millis(speed), e -> {
                hue = (hue + 1) % 360;
                rainbowLabel.setTextFill(Color.hsb(hue, 1.0, 1.0));
            })
    );
    timeline.setCycleCount(Timeline.INDEFINITE);
    timeline.play();
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



    private void scale(ImageView iv, double factor) {
        iv.setUserData(new double[]{iv.getFitWidth(), iv.getFitHeight()});
        iv.setFitWidth(iv.getFitWidth() * factor);
        iv.setFitHeight(iv.getFitHeight() * factor);
    }

    private void restore(ImageView iv) {
        double[] natural = (double[]) iv.getUserData();
        if (natural != null) {
            iv.setFitWidth(natural[0]);
            iv.setFitHeight(natural[1]);
        }
    }
}