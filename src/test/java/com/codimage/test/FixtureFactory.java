package com.codimage.test;

import com.codimage.image.Image;

/**
 * Factory for test fixtures.
 *
 * @author Ruslan Khmelyuk
 */
public class FixtureFactory {

    public static Image createImage() {
        final Image image = new Image();
        image.setUrl("test url");

        return image;
    }
}
