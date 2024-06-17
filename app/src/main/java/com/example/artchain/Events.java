package com.example.artchain;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;

public class Events extends Fragment {
    Context ctx;
    TextView the_event;
    TextView rules;
    ArrayList<Event_middle> middleList = new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_events, container,false);
        ctx = getContext();
        the_event = view.findViewById(R.id.the_event);
        rules = view.findViewById(R.id.note);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference("Event_middle");

        Event_middle eventMiddlemMan = new Event_middle("try", "what?");
        reference.push().setValue(eventMiddlemMan);

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

//                Event_middle value = snapshot.getValue(Event_middle.class);
//                the_event.setText(value.getEvent());
//                rules.setText(value.getRules());

                for(DataSnapshot ds : snapshot.getChildren()){
                    //Event_middle value = snapshot.getValue(Event_middle.class);

                    middleList.add(ds.getValue(Event_middle.class));
                }

                the_event.setText(middleList.get(0).getEvent());
                rules.setText(middleList.get(0).getRules());


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                //Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

        return inflater.inflate(R.layout.fragment_events, container, false);
    }
}