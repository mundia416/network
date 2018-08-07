package com.nosetrap.retrofitlib;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

/**
 * class that deals with the different types of connection errors
 */
public class ConnectionErrorManager {
    private ConnectionErrorCallback callback;

    public ConnectionErrorManager(ConnectionErrorCallback callback) {
        this.callback = callback;
    }

    /**
     * process an Exception/throwable and execute the right callback methods
     * this method is used in the onError method in retrofit2 callback
     */
    public void handleError(Throwable error) {
        if (error instanceof ConnectException || error instanceof SocketTimeoutException ||
                error instanceof UnknownHostException) {
            callback.onConnectionError();
        }else {
            callback.onRandomError();
        }
        //todo handle all other exceptions that could possibly be thrown
    }
}
