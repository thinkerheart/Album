package com.thinkzi.album.data.mapper;

import com.thinkzi.album.data.model.PhotoDataModel;
import com.thinkzi.album.domain.entity.Photo;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * provide a mapper to map data between 2 layers data and domain
 * */
@Singleton
public class PhotoDataModelMapper {

    @Inject
    public PhotoDataModelMapper() {
    }

    /**
     * transform a PhotoDataModel object of data layer to a Photo object of domain layer
     * */
    public Photo transform(PhotoDataModel _photoDataModel) {
        if (_photoDataModel == null)
            throw new IllegalArgumentException("Cannot transform a null PhotoDataModel");

        final Photo _photo = new Photo();
        _photo.setAlbumId(_photoDataModel.getAlbumId());
        _photo.setId(_photoDataModel.getId());
        _photo.setTitle(_photoDataModel.getTitle());
        _photo.setUrl(_photoDataModel.getUrl());
        _photo.setThumbnailUrl(_photoDataModel.getThumbnailUrl());

        return _photo;
    }

    /**
     * transform a Photo object of domain layer to a PhotoDataModel object of data layer
     * */
    public PhotoDataModel transform(Photo _photo) {
        if (_photo == null)
            throw new IllegalArgumentException("Cannot transform a null Photo");

        final PhotoDataModel _photoDataModel = new PhotoDataModel();
        _photoDataModel.setAlbumId(_photo.getAlbumId());
        _photoDataModel.setId(_photo.getId());
        _photoDataModel.setTitle(_photo.getTitle());
        _photoDataModel.setUrl(_photo.getUrl());
        _photoDataModel.setThumbnailUrl(_photo.getThumbnailUrl());

        return _photoDataModel;
    }
}
