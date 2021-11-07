package com.example.assignment1.Helper;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import com.example.assignment1.R;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

import static android.content.Context.NOTIFICATION_SERVICE;

/**
 * This class sends out notification to phone
 */
public class NotificationHelper {
    private Class<?> classForNotification;
    private Context context;
    private final String CHANNEL_ID = "my channel";
    private int notificationId;

    public NotificationHelper(Class<?> classForNotification, Context context) {
        this.classForNotification = classForNotification;
        this.context = context;
        notificationId = 0;
        createNotificationChannel();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void createNotification(String title, String content) {                                  // send notification to the channel created from the start
        NotificationCompat.Builder notifyDialog =
                new NotificationCompat.Builder(context, CHANNEL_ID);
        Intent notifyIntent = new Intent(context, classForNotification);
        PendingIntent notifyPendingIntent = PendingIntent.getActivity
                (context, 0, notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        notifyDialog
                .setSmallIcon(R.drawable.logo)
                .setContentTitle(title)
                .setContentText(content)
                .setAutoCancel(true)
                .setContentIntent(notifyPendingIntent)
                .setPriority(Notification.PRIORITY_MAX);
        Notification notification = notifyDialog.build();
        NotificationManager notificationManager =
                (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(notificationId, notification);
        notificationId++;
    }

    private void createNotificationChannel() {                                                      // Create the NotificationChannel, but only on API 26+ because the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel =
                    new NotificationChannel(CHANNEL_ID, "My name", importance);
            channel.setDescription("This is the description");
            // Register the channel with the system
            NotificationManager notificationManager =
                    context.getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}
