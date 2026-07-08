package de.infokurs.Info_Projekt_12_2_2026.view.guiControllers;

import de.infokurs.Info_Projekt_12_2_2026.model.Stable;
import de.infokurs.Info_Projekt_12_2_2026.model.unicorns.Unicorn;
import de.infokurs.Info_Projekt_12_2_2026.view.GuiManager;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;

import java.util.List;

public class StallController {

    private static final double SLOT_SIZE = 96.0;

    @FXML
    private ImageView backButton;

    @FXML
    private ScrollPane stallScrollPane;

    @FXML
    private FlowPane unicornFlowPane;

    @FXML
    private void initialize() {

        renderUnicorns();
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


    public void renderUnicorns() {
        unicornFlowPane.getChildren().clear();

        List<Unicorn> unicorns = Stable.getInstance().getUnicorns();
        for (Unicorn unicorn : unicorns) {
            unicornFlowPane.getChildren().add(createUnicornSlot(unicorn));
        }
    }


    private VBox createUnicornSlot(Unicorn unicorn) {
        ImageView imageView = new ImageView();
        imageView.setFitWidth(SLOT_SIZE);
        imageView.setFitHeight(SLOT_SIZE);
        imageView.setPreserveRatio(true);
        imageView.setPickOnBounds(true);

        String texturePath = unicorn.getTexturePath();
        if (!texturePath.startsWith("/")) {
            texturePath = "/" + texturePath;
        }

        var textureStream = getClass().getResourceAsStream(texturePath);
        if (textureStream == null) {
            System.out.println("Textur nicht gefunden für " + unicorn.getDisplayName()
                    + " unter Pfad: " + texturePath);
            return new VBox(4, new Label(unicorn.getDisplayName()));
        }

        Image image = new Image(textureStream);
        imageView.setImage(image);

        Label nameLabel = new Label(unicorn.getDisplayName());
        nameLabel.setWrapText(true);
        nameLabel.setMaxWidth(SLOT_SIZE);
        nameLabel.setAlignment(Pos.CENTER);
        nameLabel.setStyle("-fx-text-alignment: center;");

        VBox slot = new VBox(4, imageView, nameLabel);
        slot.setAlignment(Pos.CENTER);
        slot.setPrefWidth(SLOT_SIZE);

        imageView.setOnMouseClicked(event -> onUnicornSlotClicked(unicorn, event));

        return slot;
    }

    private void onUnicornSlotClicked(Unicorn unicorn, MouseEvent event) {
        System.out.println(unicorn.getDisplayName() + " wurde angeklickt.");
        GuiManager.switchToScene(event, "unicorn_profile_editor");
    }
}