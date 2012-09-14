package com.codimage.image;

import com.khmelyuk.core.ToStringBuilder;

import java.io.Serializable;
import java.util.Objects;

/**
 * Represents a single image.
 *
 * We shouldn't contain image locally, but prefer external services, like flickr or S3.
 * That's why we use only URL to identify image location.
 *
 * @author Ruslan Khmelyuk
 */
public class Image implements Serializable {

    private Long id;

    /**
     * The image url.
     */
    private String url;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override public String toString() {
        return new ToStringBuilder(Image.class)
                .field("id", id)
                .field("url", url)
                .toString();
    }

    @Override public boolean equals(Object obj) {
        if (obj == this) return true;
        if (!(obj instanceof Image)) return false;

        final Image other = (Image) obj;
        return Objects.equals(id, other.id) && Objects.equals(url, other.url);
    }
}
