package com.thinkzi.album.ui.di;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import com.thinkzi.album.data.executor.JobExecutor;
import com.thinkzi.album.data.repository.PhotoRepository;
import com.thinkzi.album.device.repository.NetworkConnectionRepository;
import com.thinkzi.album.domain.executor.PostExecutionThread;
import com.thinkzi.album.domain.executor.ThreadExecutor;
import com.thinkzi.album.domain.repository.INetworkConnectionRepository;
import com.thinkzi.album.domain.repository.IPhotoRepository;
import com.thinkzi.album.ui.AlbumApplication;
import com.thinkzi.album.ui.UIThread;

/**
 * provide Dagger module that provides objects which will live during the application lifecycle.
 */
@Module
public class ApplicationModule {

    private final AlbumApplication _albumApplication;

    public ApplicationModule(AlbumApplication _albumApplication) {
        this._albumApplication = _albumApplication;
    }

    /**
     * provide a application context
     */
    @Provides
    @Singleton
    Context provideApplicationContext() {
        return this._albumApplication;
    }

    /**
     * provide a executor thread pool
     */
    @Provides
    @Singleton
    ThreadExecutor provideThreadExecutor(JobExecutor _jobExecutor) {
        return _jobExecutor;
    }

    /**
     * provide a thread created to change context execution
     */
    @Provides
    @Singleton
    PostExecutionThread providePostExecutionThread(UIThread _uiThread) {
        return _uiThread;
    }

    /**
     * provide a photo repository
     */
    @Provides
    @Singleton
    IPhotoRepository provideIPhotoRepository(PhotoRepository _photoRepository) {
        return _photoRepository;
    }

    /**
     * provide a network connection repository
     */
    @Provides
    @Singleton
    INetworkConnectionRepository provideINetworkConnectionRepository(NetworkConnectionRepository _networkConnectionRepository) {
        return _networkConnectionRepository;
    }

}
