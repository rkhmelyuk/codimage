package com.codimage.web.controller.api;

import com.codimage.config.AppConfig;
import com.codimage.config.WebTestConfig;
import com.codimage.image.Image;
import com.codimage.image.ImageDao;
import com.codimage.test.FixtureFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

/**
 * Tests for {@link ImageController}.
 *
 * @author Ruslan Khmelyuk
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class, WebTestConfig.class}, loader = AnnotationConfigContextLoader.class)
public class ImageControllerTest {

    @Autowired private ImageDao imageDao;
    @Autowired private ImageController controller;

    @Before
    public void setUp() {
        imageDao.deleteAll();
    }

    @Test
    public void nextImageReturnsImageIfAny() {
        imageDao.create(FixtureFactory.createImage());

        assertThat(controller.nextImage(), not(nullValue()));
        assertThat(controller.nextImage(), not(nullValue()));
        assertThat(controller.nextImage(), not(nullValue()));
        assertThat(controller.nextImage(), not(nullValue()));
        assertThat(controller.nextImage(), not(nullValue()));
    }

    @Test
    public void nextImageReturnsCorrectImage() {
        final Image image1 = FixtureFactory.createImage();
        final Image image2 = FixtureFactory.createImage();
        final Image image3 = FixtureFactory.createImage();

        imageDao.create(image1);
        imageDao.create(image2);
        imageDao.create(image3);

        final Image found = controller.nextImageAfter(image3.getId());

        assertThat(found.getId(), is(image1.getId()));
    }

    @Test
    public void prevImageReturnsCorrectImage() {
        final Image image1 = FixtureFactory.createImage();
        final Image image2 = FixtureFactory.createImage();
        final Image image3 = FixtureFactory.createImage();

        imageDao.create(image1);
        imageDao.create(image2);
        imageDao.create(image3);

        final Image found = controller.prevImageBefore(image1.getId());

        assertThat(found.getId(), is(image3.getId()));
    }
}
