package de.infokurs.Info_Projekt_12_2_2026.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomGenerator<T extends Rollable> {

    private final List<T> table = new ArrayList<>();
    private final Random random = new Random();
    private double luck;

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

        double total = 0;
        for (T item : table) {
            total += effectiveWeight(item);
        }

        double r = random.nextDouble() * total;
        for (T item : table) {
            r -= effectiveWeight(item);
            if (r <= 0) {
                return item;
            }
        }
        return table.get(table.size() - 1);
    }

    private double effectiveWeight(T item) {
        return item.weight() * Math.pow(luck, item.mult());
    }
}