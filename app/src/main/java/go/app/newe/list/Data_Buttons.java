package go.app.newe.list;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

import go.app.newe.R;
import go.app.newe.article.Art_1;
import go.app.newe.article.Art_2;
import go.app.newe.article.Art_3;
import go.app.newe.article.Art_4;
import go.app.newe.article.Art_5;
import go.app.newe.article.Art_6;

public class Data_Buttons extends AppCompatActivity {

    GoogleSignInClient mGoogleSignInClient;
    ImageView photoIV;

    LinearLayout nativeContainer;

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

        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(Data_Buttons.this);
        if (acct != null) {

            Uri personPhoto = acct.getPhotoUrl();

            Glide.with(this).load(personPhoto).into(photoIV);
        }
    }

    private void setupViews() {
        // TODO => setup the titles here
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