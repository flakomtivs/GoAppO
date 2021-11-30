package go.app.newe.data.l;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefs implements ISharedPrefs {

    public static final String PREFS_NAME = "NAME_9983931276_SharedPrefs";

    public static final String IMAGE_URL = "Image_Url";

    private final SharedPreferences mSharedPrefs;


    public SharedPrefs(Context context) {
        mSharedPrefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }

    @Override
    public void setUserImage(String url) {
        mSharedPrefs.edit().putString(IMAGE_URL, url).apply();
    }

    @Override
    public String getUserImage() {
        return mSharedPrefs.getString(IMAGE_URL, "https://randomuser.me/api/portraits/men/67.jpg");
    }
}
