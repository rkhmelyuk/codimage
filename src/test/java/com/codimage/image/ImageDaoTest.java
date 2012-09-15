package com.codimage.image;

import com.codimage.config.AppConfig;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

/**
 * Tests for {@link ImageJdbcDao}
 *
 * @author Ruslan Khmelyuk
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class}, loader = AnnotationConfigContextLoader.class)
public class ImageDaoTest {

    @Autowired private ImageDao imageDao;

    @Before
    public void setUp() {
        imageDao.deleteAll();
    }

    @Test
    public void whenFindRandomImage_thenImageReturned() {
        final Image image = createImage();
        imageDao.create(image);

        final Image foundImage = imageDao.findRandomImage();
        assertThat(foundImage, not(nullValue()));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void givenMultipleImages_whenFindRandomImage_thenImagesReturnedRandomly() {
        imageDao.create(createImage());
        imageDao.create(createImage());
        imageDao.create(createImage());
        imageDao.create(createImage());
        imageDao.create(createImage());

        final Image returnedImage1 = imageDao.findRandomImage();
        final Image returnedImage2 = imageDao.findRandomImage();
        final Image returnedImage3 = imageDao.findRandomImage();

        assertThat(returnedImage1, not(allOf(equalTo(returnedImage2), equalTo(returnedImage3))));
    }

    private Image createImage() {
        final Image image = new Image();
        image.setUrl("test url");

        return image;
    }
}
