public class Offers {
    int price;
    Item item;
    ItemGen generator;
    public Offers(){
    generator = new ItemGen(1.0); //luck from temple of Forrest class
    offerGen();
    }
    void offerGen(){
    item = item.genEgg(generator.roll()); //has to generate a random item from rarity droptable + price
    price = item.getPrice();
    }
    int getPrice(){
        return price;
    }

}
