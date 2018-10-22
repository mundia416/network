package com.nosetrap.retrofitlib


abstract open class BaseRequest(private val callback: BaseCallback) {

    private val conErrorManager = ConnectionErrorManager(callback)

    /**
     * method that should be called in retrofit2.Callback's on failure method
     */
    protected fun retrofitResponseOnFailure(t: Throwable) {
        callback.onPostExecute()
        conErrorManager.handleError(t)

    }
}