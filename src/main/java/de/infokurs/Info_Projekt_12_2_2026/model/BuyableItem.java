package de.infokurs.Info_Projekt_12_2_2026.model;

import de.infokurs.Info_Projekt_12_2_2026.model.unicorns.Rarity;

public interface BuyableItem extends Rollable{
    Rarity getRarity();
    int getBasePrice();
}
