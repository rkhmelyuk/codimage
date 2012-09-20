package com.codimage;

/**
 * Application-wide constants.
 *
 * @author Ruslan Khmelyuk
 */
public interface AppConstant {

    /**
     * The name of the next image cache.
     * As we loop round the images, we can and actually cache requests for the next image.
     */
    String CACHE_NEXT_IMAGE = "nextImageCache";
}
