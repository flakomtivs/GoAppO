package go.app.newe.data.a.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ViewItemData {

    @Expose
    @SerializedName("id")
    private int id;


    @Expose
    @SerializedName("key")
    private String key;


    @Expose
    @SerializedName("value")
    private String value;


    @SerializedName("id")
    public int getId() {
        return id;
    }


    @SerializedName("id")
    public void setId(int id) {
        this.id = id;
    }


    @SerializedName("key")
    public String getKey() {
        return key;
    }


    @SerializedName("key")
    public void setKey(String key) {
        this.key = key;
    }


    @SerializedName("value")
    public String getValue() {
        return value;
    }


    @SerializedName("value")
    public void setValue(String value) {
        this.value = value;
    }
}
