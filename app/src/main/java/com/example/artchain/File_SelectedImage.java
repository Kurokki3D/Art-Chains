package com.example.artchain;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class File_SelectedImage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_selected_image);

        ImageView imageView = findViewById(R.id.file_Selectedimg);
        Intent intent = getIntent();
        if (intent != null){
            Glide.with(this).load(intent.getStringExtra("image")).placeholder(R.drawable.ogo).into(imageView);
        }
    }
}