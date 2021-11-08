package go.app.newe.splash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.app.Activity;
import java.util.Timer;
import java.util.TimerTask;

import go.app.newe.R;
import go.app.newe.login.Login;

public class Splash extends AppCompatActivity {

    int progressInt = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ProgressBar progressBar = findViewById(R.id.pBar);
        Activity activity = Splash.this;

        progressBar.setProgress(progressInt);
        progressBar.setMax(10);

        Timer timer = new Timer();

        timer.schedule(new TimerTask() {
            @Override
            public void run() {

                progressInt = progressInt+5;
                progressBar.setProgress(progressInt);

                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                    }
                });
                if (progressBar.getProgress()>=10){
                    timer.cancel();
                    Intent intent = new Intent(Splash.this, Login.class);
                    activity.startActivity(intent);
                    finish();
                }
            }
        },1000,50);
    }
}