package com.example.foodmann;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class database extends AppCompatActivity {
Button user,menu,food;
DatabaseReference data,data1,data2;
requestHelper db;
MenuHelper db1;
FoodHelper db2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);
        user=(Button)findViewById(R.id.user);
        menu=(Button)findViewById(R.id.menu);
        db=new requestHelper(this);
        db1=new MenuHelper(this);
        db2=new FoodHelper(this);
        db.cleanData();
        db1.cleanData();
        db2.cleanData();
        data= FirebaseDatabase.getInstance().getReference("Requests/");
        data1=FirebaseDatabase.getInstance().getReference("category/");
        data2=FirebaseDatabase.getInstance().getReference("food/");
        data.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds:dataSnapshot.getChildren())
                {
                    String name=ds.child("name").getValue(String.class);
                    String address=ds.child("address").getValue(String.class);
                    String phone=ds.child("phone").getValue(String.class);
                    String total=ds.child("total").getValue(String.class);
                    db.insertData(name,address,phone,total);
                }
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        data1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds:dataSnapshot.getChildren())
                {
                    String name=ds.child("name").getValue(String.class);
                    String link=ds.child("image").getValue(String.class);

                    db1.insertData(name,link);
                }
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        data2.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds:dataSnapshot.getChildren())
                {
                    String name=ds.child("name").getValue(String.class);
                    String link=ds.child("image").getValue(String.class);
                    String menu=ds.child("menu").getValue(String.class);
                    String price=ds.child("price").getValue(String.class);
                    String discount=ds.child("discount").getValue(String.class);
                    String discription=ds.child("description").getValue(String.class);
                    db2.insertData(name,link,menu,price,discount,discription);
                }
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        food=(Button)findViewById(R.id.food);
        user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(database.this, requestData.class);
                startActivity(i);
            }
        });
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              Intent i=new Intent(database.this,categorydata.class);
              startActivity(i);
            }
        });
        food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(database.this,fooddata.class);
                startActivity(i);
            }
        });
    }
}
