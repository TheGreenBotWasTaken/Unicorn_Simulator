package de.infokurs.Info_Projekt_12_2_2026.model;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class EggerListe {

    public static void main(String[] args) {

// Liste mit genau 4 Plätzen
        ArrayList<Egg> Egger = new ArrayList<>(4);

// Alle Plätze sind zunächst leer
        for (int i = 0; i < 4; i++) {
            Egger.add(null);
        }

// Egger Eggnsetzen
        Egger.set(0, new Egg());
        Egger.set(1, new Egg());
        Egger.set(3, new Egg());

// Geschlüpfte Egger entfernen
        entferneGeschluepfteEgger(Egger);

// Liste ausgeben
        zEgggeListe(Egger);
    }

    public static void entferneGeschluepfteEgger(ArrayList<Egg> liste) {
        for (int i = 0; i < liste.size(); i++) {
            Egg Egg = liste.get(i);

            if (Egg != null && Egg.getIsHatched()) {
                liste.set(i, null); // Platz blEggbt frEgg
            }
        }
    }

    public static void zEgggeListe(ArrayList<Egg> liste) {
        for (int i = 0; i < liste.size(); i++) {
            if (liste.get(i) == null) {
                System.out.println("Platz " + i + ": frEgg");
            } else {
                System.out.println("Platz " + i + ": " + liste.get(i));
            }
        }
    }
}