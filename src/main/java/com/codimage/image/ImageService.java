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

    /**
     * Gets the next image after image with id specified as parameter.
     * @param imageId the id of the image.
     * @return the next image after specified one.
     */
    Image getNextImage(long imageId);

    /**
     * Gets the previous image before image with id specified as parameter.
     * @param imageId the id of the image.
     * @return the prev image before specified one.
     */
    Image getPrevImage(long imageId);

    /**
     * Resets the next image cache.
     * TODO - get rid of this after we have added support to edit images list.
     */
    void resetImageCache();
}
