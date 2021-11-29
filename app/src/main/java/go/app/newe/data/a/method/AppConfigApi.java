package go.app.newe.data.a.method;

import go.app.newe.data.a.model.AppConfig;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface AppConfigApi {

    @GET("appConfigs")
    Observable<AppConfig> getApplicationConfiguration();


    @GET("appConfigs")
    Observable<AppConfig> getApplicationConfiguration(@Query("package_name") String packageName);

}
