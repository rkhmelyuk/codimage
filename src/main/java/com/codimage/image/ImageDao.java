package com.codimage.image;

/**
 * The DAO interface for {@link Image}.
 *
 * @author Ruslan Khmelyuk
 */
public interface ImageDao {

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

    /**
     * Find the image that goes after image with specified id.
     * @param imageId the image id.
     * @return the next image.
     */
    Image findNextImage(long imageId);
}
