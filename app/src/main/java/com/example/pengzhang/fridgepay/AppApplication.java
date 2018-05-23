package com.example.pengzhang.fridgepay;

import android.app.Application;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.DisplayMetrics;

import com.raizlabs.android.dbflow.config.FlowConfig;
import com.raizlabs.android.dbflow.config.FlowManager;

import java.util.Locale;

public class AppApplication extends Application
{
   @Override
   public void onCreate()
   {
      super.onCreate();

      FlowManager.init(new FlowConfig.Builder(this).build());

      setLanguage();
   }

   private void setLanguage() {
      Resources resources = getResources();

      DisplayMetrics metrics = resources.getDisplayMetrics();

      Configuration configuration = resources.getConfiguration();

      configuration.setLocale( Locale.GERMANY);

      resources.updateConfiguration(configuration, metrics);
   }
}
