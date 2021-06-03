package ivan.projects.broadcastreceiverutils.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast

class TickBroadcastReceiver : BroadcastReceiver() {

    /**
     * Метод, который вызывается, когда в операционной системе
     * происходит некоторое действие
     */
    override fun onReceive(context: Context, intent: Intent?) {
        Log.d("Taggingg", "onReceive: ")
        Toast.makeText(context, "Action_time_Tick", Toast.LENGTH_SHORT).show()
    }
}