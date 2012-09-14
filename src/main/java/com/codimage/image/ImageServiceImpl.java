package com.codimage.image;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The service to work with images.
 *
 * @author Ruslan Khmelyuk
 */
@Service
public class ImageServiceImpl implements ImageService {

    @Autowired ImageRepository imageRepository;

    @Override
    public Image getNextImage() {
        return imageRepository.findRandomImage();
    }
}
