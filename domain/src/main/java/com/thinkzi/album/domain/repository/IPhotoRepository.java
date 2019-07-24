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
     * get _limit photos from position _offset from room database order by _orderByColumnName asc
     * */
    Observable<Photo> getLocalPhotos(String _orderByColumnName, int _limit, int _offset);

    /**
     * save data of a photo to local database
     * */
    Completable savePhoto(Photo _photo);

}
