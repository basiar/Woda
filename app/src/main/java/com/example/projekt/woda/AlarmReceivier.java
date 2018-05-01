package com.example.projekt.woda;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;

import java.util.Calendar;

import static android.content.Context.NOTIFICATION_SERVICE;

public class AlarmReceivier extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.v("user Receieve","recive time = "+ Calendar.getInstance().getTime());
        String id = "notyfication channel id";
        String name = "chanel name";
        String description = "chanel desc";

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationManager nm = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);

            NotificationChannel notificationChannel = new NotificationChannel(id,name,NotificationManager.IMPORTANCE_HIGH);
            notificationChannel.setDescription(description);
            if(nm != null){
                nm.createNotificationChannel(notificationChannel);
            }
        }

        NotificationCompat.Builder notyfication1 = new NotificationCompat.Builder(context,id);
        Calendar calendar = Calendar.getInstance();
        notyfication1.setSmallIcon(R.drawable.ic_invert_colors_black_24dp);
        notyfication1.setTicker("Ticker");
        calendar.add(Calendar.SECOND,5);
        notyfication1.setWhen(calendar.getTimeInMillis());
        notyfication1.setContentTitle("Content Title");
        notyfication1.setContentText("Content Text");

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);
        notificationManagerCompat.notify(1, notyfication1.build());
    }
}
