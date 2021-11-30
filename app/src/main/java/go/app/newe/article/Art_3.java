package go.app.newe.article;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.ads.nativetemplates.NativeTemplateStyle;
import com.google.android.ads.nativetemplates.TemplateView;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;

import java.util.Random;

import go.app.newe.App;
import go.app.newe.R;
import go.app.newe.data.a.model.Advertisement;
import go.app.newe.data.a.model.Screen;
import go.app.newe.data.a.model.ViewItem;
import go.app.newe.list.Data_Buttons;

public class Art_3 extends AppCompatActivity {

    private Dialog dialog;
    private Button ShowDialog;

    LinearLayout nativeContainer;

    Handler handler = new Handler();
    private Screen mScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_art3);

        Random addition1 = new Random();
        int additionint1 = addition1.nextInt(198)+5;
        TextView additionText1 = (TextView) findViewById(R.id.number_likes);
        String additionString1 = String.valueOf(additionint1);
        additionText1.setText(additionString1);

        ShowDialog = findViewById(R.id.flag);

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

                Toast.makeText(Art_3.this, "Your message is delivered", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });

        Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(Art_3.this, "Cancel", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });


        ShowDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.show(); // Showing the dialog here
            }
        });



        nativeContainer = findViewById(R.id.native_container);
        setupView();
    }

    public void back(View view) {
        Intent intent = new Intent(Art_3.this, Data_Buttons.class);
        startActivity(intent);
    }

    private void setupView() {
        for (Screen screen : App.getAppConfig().getScreens()) {
            if (screen.getName().equals("article_3_screen")) {
                mScreen = screen;
                break;
            }
        }

        for (ViewItem item : mScreen.getViewItems()) {
            if (item.getName().equals("body_1") && item.getType().equals("textView")) {
                TextView body = findViewById(R.id.body_1);
                body.setText(item.getText());
            } else if (item.getName().equals("body_2") && item.getType().equals("textView")) {
                TextView body = findViewById(R.id.body_2);
                body.setText(item.getText());
            } else if (item.getName().equals("title") && item.getType().equals("textView")) {
                TextView title = findViewById(R.id.title);
                title.setText(item.getText());
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
                        AdLoader adLoader = new AdLoader.Builder(Art_3.this, advertisement.getAdId())
                                .forNativeAd(nativeAd -> {
                                    NativeTemplateStyle styles = new NativeTemplateStyle
                                            .Builder()
                                            .build();
                                    LayoutInflater layoutInflater = (LayoutInflater) Art_3.this.getSystemService(LAYOUT_INFLATER_SERVICE);
                                    View view = layoutInflater.inflate(R.layout.medium_template_view, nativeContainer);
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

    public void share(View view) {
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("text/plain");
        String sharebody = "Amazing game, You should try it!";
        String subject = "https://play.google.com/store/apps/details?id=go.app.newe";
        i.putExtra(Intent.EXTRA_SUBJECT,sharebody);
        i.putExtra(Intent.EXTRA_TEXT,subject);
        startActivity(Intent.createChooser(i,"AVADH TUTOR"));
    }
}
