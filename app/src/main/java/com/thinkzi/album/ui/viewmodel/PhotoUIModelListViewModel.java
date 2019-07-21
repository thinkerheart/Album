package com.thinkzi.album.ui.viewmodel;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;

import com.thinkzi.album.domain.entity.Photo;
import com.thinkzi.album.domain.exception.DefaultErrorBundle;
import com.thinkzi.album.domain.usecase.CheckInternetConnectionUseCase;
import com.thinkzi.album.domain.usecase.WatchLocalPhotosUseCase;
import com.thinkzi.album.domain.usecase.WatchRemotePhotosUseCase;
import com.thinkzi.album.domain.usecase.base.ObservableObserver;
import com.thinkzi.album.domain.usecase.base.SingleObserver;
import com.thinkzi.album.ui.R;
import com.thinkzi.album.ui.mapper.PhotoUIModelMapper;
import com.thinkzi.album.ui.model.PhotoUIModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * provide PhotoUIModelListViewModel for operations on PhotoUIModelListActivity: save data of View(Activity) for configuration change management,
 * treat actions from View, data binding, live data, observer result sent from UseCase ...
 * */
@Singleton
public class PhotoUIModelListViewModel extends BaseViewModel {

    // UseCase(Clean Architecture) to watch photos from server
    private final WatchRemotePhotosUseCase _watchRemotePhotosUseCase;

    // UseCase(Clean Architecture) to watch photos from local database
    private final WatchLocalPhotosUseCase _watchLocalPhotosUseCase;

    // UseCase(Clean Architecture) to check internet connection status
    private final CheckInternetConnectionUseCase _checkInternetConnectionUseCase;

    // a mapper to map data between 2 layers domain and app(ui)
    private final PhotoUIModelMapper _photoUIModelMapper;

    // a live data list of PhotoUIModel
    private final MutableLiveData<List<PhotoUIModel>> _photoUIModelListMutableLiveData;

    // a live data string of operation mode
    private final MutableLiveData<String> _operationMode;

    // application context
    private final Context _context;

    @Inject
    public PhotoUIModelListViewModel(WatchRemotePhotosUseCase _watchRemotePhotosUseCase, PhotoUIModelMapper _photoUIModelMapper,
                                     WatchLocalPhotosUseCase _watchLocalPhotosUseCase,
                                     CheckInternetConnectionUseCase _checkInternetConnectionUseCase, Context _context) {
        super();
        this._watchRemotePhotosUseCase = _watchRemotePhotosUseCase;
        this._checkInternetConnectionUseCase = _checkInternetConnectionUseCase;
        this._watchLocalPhotosUseCase = _watchLocalPhotosUseCase;
        this._photoUIModelMapper = _photoUIModelMapper;
        this._photoUIModelListMutableLiveData = new MutableLiveData<>();
        this._photoUIModelListMutableLiveData.setValue(new ArrayList<PhotoUIModel>());
        this._operationMode = new MutableLiveData<>();
        this._context = _context;

        loadPhotoList();
    }

    public MutableLiveData<List<PhotoUIModel>> getPhotoUIModelListMutableLiveData() {
        return _photoUIModelListMutableLiveData;
    }

    public MutableLiveData<String> getOperationModeMutableLiveData() {
        return _operationMode;
    }

    /**
     * if there are internet connection -> load photos from server unless load photos from local database
     * */
    private void loadPhotoList() {
        this._checkInternetConnectionUseCase.execute(new CheckInternetConnectionObserver(), null);
    }

    /**
     * handle received photo from load photo UseCase
     * */
    private void handlePhoto(Photo _photo) {

        List<PhotoUIModel> _photoUIModelList = _photoUIModelListMutableLiveData.getValue();
        _photoUIModelList.add(_photoUIModelMapper.transform(_photo));
        _photoUIModelListMutableLiveData.setValue(_photoUIModelList);

    }

    /**
     * handle received network status from check connection UseCase
     * */
    private void handleOperationMode(Boolean _isConnected) {

        if (_isConnected) {
            _operationMode.setValue(_context.getResources().getString(R.string.mode_operation_online));
            _watchRemotePhotosUseCase.execute(new WatchRemotePhotosObserver(), null);
        } else {
            _operationMode.setValue(_context.getResources().getString(R.string.mode_operation_offline));
            _watchLocalPhotosUseCase.execute(new WatchLocalPhotosObserver(), null);
        }

    }

    /**
     * provide a observer to treat photo sent from loading photos data from server UseCase
     * */
    private final class WatchRemotePhotosObserver extends ObservableObserver<Photo> {

        @Override
        public void onNext(Photo _photo) {
            handlePhoto(_photo);
        }

        @Override
        public void onError(Throwable e) {
            handleError(new DefaultErrorBundle((Exception) e));
        }

        @Override
        public void onComplete() {
            _watchRemotePhotosUseCase.dispose();
        }

    }

    /**
     * provide a observer to treat photo sent from loading photos data from local database UseCase
     * */
    private final class WatchLocalPhotosObserver extends ObservableObserver<Photo> {

        @Override
        public void onNext(Photo _photo) {
            handlePhoto(_photo);
        }

        @Override
        public void onError(Throwable e) {
            handleError(new DefaultErrorBundle((Exception) e));
        }

        @Override
        public void onComplete() {
            _watchLocalPhotosUseCase.dispose();
        }

    }

    /**
     * provide a observer to treat connection status sent from check internet connection UseCase
     * */
    private final class CheckInternetConnectionObserver extends SingleObserver<Boolean> {

        @Override
        public void onSuccess(Boolean _isConnected) {

            _checkInternetConnectionUseCase.dispose();

            handleOperationMode(_isConnected);

        }

        @Override
        public void onError(Throwable e) {
            handleError(new DefaultErrorBundle((Exception) e));

            handleOperationMode(false);
        }

    }
}
