package de.infokurs.Info_Projekt_12_2_2026.view.guiControllers;

import de.infokurs.Info_Projekt_12_2_2026.model.Egg;
import de.infokurs.Info_Projekt_12_2_2026.model.Nest;
import de.infokurs.Info_Projekt_12_2_2026.model.Stable;
import de.infokurs.Info_Projekt_12_2_2026.model.unicorns.Unicorn;
import de.infokurs.Info_Projekt_12_2_2026.view.GuiManager;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.io.InputStream;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class BreedingController {

    private static final double SLOT_SIZE = 96.0;
    private static final String EMPTY_NEST_TEXTURE = "/assets/textures/pillar.png";

    // Cache: einmal geladene Texturen werden wiederverwendet statt neu von der Platte gelesen
    private static final Map<String, Image> TEXTURE_CACHE = new ConcurrentHashMap<>();

    @FXML
    private ImageView backButton;

    @FXML
    private ScrollPane nestScrollPane;

    @FXML
    private FlowPane nestFlowPane;

    private Timeline refreshTimeline;

    @FXML
    private void initialize() {
        renderNest();

        refreshTimeline = new Timeline(new KeyFrame(Duration.millis(500), event -> renderNest()));
        refreshTimeline.setCycleCount(Timeline.INDEFINITE);
        refreshTimeline.play();
    }

    @FXML
    void backButtonPressed(MouseEvent event) {
        GuiManager.scale((ImageView) event.getSource(), GuiManager.PRESS_SCALE);
    }

    @FXML
    void backButtonReleased(MouseEvent event) {
        GuiManager.restore((ImageView) event.getSource());
        GuiManager.switchToScene(event, "main_page");
    }

    private void renderNest() {
        Nest nest = Nest.getInstance();
        int capacity = nest.getCapacity();

        for (int i = 0; i < capacity; i++) {
            Egg egg = nest.getSlot(i);
            if (egg != null && egg.isHatched()) {
                Unicorn hatched = egg.hatch();
                Stable.getInstance().addUnicorn(hatched);
                nest.clearSlot(i);
            }
        }

        nestFlowPane.getChildren().clear();
        for (int i = 0; i < capacity; i++) {
            nestFlowPane.getChildren().add(createSlot(nest.getSlot(i)));
        }
    }

    /**
     * Läd eine Textur aus dem Cache, oder liest sie einmalig von der Platte ein,
     * falls sie noch nicht gecacht wurde.
     */
    private Image loadCachedImage(String path) {
        return TEXTURE_CACHE.computeIfAbsent(path, p -> {
            try (InputStream stream = getClass().getResourceAsStream(p)) {
                if (stream == null) {
                    System.out.println("Textur nicht gefunden unter Pfad: " + p);
                    return null;
                }
                return new Image(stream);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        });
    }

    private VBox createSlot(Egg egg) {
        StackPane imageContainer = new StackPane();
        imageContainer.setPrefSize(SLOT_SIZE, SLOT_SIZE);
        imageContainer.setMinSize(SLOT_SIZE, SLOT_SIZE);
        imageContainer.setMaxSize(SLOT_SIZE, SLOT_SIZE);

        // Podest: füllt den kompletten Slot
        ImageView pillarView = new ImageView();
        pillarView.setFitWidth(SLOT_SIZE);
        pillarView.setFitHeight(SLOT_SIZE);
        pillarView.setPreserveRatio(true);

        Image pillarImage = loadCachedImage(EMPTY_NEST_TEXTURE);
        if (pillarImage != null) {
            pillarView.setImage(pillarImage);
        }

        StackPane.setAlignment(pillarView, Pos.CENTER);
        imageContainer.getChildren().add(pillarView);

        if (egg != null) {
            double eggSize = SLOT_SIZE * 0.45;

            ImageView eggView = new ImageView();
            eggView.setFitWidth(eggSize);
            eggView.setFitHeight(eggSize);
            eggView.setPreserveRatio(true);

            Image eggImage = loadCachedImage(egg.getEggTexturePath());
            if (eggImage != null) {
                eggView.setImage(eggImage);
            }

            StackPane.setAlignment(eggView, Pos.BOTTOM_CENTER);
            eggView.setTranslateY(-SLOT_SIZE * 0.70); // nach oben verschieben, damit es auf dem Podest sitzt

            imageContainer.getChildren().add(eggView);
        }

        Label statusLabel = new Label(egg == null ? "Leer" : egg.getPoptime() + "s");
        statusLabel.setAlignment(Pos.CENTER);

        VBox slot = new VBox(4, imageContainer, statusLabel);
        slot.setAlignment(Pos.CENTER);
        slot.setPrefWidth(SLOT_SIZE);

        return slot;
    }
}