package com.example.foodmann;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class fooddata extends AppCompatActivity {
    EditText name,link,menu,price,description,discount;
    Button nx,px;
    FoodHelper db;
    Cursor cursor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fooddata);
        name=(EditText)findViewById(R.id.name);
        link=(EditText)findViewById(R.id.link);
        menu=(EditText)findViewById(R.id.menu);
        price=(EditText)findViewById(R.id.price);
        description=(EditText)findViewById(R.id.description);
        discount=(EditText)findViewById(R.id.discount);
        nx=(Button)findViewById(R.id.next);
        px=(Button)findViewById(R.id.previous);
        db=new FoodHelper(this);
        cursor=db.allData();
        cursor.moveToFirst();
        name.setText(cursor.getString(0));
        link.setText(cursor.getString(1));
        menu.setText(cursor.getString(2));
        price.setText(cursor.getString(3));
        discount.setText(cursor.getString(4));
        description.setText(cursor.getString(5));
        nx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!cursor.isLast()){
                    cursor.moveToNext();
                    name.setText(cursor.getString(0));
                    link.setText(cursor.getString(1));
                    menu.setText(cursor.getString(2));
                    price.setText(cursor.getString(3));
                    discount.setText(cursor.getString(4));
                    description.setText(cursor.getString(5));
                }
            }
        });
        px.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!cursor.isFirst()){
                    cursor.moveToPrevious();
                    name.setText(cursor.getString(0));
                    link.setText(cursor.getString(1));
                    menu.setText(cursor.getString(2));
                    price.setText(cursor.getString(3));
                    discount.setText(cursor.getString(4));
                    description.setText(cursor.getString(5));
                }
            }
        });
    }
}
