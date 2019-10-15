package com.example.foodmann;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MenuViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
  public TextView txt;
  public ImageView img;
  public TextView description;
  public TextView id;
  public TextView add;
  public TextView distance;
  public TextView time;
    private itemClickListener itemClickListener;

    public MenuViewHolder(View itemView){
       super(itemView);
       txt=(TextView)itemView.findViewById(R.id.txt);
       img=(ImageView)itemView.findViewById(R.id.img);
       description=(TextView) itemView.findViewById(R.id.description);
       id=(TextView)itemView.findViewById(R.id.id);
       add=(TextView)itemView.findViewById(R.id.add);
       distance=(TextView)itemView.findViewById(R.id.distance);
       time=(TextView)itemView.findViewById(R.id.time);
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
