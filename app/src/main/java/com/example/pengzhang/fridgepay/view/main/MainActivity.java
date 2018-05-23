package com.example.pengzhang.fridgepay.view.main;

import android.app.NotificationManager;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.pengzhang.fridgepay.R;
import com.example.pengzhang.fridgepay.service.NotifyingService;

public class MainActivity extends AppCompatActivity  {

    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        intent = new Intent(MainActivity.this, NotifyingService.class);
        startService(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.cancel(NotifyingService.MOOD_NOTIFICATIONS);
    }

    @Override
    protected void onDestroy() {
        stopService(intent);
        super.onDestroy();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

}
