public class Shop {
    int money;
    Offers[] aktivOffers = new Offers[4];

    public Shop(){

    }

    void fillShop(){
        for(int i = 0; i < 4; i++){
            aktivOffers[i] = new Offers();
        }
    }

    void buyOffer(int offerID){
       if(money >=  aktivOffers[offerID].getPrice()){
           aktivOffers[offerID] = null;
       }
       else{System.out.println("not enough money");}
    }



}