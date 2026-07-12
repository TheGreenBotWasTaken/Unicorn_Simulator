package de.infokurs.Info_Projekt_12_2_2026.util;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;

import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Central cache for pixel-art textures.
 *
 * <p>JavaFX's {@code ImageView.smooth} property does not actually disable
 * bilinear filtering when an image is scaled (this is a known, unresolved
 * JavaFX/Prism limitation: nearest-neighbor filtering was never
 * implemented). This class works around that by pre-scaling every source
 * image with manual nearest-neighbor pixel duplication, and caching the
 * result so the same texture is never decoded or scaled twice.
 *
 * <p>Usage:
 * <pre>{@code
 * // Load a sprite scaled up 4x and assign it to an ImageView, with fitWidth/fitHeight
 * // matched automatically so JavaFX never re-scales it (and re-blurs it) at render time.
 * TextureCache.applyScaled(unicornImageView, "/images/unicorn_stage2.png", 4);
 *
 * // Or just fetch the scaled Image yourself.
 * Image sprite = TextureCache.getScaled("/images/egg_common.png", 3);
 * }</pre>
 *
 * <p>Thread-safe: backed by {@link ConcurrentHashMap}, safe to call from
 * background threads (e.g. Forest spawn/despawn, breeding polling).
 */
public final class TextureCache {

    /**
     * Raw, unscaled images, keyed by resource path. Loaded once each.
     */
    private static final ConcurrentHashMap<String, Image> RAW_CACHE = new ConcurrentHashMap<>();

    /**
     * Nearest-neighbor scaled images, keyed by "path@scaleFactor".
     */
    private static final ConcurrentHashMap<String, Image> SCALED_CACHE = new ConcurrentHashMap<>();

    private TextureCache() {
        // static utility class, no instances
    }

    /**
     * Returns the raw (unscaled) image for the given classpath resource,
     * loading and caching it on first request.
     *
     * @param resourcePath classpath-relative image path, e.g. "/images/unicorn.png"
     */
    public static Image getRaw(String resourcePath) {
        Objects.requireNonNull(resourcePath, "resourcePath must not be null");
        return RAW_CACHE.computeIfAbsent(resourcePath, TextureCache::loadRaw);
    }

    /**
     * Returns a nearest-neighbor upscaled version of the given texture,
     * scaled by an integer factor (2 = double size, 3 = triple size, etc.).
     * The result is cached, so repeated calls with the same path and
     * scale factor are free after the first.
     *
     * @param resourcePath classpath-relative image path
     * @param scaleFactor  integer scale factor, must be >= 1
     */
    public static Image getScaled(String resourcePath, int scaleFactor) {
        if (scaleFactor < 1) {
            throw new IllegalArgumentException("scaleFactor must be >= 1, was " + scaleFactor);
        }
        if (scaleFactor == 1) {
            return getRaw(resourcePath);
        }

        String key = resourcePath + "@" + scaleFactor;
        return SCALED_CACHE.computeIfAbsent(key, k -> {
            Image raw = getRaw(resourcePath);
            return scaleNearestNeighbor(raw, scaleFactor);
        });
    }

    /**
     * Returns a nearest-neighbor scaled image sized as closely as possible
     * to the requested target dimensions, by picking the largest integer
     * scale factor that does not exceed them. Useful when you know the
     * on-screen box (e.g. a stall slot) but not an exact scale factor.
     *
     * @param resourcePath classpath-relative image path
     * @param targetWidth  desired width in pixels
     * @param targetHeight desired height in pixels
     */
    public static Image getScaledToFit(String resourcePath, double targetWidth, double targetHeight) {
        Image raw = getRaw(resourcePath);
        int rawWidth = (int) raw.getWidth();
        int rawHeight = (int) raw.getHeight();

        int scaleFactor = Math.max(1, (int) Math.floor(
                Math.min(targetWidth / rawWidth, targetHeight / rawHeight)));

        return getScaled(resourcePath, scaleFactor);
    }

    /**
     * Convenience method: loads a texture scaled by the given integer
     * factor and applies it directly to an ImageView, setting fitWidth
     * and fitHeight to match the scaled image exactly so JavaFX never
     * has to scale (and blur) it again at render time.
     *
     * @param imageView    target ImageView
     * @param resourcePath classpath-relative image path
     * @param scaleFactor  integer scale factor, must be >= 1
     */
    public static void applyScaled(ImageView imageView, String resourcePath, int scaleFactor) {
        Objects.requireNonNull(imageView, "imageView must not be null");
        Image scaled = getScaled(resourcePath, scaleFactor);
        imageView.setImage(scaled);
        imageView.setFitWidth(scaled.getWidth());
        imageView.setFitHeight(scaled.getHeight());
        imageView.setSmooth(false); // harmless to keep set, just not sufficient on its own
        imageView.setPreserveRatio(false); // dimensions already match exactly, no ratio math needed
    }

    /**
     * Convenience method: loads a texture scaled to best fit the given
     * target box and applies it directly to an ImageView.
     *
     * @param imageView    target ImageView
     * @param resourcePath classpath-relative image path
     * @param targetWidth  desired width in pixels
     * @param targetHeight desired height in pixels
     */
    public static void applyScaledToFit(ImageView imageView, String resourcePath,
                                        double targetWidth, double targetHeight) {
        Objects.requireNonNull(imageView, "imageView must not be null");
        Image scaled = getScaledToFit(resourcePath, targetWidth, targetHeight);
        imageView.setImage(scaled);
        imageView.setFitWidth(scaled.getWidth());
        imageView.setFitHeight(scaled.getHeight());
        imageView.setSmooth(false);
        imageView.setPreserveRatio(false);
    }

    /**
     * Clears all cached textures (raw and scaled). Rarely needed, but
     * useful if you ever hot-swap texture files during development.
     */
    public static void clearAll() {
        RAW_CACHE.clear();
        SCALED_CACHE.clear();
    }

    // ------------------------------------------------------------------
    // Internal helpers
    // ------------------------------------------------------------------

    private static Image loadRaw(String resourcePath) {
        String normalized = resourcePath.startsWith("/") ? resourcePath : "/" + resourcePath;
        var stream = TextureCache.class.getResourceAsStream(normalized);
        if (stream == null) {
            throw new IllegalArgumentException("Texture resource not found: " + normalized);
        }
        return new Image(stream);
    }

    /**
     * Scales an image up by an integer factor using nearest-neighbor
     * pixel duplication (each source pixel becomes a scaleFactor x
     * scaleFactor block in the output). This is what gives crisp,
     * blocky pixel-art edges instead of JavaFX's forced bilinear blur.
     */
    private static Image scaleNearestNeighbor(Image source, int scaleFactor) {
        int width = (int) source.getWidth();
        int height = (int) source.getHeight();

        if (width <= 0 || height <= 0) {
            throw new IllegalArgumentException("Source image has invalid dimensions: "
                    + width + "x" + height);
        }

        int newWidth = width * scaleFactor;
        int newHeight = height * scaleFactor;

        PixelReader reader = source.getPixelReader();
        WritableImage output = new WritableImage(newWidth, newHeight);
        PixelWriter writer = output.getPixelWriter();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int argb = reader.getArgb(x, y);
                int destX = x * scaleFactor;
                int destY = y * scaleFactor;
                for (int dy = 0; dy < scaleFactor; dy++) {
                    for (int dx = 0; dx < scaleFactor; dx++) {
                        writer.setArgb(destX + dx, destY + dy, argb);
                    }
                }
            }
        }

        return output;
    }
}