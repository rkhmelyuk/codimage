$(document).ready(function () {
    var nextImage = null;
    //var imageTmpl = $("#imageTmpl");
    var imageHolder = $("#image");
    var showImage = function(src) {
        //imageHolder.html(imageTmpl.html().replace("{src}", src))
        imageHolder.css("background", "url(" + src + ") no-repeat");
    };
    var refreshImage = function () {
        if (nextImage) {
            showImage(nextImage.url);
        }
        $.getJSON("/api/image/next?id=" + nextImage.id, function (json) {
            nextImage = json;
            new Image().src = nextImage.url;
        });
    };
    var loadFirstImage = function() {
        $.getJSON("/api/image/next", function (json) {
            showImage(json.url);
            $.getJSON("/api/image/next?id=" + json.id, function (json) {
                nextImage = json;
                new Image().src = nextImage.url;
            });
        });
    };

    setInterval(refreshImage, 5000);

    loadFirstImage();
});