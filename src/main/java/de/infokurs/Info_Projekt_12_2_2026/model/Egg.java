package de.infokurs.Info_Projekt_12_2_2026.model;

import de.infokurs.Info_Projekt_12_2_2026.model.unicorns.Rarity;
import de.infokurs.Info_Projekt_12_2_2026.model.unicorns.Unicorn;
import de.infokurs.Info_Projekt_12_2_2026.model.unicorns.UnicornFactory;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class Egg {
    private static final float BASE_TIME = 10;

    private final String parentUnicornId; // welche Unicorn-Art schlüpft daraus
    private final Rarity rarity;
    private final int level;              // wird festgelegt durch die Elterneinhörner
    private int poptime;                  // Zeit benötigt zum Schlüpfen
    private final int firstCrack;
    private final int secondCrack;
    private boolean hatched;
    private int textureStage;             // 1, 2 oder 3 - aktuelles Rissstadium

    private transient Timeline timeline;

    public Egg(Rarity rarity, int level, String parentUnicornId) {
        this.rarity = rarity;
        this.level = level;
        this.parentUnicornId = parentUnicornId;
        this.poptime = (int) Math.ceil(rarity.getHatchFactor() * level * BASE_TIME / 2);
        this.hatched = false;
        this.firstCrack = poptime / 2;
        this.secondCrack = poptime / 4;
        this.textureStage = 1;
    }

    public boolean isHatched() {
        return hatched;
    }

    public int getPoptime() {
        return poptime;
    }

    public int getTextureStage() {
        return textureStage;
    }


    public String getEggTexturePath() {
        return ("/assets/textures/eggs/egg_" + rarity.name() + "_" + textureStage + ".png").toLowerCase();
    }

    public void startHatching() {
        if (timeline != null || hatched) {
            return;
        }

        timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            if (poptime > 0) {
                poptime--;

                if (poptime == firstCrack) {
                    textureStage = 2;
                } else if (poptime == secondCrack) {
                    textureStage = 3;
                }
            } else {
                timeline.stop();
                hatched = true;
            }
        }));

        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    public void cancelHatching() {
        if (timeline != null) {
            timeline.stop();
            timeline = null;
        }
    }


    public Unicorn hatch() {
        if (!hatched) {
            throw new IllegalStateException("Das Ei ist noch nicht bereit zum Schlüpfen.");
        }
        return UnicornFactory.createById(parentUnicornId);
    }

}