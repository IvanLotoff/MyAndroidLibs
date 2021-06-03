package ivan.projects.broadcastreceiverutils.battery

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

/**
 * Данный класс содержит 4 основных callback метода, которые реагируют на
 * следующие обстоятельства: [onBatteryLow] - батареи менее 15%, [onBatteryOkay] - батареи более 15%
 * [onPowerConnected] - колбек, который срабатывает при подключении устройства к зарядке
 * [onPowerDisconnected] - колбек, который срабатвает при отключении устройства от зарядки
 *
 * Подключение [BatteryBroadcastReceiver] в [Activity]
 *
 * В [MainActivity.onResume] регистрируем [BroadcastReceiver]
 *
 * val intentFilter = IntentFilter(Intent.ACTION_POWER_CONNECTED)
 * intentFilter.addAction(Intent.ACTION_POWER_DISCONNECTED)
 * intentFilter.addAction(Intent.ACTION_BATTERY_OKAY)
 * intentFilter.addAction(Intent.ACTION_BATTERY_LOW)
 * this.registerReceiver(broadcastReceiver, intentFilter)
 *
 * В [MainActivity.onPause] снимаем с регистрации [BroadcastReceiver]
 *
 * this.unregisterReceiver(broadcastReceiver)
 *
 * ======================================================
 * Подключение [BatteryBroadcastReceiver] в [Fragment]
 *
 * В [Fragent.onResume]
 * val intentFilter = IntentFilter(Intent.ACTION_POWER_CONNECTED)
 * intentFilter.addAction(Intent.ACTION_POWER_DISCONNECTED)
 * intentFilter.addAction(Intent.ACTION_BATTERY_OKAY)
 * intentFilter.addAction(Intent.ACTION_BATTERY_LOW)
 * context.registerReceiver(broadcastReceiver, intentFilter)
 *
 * В [Fragment.onPause]
 * context.unregister(broadcastReceiver)
 *
 * =========================================
 * Пример использования:
 * BatteryBroadcastReceiver().apply {
 * * * * onBatteryLow = {
 * * * * * * * * _, _ ->
 * * * * * * * * DO SOMETHING
 * * * * }
 * }
 */
class BatteryBroadcastReceiver() : BroadcastReceiver() {

    val onBatterLow : ((Context, Intent)->Unit)? = null
    val onBatterOkay : ((Context, Intent)->Unit)? = null
    val onPowerConnected : ((Context, Intent)->Unit)? = null
    val onPowerDisconnected : ((Context, Intent)->Unit)? = null

    companion object {
        const val TAG = "BatteryBroadcast"
    }

    override fun onReceive(context: Context, intent: Intent?) {
        if(intent != null){
            when(intent.action){
                Intent.ACTION_BATTERY_LOW->{
                    Log.d(TAG, "onReceive: ACTION_BATTERY_LOW")
                    onBatterLow?.invoke(context, intent)
                }
                Intent.ACTION_BATTERY_OKAY->{
                    Log.d(TAG, "onReceive: ACTION_BATTERY_OKAY")
                    onBatterOkay?.invoke(context, intent)
                }
                Intent.ACTION_POWER_CONNECTED->{
                    Log.d(TAG, "onReceive: ACTION_POWER_CONNECTED")
                    onPowerConnected?.invoke(context, intent)
                }
                Intent.ACTION_POWER_DISCONNECTED->{
                    Log.d(TAG, "onReceive: ACTION_POWER_DISCONNECTED")
                    onPowerDisconnected?.invoke(context, intent)
                }
            }
        }
    }

}