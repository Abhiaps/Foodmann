package com.example.foodmann;

import android.app.AlertDialog;
import android.support.annotation.NonNull;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.example.foodmann.Database.Database;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.Random;

public class Fooddetail extends AppCompatActivity {
TextView food_name,food_price,food_description;
ImageView food_image;
CollapsingToolbarLayout collapsingToolbarLayout;
FloatingActionButton btnCart;
ElegantNumberButton numberButton;
String foodId="";
FirebaseDatabase database;
String num;
TextView nm;
DatabaseReference foods,foods1;
Food currentFood;
String number;
String num1="0";
String special;
int random;
String price;
int a;
CheckBox ghee,rice,amul;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fooddetail);
        database=FirebaseDatabase.getInstance();
        nm=(TextView)findViewById(R.id.nm);
        random= new Random().nextInt(50);
        btnCart=(FloatingActionButton)findViewById(R.id.btnCart);
        foods=database.getReference("food");
        num=getIntent().getStringExtra("FoodId");
        foods1=FirebaseDatabase.getInstance().getReference("food/"+num);
        numberButton=(ElegantNumberButton)findViewById(R.id.number_button);
        ghee=(CheckBox)findViewById(R.id.ghee);
        rice=(CheckBox)findViewById(R.id.rice);
        foods1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                special=dataSnapshot.child("special").getValue().toString();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

       foods1.addListenerForSingleValueEvent(new ValueEventListener() {
           @Override
           public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
               number=dataSnapshot.child("number").getValue(String.class);
               if(number.equals(num1))
                   btnCart.setEnabled(false);
               if(special.equals("special")){
                   showMessage("Welcome", "You Will Get Discount Upto 50 Percent\n"+"AVAILABLE: "+number);
               }
               else showMessage("Welcome","AVAILABLE: "+number);
           }

           @Override
           public void onCancelled(@NonNull DatabaseError databaseError) {

           }
       });

        amul=(CheckBox)findViewById(R.id.amul);


        btnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
if(special.equals("special")){
                price=currentFood.getPrice();
                a=Integer.parseInt(price)*(100-random)/100;
                if(ghee.isChecked())
                {a=a+50;}
                if(amul.isChecked())
                {
                    a=a+80;
                }
                 new Database(getBaseContext()).addToCart(new Order(
                foodId,
                currentFood.getName(),
                numberButton.getNumber(),
                Integer.toString(a),
                currentFood.getDiscount()

));
                Toast.makeText(Fooddetail.this,"ADDED TO CART",Toast.LENGTH_LONG).show();
            }else{
    price=currentFood.getPrice();
    a=Integer.parseInt(price);
    if(ghee.isChecked())
    {a=a+50;}
    if(amul.isChecked())
    {
        a=a+80;
    }
    new Database(getBaseContext()).addToCart(new Order(
            foodId,
            currentFood.getName(),
            numberButton.getNumber(),
            Integer.toString(a),
            currentFood.getDiscount()

    ));
    Toast.makeText(Fooddetail.this,"ADDED TO CART",Toast.LENGTH_LONG).show();
            }
            }
        });
        food_name=(TextView)findViewById(R.id.food_name);
        food_price=(TextView)findViewById(R.id.food_price);
        food_description=(TextView)findViewById(R.id.food_description);

        food_image=(ImageView)findViewById(R.id.food_image);
        collapsingToolbarLayout=(CollapsingToolbarLayout)findViewById(R.id.collapsing);
        collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.ExpandedAppbar);
        collapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.CollapsedAppbar);
        foodId=getIntent().getStringExtra("FoodId");
        getDetailFood(foodId);
    }

    private void getDetailFood(String foodId) {
        foods.child(foodId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                currentFood=dataSnapshot.getValue(Food.class);
                Picasso.get().load(currentFood.getImage())
                        .into(food_image);
                collapsingToolbarLayout.setTitle(currentFood.getName());
                food_price.setText(currentFood.getPrice());
                food_name.setText(currentFood.getName());
                food_description.setText(currentFood.getDescription());
                nm.setText(currentFood.getName());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    public void showMessage(String title,String Message){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
}
