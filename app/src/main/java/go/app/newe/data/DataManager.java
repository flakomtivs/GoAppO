package go.app.newe.data;

import android.content.Context;

import go.app.newe.data.a.ApiHelper;
import go.app.newe.data.a.method.AppConfigApi;
import go.app.newe.data.a.model.AppConfig;
import go.app.newe.data.l.ISharedPrefs;
import go.app.newe.data.l.SharedPrefs;
import io.reactivex.Observable;

public class DataManager implements AppConfigApi, ISharedPrefs {

    private final ApiHelper apiHelper;
    private final SharedPrefs sharedPrefs;

    public DataManager(Context context) {
        apiHelper = new ApiHelper();
        sharedPrefs = new SharedPrefs(context);
    }

    @Override
    public Observable<AppConfig> getApplicationConfiguration() {
        return apiHelper.getApplicationConfiguration();
    }

    @Override
    public Observable<AppConfig> getApplicationConfiguration(String packageName) {
        return apiHelper.getApplicationConfiguration(packageName);
    }

    @Override
    public void setUserImage(String url) {
        sharedPrefs.setUserImage(url);
    }

    @Override
    public String getUserImage() {
        return sharedPrefs.getUserImage();
    }
}
