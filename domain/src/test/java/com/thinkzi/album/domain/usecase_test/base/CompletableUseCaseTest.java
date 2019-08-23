package com.thinkzi.album.domain.usecase_test.base;

import com.thinkzi.album.domain.executor.PostExecutionThread;
import com.thinkzi.album.domain.executor.ThreadExecutor;
import com.thinkzi.album.domain.usecase.base.CompletableObserver;
import com.thinkzi.album.domain.usecase.base.CompletableUseCase;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.given;

import io.reactivex.Completable;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.schedulers.TestScheduler;

@RunWith(MockitoJUnitRunner.class)
public class CompletableUseCaseTest {

    private CompletableUseCaseTestClass _completableUseCase;

    private CompletableObserverTestClass _completableObserver;

    @Mock
    ThreadExecutor _mockThreadExecutor;

    @Mock
    PostExecutionThread _mockPostExecutionThread;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void setUp() {
        this._completableUseCase = new CompletableUseCaseTestClass(_mockThreadExecutor, _mockPostExecutionThread);
        this._completableObserver = new CompletableObserverTestClass();
        given(_mockPostExecutionThread.getScheduler()).willReturn(new TestScheduler());
    }

    @Test
    public void constructorTestCase() {
        assertSame(this._completableUseCase.getThreadExecutor(), _mockThreadExecutor);
        assertSame(this._completableUseCase.getPostExecutionThread(), _mockPostExecutionThread);
        assertNotNull(this._completableUseCase.getCompositeDisposables());
    }

    @Test
    public void buildCompletableUseCaseTestCase() {
        this._completableUseCase.execute(this._completableObserver, Ps.EMPTY);
        assertTrue(!this._completableObserver._isCompleted);
    }

    @Test
    public void executeWithNotNullObserverTestCase() {
        this._completableUseCase.execute(this._completableObserver, Ps.EMPTY);
        assertFalse(this._completableUseCase.getCompositeDisposables().isDisposed());
        assertEquals(1, this._completableUseCase.getCompositeDisposables().size());

        this._completableUseCase.dispose();
        assertTrue(this._completableUseCase.getCompositeDisposables().isDisposed());
        assertEquals(0, this._completableUseCase.getCompositeDisposables().size());
    }

    @Test
    public void executeWithNullObserverTestCase() {
        expectedException.expect(NullPointerException.class);
        this._completableUseCase.execute(null, Ps.EMPTY);
    }

    private static class CompletableUseCaseTestClass extends CompletableUseCase<Ps> {

        CompletableUseCaseTestClass(ThreadExecutor _threadExecutor, PostExecutionThread _postExecutionThread) {
            super(_threadExecutor, _postExecutionThread);
        }

        @Override
        protected Completable buildCompletableUseCase(Ps _params) {
            return Completable.complete();
        }

        @Override
        public void execute(DisposableCompletableObserver _observer, Ps _params) {
            super.execute(_observer, _params);
        }

    }

    private static class CompletableObserverTestClass extends CompletableObserver {

        private boolean _isCompleted = false;

        @Override
        public void onComplete() {
            _isCompleted = true;
        }

        @Override
        public void onError(Throwable e) {
            _isCompleted = false;
        }
    }

    private static class Ps {
        private static Ps EMPTY = new Ps();
        private Ps() {}
    }

}
