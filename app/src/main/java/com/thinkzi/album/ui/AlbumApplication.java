package com.thinkzi.album.ui;

import android.app.Application;

import com.thinkzi.album.ui.di.ApplicationComponent;
import com.thinkzi.album.ui.di.ApplicationModule;
import com.thinkzi.album.ui.di.DaggerApplicationComponent;

/**
 * provide AlbumApplication object
 * */
public class AlbumApplication extends Application {

    private ApplicationComponent _applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        this.initializeInjector();
    }

    /**
     * initialize injector
     * */
    private void initializeInjector() {
        this._applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    /**
     * provide ApplicationComponent
     * */
    public ApplicationComponent getApplicationComponent() {
        return this._applicationComponent;
    }

}
