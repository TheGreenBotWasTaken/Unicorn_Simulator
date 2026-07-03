package de.infokurs.Info_Projekt_12_2_2026.view.guiControllers;

import de.infokurs.Info_Projekt_12_2_2026.Main;
import de.infokurs.Info_Projekt_12_2_2026.model.Forest;
import de.infokurs.Info_Projekt_12_2_2026.model.unicorns.Unicorn;
import de.infokurs.Info_Projekt_12_2_2026.view.GuiManager;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ForestController {

    @FXML
    private ImageView backButton;

    @FXML
    private Pane forestPane;

    private Forest forest;
    private final Map<Unicorn, ImageView> displayed = new HashMap<>();
    private Timeline syncTimeline;

    @FXML
    void backButtonPressed(MouseEvent event) {
        GuiManager.scale((ImageView) event.getSource(), GuiManager.PRESS_SCALE);
    }

    @FXML
    void backButtonReleased(MouseEvent event) {
        GuiManager.restore((ImageView) event.getSource());
        GuiManager.switchToScene(event, "main_page");
    }

    @FXML
    public void initialize() {
        forest = Forest.getInstance();

        syncTimeline = new Timeline(
                new KeyFrame(Duration.millis(200), e -> sync())
        );
        syncTimeline.setCycleCount(Timeline.INDEFINITE);

        Platform.runLater(() -> syncTimeline.play());
    }

    private void sync() {
        Set<Unicorn> current = new HashSet<>(forest.getUnicorns());

        displayed.keySet().removeIf(u -> {
            if (!current.contains(u)) {
                forestPane.getChildren().remove(displayed.get(u));
                return true;
            }
            return false;
        });

        for (Unicorn u : current) {
            if (!displayed.containsKey(u)) {
                addUnicornView(u);
            }
        }
    }

    private void addUnicornView(Unicorn unicorn) {
        ImageView view = new ImageView(new Image(unicorn.getTexturePath()));
        view.setFitWidth(80);
        view.setFitHeight(80);

        double maxX = Math.max(0, forestPane.getWidth() - view.getFitWidth());
        double maxY = Math.max(0, forestPane.getHeight() - view.getFitHeight());
        view.setLayoutX(Math.random() * maxX);
        view.setLayoutY(Math.random() * maxY);

        view.setOnMouseClicked(e -> {
            if (forest.catchUnicorn(unicorn)) {
                forestPane.getChildren().remove(view);
                displayed.remove(unicorn);
            }
        });

        forestPane.getChildren().add(view);
        displayed.put(unicorn, view);
    }


     // Call if you want the sync loop to pause while screen isn't visible

    public void stopSync() {
        if (syncTimeline != null) {
            syncTimeline.stop();
        }
    }
}