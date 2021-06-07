package ivan.projects.networkutils.network

import android.content.Context
import android.net.ConnectivityManager

object NetworkHelper {
    fun isWifiConnected(context : Context) : Boolean{
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return true
    }
}