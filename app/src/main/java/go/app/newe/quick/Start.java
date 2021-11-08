package go.app.newe.quick;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import go.app.newe.R;

public class Start extends AppCompatActivity {

    ProgressBar progressBar;
    Button start;

    int i = 0;
    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        final Button button = findViewById(R.id.start);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                button.setBackgroundColor(getResources().getColor(R.color.pressed));
            }
        });

        init();
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                new Thread(new Runnable(){
                    @Override
                    public void run(){
                        while (i < 100){
                            i+=1;
                            try {
                                Thread.sleep(50);
                            } catch (InterruptedException e) {
                            e.printStackTrace();
                            }
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    progressBar.setProgress(i);
                                    if (i == progressBar.getMax())
                                        startActivity(new Intent(Start.this, QuickTour.class));
                                }
                            });
                        }
                    }
                }).start();
            }
        });



    }

    private void init() {
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        start = (Button) findViewById(R.id.start);

    }

}