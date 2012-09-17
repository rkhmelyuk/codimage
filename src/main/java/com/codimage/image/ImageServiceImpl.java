package com.codimage.image;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * The service to work with images.
 *
 * @author Ruslan Khmelyuk
 */
@Service
public class ImageServiceImpl implements ImageService {

    private static final Logger log = LoggerFactory.getLogger(ImageServiceImpl.class);

    @Autowired ImageDao imageDao;

    @Override
    public Image getNextImage() {
        return imageDao.findRandomImage();
    }

    @Override
    @Cacheable("nextImageCache")
    public Image getNextImageAfter(long imageId) {
        log.info("Get image after image:{}", imageId);
        return imageDao.findNextImage(imageId);
    }
}
