package com.example.mynavigation

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.preference.PreferenceManager
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.widget.NestedScrollView
import java.util.*

class AlarmManagerDemo {
    companion object{
        const val ACTION_ALARM_REPLENISH_STOCK = "replenish.stock.action"
        const val ACTION_ALARM_SYNCHRONIZE = "witmoon.auto.synchronize.action"
        const val ALARM_REPLENISH_STOCK_CODE = 11
        const val ALARM_SYNCHRONIZE_CODE = 12

        const val KEY_SETTING_AUTO_SYNCHRONIZE = "auto_synchronize_status"
        const val KEY_SETTING_SYNCHRONIZE_TIME = "auto_synchronize_time"
        const val KEY_SETTING_AUTO_SUBMIT = "auto_submit_status"
        const val KEY_SETTING_AUTO_SUBMIT_TIME = "auto_submit_time"

        /**
         * 启动定时器（使用系统闹铃服务）
         */
        public fun startAlarm(context: Context,hour: Int,minute: Int) {
            setAlarm(context,AlarmManager.RTC_WAKEUP, hour, minute,
                    AlarmManager.INTERVAL_DAY, ALARM_REPLENISH_STOCK_CODE, ACTION_ALARM_REPLENISH_STOCK)
        }
        public fun startExactAlarm(context: Context){

            var time = 10 * 1000 //* 30;//30分钟
            var alarmManager:AlarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager;
            var intent:Intent = Intent(ACTION_ALARM_REPLENISH_STOCK);
            var intent123 = PendingIntent.getBroadcast(context,
                    ALARM_REPLENISH_STOCK_CODE, Intent(ACTION_ALARM_REPLENISH_STOCK), PendingIntent.FLAG_UPDATE_CURRENT)
            var pendingIntent:PendingIntent = PendingIntent.getService(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            if (Build.VERSION.SDK_INT < 19) {
                alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + time, intent123);//pendingIntent
            } else {
                alarmManager.setExact(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + time, intent123);//pendingIntent
            }
        }
        public fun startInexactRepeating(context: Context){
            var time = 10 * 1000 //* 30;//30分钟
            var alarmManager:AlarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager;
            var intent:Intent = Intent(ACTION_ALARM_REPLENISH_STOCK);
            var intent123 = PendingIntent.getBroadcast(context,
                    ALARM_REPLENISH_STOCK_CODE, Intent(ACTION_ALARM_REPLENISH_STOCK), PendingIntent.FLAG_UPDATE_CURRENT)
            var pendingIntent:PendingIntent = PendingIntent.getService(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + time,20 * 1000, intent123);//pendingIntent
        }
        /**
         * 启动定时器（使用系统闹铃服务）
         */
        private fun startAlarm(context: Context) {

            var hourMinuteArray =  arrayOf("00","00")
            setAlarm(context,AlarmManager.RTC_WAKEUP, hourMinuteArray[0].toInt(), hourMinuteArray[1].toInt(),
                    AlarmManager.INTERVAL_DAY, ALARM_REPLENISH_STOCK_CODE, ACTION_ALARM_REPLENISH_STOCK)
            // 判断是否需要启动定时同步任务
//            setAlarm(context,AlarmManager.RTC_WAKEUP, hourMinuteArray[0].toInt(), hourMinuteArray[1].toInt(),
//                    AlarmManager.INTERVAL_DAY, ALARM_SYNCHRONIZE_CODE, ACTION_ALARM_SYNCHRONIZE)
        }

        /**
         * 取消闹钟
         */
        fun cancelAlarm(context: Context,requestCode: Int, action: String) {
            var alarmManager: AlarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
            alarmManager.cancel(
                    PendingIntent.getBroadcast(context, requestCode, Intent(action), PendingIntent.FLAG_UPDATE_CURRENT))
        }

        fun setAlarm(context: Context,type: Int, hour: Int, minute: Int, intervalMillis: Long, requestCode: Int, action: String) {
            var now = Calendar.getInstance()
            var targetTime = now.clone() as Calendar
            targetTime.set(Calendar.HOUR_OF_DAY, hour)
            targetTime.set(Calendar.MINUTE, minute)
            targetTime.set(Calendar.SECOND, 0)
            targetTime.set(Calendar.MILLISECOND, 0)
            if (targetTime.before(now))
                targetTime.add(Calendar.DATE, 1)
            // 方便测试，这里指定即时启动，每20秒执行一次
            setAlarm(context,type, 0, 20 * 1000, requestCode, action)
            //setAlarm(context,type, targetTime.timeInMillis, intervalMillis, requestCode, action)
        }

        /**
         * 设置闹钟
         */
        fun setAlarm(context: Context,type: Int, triggerAtMillis: Long, intervalMillis: Long, requestCode: Int, action: String) {
            var alarmManager: AlarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
            alarmManager.setRepeating(type, triggerAtMillis, intervalMillis, PendingIntent.getBroadcast(context,
                    requestCode, Intent(action), PendingIntent.FLAG_UPDATE_CURRENT))
        }
    }

}