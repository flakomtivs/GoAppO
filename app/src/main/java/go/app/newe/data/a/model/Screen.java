package go.app.newe.data.a.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Screen {


    @Expose
    @SerializedName("id")
    private int id;


    @Expose
    @SerializedName("ads_provider")
    private String adsProvider;


    @Expose
    @SerializedName("background")
    private String background;


    @Expose
    @SerializedName("background_type")
    private String backgroundType;


    @Expose
    @SerializedName("name")
    private String name;


    @Expose
    @SerializedName("views")
    private List<ViewItem> viewItems;


    @Expose
    @SerializedName("advertisements")
    private List<Advertisement> advertisements;


    @SerializedName("name")
    public String getName() {
        return name;
    }


    @SerializedName("name")
    public void setName(String name) {
        this.name = name;
    }


    @SerializedName("advertisements")
    public List<Advertisement> getAdvertisements() {
        return advertisements;
    }


    @SerializedName("advertisements")
    public void setAdvertisements(List<Advertisement> advertisements) {
        this.advertisements = advertisements;
    }

    @SerializedName("views")
    public List<ViewItem> getViewItems() {
        return viewItems;
    }


    @SerializedName("views")
    public void setViewItems(List<ViewItem> viewItems) {
        this.viewItems = viewItems;
    }

    @SerializedName("id")
    public int getId() {
        return id;
    }


    @SerializedName("id")
    public void setId(int id) {
        this.id = id;
    }


    @SerializedName("ads_provider")
    public String getAdsProvider() {
        return adsProvider;
    }


    @SerializedName("ads_provider")
    public void setAdsProvider(String adsProvider) {
        this.adsProvider = adsProvider;
    }


    @SerializedName("background")
    public String getBackground() {
        return background;
    }


    @SerializedName("background")
    public void setBackground(String background) {
        this.background = background;
    }


    @SerializedName("background_type")
    public String getBackgroundType() {
        return backgroundType;
    }


    @SerializedName("background_type")
    public void setBackgroundType(String backgroundType) {
        this.backgroundType = backgroundType;
    }
}
