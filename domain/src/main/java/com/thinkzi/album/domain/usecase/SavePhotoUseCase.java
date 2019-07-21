package com.thinkzi.album.domain.usecase;

import com.thinkzi.album.domain.entity.Photo;
import com.thinkzi.album.domain.executor.PostExecutionThread;
import com.thinkzi.album.domain.executor.ThreadExecutor;
import com.thinkzi.album.domain.repository.IPhotoRepository;
import com.thinkzi.album.domain.usecase.base.CompletableUseCase;
import com.thinkzi.album.domain.utility.check.Checker;

import javax.inject.Inject;

import io.reactivex.Completable;

/**
 * provide UseCase(Clean Architecture) to save data of a photo to local database
 * */
public final class SavePhotoUseCase extends CompletableUseCase<Photo> {

    private final IPhotoRepository _iPhotoRepository;

    @Inject
    public SavePhotoUseCase(ThreadExecutor _threadExecutor, PostExecutionThread _postExecutionThread, IPhotoRepository _iPhotoRepository) {
        super(_threadExecutor, _postExecutionThread);
        this._iPhotoRepository = _iPhotoRepository;
    }

    @Override
    protected Completable buildCompletableUseCase(Photo _params) {

        Checker.checkNotNull(_params);

        return _iPhotoRepository.savePhoto(_params);

    }
}
