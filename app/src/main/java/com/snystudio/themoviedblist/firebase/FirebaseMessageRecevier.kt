package com.snystudio.themoviedblist.firebase

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.net.Uri
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.snystudio.themoviedblist.MainActivity
import com.snystudio.themoviedblist.R

class FirebaseMessageRecevier : FirebaseMessagingService() {

    override fun onMessageReceived(p0: RemoteMessage) {
        super.onMessageReceived(p0)

//        if (p0.notification!=null){
//           val map:Map<String,String> =p0.data
//            val title=map.get("title")
//            val message=map.get("message")
//            val deeplink=map.get("deepLink")
////            val notification:RemoteMessage.Notification =null?
////            notification.title=title
//            getNotification(this,title!!,message!!,deeplink!!)
//            Log.d("message service", "onMessageReceived: "+title+" "+message+" "+deeplink)
//        }

        if (p0.data.isNotEmpty()){
            Log.d("message Service", "onMessageReceived: ${p0.data}")
        }
        val title=p0.data.get("title")
        val message=p0.data.get("message")
        val deepLink=p0.data.get("deepLink")
        Log.d("message service", "onMessageReceived: "+title+" "+message+" "+deepLink)
        getNotification(this,title,message,deepLink)

//        if (p0.data.size>0){
//            val entryset=p0.data.entries
//            for (i in entryset){
//                Log.d("firebasemessageservice", "onMessageReceived: key="+i.key+" value="+i.value)
//            }
//        }

    }

    override fun onNewToken(p0: String) {
        super.onNewToken(p0)
        Log.d("token", "onNewToken: "+p0)
    }

    fun getNotification(context: Context,title:String?,message:String?,deeplink:String?){
        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT>=26){
            val notificationChannel = NotificationChannel("any_default_id","any_channel_name",NotificationManager.IMPORTANCE_HIGH)
            notificationChannel.description="any description can be given!"
            notificationManager.createNotificationChannel(notificationChannel)
        }
        val defaultSound=RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val notificationBuilder=NotificationCompat.Builder(context)
            .setAutoCancel(true)
            .setSound(defaultSound)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setPriority(Notification.PRIORITY_MAX)
            .setDefaults(Notification.DEFAULT_ALL)

        val intent = Intent(this,MainActivity::class.java)
        intent.action=Intent.ACTION_VIEW
        intent.data= Uri.parse(deeplink)
        intent.flags=Intent.FLAG_ACTIVITY_CLEAR_TOP

        val pendingIntent=PendingIntent.getActivity(context,0,intent,PendingIntent.FLAG_ONE_SHOT)

        notificationBuilder.setContentTitle(title)
            .setContentText(message)
            .setContentIntent(pendingIntent)
        notificationManager.notify(0,notificationBuilder.build())
        Log.d("message service", "onMessageReceived: "+title+" "+message+" "+deeplink)
    }
}