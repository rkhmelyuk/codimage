package com.codimage.image;

/**
 * The interface for image service.
 *
 * @author Ruslan Khmelyuk
 */
public interface ImageService {

    /**
     * Gets the next random image to display to the user.
     * @return the next random image, or null if no images.
     */
    Image getNextImage();
}
