package de.infokurs.Info_Projekt_12_2_2026.model;

import de.infokurs.Info_Projekt_12_2_2026.model.unicorns.Rarity;
import javafx.application.Platform;

import java.util.Scanner;


public class DebugConsole {

    private static final int DEFAULT_DEBUG_LEVEL = 1; // Level, mit dem Debug-Eier erzeugt werden

    private static Thread consoleThread;

    public static void start() {
        if (consoleThread != null) {
            return;
        }

        consoleThread = new Thread(DebugConsole::runLoop, "debug-console");
        consoleThread.setDaemon(true);
        consoleThread.start();

        System.out.println("[DebugConsole] gestartet. Tippe /help für Befehle.");
    }

    private static void runLoop() {
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();
            if (line.isEmpty()) {
                continue;
            }
            Platform.runLater(() -> handleCommand(line));
        }
    }

    private static void handleCommand(String line) {
        String[] parts = line.split("\\s+");
        String command = parts[0];

        switch (command) {
            case "/egg" -> handleEggCommand(parts);
            case "/addMoneten" -> handleMonetenCommand(parts);
            case "/help" -> printHelp();
            default -> System.out.println("Unbekannter Befehl: " + command + " (tippe /help)");
        }
    }

    private static void handleMonetenCommand(String[] parts) {
        if (parts.length != 2) {
            System.out.println("Verwendung: /addMoneten <amount>");
            return;
        }
        int amount = Integer.parseInt(parts[1]);
        Wallet.getInstance().add(amount);
    }

    private static void handleEggCommand(String[] parts) {
        if (parts.length != 4) {
            System.out.println("Verwendung: /egg <rarity> <unicornId> <count>");
            return;
        }

        String rarityInput = parts[1];
        String unicornId = parts[2];
        String countInput = parts[3];

        Rarity rarity;
        try {
            rarity = Rarity.valueOf(rarityInput.toUpperCase());
        } catch (IllegalArgumentException e) {
            System.out.println("Unbekannte Rarity: " + rarityInput
                    + ". Verfügbare Werte: " + java.util.Arrays.toString(Rarity.values()));
            return;
        }

        int count;
        try {
            count = Integer.parseInt(countInput);
        } catch (NumberFormatException e) {
            System.out.println("Ungültige Anzahl: " + countInput);
            return;
        }

        if (count <= 0) {
            System.out.println("Anzahl muss größer als 0 sein.");
            return;
        }

        int added = 0;
        for (int i = 0; i < count; i++) {
            Egg egg = new Egg(rarity, DEFAULT_DEBUG_LEVEL, unicornId);
            boolean success = Nest.getInstance().addEgg(egg);
            if (!success) {
                System.out.println("Nest ist voll! " + added + " von " + count + " Eiern hinzugefügt.");
                return;
            }
            added++;
        }

        System.out.println(added + " " + rarity + "-Ei/Eier für '" + unicornId + "' zum Nest hinzugefügt.");
    }

    private static void printHelp() {
        System.out.println("""
                Verfügbare Debug-Befehle:
                  /egg <rarity> <unicornId> <count>   - Erzeugt Eier im Nest
                  /help                                - Diese Hilfe anzeigen
                """);
    }
}