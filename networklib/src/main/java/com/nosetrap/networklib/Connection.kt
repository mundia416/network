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
import android.content.Intent
import androidx.core.content.ContextCompat.startActivity






class Connection(private val context: Context) {

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
    private val telephonyManager = context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager

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

    /**
     * checks if the internet connection is a fast internet connection
     */
    fun isFastNetwork(): Boolean {


        return when (telephonyManager.networkType) {
            TelephonyManager.NETWORK_TYPE_EVDO_0, TelephonyManager.NETWORK_TYPE_EVDO_A,
            TelephonyManager.NETWORK_TYPE_HSDPA, TelephonyManager.NETWORK_TYPE_HSPA,
            TelephonyManager.NETWORK_TYPE_HSUPA, TelephonyManager.NETWORK_TYPE_UMTS,
            TelephonyManager.NETWORK_TYPE_EHRPD, TelephonyManager.NETWORK_TYPE_EVDO_B,
            TelephonyManager.NETWORK_TYPE_HSPAP, TelephonyManager.NETWORK_TYPE_LTE -> {
                true
            }
            TelephonyManager.NETWORK_TYPE_1xRTT, TelephonyManager.NETWORK_TYPE_CDMA,
            TelephonyManager.NETWORK_TYPE_EDGE, TelephonyManager.NETWORK_TYPE_GPRS,
            TelephonyManager.NETWORK_TYPE_IDEN, TelephonyManager.NETWORK_TYPE_UNKNOWN -> {
                false
            }
            else -> {
                false
            }
        }
    }

    /**
     * Open the settings of wireless.
     */
    fun openWirelessSettings() {
        context.startActivity(
                Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS)
                        .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        )
    }
}