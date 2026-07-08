package de.infokurs.Info_Projekt_12_2_2026.model;

public class Offer {

    private final BuyableItem item;
    private final int count;
    private final float discount;
    private final int basePrice;
    private final int discountedPrice;

    public Offer(BuyableItem item, int count, float discount) {
        this.item = item;
        this.count = count;
        this.discount = discount;
        this.basePrice = item.getBasePrice() * count;
        this.discountedPrice = Math.round(basePrice * (1f - discount));
    }

    public BuyableItem getItem() {
        return item;
    }

    public int getCount() {
        return count;
    }

    public float getDiscount() {
        return discount;
    }

    public int getBasePrice() {
        return basePrice;
    }

    public int getDiscountedPrice() {
        return discountedPrice;
    }

    public boolean hasDiscount() {
        return discount > 0f;
    }
}