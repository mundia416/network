package com.nosetrap.retrofitlib

    /**
     * a base callback interface which should be implemented by the callback interfaces of the request
     * classes.
     * this interface contains methods that are common to all server request callback interfaces
     */
    interface BaseCallback : ConnectionErrorCallback {


        /**
         * should be called before a request executes
         */
        fun onPreExecute()

        /**
         * should be called after a request executes
         */
        fun onPostExecute()
    }