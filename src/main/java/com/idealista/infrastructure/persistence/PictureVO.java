package com.idealista.infrastructure.persistence;

import java.util.Objects;

public class PictureVO {

    private Integer id;
    private String url;
    private String quality;



    public PictureVO() {}

    public PictureVO(Integer id, String url, String quality) {
        this.id = id;
        this.url = url;
        this.quality = quality;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PictureVO pictureVO = (PictureVO) o;
        return id.equals(pictureVO.id) &&
                url.equals(pictureVO.url) &&
                quality.equals(pictureVO.quality);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, url, quality);
    }
}
