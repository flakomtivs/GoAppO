package go.app.newe.data.a.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Advertisement {


    @Expose
    @SerializedName("id")
    private int id;


    @Expose
    @SerializedName("is_enabled")
    private Boolean isEnabled;


    @Expose
    @SerializedName("provider")
    private String provider;


    @Expose
    @SerializedName("ad_id")
    private String adId;

    @Expose
    @SerializedName("name")
    private String name;


    @Expose
    @SerializedName("type")
    private String type;        // interstitial, banner, native


    @SerializedName("name")
    public String getName() {
        return name;
    }


    @SerializedName("name")
    public void setName(String name) {
        this.name = name;
    }

    @SerializedName("id")
    public int getId() {
        return id;
    }


    @SerializedName("id")
    public void setId(int id) {
        this.id = id;
    }


    @SerializedName("is_enabled")
    public Boolean getEnabled() {
        return isEnabled;
    }


    @SerializedName("is_enabled")
    public void setEnabled(Boolean enabled) {
        isEnabled = enabled;
    }


    @SerializedName("provider")
    public String getProvider() {
        return provider;
    }


    @SerializedName("provider")
    public void setProvider(String provider) {
        this.provider = provider;
    }


    @SerializedName("ad_id")
    public String getAdId() {
        return adId;
    }


    @SerializedName("ad_id")
    public void setAdId(String adId) {
        this.adId = adId;
    }


    @SerializedName("type")
    public String getType() {
        return type;
    }


    @SerializedName("type")
    public void setType(String type) {
        this.type = type;
    }
}
