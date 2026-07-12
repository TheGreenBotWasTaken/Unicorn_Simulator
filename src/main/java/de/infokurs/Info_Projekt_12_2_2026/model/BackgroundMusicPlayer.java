package de.infokurs.Info_Projekt_12_2_2026.model;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BackgroundMusicPlayer {

    private static final String[] SONG_FILES = {
            "Amazing_Plan.mp3",
            "Carefree.mp3",
            "Cipher2.mp3",
            "Fluffing_Duck.mp3",
            "Merry_Go.mp3",
            "Monkeys_Spinning_Monkeys.mp3",
            "Move_Forward.mp3",
            "Pixel_Peeker_Polka.mp3",
            "Scheming_Weasel.mp3",
            "Sneaky_Adventure.mp3",
            "Sneaky_Snitch.mp3",
            "The_Builder.mp3"
    };

    private static final String EASTER_EGG_FILE = "nothingtoseeheremovealong.mp3";

    private String easterEggResource;
    private final List<String> songs = new ArrayList<>();
    private final Random random = new Random();

    private MediaPlayer mediaPlayer;
    private double volume;
    private int lastSongIndex = -1;

    public BackgroundMusicPlayer(String musicFolderPath) {
        loadSongs(musicFolderPath);

        if (songs.isEmpty()) {
            throw new IllegalArgumentException(
                    "No MP3 files found in: " + musicFolderPath
            );
        }
    }

    private void loadSongs(String folderPath) {
        String normalizedFolder = folderPath.startsWith("/") ? folderPath : "/" + folderPath;

        for (String name : SONG_FILES) {
            String resourcePath = normalizedFolder + "/" + name;
            if (getClass().getResource(resourcePath) == null) {
                System.err.println("[MUSIC] Missing resource: " + resourcePath);
                continue;
            }
            songs.add(resourcePath);
        }

        String easterEggPath = normalizedFolder + "/" + EASTER_EGG_FILE;
        if (getClass().getResource(easterEggPath) != null) {
            easterEggResource = easterEggPath;
        } else {
            System.err.println("[MUSIC] Missing easter egg resource: " + easterEggPath);
        }
    }

    public void play() {
        playNext();
    }

    private void playNext() {
        if (songs.isEmpty()) return;

        if (random.nextInt(100) == 1 && easterEggResource != null) {
            playFile(easterEggResource);
            return;
        }

        int index;

        if (songs.size() == 1) {
            index = 0;
        } else {
            do {
                index = random.nextInt(songs.size());
            } while (index == lastSongIndex);
        }

        lastSongIndex = index;

        playFile(songs.get(index));
    }

    private void playFile(String resourcePath) {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.dispose();
        }

        URL resourceUrl = getClass().getResource(resourcePath);
        if (resourceUrl == null) {
            System.err.println("[MUSIC] Resource disappeared: " + resourcePath);
            return;
        }

        Media media = new Media(resourceUrl.toExternalForm());
        mediaPlayer = new MediaPlayer(media);

        mediaPlayer.setVolume(volume);
        mediaPlayer.setOnEndOfMedia(this::playNext);
        mediaPlayer.play();

        System.out.println("[MUSIC] Now playing: " + resourcePath);
    }

    public void stop() {
        if (mediaPlayer != null) mediaPlayer.stop();
    }

    public void pause() {
        if (mediaPlayer != null) mediaPlayer.pause();
    }

    public void resume() {
        if (mediaPlayer != null) mediaPlayer.play();
    }

    public void setVolume(double volume) {
        this.volume = Math.max(0.0, Math.min(1.0, volume));
        if (mediaPlayer != null) mediaPlayer.setVolume(this.volume);
    }

    public double getVolume() {
        return volume;
    }
}