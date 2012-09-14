$(document).ready(function () {
    var nextImage = null;
    var imageTmpl = $("#imageTmpl");
    var imageHolder = $("#image");
    var showImage = function(src) {
        imageHolder.html(imageTmpl.html().replace("{src}", src))
    };
    var refreshImage = function () {
        if (nextImage) {
            showImage(nextImage.url);
        }
        else {
            $.getJSON("/api/image/next", function (json) {
                showImage(json.url);
            });
        }
        $.getJSON("/api/image/next", function (json) {
            nextImage = json;
            new Image().src = nextImage.url;
        });
    };

    setInterval(refreshImage, 5000);
    refreshImage();
});