package com.example.artchain;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.ListResult;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class Gallery extends Fragment implements RecyclerViewInterface {


//    This constructor was made to pass in the getSupportFragmentManager() because it dosn't exist here

    Context ctx;
    ArrayList<String> imgList = new ArrayList<>();
    ProgressBar progressBar;
    RecyclerView recyclerView;
    ImageView selectedimg;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_gallery, container,false);
        ctx = getContext();




//
//        RecyclerView recyclerView =view.findViewById(R.id.recyclerView);
//        RecyclerViewAdapter theAdapter = new RecyclerViewAdapter(imgList, ctx);
//
////        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2, GridLayoutManager.VERTICAL, false);
//        recyclerView.setLayoutManager(gridLayoutManager);
//        StorageReference storageReference = FirebaseStorage.getInstance().getReference().child("Images");
//        storageReference.listAll().addOnSuccessListener(new OnSuccessListener<ListResult>() {
//            @Override
//            public void onSuccess(ListResult listResult) {
//                for (StorageReference fileRef : listResult.getItems()){
//                    fileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
//                        @Override
//                        public void onSuccess(Uri uri) {
//                            imgList.add(uri.toString());
//                            Log.d("item", uri.toString());
//                        }
//                    }).addOnSuccessListener(new OnSuccessListener<Uri>() {
//                        @Override
//                        public void onSuccess(Uri uri) {
//                            recyclerView.setAdapter(theAdapter);
//                        }
//                    });
//                }
//            }
//        });

        return inflater.inflate(R.layout.fragment_gallery, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        progressBar = view.findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);
        selectedimg = view.findViewById(R.id.imgSelected);

        recyclerView = view.findViewById(R.id.recyclerView);


        RecyclerViewAdapter theAdapter = new RecyclerViewAdapter(imgList, ctx, this);

//        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(gridLayoutManager);
        StorageReference storageReference = FirebaseStorage.getInstance().getReference().child("Images");
        storageReference.listAll().addOnSuccessListener(new OnSuccessListener<ListResult>() {
            @Override
            public void onSuccess(ListResult listResult) {
                for (StorageReference fileRef : listResult.getItems()){
                    fileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            imgList.add(uri.toString());
                            Log.d("item", uri.toString());
                        }
                    }).addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            recyclerView.setAdapter(theAdapter);
                            progressBar.setVisibility(View.GONE);
                        }
                    });
                }
            }
        });

    }

    @Override
    public void onItemClick(ArrayList<String> p , int position) {


//        this way was working but i couldnt get it to desplay the selected img in the fragment so ill just use and Activity.

        //int val = (Integer.valueOf(imgList.get(position)));
        //selectedimg.getResources().getDrawable(R.drawable.ogo);


//        FragmentManager fragmentManager = getFragmentManager();
//        fragmentManager.beginTransaction()
//                .replace(R.id.selectedItemContainer, Selected_Img.class, null)
//                .setReorderingAllowed(true)
//                .addToBackStack("going back")
//                .commit();

        Intent intent = new Intent(ctx, Display_sewlected_Img.class);
        intent.putExtra("selected img", p.get(position));
        startActivity(intent);


    }
}