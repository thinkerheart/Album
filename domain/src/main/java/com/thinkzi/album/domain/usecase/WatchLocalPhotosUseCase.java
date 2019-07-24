package com.thinkzi.album.domain.usecase;

import com.thinkzi.album.domain.entity.Photo;
import com.thinkzi.album.domain.executor.PostExecutionThread;
import com.thinkzi.album.domain.executor.ThreadExecutor;
import com.thinkzi.album.domain.repository.IPhotoRepository;
import com.thinkzi.album.domain.usecase.base.ObservableUseCase;
import com.thinkzi.album.domain.utility.check.Checker;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * provide UseCase(Clean Architecture) to watch photos from local database
 * */
public final class WatchLocalPhotosUseCase extends ObservableUseCase<Photo, WatchLocalPhotosUseCase.Ps> {

    private final IPhotoRepository _iPhotoRepository;

    @Inject
    public WatchLocalPhotosUseCase(ThreadExecutor _threadExecutor, PostExecutionThread _postExecutionThread, IPhotoRepository _iPhotoRepository) {
        super(_threadExecutor, _postExecutionThread);
        this._iPhotoRepository = _iPhotoRepository;
    }

    @Override
    protected Observable<Photo> buildObservableUseCase(Ps _params) {

        Checker.checkNotNull(_params);

        return _iPhotoRepository.getLocalPhotos(_params._orderByColumnName, _params._limit, _params._offset);
    }

    /**
     * provide parameters to get photos from local database
     * */
    public static final class Ps {

        // order by column name
        private final String _orderByColumnName;

        // amount of photo for each small query to local room database
        private final int _limit;

        // query position for each small query to local room database
        private final int _offset;

        private Ps(String _orderByColumnName, int _limit, int _offset) {

            this._orderByColumnName = _orderByColumnName;
            this._limit = _limit;
            this._offset = _offset;

        }

        public static Ps forPs(String _orderByColumnName, int _limit, int _offset) {

            return new Ps(_orderByColumnName, _limit, _offset);

        }

    }
}
