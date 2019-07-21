package com.thinkzi.album.domain.entity;

/**
* provide Photo Entity for saving photo data of domain layer (business logic layer) in Clean Architecture
* */
public class Photo {

    private int _albumId;
    private int _id;
    private String _title;
    private String _url;
    private String _thumbnailUrl;

    public Photo() {
        this._albumId = 0;
        this._id = 0;
        this._title = "";
        this._url = "";
        this._thumbnailUrl = "";
    }

    public int getAlbumId() {
        return _albumId;
    }

    public void setAlbumId(int _albumId) {
        this._albumId = _albumId;
    }

    public int getId() {
        return _id;
    }

    public void setId(int _id) {
        this._id = _id;
    }

    public String getTitle() {
        return _title;
    }

    public void setTitle(String _title) {
        this._title = _title;
    }

    public String getUrl() {
        return _url;
    }

    public void setUrl(String _url) {
        this._url = _url;
    }

    public String getThumbnailUrl() {
        return _thumbnailUrl;
    }

    public void setThumbnailUrl(String _thumbnailUrl) {
        this._thumbnailUrl = _thumbnailUrl;
    }
}
