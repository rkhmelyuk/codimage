package com.codimage.image;

/**
 * The DAO interface for {@link Image}.
 *
 * @author Ruslan Khmelyuk
 */
public interface ImageRepository {

    /**
     * Creates a new image resource.
     * @param image the image to create.
     */
    void create(Image image);

    /**
     * Finds random image and returns it.
     * @return the random image or null if none image found.
     */
    Image findRandomImage();

    /**
     * Removes all images.
     */
    void deleteAll();
}
