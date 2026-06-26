

public class forrestStable {


    static Unicorn start;
    int slots;
    int time;
    Thread t1 = new Thread(() -> {
        try {
            timeLimit();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    });



    public forrestStable() {
        start = null;
        slots = 5;
        time = 10;
        t1.start();
    }

    public void setTime(int time) {
        this.time = time;
    }

    int giveSlots() {
        return slots;
    }

    void setSlots(int slotsNew){
        slots = slotsNew;
    }

    void addStables(Unicorn unicornNew)  {
        if (start == null) {
            start = unicornNew;
            //printAll(start);
        } else {
            start.add(unicornNew);
           //printAll(start);
        }

    }

//   void printAll(Unicorn u) {
//        if (u != null) {
//            System.out.println(u.num);
//            printAll(u.giveNextTo());
//        }
//        System.out.println("");
//    }

    void timeLimit() throws InterruptedException {
        int seconds = time;
        if (slots < 5) {
            for (int i = seconds; i >= 0; i--) {
                Thread.sleep(1000);
            }
            if (start.giveNextTo() != null) {
                // System.out.println(start +" deleted");
                start = start.giveNextTo();


                slots++;
                timeLimit();
            }

        }

    }


    void removeStables(Unicorn u)  {
    if(start == u) {
        start = start.giveNextTo();
    }
    else  {
        start.remove(u);
    }
    slots--;
    }
}

