public interface Item {
    String getRarity();
    Item genEgg(String rarity); //Generates a random Egg with given rarity (placeholder)
    int getPrice();
}
