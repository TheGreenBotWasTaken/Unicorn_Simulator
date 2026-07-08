public class ShopItems{

    RandomGenerator<BuyableItem> gen;

    public ShopItems() {

        gen = new RandomGenerator<>(Temple.getInstance().getLuck());

        gen.add(commonEgg);
        commonEgg.setPrice(500);
        commonEgg.setWeight(500);
        commonEgg.setMult(0);
        gen.add(uncommonEgg);
        uncommonEgg.setPrice(1000);
        uncommonEgg.setWeight(250);
        uncommonEgg.setMult(0);
        gen.add(rareEgg);
        rareEgg.setPrice(2000);
        rareEgg.setWeight(150);
        rareEgg.setMult(1);
        gen.add(epicEgg);
        epicEgg.setPrice(4000);
        epicEgg.setWeight(90);
        epicEgg.setMult(2);
        gen.add(legendaryEgg);
        legendaryEgg.setPrice(8000);
        legendaryEgg.setWeight(10);
        legendaryEgg.setMult(3);
        //price stc

        shopRotation();
    }

    BuyableItem shopRotation(){

         return gen.roll();

    }

}