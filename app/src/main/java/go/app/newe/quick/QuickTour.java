package go.app.newe.quick;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import android.os.Build;

import android.view.View;
import android.view.ViewGroup;

import android.widget.LinearLayout;
import android.widget.Toast;

import go.app.newe.R;
import go.app.newe.pages.Activity_1;

public class QuickTour extends AppCompatActivity {


    LinearLayout nativeContainer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quick_tour);
        nativeContainer = findViewById(R.id.native_container);

        // TODO => setup native ads

    }


    public void takeme(View view) {
        Intent intent = new Intent(QuickTour.this, Activity_1.class);
        startActivity(intent);
    }
}