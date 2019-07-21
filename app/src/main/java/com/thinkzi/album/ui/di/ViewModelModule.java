package com.thinkzi.album.ui.di;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.thinkzi.album.ui.viewmodel.PhotoUIModelListViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

/**
 * Allow us to inject dependencies via constructor injection
 */
@Module
public abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(PhotoUIModelListViewModel.class)
    abstract ViewModel bindsPhotoUIModelListViewModel(PhotoUIModelListViewModel _photoUIModelListViewModel);

    @Binds
    abstract ViewModelProvider.Factory bindsViewModelFactory(ViewModelFactory _viewModelFactory);
}
