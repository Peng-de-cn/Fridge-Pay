package com.example.pengzhang.fridgepay.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

import com.example.pengzhang.fridgepay.R;
import com.example.pengzhang.fridgepay.view.main.MainActivity;

import java.util.Calendar;
import java.util.Date;

public class NotifyingService extends Service {

    public static int MOOD_NOTIFICATIONS = 100;
    private NotificationManager notificationManager;

    @Override
    public void onCreate() {
        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        Date today = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(today);

        calendar.add(Calendar.MONTH, 1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        do{
            calendar.add(Calendar.DATE, -1);
        } while (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY
                || calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY );
        Date lastDayOfMonth = calendar.getTime();

        /* For Test */
//        if (today.before(lastDayOfMonth)) {
//            showNotification();
//        }

        if (today.compareTo(lastDayOfMonth) == 0) {
            showNotification();
        }

    }

    @Override
    public void onDestroy() {
        notificationManager.cancel(NotifyingService.MOOD_NOTIFICATIONS);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    @SuppressWarnings("deprecation")
    private void showNotification() {

        PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
                new Intent(this, MainActivity.class), 0);
        Notification notification = new Notification.Builder(this)
                .setContentTitle(getResources().getString(R.string.notification_pay_title))
                .setContentText(getResources().getString(R.string.notification_body_title))
                .setTicker(getResources().getString(R.string.notification_pay_title))
                .setContentIntent(contentIntent)
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .build();
        notificationManager.notify(MOOD_NOTIFICATIONS, notification);
    }

    private final IBinder binder = new Binder() {
        @Override
        protected boolean onTransact(int code, Parcel data, Parcel reply,
                                     int flags) throws RemoteException {
            return super.onTransact(code, data, reply, flags);
        }
    };

}