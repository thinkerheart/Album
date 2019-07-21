package com.thinkzi.album.device.repository;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.thinkzi.album.device.exception.NetworkConnectionException;
import com.thinkzi.album.domain.repository.INetworkConnectionRepository;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleOnSubscribe;

/**
 * provide implementation for network connection data repository
 * */
@Singleton
public class NetworkConnectionRepository implements INetworkConnectionRepository {

    // application context
    private final Context _context;

    @Inject
    public NetworkConnectionRepository(Context _context) {
        this._context = _context;
    }

    @Override
    public Single<Boolean> checkInternetConnection() {

        return Single.create(new SingleOnSubscribe<Boolean>() {
            @Override
            public void subscribe(SingleEmitter<Boolean> emitter) throws Exception {

                try {

                    boolean _isConnected;

                    ConnectivityManager _connectivityManager = (ConnectivityManager) _context.getSystemService(Context.CONNECTIVITY_SERVICE);
                    NetworkInfo _networkInfo = _connectivityManager.getActiveNetworkInfo();
                    _isConnected = (_networkInfo != null && _networkInfo.isConnectedOrConnecting());

                    // send network connection status to observers
                    emitter.onSuccess(_isConnected);

                } catch (Exception e) {

                    // send a error as result to observers
                    emitter.onError(new NetworkConnectionException(e.getCause()));
                }

            }
        });

    }
}
