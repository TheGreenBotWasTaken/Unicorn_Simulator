package de.infokurs.Info_Projekt_12_2_2026.model;

import de.infokurs.Info_Projekt_12_2_2026.model.unicorns.Rarity;
import de.infokurs.Info_Projekt_12_2_2026.model.unicorns.Unicorn;
import de.infokurs.Info_Projekt_12_2_2026.model.unicorns.UnicornFactory;

import java.util.List;
import java.util.concurrent.*;

public class Shop {

    private static Shop instance;

    private static final int OFFER_SLOTS = 4;
    private static final float[] POSSIBLE_DISCOUNTS = {0f, 0f, 0.1f, 0.2f, 0.3f, 0.4f};
    private static final long ROTATION_MINUTES = 5;

    //Available unicorns for Shop rotation
    private static final String[] PURCHASABLE_UNICORN_IDS = {
            "common_unicorn",
            "unusual_unicorn",
            "rare_unicorn",
            "epic_unicorn",
            "legendary_unicorn",
            "ultra_unicorn"
    };

    private final RandomGenerator<BuyableItem> gen;
    private final List<Offer> currentOffers = new CopyOnWriteArrayList<>();

    private final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor(r -> {
        Thread t = new Thread(r, "shop-rotation-thread");
        t.setDaemon(true);
        return t;
    });

    private Shop() {
        gen = new RandomGenerator<>(Temple.getInstance().getLuck());
        populateGenerator();

        generateOffers();
        scheduler.scheduleAtFixedRate(this::generateOffers, ROTATION_MINUTES, ROTATION_MINUTES, TimeUnit.MINUTES);
    }

    public static synchronized Shop getInstance() {
        if (instance == null) {
            instance = new Shop();
        }
        return instance;
    }

    public void updateLuck() {
        gen.setLuck(Temple.getInstance().getLuck());
        populateGenerator();
        generateOffers();
    }

    private void populateGenerator() {
        gen.add(new ShopEgg("common_egg", "Common Egg", Rarity.COMMON, 500, 500, 0));
        gen.add(new ShopEgg("uncommon_egg", "Uncommon Egg", Rarity.UNUSUAL, 1000, 250, 0));
        gen.add(new ShopEgg("rare_egg", "Rare Egg", Rarity.RARE, 2000, 150, 1));
        gen.add(new ShopEgg("epic_egg", "Epic Egg", Rarity.EPIC, 4000, 90, 2));
        gen.add(new ShopEgg("legendary_egg", "Legendary Egg", Rarity.LEGENDARY, 8000, 10, 3));
        gen.add(new ShopEgg("ultra_egg", "ULTRA EGG", Rarity.ULTRA, 80000, 1, 4));

        for (String unicornId : PURCHASABLE_UNICORN_IDS) {
            Unicorn unicorn = UnicornFactory.createById(unicornId);
            gen.add(unicorn);
        }
    }


    public synchronized void generateOffers() {
        currentOffers.clear();
        for (int i = 0; i < OFFER_SLOTS; i++) {
            currentOffers.add(rollNewOffer());
        }
    }

    private Offer rollNewOffer() {
        BuyableItem item = gen.roll();
        int count = 1 + ThreadLocalRandom.current().nextInt(3); // 1 - 3
        float discount = POSSIBLE_DISCOUNTS[ThreadLocalRandom.current().nextInt(POSSIBLE_DISCOUNTS.length)];
        return new Offer(item, count, discount);
    }

    public List<Offer> getCurrentOffers() {
        return currentOffers;
    }


    public synchronized boolean purchase(Offer offer) {
        if (!currentOffers.contains(offer)) {
            return false; // Angebot nicht mehr aktuell
        }
        if (!Wallet.getInstance().spend(offer.getDiscountedPrice())) {
            return false; // nicht genug Moneten
        }

        grantItem(offer);

        int index = currentOffers.indexOf(offer);
        if (index != -1) {
            currentOffers.set(index, rollNewOffer());
        }
        return true;
    }

    private void grantItem(Offer offer) {
        BuyableItem item = offer.getItem();

        if (item instanceof Unicorn unicornTemplate) {
            for (int i = 0; i < offer.getCount(); i++) {
                Stable.getInstance().addUnicorn(UnicornFactory.createById(unicornTemplate.getId()));
            }
        } else if (item instanceof ShopEgg shopEgg) {
            for (int i = 0; i < offer.getCount(); i++) {
                Egg egg = shopEgg.toEgg();
                Nest.getInstance().addEgg(egg);
            }
        }
    }
}