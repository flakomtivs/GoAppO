package go.app.newe.splash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ProgressBar;
import android.app.Activity;

import java.util.Timer;
import java.util.TimerTask;

import go.app.newe.App;
import go.app.newe.R;
import go.app.newe.login.Login;
import io.reactivex.disposables.CompositeDisposable;

public class Splash extends AppCompatActivity {

    int progressInt = 0;

    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

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

                progressInt = progressInt + 5;
                progressBar.setProgress(progressInt);

                activity.runOnUiThread(() -> {
                });
                if (progressBar.getProgress() >= 10) {
                    timer.cancel();
                    getAdConfig();
                }
            }
        }, 1000, 50);
    }

    private void getAdConfig() {
        compositeDisposable.add(
                App.getDataManager().getApplicationConfiguration(this.getPackageName())
                        .subscribeOn(App.getSchedulerProvider().io())
                        .observeOn(App.getSchedulerProvider().ui())
                        .subscribe(appConfig -> {
                            App.setAppConfig(appConfig);
                            openLoginActivity();
                        }, throwable -> Log.d("TAG", "getAdConfig: " + throwable.getMessage()))
        );
    }


    @Override
    protected void onDestroy() {
        compositeDisposable.dispose();
        super.onDestroy();
    }

    private void openLoginActivity() {
        Intent intent = new Intent(Splash.this, Login.class);
        startActivity(intent);
        finish();
    }
}