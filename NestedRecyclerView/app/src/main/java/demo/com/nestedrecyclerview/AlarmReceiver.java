package demo.com.nestedrecyclerview;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class AlarmReceiver extends BroadcastReceiver {

    private static final String CHANNEL_ID = "Shamim Al Mamun";
    private static final String CHANNEL_NAME = "Dsmart";
    private static final String CHANNEL_DESC = "Simple Notification";

    @Override
    public void onReceive(Context context, Intent intent) {

        //Get id and string from intent
        int notificationId = intent.getIntExtra("id", 0);
        String message = intent.getStringExtra("msg");

        Log.e("id", String.valueOf(notificationId));

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription(CHANNEL_DESC);
            NotificationManager manager = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);
            manager.createNotificationChannel(channel);
        }

        //When notification is tapped, call MainActivity
        Intent mainIntent = new Intent(context, MainActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(context, 0, mainIntent, 0);

//        NotificationManager myNotificationManager = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);

//        //Prepare Notification
//        Notification.Builder builder = new Notification.Builder(context);
//        builder.setSmallIcon(android.R.drawable.ic_dialog_info)
//                .setContentTitle("It's Time!!")
//                .setContentText(message)
//                .setWhen(System.currentTimeMillis())
//                .setAutoCancel(true)
//                .setContentIntent(contentIntent)
//                .setPriority(Notification.PRIORITY_MAX)
//                .setDefaults(Notification.DEFAULT_ALL);
//
//        //Notify
//        myNotificationManager.notify(notificationId, builder.build());

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);

        Notification notification = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(android.R.drawable.ic_dialog_info)
                .setContentTitle("It's Time!!")
                .setContentText(message)
                .setWhen(System.currentTimeMillis())
                .setAutoCancel(true)
                .setContentIntent(contentIntent)
                .setChannelId(CHANNEL_ID)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .build();

        notificationManagerCompat.notify(notificationId, notification);

    }
}
