package com.thinkzi.album.ui.di;

import javax.inject.Singleton;

import dagger.Component;
import com.thinkzi.album.ui.AlbumApplication;
import com.thinkzi.album.ui.view.activity.BaseActivity;
import com.thinkzi.album.ui.view.activity.PhotoUIModelListActivity;

/**
 * provide a component whose lifetime is the life of the application.
 */
@Singleton // Constraints this component to one-per-application or unscoped bindings.
@Component(modules = {ApplicationModule.class, ViewModelModule.class})
public interface ApplicationComponent {

    /**
     * inject dependency for AlbumApplication
     */
    void inject(AlbumApplication _albumApplication);

    /**
     * inject dependency for BaseActivity
     */
    void inject(BaseActivity _baseActivity);

    /**
     * inject dependency for PhotoUIModelListActivity
     */
    void inject(PhotoUIModelListActivity _photoUIModelListActivity);

}
