package com.thinkzi.album.domain.repository;

import io.reactivex.Single;

/**
 * provide network connection data repository interface
 * */
public interface INetworkConnectionRepository {

    /**
     * check internet connection status
     * */
    Single<Boolean> checkInternetConnection();

}
