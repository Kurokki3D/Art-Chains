package com.example.artchain;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.LiveFolders;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.ListResult;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager2 viewPager;

    Toolbar toolbar;

    ImageView menueImg;

    String folderNane = "ArtChain";
    public static final int PERMISSION_REQUEST_CODE = 7;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        created a folder here to hold the imeges from storage;
        if (ContextCompat.checkSelfPermission(MainActivity.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
            createDirectory(folderNane);

        }else {
            askPermission();
        }


//      Didnt use an action bar, used a toolBar instead (its better) this is where the ToolBar was set
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//      These are initialisations
        tabLayout = findViewById(R.id.TabLayout);
        viewPager = findViewById(R.id.ViewPager2);
        menueImg = findViewById(R.id.menueimg);

        StorageReference storageReference = FirebaseStorage.getInstance().getReference().child("Featured");
        storageReference.listAll().addOnSuccessListener(new OnSuccessListener<ListResult>() {
            @Override
            public void onSuccess(ListResult listResult) {
                for (StorageReference fileRef : listResult.getItems()){
                    fileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            Picasso.get().load(uri.toString()).into(menueImg);
                        }
                    });
                }
            }
        });

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), getLifecycle());
        viewPagerAdapter.getFragment(new Gallery());
        viewPagerAdapter.getFragment(new MyUploads());
        viewPagerAdapter.getFragment(new Events());

        viewPager.setAdapter(viewPagerAdapter);
        viewPager.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);

        new TabLayoutMediator(
                tabLayout,
                viewPager,

                new TabLayoutMediator.TabConfigurationStrategy() {
                    @Override
                    public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                        switch (position){
                            case 0:
                                tab.setText("Gallery");
                                tab.setIcon(getResources().getDrawable(android.R.drawable.ic_dialog_dialer));
                                break;

                            case 1:
                                tab.setText("MyArt");
                                tab.setIcon(getResources().getDrawable(android.R.drawable.checkbox_on_background));
                                break;

                            case 2:
                                tab.setText("Events");
                                tab.setIcon(getResources().getDrawable(android.R.drawable.ic_menu_edit));
                                break;

                            default:
                        }
                    }
                }).attach();
    }

//    needed for notifications;
    private void askPermission() {
        ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull @NotNull String[] permissions, @NonNull @NotNull int[] grantResults) {

        if (requestCode == PERMISSION_REQUEST_CODE){
            if (grantResults.length == 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                createDirectory(folderNane);

            }else {
                Toast.makeText(this, "permission denied", Toast.LENGTH_LONG).show();
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    private void createDirectory(String folderNane) {
        File file = new File(Environment.getExternalStorageDirectory(), folderNane);

//        File file = this.getDir("users", Context.MODE_PRIVATE);
        if (!file.exists()){
            file.mkdir();
            Toast.makeText(this, "Sucessful", Toast.LENGTH_LONG).show();

        }else {
            Toast.makeText(this, "folder already exist", Toast.LENGTH_LONG).show();

        }

    }

    //    Override the onCreate to add the menu, use menu inflater to inflate
//    the menu then pass in the menu you made and the menu from bracket(Menu menu)
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.collapsing_menue,menu);
        return true;
    }


//    This Override is to set click actions for the items in the menu, use an if statement
//    to check witch item got clicked and perform the selected action
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.add) {
            Toast.makeText(this, "hide", Toast.LENGTH_LONG).show();

        } else if (item.getItemId() == R.id.settings) {
            Toast.makeText(this, "run", Toast.LENGTH_LONG).show();
        }
        return super.onOptionsItemSelected(item);
    }

}