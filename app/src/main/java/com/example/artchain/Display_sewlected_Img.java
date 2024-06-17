package com.example.artchain;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Display_sewlected_Img extends AppCompatActivity {
    ImageView selected_image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_sewlected_img);

        String selected_img = getIntent().getStringExtra("selected img");
        selected_image = findViewById(R.id.imageView);

        if (selected_img == null) {
            Log.d("ISNULL", "NULL");
        } else {
            Log.d("ISNULL", "NOT NULL");
        }
        //Log.d("selected img", selected_img);
        Picasso.get().load(selected_img).into(selected_image);
//        selected_image.setImageResource(Integer.parseInt(the_images.get(Integer.parseInt("1"))));
    }
}