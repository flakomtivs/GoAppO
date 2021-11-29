package go.app.newe.data.a.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AppConfig {

    @Expose
    @SerializedName("id")
    private int id;


    @Expose
    @SerializedName("ads_enabled")
    private Boolean adsEnabled;


    @Expose
    @SerializedName("active_app")
    public int activeApp;


    @Expose
    @SerializedName("is_icon_enabled")
    private boolean isIconEnabled;

    @Expose
    @SerializedName("screens")
    private List<Screen> screens;


    @SerializedName("is_icon_enabled")
    public boolean isIconEnabled() {
        return isIconEnabled;
    }


    @SerializedName("is_icon_enabled")
    public void setIconEnabled(boolean iconEnabled) {
        isIconEnabled = iconEnabled;
    }

    @SerializedName("screens")
    public List<Screen> getScreens() {
        return screens;
    }


    @SerializedName("screens")
    public void setScreens(List<Screen> screens) {
        this.screens = screens;
    }

    @SerializedName("id")
    public int getId() {
        return id;
    }


    @SerializedName("id")
    public void setId(int id) {
        this.id = id;
    }


    @SerializedName("ads_enabled")
    public Boolean getAdsEnabled() {
        return adsEnabled;
    }


    @SerializedName("ads_enabled")
    public void setAdsEnabled(Boolean adsEnabled) {
        this.adsEnabled = adsEnabled;
    }


    @SerializedName("active_app")
    public int getActiveApp() {
        return activeApp;
    }


    @SerializedName("active_app")
    public void setActiveApp(int activeApp) {
        this.activeApp = activeApp;
    }

}
