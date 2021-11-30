package go.app.newe.list;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.ads.nativetemplates.NativeTemplateStyle;
import com.google.android.ads.nativetemplates.TemplateView;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

import go.app.newe.App;
import go.app.newe.R;
import go.app.newe.article.Art_1;
import go.app.newe.article.Art_2;
import go.app.newe.article.Art_3;
import go.app.newe.article.Art_4;
import go.app.newe.article.Art_5;
import go.app.newe.article.Art_6;
import go.app.newe.data.a.model.Advertisement;
import go.app.newe.data.a.model.Screen;
import go.app.newe.data.a.model.ViewItem;
import go.app.newe.pages.Activity_6;
import go.app.newe.settings.Settings_Activity;

public class Data_Buttons extends AppCompatActivity {

    GoogleSignInClient mGoogleSignInClient;
    ImageView photoIV;

    LinearLayout nativeContainer;

    private DialogN dialogN = DialogN.getInstance();

    private InterstitialAd mInterstitialAd;

    Handler handler = new Handler();
    private Screen mScreen;

    private TextView title1;
    private TextView title2;
    private TextView title3;
    private TextView title4;
    private TextView title5;
    private TextView title6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_buttons);
        nativeContainer = findViewById(R.id.native_container);

        photoIV = findViewById(R.id.photo);

        title1 = findViewById(R.id.title_1);
        title2 = findViewById(R.id.title_2);
        title3 = findViewById(R.id.title_3);
        title4 = findViewById(R.id.title_4);
        title5 = findViewById(R.id.title_5);
        title6 = findViewById(R.id.title_6);
        Button settings = findViewById(R.id.settings);
        settings.setOnClickListener((v) -> startActivity(new Intent(Data_Buttons.this, Settings_Activity.class)));
        setupViews();
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

    private void setupViews() {
        for (Screen screen : App.getAppConfig().getScreens()) {
            if (screen.getName().equals("data_list_screen")) {
                mScreen = screen;
                break;
            }
        }

        for (ViewItem item : mScreen.getViewItems()) {
            switch (item.getName()) {
                case "title_1":
                    title1.setText(item.getText());
                    break;

                case "title_2":
                    title2.setText(item.getText());
                    break;

                case "title_3":
                    title3.setText(item.getText());
                    break;

                case "title_4":
                    title4.setText(item.getText());
                    break;

                case "title_5":
                    title5.setText(item.getText());
                    break;

                case "title_6":
                    title6.setText(item.getText());
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
                        AdLoader adLoader = new AdLoader.Builder(Data_Buttons.this, advertisement.getAdId())
                                .forNativeAd(nativeAd -> {
                                    NativeTemplateStyle styles = new NativeTemplateStyle
                                            .Builder()
                                            .build();
                                    LayoutInflater layoutInflater = (LayoutInflater) Data_Buttons.this.getSystemService(LAYOUT_INFLATER_SERVICE);
                                    View view = layoutInflater.inflate(R.layout.m, nativeContainer);
                                    TemplateView template = view.findViewById(R.id.my_template);
                                    template.setStyles(styles);
                                    template.setNativeAd(nativeAd);
                                })
                                .build();
                        adLoader.loadAd(new AdRequest.Builder().build());

                    }
                }
            }
        });

    }

    @SuppressLint("NonConstantResourceId")
    public void data(@NonNull View v) {
        switch (v.getId()) {
            case R.id.btn1:
                Intent intent = new Intent(Data_Buttons.this, Art_1.class);
                startActivity(intent);
                break;
            case R.id.btn2:
                intent = new Intent(Data_Buttons.this, Art_2.class);
                startActivity(intent);
                break;
            case R.id.btn3:
                intent = new Intent(Data_Buttons.this, Art_3.class);
                startActivity(intent);
                break;
            case R.id.btn4:
                intent = new Intent(Data_Buttons.this, Art_4.class);
                startActivity(intent);
                break;
            case R.id.btn5:
                intent = new Intent(Data_Buttons.this, Art_5.class);
                startActivity(intent);
                break;
            case R.id.btn6:
                intent = new Intent(Data_Buttons.this, Art_6.class);
                startActivity(intent);
        }
    }

    public void get_started(View view) {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/")));
    }
}