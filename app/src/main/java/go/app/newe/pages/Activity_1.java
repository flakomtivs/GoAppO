package go.app.newe.pages;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import br.com.simplepass.loading_button_lib.customViews.CircularProgressButton;
import go.app.newe.R;
import go.app.newe.login.Login;

public class Activity_1 extends AppCompatActivity {

    GoogleSignInClient mGoogleSignInClient;
    Button sign_out;
    TextView nameTV;
    TextView emailTV;
    TextView idTV;
    ImageView photoIV;
    CircularProgressButton circularProgressButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_1);


        circularProgressButton = (CircularProgressButton) findViewById(R.id.next);
        circularProgressButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AsyncTask<String, String, String> demoDownload = new AsyncTask<String, String, String>() {
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
                            Intent intent = new Intent(Activity_1.this, Activity_2.class);
                            startActivity(intent);


                            circularProgressButton.doneLoadingAnimation(Color.parseColor("#333639"), BitmapFactory.decodeResource(getResources(), R.drawable.ic_done_white_48dp));


                        }

                    }
                };
                circularProgressButton.startAnimation();
                demoDownload.execute();
            }
        });

        photoIV = findViewById(R.id.photo);

        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(Activity_1.this);
        if (acct != null) {

            Uri personPhoto = acct.getPhotoUrl();

            Glide.with(this).load(personPhoto).into(photoIV);
        }



    }
    }