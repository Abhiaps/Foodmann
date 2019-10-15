package com.example.foodmann;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class FoodViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView txt1;
    public ImageView img1;
    public TextView plates;
    public TextView price;
    public TextView desc;
    public TextView discount;
    private itemClickListener itemClickListener;

    public FoodViewHolder(View itemView){
        super(itemView);
        txt1=(TextView)itemView.findViewById(R.id.txt1);
        img1=(ImageView)itemView.findViewById(R.id.img1);
        plates=(TextView)itemView.findViewById(R.id.plates);
        price=(TextView)itemView.findViewById(R.id.price);
        desc=(TextView)itemView.findViewById(R.id.desc);
        discount=(TextView)itemView.findViewById(R.id.discount);
        itemView.setOnClickListener(this);
    }
    public void setItemClickListener(itemClickListener itemClickListener)
    {
        this.itemClickListener=itemClickListener;
    }
    @Override
    public void onClick(View v) {
        itemClickListener.onClick(v,getAdapterPosition(),false);
    }
}
