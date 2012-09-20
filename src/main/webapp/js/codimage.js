$(document).ready(function () {
    var nextImage = null;

    var imageDiv = $("#image");
    var showImage = function (image) {
        imageDiv.css("background", "url(" + image.url + ") no-repeat center center fixed");
    };
    var preloadNextImage = function(id) {
        Codimage.nextImage(id, function(image) {
            nextImage = image;
            new Image().src = nextImage.url;
        });
    };
    var showNextImage = function () {
        if (nextImage) {
            showImage(nextImage);
        }
        preloadNextImage(nextImage.id)
    };

    // load the first image, it should be random one
    Codimage.randomImage(function(image) {
        showImage(image);
        preloadNextImage(image.id);
    });

    // show next image every 5 seconds
    setInterval(showNextImage, 5000);
});

var Codimage = {
    /**
     * Get the random image.
     */
    randomImage:function (successCallback, errorCallback) {
        $.ajax({
            url:"/api/image/next",
            dataType:"json",
            success:successCallback,
            error:errorCallback
        });
    },

    /**
     * Gets the next image after the current image.
     * @param currentImageId the current image id.
     */
    nextImage:function (currentImageId, successCallback, errorCallback) {
        $.ajax({
            url:"/api/image/next",
            data:{id:currentImageId},
            dataType:"json",
            success:successCallback,
            error:errorCallback
        });
    },

    /**
     * Gets the previous image before the current image.
     * @param currentImageId the current image id.
     */
    prevImage:function (currentImageId, successCallback, errorCallback) {
        $.ajax({
            url:"/api/image/prev",
            data:{id:currentImageId},
            dataType:"json",
            success:successCallback,
            error:errorCallback
        });
    }

};