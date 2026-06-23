package de.infokurs.Info_Projekt_12_2_2026.model;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BackgroundMusicPlayer {

    private File easterEggFile;
    private final List<File> songs = new ArrayList<>();
    private final Random random = new Random();

    private MediaPlayer mediaPlayer;
    private double volume = 0.5;
    private int lastSongIndex = -1;

    private static final String EASTER_EGG_FILE = "nothingtoseeheremovealong.mp3";

    public BackgroundMusicPlayer(String musicFolderPath) {
        loadSongs(musicFolderPath);

        if (songs.isEmpty()) {
            throw new IllegalArgumentException(
                    "No MP3 files found in: " + musicFolderPath
            );
        }
    }

    private void loadSongs(String folderPath) {
        URL musicURL = getClass().getResource(folderPath);

        if (musicURL == null) {
            throw new IllegalArgumentException("Music folder not found: " + folderPath);
        }

        File folder;
        try {
            folder = new File(musicURL.toURI());
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

        if (!folder.exists() || !folder.isDirectory()) {
            throw new IllegalArgumentException("Invalid music folder: " + folderPath);
        }

        File[] files = folder.listFiles();
        if (files == null) return;
        for (File file : files) {
            if (file.isFile() && file.getName().toLowerCase().endsWith(".mp3")) {

                if (file.getName().equalsIgnoreCase(EASTER_EGG_FILE)) {
                    easterEggFile = file; // store separately
                    continue;
                }

                songs.add(file);
            }
        }
    }

    public void play() {
        playNext();
    }

    private void playNext() {
        if (songs.isEmpty()) return;

        if (random.nextInt(100) == 0) {
            File easterEgg = findEasterEgg();

            if (easterEgg != null) {
                playFile(easterEgg);
                return;
            }
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

    private void playFile(File file) {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.dispose();
        }

        Media media = new Media(file.toURI().toString());
        mediaPlayer = new MediaPlayer(media);

        mediaPlayer.setVolume(volume);
        mediaPlayer.setOnEndOfMedia(this::playNext);
        mediaPlayer.play();

        System.out.println("Now playing: " + file.getName());
    }

    private File findEasterEgg() {
        return easterEggFile;
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