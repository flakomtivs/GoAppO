package go.app.newe;

import android.app.Application;

import go.app.newe.data.DataManager;
import go.app.newe.data.a.model.AppConfig;
import go.app.newe.utils.SchedulerProvider;

public class App extends Application {


    public static App rootApplication;

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


    public App() {
        super();
        rootApplication = this;
    }

}
