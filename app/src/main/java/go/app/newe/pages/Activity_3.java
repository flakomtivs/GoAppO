package go.app.newe.pages;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.ads.nativetemplates.NativeTemplateStyle;
import com.google.android.ads.nativetemplates.TemplateView;
import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

import br.com.simplepass.loading_button_lib.customViews.CircularProgressButton;
import go.app.newe.App;
import go.app.newe.R;
import go.app.newe.data.a.model.Advertisement;
import go.app.newe.data.a.model.Screen;
import go.app.newe.data.a.model.ViewItem;
import go.app.newe.list.DialogN;
import go.app.newe.settings.Settings_Activity;

public class Activity_3 extends AppCompatActivity {


    GoogleSignInClient mGoogleSignInClient;
    Button sign_out;
    TextView nameTV;
    TextView emailTV;
    TextView idTV;
    ImageView photoIV;
    CircularProgressButton circularProgressButton;

    LinearLayout bannerContainer;
    LinearLayout nativeContainer;

    private DialogN dialogN = DialogN.getInstance();


    private InterstitialAd mInterstitialAd;

    Handler handler = new Handler();
    private Screen mScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_3);

        bannerContainer = findViewById(R.id.banner_container);
        nativeContainer = findViewById(R.id.native_container);
        setupAds();


        circularProgressButton = (CircularProgressButton) findViewById(R.id.next);
        circularProgressButton.setOnClickListener(v -> {
            @SuppressLint("StaticFieldLeak") AsyncTask<String, String, String> demoDownload = new AsyncTask<String, String, String>() {
                @NonNull
                @Override
                protected String doInBackground(String... strings) {
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return "done";
                }

                @Override
                protected void onPostExecute(String s) {
                    if (s.equals("done")) {
                        showInterstitial();
                        circularProgressButton.doneLoadingAnimation(Color.parseColor("#333639"), BitmapFactory.decodeResource(getResources(), R.drawable.ic_done_white_48dp));
                    }
                }
            };
            circularProgressButton.startAnimation();
            demoDownload.execute();
        });
        photoIV = findViewById(R.id.photo);
        Glide.with(this).load(App.getDataManager().getUserImage()).into(photoIV);
        photoIV.setOnClickListener((v) -> {
            if (dialogN == null) {
                dialogN = DialogN.getInstance();
            }
            dialogN.setListener(() -> {
                if (dialogN != null)
                    dialogN.dismiss();
                Glide.with(this).load(App.getDataManager().getUserImage()).into(photoIV);
            });
            dialogN.show(getSupportFragmentManager(), "A_TAG");
        });
    }

    private void setupAds() {
        for (Screen screen : App.getAppConfig().getScreens()) {
            if (screen.getName().equals("step_3_screen")) {
                mScreen = screen;
                break;
            }
        }

        for (ViewItem item : mScreen.getViewItems()) {
            if (item.getName().equals("body_1") && item.getType().equals("textView")) {
                TextView body = findViewById(R.id.body);
                body.setText(item.getText());
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
                        AdLoader adLoader = new AdLoader.Builder(Activity_3.this, advertisement.getAdId())
                                .forNativeAd(nativeAd -> {
                                    NativeTemplateStyle styles = new NativeTemplateStyle
                                            .Builder()
                                            .build();
                                    LayoutInflater layoutInflater = (LayoutInflater) Activity_3.this.getSystemService(LAYOUT_INFLATER_SERVICE);
                                    View view = layoutInflater.inflate(R.layout.medium_template_view, nativeContainer);
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
                        InterstitialAd.load(Activity_3.this, advertisement.getAdId(), adRequest,
                                new InterstitialAdLoadCallback() {
                                    @Override
                                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
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

                    if (advertisement.getProvider().equals("admob")
                            && advertisement.getType().equals("banner")
                            && advertisement.getName().equals("banner_1")
                            && advertisement.getEnabled()
                            && advertisement.getAdId() != null) {
                        AdView adView = new AdView(this);
                        adView.setAdSize(AdSize.SMART_BANNER);
                        adView.setAdUnitId(advertisement.getAdId());
                        bannerContainer.addView(adView);
                        adView.loadAd(new AdRequest.Builder().build());
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
                    startActivity(new Intent(Activity_3.this, Activity_4.class));
                    finish();
                }

                @Override
                public void onAdFailedToShowFullScreenContent(@NonNull AdError adError) {
                }

                @Override
                public void onAdShowedFullScreenContent() {
                    mInterstitialAd = null;
                    Log.d("TAG", "The ad was shown.");
                }
            });
        }
    }

    private void showInterstitial() {
        if (mInterstitialAd != null) {
            mInterstitialAd.show(this);
        } else {
            Intent intent = new Intent(this, Activity_4.class);
            startActivity(intent);
            finish();
        }
    }

    public void settings(View view) {
        Intent intent = new Intent(this, Settings_Activity.class);
        startActivity(intent);
    }
}