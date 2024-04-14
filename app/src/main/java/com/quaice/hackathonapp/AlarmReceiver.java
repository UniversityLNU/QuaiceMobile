package com.quaice.hackathonapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        // Create the content for the notification
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "notifyChannel")
                //.setSmallIcon(R.drawable.notification_icon) // replace with your own icon
                .setContentTitle("Scheduled Notification")
                .setContentText("This is a scheduled notification.")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        // Show the notification
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        notificationManager.notify(200, builder.build());
    }
}