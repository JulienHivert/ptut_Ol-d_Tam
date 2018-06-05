package com.example.iem.oldtam.view.tools;

import android.app.Notification;
import android.support.v4.app.NotificationCompat.Builder;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.example.iem.oldtam.R;

public class Notify {

    /** Message ID Counter **/

    private static int MessageID = 0 ;
    /**
     * Displays a notification in the notification area of the UI
     * @param context Context from which to create the notification
     * @param messageString The string to display to the user as a message
     * @param intent The intent which will start the activity when the user clicks the notification
     * @param notificationTitle The resource reference to the notification title
     */
    public static void notification(Context context, String messageString, Intent intent, int notificationTittle)
        {
            String ns = Context.NOTIFICATION_SERVICE;
            long when = System.currentTimeMillis();
            NotificationManager notificationManager = (NotificationManager) context.getSystemService(ns);
            CharSequence contentTittle = context.getString(notificationTittle);
            String ticker =  contentTittle + " " + messageString;
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0,intent,0);


            Builder notificationBuilder = new Builder(context);
            notificationBuilder.setAutoCancel(true)
                    .setTicker(ticker)
                    .setWhen(when)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentText(messageString)
                    .setContentIntent(pendingIntent);
            Notification notification = notificationBuilder.build();
            notificationManager.notify(MessageID, notification);
            MessageID++;


        }
    /**
     * Display a toast notification to the user
     * @param context Context from which to create a notification
     * @param text The text the toast should display
     * @param duration The amount of time for the toast to appear to the user
     */

    public static void toast(Context context, CharSequence text, @SuppressWarnings("SameParameterValue") int duration){
        Toast toast = Toast.makeText(context, text,duration);
        toast.show();
    }
}
