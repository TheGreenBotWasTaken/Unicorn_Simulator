package de.infokurs.Info_Projekt_12_2_2026.model;

public class Offer {
    int basePrice;
    float visualDiscount;
    int discountedPrice;
    BuyableItem item;
    int count;
    RandomGenerator<BuyableItem> generator;

    public Offer(BuyableItem item, int price, int count, float visualDiscount) {
        this.item = item;
        this.discountedPrice = price;
        this.count = count;
        this.visualDiscount = visualDiscount;
        generator = new RandomGenerator<>(Temple.getInstance().getLuck()); //luck from temple of Forrest class
        offerGen();
    }

    public int getDiscountedPrice() {
        return discountedPrice;
    }

    void offerGen() {
        item = null; //has to generate a random item from rarity droptable + price
        basePrice = item.getBasePrice();
    }

}
