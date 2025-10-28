package com.daluwi.sc_news.core.connectivity

import android.Manifest
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.annotation.RequiresPermission

/**
 * Konkrete Implementierung von [NetworkChecker] für Android-Geräte.
 * Verwendet den Systemdienst [ConnectivityManager].
 *
 * @param context Der Anwendungs-Kontext, benötigt, um auf Systemdienste zuzugreifen.
 */
class NetworkCheckerImpl(private val context: Context) : NetworkChecker {

    @RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
    override fun isAvailable(): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork ?: return false
        val capabilities = connectivityManager.getNetworkCapabilities(network) ?: return false
        return capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ||
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)
    }
}