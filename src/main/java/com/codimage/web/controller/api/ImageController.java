package com.codimage.web.controller.api;

import com.codimage.image.Image;
import com.codimage.image.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Controller to support work with images.
 *
 * @author Ruslan Khmelyuk
 */
@Controller
@RequestMapping("/api/image")
public class ImageController {

    @Autowired
    private ImageService imageService;

    @RequestMapping(value = "/next", produces = "application/json")
    @ResponseBody
    public Image nextImage() {
        return imageService.getNextImage();
    }

    @RequestMapping(value = "/next", params = "id", produces = "application/json")
    @ResponseBody
    public Image nextImageAfter(@RequestParam("id") long id) {
        return imageService.getNextImage(id);
    }

    @RequestMapping(value = "/prev", params = "id", produces = "application/json")
    @ResponseBody
    public Image prevImageBefore(@RequestParam("id") long id) {
        return imageService.getPrevImage(id);
    }

    @RequestMapping(value = "/cache/reset", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void resetNextImageCache() {
        imageService.resetImageCache();
    }
}
