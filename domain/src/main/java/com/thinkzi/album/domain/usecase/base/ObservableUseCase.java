package com.thinkzi.album.domain.usecase.base;

import com.thinkzi.album.domain.executor.PostExecutionThread;
import com.thinkzi.album.domain.executor.ThreadExecutor;
import com.thinkzi.album.domain.utility.check.Checker;
import io.reactivex.Observable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * provide common abstract execution of all reactive asynchronous UseCase(Clean Architecture) that have only results (Rs object or complete or error)
 * */
public abstract class ObservableUseCase<Rs, Ps> extends AsynchronousRxUseCase {

    protected ObservableUseCase(ThreadExecutor _threadExecutor, PostExecutionThread _postExecutionThread) {

        super(_threadExecutor, _postExecutionThread);

    }

    /**
     * build work execution of UseCase
     * */
    protected abstract Observable<Rs> buildObservableUseCase(Ps _params);

    /**
     * add observer for result and execute UseCase with input parameter
     * */
    public void execute(DisposableObserver<Rs> _observer, Ps _params) {

        Checker.checkNotNull(_observer);

        final Observable<Rs> _observable = this.buildObservableUseCase(_params)
                .subscribeOn(Schedulers.from(_threadExecutor))
                .observeOn(_postExecutionThread.getScheduler());

        addDisposable(_observable.subscribeWith(_observer));

    }

}
