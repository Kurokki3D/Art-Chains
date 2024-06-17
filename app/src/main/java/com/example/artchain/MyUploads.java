package com.example.artchain;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class MyUploads extends Fragment {

    RecyclerView recyclerView;
    Context ctx;
    Button button;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_uploads, container,false);

        ctx = getContext();
        recyclerView = view.findViewById(R.id.your_img_recyclerView);
        button = view.findViewById(R.id.to_phonestorage);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ctx, Internal_lmeges.class);
                startActivity(intent);

            }
        });

        return view;
    }

}