package ivan.projects.broadcastreceiverutils.battery

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

/**
 * [BroadcastReceiver], который вызывает переданный в качестве параметра [callback] при условии,
 * что уровень батареи в телефоне стал ниже [percent].
 *
 * [callback] обладает следующей сигнатурой (Context, Intent, Int)-> Unit. Третим параметром является
 * текущий процент заряда батареи.
 *
 * Подключение данного [BroadcastReceiver].
 * 1. Cоздать эксземляр [LowerThanXPercentCallback], например так:
 * private val broadcastReceiver = LowerThanXPercentCallback(50){
 * * * * _, _, percent ->
 * * * * Toast.makeText(this, "Now battery at percent = $percent", Toast.LENGTH_SHORT).show()
 * }
 * В данном случае, при падении уровня батареи ниже 50% будет выведено сообщение на экран
 * 2. Зарегестрировать данный [BroadcastReceiver] в методе [onResume]
 * val intentFilter = IntentFilter(Intent.ACTION_BATTERY_CHANGED)
 * this.registerReceiver(broadcastReceiver, intentFilter) (либо вместо this context, если мы находимся внутри фрагмента)
 * 3. Убрать регистрацию в [onPause]
 * this.unregister(broadcastReceiver)
 */
class LowerThanXPercentCallback(private val percent : Int,
                                private val callback: (Context, Intent, Int)-> Unit) : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val batteryPercentage = intent?.getIntExtra("level", 0) ?: 0
        if(batteryPercentage < percent)
            callback.invoke(context!!, intent!!, batteryPercentage)
    }
}