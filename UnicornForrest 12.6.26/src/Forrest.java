


public class Forrest {

    Temple temple;
    forrestStable stable;
    RandomGenerator gen;

    public Forrest() throws InterruptedException {
        temple = new Temple();
        stable = new forrestStable();
        gen  = new RandomGenerator(temple.returnLuck());
        forrestThread.start();
    }

    Thread forrestThread = new Thread(()-> {
        try
    {
    forrestSlots();
    }
    catch(InterruptedException e)
    {
        Thread.currentThread().interrupt();
    }
    });

    void upgradeLuck(){
      temple.luckUp();
        gen.setLuck(temple.returnLuck());
    }

    void upgradeTime(){
        temple.remainTimeUp();
        stable.setTime(temple.returnRemainTime());
    }

    void upgradeCD(){
        temple.spawnRateUp();
    }


    String spawn() throws InterruptedException {
        int seconds = Math.toIntExact(Math.round(60 / temple.returnSpawnRate()));
        for (int i = seconds; i >= 0; i--) {
            Thread.sleep(1000);
        }

        //System.out.println(r);
        return gen.roll();

    }

    Unicorn unicornSpawn(String r) {

        return new Unicorn(r);
    }

    void forrestSlots() throws InterruptedException {
        //while (true) {
            int slots = stable.giveSlots();

            if (slots > 0) {
                slots--;
                stable.setSlots(slots);
                stable.addStables(unicornSpawn(spawn())); //has to create a random unicorn
                forrestSlots();
            }


           // else{
                Thread.sleep(1000);
            forrestSlots();
            //}
       // }

    }
}
