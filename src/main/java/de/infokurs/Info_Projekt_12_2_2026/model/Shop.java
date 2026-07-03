package de.infokurs.Info_Projekt_12_2_2026.model;

public class Shop {
    private static Shop INSTANCE;
    int money;
    Offer[] activeOffers = new Offer[4];

    void refreshShop(){
        for(int i = 0; i < 4; i++){
            activeOffers[i] = null; //GEN OFFER
        }
    }
    public static Shop getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new Shop();
        }

        return INSTANCE;
    }

    void buyOffer(int offerID){
        int discountedPrice = activeOffers[offerID].getDiscountedPrice();
       if(money >=  discountedPrice){
           activeOffers[offerID] = null;
           money -= discountedPrice;
       }
       else{System.out.println("Not enough money!");}
    }



}