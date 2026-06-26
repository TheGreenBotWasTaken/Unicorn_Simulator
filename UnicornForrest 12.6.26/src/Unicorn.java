public class Unicorn {
    Unicorn nextTo;
    String rarity;
    Unicorn(String r) {
        rarity = r;
        nextTo = null;
    }

    void setNextTo(Unicorn nextToNew) {
        nextTo = nextToNew;
    }

    Unicorn giveNextTo() {
        return nextTo;
    }

    void add(Unicorn u) {
        if (giveNextTo() == null) {
            setNextTo(u);
        } else nextTo.add(u);
    }


    void remove(Unicorn u) {
        if (giveNextTo() == u) {
            setNextTo(u.giveNextTo());
        }
        else nextTo.remove(u);
    }

}

