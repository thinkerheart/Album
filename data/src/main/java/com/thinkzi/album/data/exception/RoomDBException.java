package com.thinkzi.album.data.exception;

/**
 * provide Exception throw by the application when a there is a local room database exception.
 */
public class RoomDBException extends Exception {

    public RoomDBException() {
        super();
    }

    public RoomDBException(final Throwable _cause) {
        super(_cause);
    }

}
