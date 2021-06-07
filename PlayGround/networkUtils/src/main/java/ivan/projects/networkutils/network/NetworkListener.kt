package ivan.projects.networkutils.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.os.Build
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

/**
 * Подключение:
 * NetworkListener.getNetworkStateListener(this [context]).observe(this [lifecycleOwner],{ ba->
 * Toast.makeText(this,"Network " + ba, Toast.LENGTH_SHORT).show()
 * })
 */
object NetworkListener: ConnectivityManager.NetworkCallback() {
    private val networkStateListener = MutableLiveData<Boolean>()
    private lateinit var connectivityManager : ConnectivityManager
    fun getNetworkStateListener(context : Context) : LiveData<Boolean>{
        connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        // permision access_network_state
        val builder = NetworkRequest.Builder()
        connectivityManager.registerNetworkCallback(builder.build(), this)
        var isConnected = false
        connectivityManager.allNetworks.forEach {
            network ->
            val networkCapability = connectivityManager.getNetworkCapabilities(network)
            networkCapability?.let {
                if(it.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)){
                    isConnected = true
                    return@forEach
                }
            }
        }

        networkStateListener.postValue(isConnected)
        return networkStateListener
    }

    override fun onAvailable(network: Network) {
        networkStateListener.postValue(true)
    }


    override fun onLost(network: Network) {
        networkStateListener.postValue(false)
    }

    override fun onUnavailable() {
        networkStateListener.postValue(false)
    }
    private const val TAG = "TAGGINN"
}