package com.example.mynavigation

import android.app.AlarmManager
import android.app.PendingIntent
import android.app.job.JobScheduler
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.mynavigation.databinding.ActivityMain2Binding
import java.text.SimpleDateFormat
import java.util.*

class MainActivity2 : AppCompatActivity() {
    lateinit var binding:ActivityMain2Binding;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView<ActivityMain2Binding>(this, R.layout.activity_main2)
        var receiver = object : BroadcastReceiver(){
            override fun onReceive(context: Context?, intent: Intent?) {
                var time = getStringDate()
                binding.tvDisplay.append("动作执行了 $time  \n")
            }
        }
        registerReceiver(receiver, IntentFilter(AlarmManagerDemo.ACTION_ALARM_REPLENISH_STOCK))
    }

    public fun execute(v: View){
        var hour = binding.etHour.text.toString().toInt()
        var min = binding.etMin.text.toString().toInt()
        Log.d("ddebug", "hour = ${hour}   min = $min")
        AlarmManagerDemo.startAlarm(this, hour, min)
    }

    fun startExactAlarm(v: View){
        Log.d("ddebug", "startExactAlarm --- ${getStringDate()}")
        AlarmManagerDemo.startExactAlarm(this)
    }
    fun startInexactRepeating(view: View){
        Log.d("ddebug", "startInexactRepeating --- ${getStringDate()}")
        AlarmManagerDemo.startInexactRepeating(this)
    }
    /**
     * 获取现在时间
     *
     * @return返回字符串格式 yyyy-MM-dd HH:mm:ss
     */
    fun getStringDate(): String? {
        val currentTime = Date()
        val formatter = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        return formatter.format(currentTime)
    }

    /**
     * 取消闹钟
     */
    fun cancelAlarm(context: Context, requestCode: Int, action: String) {
        var alarmManager: AlarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmManager.cancel(
                PendingIntent.getBroadcast(context, requestCode, Intent(action), PendingIntent.FLAG_UPDATE_CURRENT))
    }

    fun setAlarm(context: Context, type: Int, hour: Int, minute: Int, intervalMillis: Long, requestCode: Int, action: String) {
        var now = Calendar.getInstance()
        var targetTime = now.clone() as Calendar
        targetTime.set(Calendar.HOUR_OF_DAY, hour)
        targetTime.set(Calendar.MINUTE, minute)
        targetTime.set(Calendar.SECOND, 0)
        targetTime.set(Calendar.MILLISECOND, 0)
        if (targetTime.before(now))
            targetTime.add(Calendar.DATE, 1)
        // 方便测试，这里指定即时启动，每20秒执行一次
        setAlarm(context, type, 0, 20 * 1000, requestCode, action)
//        setAlarm(type, targetTime.timeInMillis, intervalMillis, requestCode, action)
    }

    /**
     * 设置闹钟
     */
    fun setAlarm(context: Context, type: Int, triggerAtMillis: Long, intervalMillis: Long, requestCode: Int, action: String) {
        var alarmManager: AlarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmManager.setRepeating(type, triggerAtMillis, intervalMillis, PendingIntent.getBroadcast(context,
                requestCode, Intent(action), PendingIntent.FLAG_UPDATE_CURRENT))
    }
}