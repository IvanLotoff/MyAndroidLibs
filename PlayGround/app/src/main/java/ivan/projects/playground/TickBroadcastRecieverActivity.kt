package ivan.projects.playground

import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import ivan.projects.broadcastreceiverutils.battery.BatteryBroadcastReceiver
import ivan.projects.broadcastreceiverutils.battery.LowerThanXPercentCallback
import ivan.projects.broadcastreceiverutils.network.NetworkStateReceiver
import ivan.projects.broadcastreceiverutils.receivers.TickBroadcastReceiver
import ivan.projects.networkutils.network.NetworkListener

class TickBroadcastRecieverActivity : AppCompatActivity() {

    //private val filter : IntentFilter = IntentFilter(Intent.ACTION_TIME_TICK) //private val broadcastReceiver = NetworkStateReceiver()
//
//    override fun onResume() {
//        val intentFilter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
////        intentFilter.addAction(Intent.ACTION_POWER_DISCONNECTED)
////        intentFilter.addAction(Intent.ACTION_BATTERY_OKAY)
////        intentFilter.addAction(Intent.ACTION_BATTERY_LOW)
//        this.registerReceiver(broadcastReceiver, intentFilter)
//        //LocalBroadcastManager.getInstance(this).registerReceiver(broadcastReceiver, filter)
//        super.onResume()
//    }
//
//    override fun onPause() {
//        //LocalBroadcastManager.getInstance(this).unregisterReceiver(broadcastReceiver)
//        this.unregisterReceiver(broadcastReceiver)
//        super.onPause()
//    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tick_broadcast_reciever)
        //LocalBroadcastManager.getInstance(this).registerReceiver(broadcastReceiver, filter)
//        NetworkListener.getNetworkStateListener(this).observe(this, { state ->
//            Toast.makeText(this, "network state = $state", Toast.LENGTH_SHORT).show()
//        })
        NetworkListener.getNetworkStateListener(this).observe(this,{ ba->
            Toast.makeText(this,"Network " + ba, Toast.LENGTH_SHORT).show()
        })
    }
}