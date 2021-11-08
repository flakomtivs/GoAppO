package go.app.newe.data;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

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
import go.app.newe.pages.Activity_6;

public class Data_Buttons extends AppCompatActivity {

    GoogleSignInClient mGoogleSignInClient;
    ImageView photoIV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_buttons);


        photoIV = findViewById(R.id.photo);

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

    public void data(View v) {
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