package com.thinkzi.album.domain.repository;

import com.thinkzi.album.domain.entity.Photo;

import io.reactivex.Completable;
import io.reactivex.Observable;

/**
 * provide photo data repository interface
 * */
public interface IPhotoRepository {

    /**
     * get photo data from server
     * */
    Observable<Photo> getRemotePhotos();

    /**
     * get photo data from local database
     * */
    Observable<Photo> getLocalPhotos();

    /**
     * save data of a photo to local database
     * */
    Completable savePhoto(Photo _photo);

}
