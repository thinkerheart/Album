package com.thinkzi.album.ui.mapper;

import com.thinkzi.album.domain.entity.Photo;
import com.thinkzi.album.ui.model.PhotoUIModel;

import javax.inject.Inject;

/**
 * provide a mapper to map data between 2 layers domain and app(ui)
 * */
public class PhotoUIModelMapper {

    @Inject
    public PhotoUIModelMapper() {
    }

    /**
     * transform a Photo object of domain layer to a PhotoUIModel object of app(ui) layer
     * */
    public PhotoUIModel transform(Photo _photo) {

        if (_photo == null)
            throw new IllegalArgumentException("Cannot transform a null Photo");

        PhotoUIModel _productUIModel = new PhotoUIModel();
        _productUIModel.setAlbumId(_photo.getAlbumId());
        _productUIModel.setId(_photo.getId());
        _productUIModel.setTitle(_photo.getTitle());
        _productUIModel.setUrl(_photo.getUrl());
        _productUIModel.setThumbnailUrl(_photo.getThumbnailUrl());

        return _productUIModel;

    }
}
