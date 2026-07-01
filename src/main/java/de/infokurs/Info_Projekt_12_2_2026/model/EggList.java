package de.infokurs.Info_Projekt_12_2_2026.model;

import java.util.ArrayList;

public class EggList {

    public static void main(String[] args) {

        ArrayList<Egg> eggs = new ArrayList<>(4);

        for (int i = 0; i < 4; i++) {
            eggs.add(null);
        }

// eggs Einsetzen

// Geschlüpfte eggs entfernen
        entferneGeschluepfteEgger(eggs);

    }

    public static void entferneGeschluepfteEgger(ArrayList<Egg> liste) {
        for (int i = 0; i < liste.size(); i++) {
            Egg Egg = liste.get(i);

            if (Egg != null && Egg.getHatched()) {
                liste.set(i, null); // Platz bleibt frei
            }
        }
    }
}