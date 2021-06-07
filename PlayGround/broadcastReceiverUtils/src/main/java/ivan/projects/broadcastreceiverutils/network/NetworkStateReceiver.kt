package ivan.projects.broadcastreceiverutils.network

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.util.Log

/**
 * val intentFilter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
 * this.registerReceiver(broadcastReceiver, intentFilter)
 */
class NetworkStateReceiver : BroadcastReceiver() {
    interface OnNetworkStateListener {
        fun networkAvailable()
        fun networkUnavailable()
    }
    val onNetworkStateListener : OnNetworkStateListener? = null
    override fun onReceive(context: Context?, intent: Intent?) {

        val noConnection = intent!!.getBooleanExtra(ConnectivityManager.EXTRA_NO_CONNECTIVITY, false)
        if(noConnection)
            onNetworkStateListener?.networkUnavailable()
        else
            onNetworkStateListener?.networkAvailable()
    }
}