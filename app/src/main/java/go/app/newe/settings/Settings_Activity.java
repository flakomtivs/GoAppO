package go.app.newe.settings;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SwitchCompat;

import android.app.Dialog;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Toast;

import go.app.newe.R;
import go.app.newe.article.Art_1;
import go.app.newe.pages.Activity_1;
import go.app.newe.quick.QuickTour;

public class Settings_Activity extends AppCompatActivity {

    private Dialog dialog;
    private Button ShowDialog;
    private SwitchCompat darkModeSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);


        ShowDialog = findViewById(R.id.report);
        //Create the Dialog here
        dialog = new Dialog(this);
        dialog.setContentView(R.layout.custom_dialog_layout);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            dialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.custom_dialog_background));
        }
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(false); //Optional
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation; //Setting the animations to dialog
        Button Okay = dialog.findViewById(R.id.btn_okay);
        Button Cancel = dialog.findViewById(R.id.btn_cancel);
        Okay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Settings_Activity.this, "Your message is delivered!", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
        Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Settings_Activity.this, "Cancel", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
        ShowDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.show(); // Showing the dialog here
            }
        });

        darkModeSwitch = findViewById(R.id.dark_mode_switch);
        int currentNightMode = getResources().getConfiguration().uiMode
                & Configuration.UI_MODE_NIGHT_MASK;

        darkModeSwitch.setChecked(currentNightMode != Configuration.UI_MODE_NIGHT_NO);

        darkModeSwitch.setOnCheckedChangeListener((compoundButton, b) -> {
            if (b && compoundButton.isPressed()) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            } else if (!b && compoundButton.isPressed()) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            }
        });

    }

    public void profil(View view) {
        Intent intent = new Intent(Settings_Activity.this, My_Profil.class);
        startActivity(intent);
    }

    public void update(View view) {
        Toast.makeText(Settings_Activity.this, "Current Version 2.0", Toast.LENGTH_SHORT).show();
        dialog.dismiss();
    }

    public void about(View view) {
        Intent intent = new Intent(Settings_Activity.this, About_App.class);
        startActivity(intent);
    }

    public void share(View view) {
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("text/plain");
        String sharebody = "Amazing game, You should try it!";
        String subject = "https://play.google.com/store/apps/details?id=go.app.newe";
        i.putExtra(Intent.EXTRA_SUBJECT, sharebody);
        i.putExtra(Intent.EXTRA_TEXT, subject);
        startActivity(Intent.createChooser(i, "AVADH TUTOR"));

    }


}
