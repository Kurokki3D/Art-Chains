package com.example.artchain;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;

import java.io.File;
import java.util.ArrayList;

public class Internal_lmeges extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<Image> arrayList = new ArrayList<>();
    private final ActivityResultLauncher<String> activityResultLauncher = registerForActivityResult(new ActivityResultContracts.RequestPermission(),
            result -> {
                if (result){
                    getImages();
                }
            });
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_internal_lmeges);

        recyclerView = findViewById(R.id.recyclerView2);
        recyclerView.setLayoutManager(new LinearLayoutManager(Internal_lmeges.this));
        recyclerView.setHasFixedSize(true);

        if (ActivityCompat.checkSelfPermission(Internal_lmeges.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            activityResultLauncher.launch(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        
        } else if (ActivityCompat.checkSelfPermission(Internal_lmeges.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            activityResultLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE);

        }else {
            getImages();
        }
    }

    public void getImages(){
        arrayList.clear();

        String filePath = "/storage/emulated/0/Pictures";
        //File file = new File(filePath);
        File file = new File(getExternalFilesDir(null).getAbsolutePath());
        //File file = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);


        File[] files = file.listFiles();
        if (files != null) {
            for (File file1: files) {
                if (file1.getPath().endsWith(".png") || file1.getPath().endsWith(".jpg") || file1.getPath().endsWith(".jpeg")) {
                    arrayList.add(new Image(file1.getName(), file1.getPath(), file1.length()));
                }
            }
        }

        ImageAdapter imageAdapter = new ImageAdapter(arrayList, this);
        recyclerView.setAdapter(imageAdapter);

        imageAdapter.setOnItemClickListener((view, path) -> startActivity(new Intent(Internal_lmeges.this, File_SelectedImage.class).putExtra("image", path)));
    }
}