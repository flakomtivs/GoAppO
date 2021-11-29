package go.app.newe.data.a.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ViewItem {

    @Expose
    @SerializedName("id")
    private int id;


    @Expose
    @SerializedName("name")
    private String name;


    @Expose
    @SerializedName("type")
    private String type;


    @Expose
    @SerializedName("background_type")
    private String backgroundType;


    @Expose
    @SerializedName("background")
    private String background;


    @Expose
    @SerializedName("text")
    private String text;


    @Expose
    @SerializedName("views_data")
    private List<ViewItemData> viewItemData;


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


    @SerializedName("type")
    public String getType() {
        return type;
    }


    @SerializedName("type")
    public void setType(String type) {
        this.type = type;
    }


    @SerializedName("background_type")
    public String getBackgroundType() {
        return backgroundType;
    }


    @SerializedName("background_type")
    public void setBackgroundType(String backgroundType) {
        this.backgroundType = backgroundType;
    }


    @SerializedName("background")
    public String getBackground() {
        return background;
    }


    @SerializedName("background")
    public void setBackground(String background) {
        this.background = background;
    }


    @SerializedName("text")
    public String getText() {
        return text;
    }


    @SerializedName("text")
    public void setText(String text) {
        this.text = text;
    }


    @SerializedName("views_data")
    public List<ViewItemData> getViewItemData() {
        return viewItemData;
    }


    @SerializedName("views_data")
    public void setViewItemData(List<ViewItemData> viewItemData) {
        this.viewItemData = viewItemData;
    }
}
