package com.thinkzi.album.domain.usecase;

import com.thinkzi.album.domain.executor.PostExecutionThread;
import com.thinkzi.album.domain.executor.ThreadExecutor;
import com.thinkzi.album.domain.repository.INetworkConnectionRepository;
import com.thinkzi.album.domain.usecase.base.SingleUseCase;

import javax.inject.Inject;

import io.reactivex.Single;

/**
 * provide UseCase(Clean Architecture) to check internet connection
 * */
public final class CheckInternetConnectionUseCase extends SingleUseCase<Boolean, Void> {

    private final INetworkConnectionRepository _iNetworkConnectionRepository;

    @Inject
    public CheckInternetConnectionUseCase(ThreadExecutor _threadExecutor, PostExecutionThread _postExecutionThread, INetworkConnectionRepository _iNetworkConnectionRepository) {
        super(_threadExecutor, _postExecutionThread);
        this._iNetworkConnectionRepository = _iNetworkConnectionRepository;
    }

    @Override
    protected Single<Boolean> buildSingleUseCase(Void _params) {
        return _iNetworkConnectionRepository.checkInternetConnection();
    }
}
