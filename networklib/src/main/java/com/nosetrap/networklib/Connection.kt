package com.nosetrap.networklib

import android.Manifest
import android.content.Context
import android.net.ConnectivityManager
import androidx.annotation.RequiresPermission
import android.util.Patterns
import android.net.wifi.WifiManager
import android.text.format.Formatter
import java.net.InetAddress
import java.util.regex.Pattern
import android.telephony.TelephonyManager
import android.net.NetworkInfo




class Connection(context: Context) {

    companion object {

        /**
         * check if the url pattern is valid
         */
        fun isValidURL(urlStr: String): Boolean {
            return Patterns.WEB_URL.matcher(urlStr).matches()
        }

        /**
         *         * check if the format of the email address is valid
         */
        fun isValidEmail(email: String): Boolean {
            return Patterns.EMAIL_ADDRESS.matcher(email).matches()
        }

        /**
         * check if the format of the ip address is valid
         */
        fun isValidIP(ip: String): Boolean {
            return Patterns.IP_ADDRESS.matcher(ip).matches()
        }
    }


    private val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager



    /**
     * checks whether there is a connection to the internet, this can be of any type as long as
     * the device is connected to the internet

    make sure the permission ACCESS_NETWORK_STATE is added to the manifest
     */
    @RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
    fun isInternetConnected(): Boolean {
        val networkInfo = connectivityManager.activeNetworkInfo
        if (networkInfo != null) {
            if (networkInfo.isConnected) {
                return true
            }
        }
        return false
    }
}