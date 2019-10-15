package com.example.foodmann;



import android.content.Context;
import android.content.ContextWrapper;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class BannerAdapter extends RecyclerView.Adapter<BannerAdapter.BannerHolder> {
    private ArrayList<String> data;
    public BannerAdapter(ArrayList<String> data) {
        this.data=data;
    }

    @NonNull
    @Override
    public BannerHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater=LayoutInflater.from(viewGroup.getContext());
        View view=inflater.inflate(R.layout.bannerlayout,viewGroup,false);
        return new BannerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BannerHolder BannerHolder, int i) {
     String list=data.get(i);
        Picasso.get().load(list).into(BannerHolder.img);

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class BannerHolder extends RecyclerView.ViewHolder {
        ImageView img;
        private itemClickListener itemClickListener;
        public BannerHolder(@NonNull View itemView) {

            super(itemView);
            img=itemView.findViewById(R.id.img);

        }


    }
}
