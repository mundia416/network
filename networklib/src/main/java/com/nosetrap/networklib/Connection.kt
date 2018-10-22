package com.nosetrap.networklib

import android.Manifest
import android.content.Context
import android.net.ConnectivityManager
import androidx.annotation.RequiresPermission

class Connection(context: Context) {

    private val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    /**
     * checks whether there is a connection to the internet, this can be of any type as long as
     * the device is connected to the internet

    make sure the permission ACCESS_NETWORK_STATE is added to the manifest
     */
    @RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
    fun isInternetConnected(): Boolean {
        val networkInfo = connectivityManager.activeNetworkInfo
         if (networkInfo != null){
             if(networkInfo.isConnected){
                 return  true
             }
         }
        return  false
    }
}