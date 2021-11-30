package go.app.newe;

import android.app.Application;

import androidx.appcompat.app.AppCompatDelegate;

import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.RequestConfiguration;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.util.Arrays;
import java.util.Collections;

import go.app.newe.data.DataManager;
import go.app.newe.data.a.model.AppConfig;
import go.app.newe.utils.AppOpenManager;
import go.app.newe.utils.SchedulerProvider;

public class App extends Application {


    public static App rootApplication;

    public static AppOpenManager appOpenManager;

    private static DataManager dataManager;
    private static SchedulerProvider schedulerProvider;
    private static AppConfig appConfig;

    public static AppConfig getAppConfig() {
        return appConfig;
    }

    public static void setAppConfig(AppConfig config) {
        appConfig = config;
    }

    public static synchronized DataManager getDataManager() {
        if (dataManager == null) {
            dataManager = new DataManager(App.rootApplication);
        }
        return dataManager;
    }

    public static synchronized SchedulerProvider getSchedulerProvider() {
        if (schedulerProvider == null) {
            schedulerProvider = new SchedulerProvider();
        }
        return schedulerProvider;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        MobileAds.initialize(this, initializationStatus -> {
        });
        RequestConfiguration r = new RequestConfiguration.Builder()
                .setTestDeviceIds(Collections.singletonList("998E7426AF3880E009A64950CAD6CE68"))
                .build();
        MobileAds.setRequestConfiguration(r);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
    }

    public static void instantiateOpenManager(String adId) {
        appOpenManager = new AppOpenManager(App.rootApplication, adId);
        appOpenManager.fetchAd();
    }

    public App() {
        super();
        rootApplication = this;
    }

}
