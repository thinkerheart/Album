package com.thinkzi.album.domain.usecase.base;

import com.thinkzi.album.domain.executor.PostExecutionThread;
import com.thinkzi.album.domain.executor.ThreadExecutor;
import com.thinkzi.album.domain.utility.check.Checker;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * provide common abstract execution of all reactive asynchronous UseCase(Clean Architecture)
 * */
public abstract class AsynchronousRxUseCase {

    // executor thread pool to execute work
    final ThreadExecutor _threadExecutor;

    // thread created to change the execution context
    final PostExecutionThread _postExecutionThread;

    // observer set for work result
    private final CompositeDisposable _compositeDisposables;

    protected AsynchronousRxUseCase(ThreadExecutor _threadExecutor, PostExecutionThread _postExecutionThread) {

        this._threadExecutor = _threadExecutor;
        this._postExecutionThread = _postExecutionThread;
        this._compositeDisposables = new CompositeDisposable();

    }

    /**
     * dispose all observers
     * */
    public void dispose() {

        if (!_compositeDisposables.isDisposed()) {

            _compositeDisposables.dispose();

        }

    }

    /**
     * add a observer
     * */
    public void addDisposable(Disposable _disposable) {

        Checker.checkNotNull(_disposable);
        Checker.checkNotNull(_compositeDisposables);

        _compositeDisposables.add(_disposable);

    }

    public ThreadExecutor getThreadExecutor() {
        return _threadExecutor;
    }

    public PostExecutionThread getPostExecutionThread() {
        return _postExecutionThread;
    }

    public CompositeDisposable getCompositeDisposables() {
        return _compositeDisposables;
    }
}
