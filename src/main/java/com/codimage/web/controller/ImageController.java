package com.codimage.web.controller;

import com.codimage.image.Image;
import com.codimage.image.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Controller to support work with images.
 *
 * @author Ruslan Khmelyuk
 */
@Controller
public class ImageController {

    @Autowired
    private ImageService imageService;

    @RequestMapping(value = "/image/next", produces = "application/json")
    @ResponseBody
    public Image nextImage() {
        return imageService.getNextImage();
    }
}
