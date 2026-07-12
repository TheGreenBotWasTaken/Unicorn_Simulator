package de.infokurs.Info_Projekt_12_2_2026.model;

import de.infokurs.Info_Projekt_12_2_2026.model.unicorns.Rarity;
import de.infokurs.Info_Projekt_12_2_2026.model.unicorns.UnicornRegistry;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class ShopEgg implements BuyableItem {

    private final String id;
    private final String displayName;
    private final Rarity rarity;
    private int price;
    private int weight;
    private double mult;

    public ShopEgg(String id, String displayName, Rarity rarity, int price, int weight, double mult) {
        this.id = id;
        this.displayName = displayName;
        this.rarity = rarity;
        this.price = price;
        this.weight = weight;
        this.mult = mult;
    }

    public String getId() {
        return id;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void setMult(double mult) {
        this.mult = mult;
    }

    @Override
    public Rarity getRarity() {
        return rarity;
    }

    @Override
    public int getBasePrice() {
        return price;
    }

    @Override
    public int weight() {
        return weight;
    }

    @Override
    public double mult() {
        return mult;
    }

    @Override
    public String getDisplayName() {
        return displayName;
    }

    @Override
    public String getTexturePath() {
        return "assets/textures/shop/egg_" + id + ".png";
    }

    public Egg toEgg() {
        List<String> candidateIds = UnicornRegistry.getInstance().getIdsByRarity(rarity);
        if (candidateIds.isEmpty()) {
            throw new IllegalStateException("Keine Einhörner der Rarity " + rarity + " registriert.");
        }
        String chosenId = candidateIds.get(ThreadLocalRandom.current().nextInt(candidateIds.size()));
        return new Egg(rarity, 1, chosenId); // Level 1, da noch kein Eltern-Level bekannt
    }
}