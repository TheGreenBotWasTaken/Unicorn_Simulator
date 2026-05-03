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

    private double hue = 0;
    private int speed = 16; //in ms
    private ArrayList<ImageView> buttonList;
    @FXML
    private Label rainbowLabel;


    @FXML
    private ImageView shopButton;
    @FXML
    private ImageView unicornButton;

    @FXML
    private GridPane gridPane;

    @FXML
    public void initialize() {
        buttonList = new ArrayList<ImageView>();

        buttonList.add(unicornButton);
        buttonList.add(shopButton);

        gridPane.widthProperty().addListener((obs, oldVal, newVal) -> updateImageSize());
        gridPane.heightProperty().addListener((obs, oldVal, newVal) -> updateImageSize());


        Timeline timeline = new Timeline(
                new KeyFrame(Duration.millis(speed), e -> {
                    hue = (hue + 1) % 360;
                    rainbowLabel.setTextFill(Color.hsb(hue, 1.0, 1.0));
                })
        );
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();



    }

    private void updateImageSize() {

        ColumnConstraints col = gridPane.getColumnConstraints().get(1);
        RowConstraints row = gridPane.getRowConstraints().get(1);

        double cellWidth = gridPane.getWidth() * (col.getPercentWidth() / 100);
        double cellHeight = gridPane.getHeight() * (row.getPercentHeight() / 100);
        for(ImageView imageView : buttonList) {
            imageView.setFitWidth(cellWidth);
            imageView.setFitHeight(cellHeight);
        }


    }


    @FXML
    void unicornButtonMousePressed(MouseEvent event) {
        Properties.setRainbows(Properties.getRainbows() + 1);
        rainbowLabel.setText("current reighnbowes: " + Properties.getRainbows());
        System.out.println(Properties.getRainbows());
        adjustScale(event, 10);
    }

    @FXML
    void unicornButtonMouseReleased(MouseEvent event) {
        adjustScale(event, -10);
    }

    @FXML
    void shopButtonPressed(MouseEvent event) {
        adjustScale(event, 10);
    }

    @FXML
    void shopButtonReleased(MouseEvent event) {
         adjustScale(event, -10);

    }

    void adjustScale(MouseEvent event, int amount) {
        ((ImageView) event.getSource()).setFitHeight(unicornButton.getFitHeight() + amount);
        ((ImageView) event.getSource()).setFitWidth(unicornButton.getFitWidth() + amount);
    }
}
