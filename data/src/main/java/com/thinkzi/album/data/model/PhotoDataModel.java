package com.thinkzi.album.data.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * provide PhotoDataModel for saving photo data of data layer in Clean Architecture
 * */
@Entity(tableName = "Photos")
public class PhotoDataModel {

    @SerializedName("albumId")
    @Expose
    @ColumnInfo(name = "albumId")
    private int _albumId;

    @SerializedName("id")
    @Expose
    @PrimaryKey
    @ColumnInfo(name = "id")
    @NonNull
    private int _id;

    @SerializedName("title")
    @Expose
    @ColumnInfo(name = "title")
    private String _title;

    @SerializedName("url")
    @Expose
    @ColumnInfo(name = "url")
    private String _url;

    @SerializedName("thumbnailUrl")
    @Expose
    @ColumnInfo(name = "thumbnailUrl")
    private String _thumbnailUrl;

    @Ignore
    public PhotoDataModel() {
        this._albumId = 0;
        this._id = 0;
        this._title = "";
        this._url = "";
        this._thumbnailUrl = "";
    }

    public PhotoDataModel(int _albumId, @NonNull int _id, String _title, String _url, String _thumbnailUrl) {
        this._albumId = _albumId;
        this._id = _id;
        this._title = _title;
        this._url = _url;
        this._thumbnailUrl = _thumbnailUrl;
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
