package com.codingmart.currencycompare.helper

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo


object NetworkHelper {

    fun isConnectedToInternet(context: Context): Boolean {
        val connectivity = context.getSystemService(
            Context.CONNECTIVITY_SERVICE
        ) as ConnectivityManager
        val info = connectivity.allNetworkInfo
        if (info != null)
            for (i in info.indices)
                if (info[i].state == NetworkInfo.State.CONNECTED) {
                    return true
                }
        context.showToast("No Internet Connection")
        return false
    }
}

