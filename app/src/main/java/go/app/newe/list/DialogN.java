package go.app.newe.list;

import android.hardware.lights.LightState;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.fragment.app.DialogFragment;

import com.bumptech.glide.Glide;

import org.jetbrains.annotations.Contract;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import go.app.newe.App;
import go.app.newe.R;

public class DialogN extends DialogFragment {


    @NonNull
    @Contract(" -> new")
    public static DialogN getInstance() {
        return new DialogN();
    }

    private DialogNListener listener;

    public void setListener(DialogNListener listener) {
        this.listener = listener;
    }

    private List<String> photos = new ArrayList<>();

    private CircleImageView imageView1;
    private CircleImageView imageView2;
    private CircleImageView imageView3;
    private CircleImageView imageView4;
    private CircleImageView imageView5;
    private CircleImageView imageView6;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.custom_image_dialog, container, false);

        photos.add("https://randomuser.me/api/portraits/men/32.jpg");
        photos.add("https://randomuser.me/api/portraits/men/10.jpg");
        photos.add("https://randomuser.me/api/portraits/men/11.jpg");
        photos.add("https://randomuser.me/api/portraits/men/12.jpg");
        photos.add("https://randomuser.me/api/portraits/men/13.jpg");
        photos.add("https://randomuser.me/api/portraits/men/14.jpg");


        imageView1 = view.findViewById(R.id.image_1);
        imageView2 = view.findViewById(R.id.image_2);
        imageView3 = view.findViewById(R.id.image_3);
        imageView4 = view.findViewById(R.id.image_4);
        imageView5 = view.findViewById(R.id.image_5);
        imageView6 = view.findViewById(R.id.image_6);

        Glide.with(this).load(photos.get(0)).into(imageView1);
        Glide.with(this).load(photos.get(1)).into(imageView2);
        Glide.with(this).load(photos.get(2)).into(imageView3);
        Glide.with(this).load(photos.get(3)).into(imageView4);
        Glide.with(this).load(photos.get(4)).into(imageView5);
        Glide.with(this).load(photos.get(5)).into(imageView6);

        imageView1.setOnClickListener((view1 -> {
            App.getDataManager().setUserImage(photos.get(0));
            listener.onImageClick();
        }));

        imageView2.setOnClickListener((view1 -> {
            App.getDataManager().setUserImage(photos.get(1));
            listener.onImageClick();
        }));

        imageView3.setOnClickListener((view1 -> {
            App.getDataManager().setUserImage(photos.get(2));
            listener.onImageClick();
        }));

        imageView4.setOnClickListener((view1 -> {
            App.getDataManager().setUserImage(photos.get(3));
            listener.onImageClick();
        }));

        imageView5.setOnClickListener((view1 -> {
            App.getDataManager().setUserImage(photos.get(4));
            listener.onImageClick();
        }));

        imageView6.setOnClickListener((view1 -> {
            App.getDataManager().setUserImage(photos.get(5));
            listener.onImageClick();
        }));

        return view;
    }

    public interface DialogNListener {
        void onImageClick();
    }
}
