package go.app.newe.quick;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.Button;

import android.os.Build;

import android.view.View;
import android.view.ViewGroup;

import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.ads.nativetemplates.NativeTemplateStyle;
import com.google.android.ads.nativetemplates.TemplateView;
import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;

import go.app.newe.App;
import go.app.newe.R;
import go.app.newe.data.a.model.Advertisement;
import go.app.newe.data.a.model.Screen;
import go.app.newe.list.Data_Buttons;
import go.app.newe.pages.Activity_1;

public class QuickTour extends AppCompatActivity {


    LinearLayout nativeContainer;

    private Screen quickScreen;
    private InterstitialAd mInterstitialAd;

    Handler handler = new Handler();

    private Screen mScreen;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quick_tour);
        nativeContainer = findViewById(R.id.native_container);

        setupView();
    }



    private void setupView() {

        for (Screen screen : App.getAppConfig().getScreens()) {
            if (screen.getName().equals("quick_tour_screen")) {
                mScreen = screen;
                break;
            }
        }

        handler.post(() -> {
            if (mScreen != null) {
                for (Advertisement advertisement : mScreen.getAdvertisements()) {
                    if (advertisement.getProvider().equals("admob")
                            && advertisement.getType().equals("native")
                            && advertisement.getName().equals("native_1")
                            && advertisement.getEnabled()
                            && advertisement.getAdId() != null) {
                        Log.d("TAG", "setupView: ad id => " + advertisement.getAdId());
                        LinearLayout layout = findViewById(R.id.native_container);
                        AdLoader adLoader = new AdLoader.Builder(QuickTour.this, advertisement.getAdId())
                                .forNativeAd(nativeAd -> {
                                    NativeTemplateStyle styles = new NativeTemplateStyle
                                            .Builder()
                                            .build();
                                    LayoutInflater layoutInflater = (LayoutInflater) QuickTour.this.getSystemService(LAYOUT_INFLATER_SERVICE);
                                    View view = layoutInflater.inflate(R.layout.medium_template_view, layout);
                                    TemplateView template = view.findViewById(R.id.my_template);
                                    template.setStyles(styles);
                                    template.setNativeAd(nativeAd);
                                })
                                .build();
                        adLoader.loadAd(new AdRequest.Builder().build());

                    }

                    if (advertisement.getProvider().equals("admob")
                            && advertisement.getType().equals("interstitial")
                            && advertisement.getName().equals("interstitial_1")
                            && advertisement.getEnabled()
                            && advertisement.getAdId() != null) {
                        AdRequest adRequest = new AdRequest.Builder().build();
                        InterstitialAd.load(QuickTour.this, advertisement.getAdId(), adRequest,
                                new InterstitialAdLoadCallback() {
                                    @Override
                                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                                        // The mInterstitialAd reference will be null until
                                        // an ad is loaded.
                                        mInterstitialAd = interstitialAd;
                                        setListener();
                                    }

                                    @Override
                                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                                        // Handle the error
                                        Log.i("TAG", loadAdError.getMessage());
                                        mInterstitialAd = null;
                                    }
                                });
                    }
                }
            }
        });

    }

    private void setListener() {
        if (mInterstitialAd != null) {
            mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                @Override
                public void onAdDismissedFullScreenContent() {
                    startActivity(new Intent(QuickTour.this, Activity_1.class));          // TODO => change this
                    finish();
                    Log.d("TAG", "The ad was dismissed.");
                }

                @Override
                public void onAdFailedToShowFullScreenContent(@NonNull AdError adError) {
                    // Called when fullscreen content failed to show.
                    Log.d("TAG", "The ad failed to show.");
                }

                @Override
                public void onAdShowedFullScreenContent() {
                    // Called when fullscreen content is shown.
                    // Make sure to set your reference to null so you don't
                    // show it a second time.
                    mInterstitialAd = null;
                    Log.d("TAG", "The ad was shown.");
                }
            });
        }
    }


    public void takeme(View view) {
        if (mInterstitialAd != null) {
            mInterstitialAd.show(QuickTour.this);
        } else {
            startActivity(new Intent(QuickTour.this, Activity_1.class));
            finish();
        }
    }
}