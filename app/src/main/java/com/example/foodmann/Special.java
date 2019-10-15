package com.example.foodmann;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class Special extends AppCompatActivity {
    FirebaseDatabase database;
    DatabaseReference foodlist;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView recyclerfood;
    FirebaseRecyclerAdapter<Food,FoodViewHolder> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foodlist);
        database=FirebaseDatabase.getInstance();
        foodlist=database.getReference("food");
        recyclerfood=(RecyclerView)findViewById(R.id.recyclerfood);
        recyclerfood.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(this);
        recyclerfood.setLayoutManager(layoutManager);
        Bundle bundle=getIntent().getExtras();

        loadlistFood();
    }

    private void loadlistFood() {

        adapter=new FirebaseRecyclerAdapter<Food, FoodViewHolder>(Food.class,R.layout.food_item,FoodViewHolder.class,foodlist.orderByChild("special").equalTo("special")) {
            @Override
            protected void populateViewHolder(FoodViewHolder viewHolder, final Food model, int position) {
                viewHolder.txt1.setText(model.getName());
                viewHolder.price.setText(model.getPrice());
                viewHolder.discount.setText(model.getDiscount());
                viewHolder.plates.setText(model.getNumber());
                viewHolder.desc.setText(model.getDescription());
                Picasso.get().load(model.getImage())
                        .into(viewHolder.img1);

                final Food clickitem=model;
                viewHolder.setItemClickListener(new itemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                        Intent i=new Intent(Special.this,Fooddetail.class);
                        i.putExtra("FoodId",adapter.getRef(position).getKey());
                        startActivity(i);
                    }
                });

            }
        };
        recyclerfood.setAdapter(adapter);
    }
}
