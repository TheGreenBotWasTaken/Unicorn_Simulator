package de.infokurs.Info_Projekt_12_2_2026.model;

import java.util.ArrayList;

public class EierListe {

    public static void main(String[] args) {

// Liste mit genau 4 Plätzen
        ArrayList<Egg> eier = new ArrayList<>(4);

// Alle Plätze sind zunächst leer
        for (int i = 0; i < 4; i++) {
            eier.add(null);
        }

// Eier einsetzen
        eier.set(0, new Egg());
        eier.set(1, new Egg());
        eier.set(3, new Egg());

// Geschlüpfte Eier entfernen
        entferneGeschluepfteEier(eier);

// Liste ausgeben
        zeigeListe(eier);
    }

    public static void entferneGeschluepfteEier(List<Ei> liste) {
        for (int i = 0; i < liste.size(); i++) {
            Ei ei = liste.get(i);

            if (ei != null && ei.getIsHatched()) {
                liste.set(i, null); // Platz bleibt frei
            }
        }
    }

    public static void zeigeListe(List<Ei> liste) {
        for (int i = 0; i < liste.size(); i++) {
            if (liste.get(i) == null) {
                System.out.println("Platz " + i + ": frei");
            } else {
                System.out.println("Platz " + i + ": " + liste.get(i));
            }
        }
    }
}