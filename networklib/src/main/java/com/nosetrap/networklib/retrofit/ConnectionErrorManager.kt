package com.nosetrap.retrofitlib

import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

/**
 * class that deals with the different types of connection errors
 */
internal class ConnectionErrorManager(private val callback: ConnectionErrorCallback) {

    /**
     * process an Exception/throwable and execute the right callback methods
     * this method is used in the onError method in retrofit2 callback
     */
    fun handleError(error: Throwable) {
        if (error is ConnectException ||
                error is SocketTimeoutException ||
                error is UnknownHostException) {
            callback.onConnectionError()
        } else {
            callback.onRandomError()
        }
        //todo handle all other exceptions that could possibly be thrown
    }
}
