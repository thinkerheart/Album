package com.thinkzi.album.domain.usecase_test.base;

import com.thinkzi.album.domain.executor.PostExecutionThread;
import com.thinkzi.album.domain.executor.ThreadExecutor;
import com.thinkzi.album.domain.usecase.base.AsynchronousRxUseCase;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import io.reactivex.observers.DisposableObserver;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class AsynchronousRxUseCaseTest {

    private AsynchronousRxUseCaseTestClass _asynchronousRxUseCase;

    private DisposableObserverTestClass<Object> _disposableObserver;

    @Mock
    ThreadExecutor _mockThreadExecutor;

    @Mock
    PostExecutionThread _mockPostExecutionThread;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void setUp() {
        this._asynchronousRxUseCase = new AsynchronousRxUseCaseTestClass(_mockThreadExecutor, _mockPostExecutionThread);
        this._disposableObserver = new DisposableObserverTestClass<>();
    }

    @Test
    public void constructorTestCase() {
        assertSame(this._asynchronousRxUseCase.getThreadExecutor(), _mockThreadExecutor);
        assertSame(this._asynchronousRxUseCase.getPostExecutionThread(), _mockPostExecutionThread);
        assertNotNull(this._asynchronousRxUseCase.getCompositeDisposables());
    }

    @Test
    public void disposeTestCase() {
        this._asynchronousRxUseCase.dispose();
        assertTrue(this._asynchronousRxUseCase.getCompositeDisposables().isDisposed());
    }

    @Test
    public void addDisposableNullTestCase() {
        expectedException.expect(NullPointerException.class);
        this._asynchronousRxUseCase.addDisposable(null);
    }

    @Test
    public void addDisposableNotNullTestCase() {
        this._asynchronousRxUseCase.addDisposable(_disposableObserver);
        assertEquals(1, this._asynchronousRxUseCase.getCompositeDisposables().size());
    }

    private static class AsynchronousRxUseCaseTestClass extends AsynchronousRxUseCase {

        AsynchronousRxUseCaseTestClass(ThreadExecutor _threadExecutor, PostExecutionThread _postExecutionThread) {
            super(_threadExecutor, _postExecutionThread);
        }

    }

    private static class DisposableObserverTestClass<T> extends DisposableObserver<T> {

        @Override public void onNext(T value) {
            // no-op by default.
        }

        @Override public void onError(Throwable e) {
            // no-op by default.
        }

        @Override public void onComplete() {
            // no-op by default.
        }
    }

}
