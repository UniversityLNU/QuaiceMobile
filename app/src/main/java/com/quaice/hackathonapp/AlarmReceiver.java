package com.quaice.hackathonapp;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class AlarmReceiver extends BroadcastReceiver {
    private static final String CHANNEL_ID = "notifyChannel";

    @Override
    public void onReceive(Context context, Intent intent) {
        createNotificationChannel(context);

        // Create an Intent that will open the FoundationActivity
        Intent foundationIntent = new Intent(context, CreatePostActivity.class);
        // Add any extras to the intent here, if needed

        // Create a PendingIntent that will start the FoundationActivity
        PendingIntent foundationPendingIntent = PendingIntent.getActivity(context, 0, foundationIntent, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.drawable.cart) // replace with your own icon
                .setContentTitle("Donation Reminder")
                .setContentText("New Donate is waiting for you!")
                .setContentIntent(foundationPendingIntent) // Set the click action
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        notificationManager.notify(200, builder.build());
    }

    private void createNotificationChannel(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "notifyChannel";
            String description = "Channel for scheduled notifications";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}