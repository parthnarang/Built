package tasks;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;

import java.util.logging.LogManager;
import java.util.logging.Logger;

import model.billtCore.Invoice;
import parth.com.buiit.R;

import static android.content.Context.NOTIFICATION_SERVICE;

public class NotificationTasks {

    Context context;

    public static final String NOTIFICATION_CHANNEL_ID = "10001";
    public static final String CHANNEL_ONE_NAME = "Channel One";
    public static NotificationManager notificationManager;
    private static final String LOG = NotificationTasks.class.getName();

    public NotificationTasks(Context context){
        this.context = context;
    }

    public void showNewInvoiceNotification(Invoice invoice){
        notificationManager = (NotificationManager)context.getSystemService(NOTIFICATION_SERVICE);

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(context)
                .setSmallIcon(R.drawable.buiit) //your app icon
                .setBadgeIconType(R.drawable.buiit) //your app icon
                .setContentTitle("New Invoice")
                .setAutoCancel(false)
                .setNumber(1)
                .setColor(255)
                .setContentText("You have received invoice from ")
                .setWhen(System.currentTimeMillis());

        Log.d(LOG,"showing notification for new invoice for transId = "+invoice.getInvId());

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Log.d(LOG,"creating notification channel");
            NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID,
                    CHANNEL_ONE_NAME, NotificationManager.IMPORTANCE_HIGH);
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.setShowBadge(true);
            notificationChannel.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);
            notificationManager.createNotificationChannel(notificationChannel);
            notificationBuilder.setChannelId(NOTIFICATION_CHANNEL_ID);

        }


        notificationManager.notify(0, notificationBuilder.build());

    }

}
