package de.infokurs.Info_Projekt_12_2_2026.model;

public class Nest {
    private static volatile Nest INSTANCE;

    private static final int DEFAULT_CAPACITY = 4;
    private static final int MAX_CAPACITY = 12; //TODO: Research-Tree

    private final Egg[] slots;
    private int capacity;

    private Nest() {
        this.capacity = DEFAULT_CAPACITY;
        this.slots = new Egg[MAX_CAPACITY];
    }

    public static Nest getInstance() {
        Nest result = INSTANCE;
        if (result == null) {
            synchronized (Nest.class) {
                result = INSTANCE;
                if (result == null) {
                    INSTANCE = result = new Nest();
                }
            }
        }
        return result;
    }

    public int getCapacity() {
        return capacity;
    }

    public boolean upgradeCapacity() {
        if (capacity >= MAX_CAPACITY) {
            return false;
        }
        capacity++;
        return true;
    }

    public Egg getSlot(int index) {
        return slots[index];
    }

    public boolean addEgg(Egg egg) {
        for (int i = 0; i < capacity; i++) {
            if (slots[i] == null) {
                slots[i] = egg;
                egg.startHatching();
                return true;
            }
        }
        return false; // Nest ist voll
    }

    public void clearSlot(int index) {
        Egg egg = slots[index];
        if (egg != null) {
            egg.cancelHatching();
        }
        slots[index] = null;
    }

    public boolean isFull() {
        for (int i = 0; i < capacity; i++) {
            if (slots[i] == null) return false;
        }
        return true;
    }
}