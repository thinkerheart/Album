package com.thinkzi.album.domain.exception_test;

import com.thinkzi.album.domain.exception.DefaultErrorBundle;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.verify;

/**
 * provide DefaultErrorBundleTest for testing DefaultErrorBundle class
 * */
@RunWith(MockitoJUnitRunner.class)
public class DefaultErrorBundleTest {

    //DefaultErrorBundle object instance for test
    private DefaultErrorBundle _defaultErrorBundle;

    //mocked exception object for test
    @Mock
    private Exception _mockException;

    /**
     * prepare things for testing
     * */
    @Before
    public void setUp() {
        _defaultErrorBundle = new DefaultErrorBundle(_mockException);
    }

    /**
     * test case : constructor of Photo object with one argument is mocked exception object
     * */
    @Test
    public void constructorTestCase() {
        assertSame(_defaultErrorBundle.getException(), _mockException);
    }

    /**
     * test case : get exception of _defaultErrorBundle
     * */
    @Test
    public void getExceptionTestCase() {
        assertSame(_defaultErrorBundle.getException(), _mockException);
    }

    /**
     * test case : get exception's error message of _defaultErrorBundle
     * */
    @Test
    public void getErrorMessageTestCase() {
        _defaultErrorBundle.getErrorMessage();

        //Mockito Verify methods are used to check that certain behavior happened
        //Mockito verify() methods can be used to make sure the mock object methods are being called.
        //verify that the getMessage() was called only once on the _mockException object
        verify(_mockException).getMessage();
    }

}
