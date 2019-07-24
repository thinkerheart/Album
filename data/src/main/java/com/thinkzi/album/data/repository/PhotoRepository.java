package com.thinkzi.album.data.repository;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.thinkzi.album.data.database.AlbumApplicationDatabase;
import com.thinkzi.album.data.exception.NetworkConnectionException;
import com.thinkzi.album.data.exception.RoomDBException;
import com.thinkzi.album.data.mapper.PhotoDataModelMapper;
import com.thinkzi.album.data.model.PhotoDataModel;
import com.thinkzi.album.data.net.APIConnection;
import com.thinkzi.album.domain.entity.Photo;
import com.thinkzi.album.domain.exception.DefaultErrorBundle;
import com.thinkzi.album.domain.executor.PostExecutionThread;
import com.thinkzi.album.domain.executor.ThreadExecutor;
import com.thinkzi.album.domain.repository.IPhotoRepository;
import com.thinkzi.album.domain.usecase.base.CompletableObserver;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Completable;
import io.reactivex.CompletableEmitter;
import io.reactivex.CompletableOnSubscribe;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

/**
 * provide implementation for photo data repository
 * */
@Singleton
public class PhotoRepository implements IPhotoRepository {

    // mapper to map data between 2 layers data and domain
    private final PhotoDataModelMapper _photoDataModelMapper;

    // application conext
    private final Context _context;

    // executor thread pool to execute work
    private final ThreadExecutor _threadExecutor;

    // thread created to change the execution context
    private final PostExecutionThread _postExecutionThread;

    @Inject
    public PhotoRepository(PhotoDataModelMapper _photoDataModelMapper, Context _context, ThreadExecutor _threadExecutor, PostExecutionThread _postExecutionThread) {

        this._photoDataModelMapper = _photoDataModelMapper;
        this._context = _context;
        this._threadExecutor = _threadExecutor;
        this._postExecutionThread = _postExecutionThread;

    }

    @Override
    public Observable<Photo> getRemotePhotos() {

        // use Retrofit to JSON data as a stream (ResponseBody) and apply a Function in flatMapObservable to return a Observable<Photo> stream
        return APIConnection.getInstance().getPhotoStream().flatMapObservable(new Function<ResponseBody, Observable<Photo>>() {
            @Override
            public Observable<Photo> apply(final ResponseBody responseBody) throws Exception {

                // return Observable<Photo> stream
                return Observable.create(new ObservableOnSubscribe<Photo>() {
                    @Override
                    public void subscribe(ObservableEmitter<Photo> emitter) throws Exception {

                        try {

                            // use Gson stream to treat Json result. Idea: not put all json objects in memory
                            Gson _gson = new GsonBuilder().create();
                            JsonReader _reader = _gson.newJsonReader(responseBody.charStream());

                            // start reading Json data array
                            _reader.beginArray();

                            // while Json data array has next item
                            while (_reader.hasNext() && _reader.peek() != JsonToken.END_ARRAY) {

                                // read a photo data object from Json data
                                PhotoDataModel _photoDataModel = _gson.fromJson(_reader, PhotoDataModel.class);

                                // save this read photo data object to local database(Room)
                                savePhoto(_photoDataModelMapper.transform(_photoDataModel))
                                        .subscribeOn(Schedulers.from(_threadExecutor))
                                        .observeOn(_postExecutionThread.getScheduler()).subscribe(new SavePhotoObserver());

                                // send photo data object as a stream to observers
                                emitter.onNext(_photoDataModelMapper.transform(_photoDataModel));

                            }

                            // send work complete result to observers
                            emitter.onComplete();

                        } catch (Exception e) {

                            // send a error as result to observers
                            emitter.onError(new NetworkConnectionException(e.getCause()));

                        }

                    }
                });

            }
        });

    }

    @Override
    public Observable<Photo> getLocalPhotos(final String _orderByColumnName, final int _limit, final int _offset) {

        // use Room to get photos from local database and return a Observable<Photo> stream
        return Observable.create(new ObservableOnSubscribe<Photo>() {
            @Override
            public void subscribe(final ObservableEmitter<Photo> emitter) throws Exception {

                try {

                    // get all photos from local database
                    //List<PhotoDataModel> _photoDataModelList = AlbumApplicationDatabase.getInstance(_context).getPhotoDAO().getPhotoDataModelList();

                    // get amount of photo
                    int _amountOfPhoto = AlbumApplicationDatabase.getInstance(_context).getPhotoDAO().getAmountOfPhoto();

                    // execute many small queries to get all photos from room local database
                    // STARTING small query will get _limit photos from position _offset order by _orderByColumnName asc
                    for (int i = _offset; i < _amountOfPhoto; i = i + _limit) {

                        // get _limit photos from position _offset from local room database order by _orderByColumnName asc
                        List<PhotoDataModel> _photoDataModelListWithAmountOfLimit = AlbumApplicationDatabase.getInstance(_context).getPhotoDAO().getPhotoDataModelList(_orderByColumnName, _limit, i);

                        // after each small query result -> send result as stream to observers
                        for (PhotoDataModel _photoDataModel : _photoDataModelListWithAmountOfLimit) {

                            // send photo data object as a stream to observers
                            emitter.onNext(_photoDataModelMapper.transform(_photoDataModel));

                        }

                    }

                    // send work complete result to observers
                    emitter.onComplete();

                } catch (Exception e) {

                    // send a error as result to observers
                    emitter.onError(new RoomDBException(e.getCause()));

                }

            }
        });

    }

    @Override
    public Completable savePhoto(final Photo _photo) {

        // use Room to save data of a photo to local database and return result complete or error
        return Completable.create(new CompletableOnSubscribe() {
            @Override
            public void subscribe(CompletableEmitter emitter) throws Exception {

                try {

                    // save photo to local database, if exist -> replace it
                    AlbumApplicationDatabase.getInstance(_context).getPhotoDAO().addPhotoDataModel(_photoDataModelMapper.transform(_photo));

                    // send work complete result to observers
                    emitter.onComplete();

                } catch (Exception e) {

                    // send a error as result to observers
                    emitter.onError(new RoomDBException(e.getCause()));

                }

            }
        });

    }

    private void handleError(DefaultErrorBundle _defaultErrorBundle) {

        //write log

    }

    /**
     * provide a observer for result of the photo saving to local database
     * */
    private final class SavePhotoObserver extends CompletableObserver {

        @Override
        public void onComplete() {
            this.dispose();
        }

        @Override
        public void onError(Throwable e) {
            handleError(new DefaultErrorBundle((Exception) e));
        }

    }

}
