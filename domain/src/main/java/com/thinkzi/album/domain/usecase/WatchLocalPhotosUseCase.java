package com.thinkzi.album.domain.usecase;

import com.thinkzi.album.domain.entity.Photo;
import com.thinkzi.album.domain.executor.PostExecutionThread;
import com.thinkzi.album.domain.executor.ThreadExecutor;
import com.thinkzi.album.domain.repository.IPhotoRepository;
import com.thinkzi.album.domain.usecase.base.ObservableUseCase;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * provide UseCase(Clean Architecture) to watch photos from local database
 * */
public final class WatchLocalPhotosUseCase extends ObservableUseCase<Photo, Void> {

    private final IPhotoRepository _iPhotoRepository;

    @Inject
    public WatchLocalPhotosUseCase(ThreadExecutor _threadExecutor, PostExecutionThread _postExecutionThread, IPhotoRepository _iPhotoRepository) {
        super(_threadExecutor, _postExecutionThread);
        this._iPhotoRepository = _iPhotoRepository;
    }

    @Override
    protected Observable<Photo> buildObservableUseCase(Void _params) {
        return _iPhotoRepository.getLocalPhotos();
    }
}
