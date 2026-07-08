package de.infokurs.Info_Projekt_12_2_2026.model;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class RandomGenerator<T extends Rollable> {

    private final List<T> table = new ArrayList<>();
    private volatile double luck;

    public RandomGenerator(double luck) {
        this.luck = luck;
    }

    public RandomGenerator() {
        this(1.0);
    }

    public void setLuck(double luck) {
        this.luck = luck;
    }

    public double getLuck() {
        return luck;
    }

    public void add(T item) {
        table.add(item);
    }

    public T roll() {
        if (table.isEmpty()) {
            throw new IllegalStateException("Table is empty");
        }

        double currentLuck = luck;
        double total = 0;
        for (T item : table) {
            total += effectiveWeight(item, currentLuck);
        }

        double r = ThreadLocalRandom.current().nextDouble() * total;
        for (T item : table) {
            r -= effectiveWeight(item, currentLuck);
            if (r <= 0) {
                return item;
            }
        }
        return table.get(table.size() - 1);
    }

    private double effectiveWeight(T item, double currentLuck) {
        return item.weight() * Math.pow(currentLuck, item.mult());
    }
}