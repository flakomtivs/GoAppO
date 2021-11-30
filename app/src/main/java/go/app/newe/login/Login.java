package go.app.newe.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.Toast;


import com.google.android.ads.nativetemplates.NativeTemplateStyle;
import com.google.android.ads.nativetemplates.TemplateView;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.nativead.NativeAd;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

import java.util.Objects;

import go.app.newe.App;
import go.app.newe.R;
import go.app.newe.data.DataManager;
import go.app.newe.data.a.model.Advertisement;
import go.app.newe.data.a.model.Screen;
import go.app.newe.quick.Start;

public class Login extends AppCompatActivity {

    int RC_SIGN_IN = 0;
    SignInButton signInButton;
    GoogleSignInClient mGoogleSignInClient;

    private Screen loginScreen;
    private Handler handler = new Handler(Looper.getMainLooper());


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);

        // TODO: Add adView to your view hierarchy.
        //Initializing Views
        signInButton = findViewById(R.id.sign_in_button);

        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        signInButton.setOnClickListener(view -> signIn());
        MobileAds.initialize(this);
        setupView();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(Login.this, Start.class));
            }
        }, 5000);
    }

    private void setupView() {
        for (Screen screen : App.getAppConfig().getScreens()) {
            if (screen.getName().equals("login_screen")) {
                loginScreen = screen;
                break;
            }
        }

        handler.post(() -> {
            if (loginScreen != null) {
                for (Advertisement advertisement : loginScreen.getAdvertisements()) {
                    if (advertisement.getProvider().equals("admob")
                            && advertisement.getType().equals("native")
                            && advertisement.getName().equals("native_1")
                            && advertisement.getEnabled()
                            && advertisement.getAdId() != null) {
                        Log.d("TAG", "setupView: ad id => " + advertisement.getAdId());
                        LinearLayout layout = findViewById(R.id.LinearNat);
                        AdLoader adLoader = new AdLoader.Builder(this, advertisement.getAdId())
                                .forNativeAd(nativeAd -> {
                                    NativeTemplateStyle styles = new NativeTemplateStyle
                                            .Builder()
                                            .build();
                                    LayoutInflater layoutInflater = (LayoutInflater) Login.this.getSystemService(LAYOUT_INFLATER_SERVICE);
                                    View view = layoutInflater.inflate(R.layout.medium_template_view, layout);
                                    TemplateView template = view.findViewById(R.id.my_template);
                                    template.setStyles(styles);
                                    template.setNativeAd(nativeAd);
                                })
                                .build();
                        adLoader.loadAd(new AdRequest.Builder().build());

                    }

                    if (advertisement.getProvider().equals("admob")
                            && advertisement.getType().equals("banner")
                            && advertisement.getName().equals("banner_1")
                            && advertisement.getEnabled()
                            && advertisement.getAdId() != null) {
                        AdView adView = new AdView(this);
                        LinearLayout ad = findViewById(R.id.banner_container);
                        adView.setAdSize(AdSize.SMART_BANNER);
                        adView.setAdUnitId(advertisement.getAdId());
                        ad.addView(adView);
                        adView.loadAd(new AdRequest.Builder().build());
                    }
                }
            }
        });

    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            App.getDataManager().setUserImage(Objects.requireNonNull(account.getPhotoUrl()).toString());
            startActivity(new Intent(Login.this, Start.class));
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w("Google Sign In Error", "signInResult:failed code=" + e.getStatusCode());
            Toast.makeText(Login.this, "Failed", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onStart() {
        // Check for existing Google Sign In account, if the user is already signed in
        // the GoogleSignInAccount will be non-null.
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        if (account != null) {
            startActivity(new Intent(Login.this, Start.class));
        }
        super.onStart();
    }

    public void fb(View view) {
        Toast toast = Toast.makeText(this, "Failed", Toast.LENGTH_LONG);
        toast.show();

    }

    public void privacy(View view) {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/")));
    }

    public void terms(View view) {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/")));
    }
}
