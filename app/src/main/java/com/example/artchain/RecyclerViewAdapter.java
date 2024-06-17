package com.example.artchain;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private ArrayList<String>imageList = new ArrayList<>();
    private Context context;
    private final RecyclerViewInterface recyclerViewInterface;

    public RecyclerViewAdapter(ArrayList<String> imageList, Context ctx, RecyclerViewInterface recyclerViewInterface) {
        this.imageList = imageList;
//        this.context = context;
        this.recyclerViewInterface = recyclerViewInterface;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.r_v_refrence,parent,false);
        return new ViewHolder(view, recyclerViewInterface, imageList);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, int position) {
        Picasso.get().load(imageList.get(position)).into(holder.imageView);
//        holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                FragmentManager fragmentManager = getSoportFragmentManager();
//                fragmentManager.beginTransaction()
//                        .replace(R.id.selectedItemContainer, Selected_Img.class, null)
//                        .setReorderingAllowed(true)
//                        .addToBackStack("going back")
//                        .commit();
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return imageList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        ConstraintLayout constraintLayout;
        public ViewHolder(@NonNull View itemView, RecyclerViewInterface recyclerViewInterface, ArrayList<String> iL) {
            super(itemView);
            constraintLayout = itemView.findViewById(R.id.k);
            imageView = itemView.findViewById(R.id.img);
            imageView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    if (recyclerViewInterface != null){
                        int pos = getAdapterPosition();

                        if (pos != RecyclerView.NO_POSITION){
                            recyclerViewInterface.onItemClick(iL,pos);
                        }
                    }
                    }
            });
        }
    }
}
