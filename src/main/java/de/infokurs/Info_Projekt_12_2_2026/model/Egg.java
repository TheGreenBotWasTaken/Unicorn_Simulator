package de.infokurs.Info_Projekt_12_2_2026.model;

import de.infokurs.Info_Projekt_12_2_2026.model.unicorns.Rarity;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;
import java.io.InputStream;

public class Egg implements BuyableItem {
    private static final float BASE_TIME = 10;
    private BufferedImage currentTexture;
    Rarity rarity;
    int level;        //wird festgelegt durch die Elterneinhörner
    int poptime;        //Zeit benötigt zum Schlüpfen
    boolean hatched;
    private ScheduledExecutorService scheduler;
    int firstCrack;        //Zeit wann neue Textur geladen wird
    int secondCrack;        //Zeit wann neue Textur geladen wird

    public Egg(Rarity rarity, int level) {
        this.rarity = rarity;
        this.level = level;
        poptime = (int) Math.ceil(rarity.getHatchFactor() * level * BASE_TIME / 2);
        hatched = false;
        firstCrack = poptime / 2;
        secondCrack = poptime / 4;
        setEggTexture(1);
    }


    boolean getHatched() {
        return hatched;
    }

    public float getPoptime() {
        return poptime;
    }

    public void startHatching() {
        scheduler = Executors.newSingleThreadScheduledExecutor();

        final Runnable counter = () -> {
            if (poptime > 0) {
                poptime--;

                if (poptime == firstCrack) {        //Läd neue Textur
                    setEggTexture(2);
                } else if (poptime == secondCrack) {        //Läd neue Textur
                    setEggTexture(3);
                }
            } else {
                scheduler.shutdown();        //Ende, wenn geschlüpft
                hatched = true;
            }
        };

        scheduler.scheduleAtFixedRate(counter, 0, 1, TimeUnit.SECONDS);

    }


    private void setEggTexture(int stage) {
        String imagePath = "/egg_" + rarity.getName() + "_" + stage + ".png";
        try {
            InputStream is = getClass().getResourceAsStream(imagePath);

            if (is != null) {
                currentTexture = ImageIO.read(is);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int weight() {
        return 0;
    }

    @Override
    public double mult() {
        return 0;
    }

    @Override
    public Rarity getRarity() {
        return rarity;
    }

    @Override
    public int getBasePrice() {
        return 1;
    }
}