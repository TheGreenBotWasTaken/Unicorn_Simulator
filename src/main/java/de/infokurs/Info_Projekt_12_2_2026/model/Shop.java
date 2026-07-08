package de.infokurs.Info_Projekt_12_2_2026.model;

public class Shop {
    private static Shop INSTANCE;
    private static final int OFFER_SLOTS = 4;

    int money;
    Offer[] activeOffers = new Offer[OFFER_SLOTS];

    private Shop() {
    }

    public static Shop getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Shop();
        }
        return INSTANCE;
    }

    void refreshShop() {
        for (int i = 0; i < OFFER_SLOTS; i++) {
            activeOffers[i] = null; //GEN OFFER
        }
    }

    void buyOffer(int offerID) {
        if (offerID < 0 || offerID >= OFFER_SLOTS) {
            System.out.println("Ungültige Angebots-ID: " + offerID);
            return;
        }

        Offer offer = activeOffers[offerID];
        if (offer == null) {
            System.out.println("Dieses Angebot ist nicht verfügbar.");
            return;
        }

        int discountedPrice = offer.getDiscountedPrice();
        if (money >= discountedPrice) {
            activeOffers[offerID] = null;
            money -= discountedPrice;
        } else {
            System.out.println("Not enough money!");
        }
    }
}