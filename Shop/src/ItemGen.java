import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ItemGen {

        double luck;
        public ItemGen(double luckNew) {
            luck = luckNew;
            add("common",500,0);//common
            add("uncommon",250,0);//unusual
            add("rare",150,1);//rare
            add("epic",90,2);//epic
            add("legendary",10,3);//legendary

        }
        void setLuck(double luck){
            this.luck = luck;
        }


        record Item(String rarity, int weight, int mult) {} //string should be changed to class Rarity

        private final List<Item> table = new ArrayList<>();
        private final Random random = new Random();

        public void add(String rarity, int weight, int mult) {
            table.add(new Item(rarity, weight, mult));
        }
        public String roll(){
            if (table.isEmpty()){throw new IllegalStateException("Table is empty");}
            double total = 0;
            for  (Item item : table){total += effectiveWeight(item, luck);}
            double r =  random.nextDouble() *total;
            for  (Item item : table){r -= item.weight;
                if(r <= 0){return item.rarity;}
            }
            return table.getLast().rarity();
        }

        private double effectiveWeight(Item item, double luck){
            return item.weight * Math.pow(luck, item.mult);
        }

    }
