package de.infokurs.Info_Projekt_12_2_2026.view.guiControllers;

import de.infokurs.Info_Projekt_12_2_2026.model.*;
import de.infokurs.Info_Projekt_12_2_2026.util.TextureCache;
import de.infokurs.Info_Projekt_12_2_2026.view.GuiManager;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class ShopController {

    @FXML
    private ImageView backButton;

    @FXML
    private HBox offersBox;

    @FXML
    private Text moneyText;

    @FXML
    public void initialize() {
        TextureCache.applyScaled(backButton, "/assets/textures/gui/back.png", 5);
        refreshMoneyDisplay();
        renderOffers();
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

    private void refreshMoneyDisplay() {
        if (moneyText != null) {
            moneyText.setText("MONETEN: " + Wallet.getInstance().getBalance());
        }
    }

    private void renderOffers() {
        if (offersBox == null) return;
        offersBox.getChildren().clear();
        for (Offer offer : Shop.getInstance().getCurrentOffers()) {
            offersBox.getChildren().add(buildOfferCard(offer));
        }
    }

    private VBox buildOfferCard(Offer offer) {
        BuyableItem item = offer.getItem();
        String itemName = (item instanceof ShopEgg egg) ? egg.getDisplayName() : "Unicorn";

        VBox card = new VBox(6);
        card.setAlignment(Pos.CENTER);
        card.setPadding(new Insets(10));
        card.setPrefWidth(140);

        card.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(10), new BorderWidths(2))));
        Label rarityLabel = new Label(item.getRarity().toString());
        rarityLabel.setTextFill(getComplementaryColor(item.getRarity().getColor()));
        rarityLabel.setFont(Font.font("Arial", 13));

        Label nameLabel = new Label(offer.getCount() + "x " + itemName);
        nameLabel.setTextFill(Color.BLACK);
        nameLabel.setFont(Font.font("Arial", FontWeight.BOLD, 13));
        nameLabel.setWrapText(true);
        nameLabel.setAlignment(Pos.CENTER);


        Label priceLabel = new Label();
        if (offer.hasDiscount()) {
            priceLabel.setText(offer.getBasePrice() + " → " + offer.getDiscountedPrice());
            priceLabel.setTextFill(Color.web("#7CFC00"));
        } else {
            priceLabel.setText(offer.getDiscountedPrice() + " Moneten");
            priceLabel.setTextFill(Color.WHITE);
        }
        priceLabel.setFont(Font.font("Arial", 12));
        if (item.getRarity().isRainbow()) {
            priceLabel.setTextFill(Color.RED);
            nameLabel.setTextFill(Color.BLACK);
            // Create the rainbow stops once
            List<Stop> baseStops = new ArrayList<>();
            for (int i = 0; i <= 100; i++) {
                double t = i / 100.0;
                baseStops.add(new Stop(t, Color.hsb(t * 360, 1.0, 1.0)));
            }

            AnimationTimer timer = new AnimationTimer() {

                private long lastTime = 0;
                private double offset = 0;

                @Override
                public void handle(long now) {
                    if (lastTime == 0) {
                        lastTime = now;
                        return;
                    }

                    double dt = (now - lastTime) / 1_000_000_000.0;
                    lastTime = now;

                    // Scroll speed (gradient lengths per second)
                    offset += dt * 0.25;
                    offset %= 1.0;

                    List<Stop> animatedStops = new ArrayList<>();

                    for (Stop stop : baseStops) {
                        double pos = stop.getOffset() + offset;
                        if (pos > 1) pos -= 1;
                        animatedStops.add(new Stop(pos, stop.getColor()));
                    }

                    // Stops must be sorted by offset
                    animatedStops.sort(Comparator.comparingDouble(Stop::getOffset));

                    LinearGradient gradient = new LinearGradient(
                            0, 0, 1, 1,
                            true,
                            CycleMethod.REPEAT,
                            animatedStops
                    );

                    card.setBackground(new Background(
                            new BackgroundFill(gradient, new CornerRadii(10), Insets.EMPTY)
                    ));
                }
            };

            timer.start();


        } else {
            card.setBackground(new Background(
                    new BackgroundFill(item.getRarity().getColor(), new CornerRadii(10), Insets.EMPTY)
            ));
        }
        Label buyButton = new Label("KAUFEN");
        buyButton.setTextFill(Color.BLACK);
        buyButton.setFont(Font.font("Arial", FontWeight.BOLD, 12));
        buyButton.setPadding(new Insets(6, 14, 6, 14));
        buyButton.setBackground(new Background(new BackgroundFill(Color.web("#FFD700"), new CornerRadii(6), Insets.EMPTY)));
        buyButton.setOnMouseClicked(e -> handlePurchaseClick(offer, itemName));

        card.getChildren().addAll(rarityLabel, nameLabel, priceLabel, buyButton);
        return card;
    }

    private void handlePurchaseClick(Offer offer, String itemName) {
        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
        confirm.setTitle("Kauf bestätigen");
        confirm.setHeaderText("Möchtest du dieses Angebot wirklich kaufen?");
        confirm.setContentText(offer.getCount() + "x " + itemName + " für " + offer.getDiscountedPrice() + " Moneten.");

        Optional<ButtonType> result = confirm.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            boolean success = Shop.getInstance().purchase(offer);
            if (!success) {
                Alert error = new Alert(Alert.AlertType.ERROR);
                error.setTitle("Kauf fehlgeschlagen");
                error.setHeaderText(null);
                error.setContentText("Nicht genug Moneten oder das Angebot ist nicht mehr verfügbar.");
                error.showAndWait();
            }
            refreshMoneyDisplay();
            renderOffers();
        }
    }

    private Color getComplementaryColor(Color color) {
        return Color.color(
                1 - color.getRed(),
                1 - color.getGreen(),
                1 - color.getBlue()
        );
    }
}