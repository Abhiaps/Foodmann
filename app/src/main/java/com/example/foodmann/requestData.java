package com.example.foodmann;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class requestData extends AppCompatActivity {
    EditText name,address,phone,total;
    Button nx,px;
    requestHelper db;
    Cursor cursor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requestdata);
        name=(EditText)findViewById(R.id.name);
        address=(EditText)findViewById(R.id.address);
        phone=(EditText)findViewById(R.id.phone);
        total=(EditText)findViewById(R.id.total);
        nx=(Button)findViewById(R.id.next);
        px=(Button)findViewById(R.id.previous);
        db=new requestHelper(this);
        cursor=db.allData();
        cursor.moveToFirst();
        name.setText(cursor.getString(0));
        address.setText(cursor.getString(1));
        phone.setText(cursor.getString(2));
        total.setText(cursor.getString(3));
        nx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!cursor.isLast()){
                    cursor.moveToNext();
                    name.setText(cursor.getString(0));
                    address.setText(cursor.getString(1));
                    phone.setText(cursor.getString(2));
                    total.setText(cursor.getString(3));
                }
            }
        });
        px.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!cursor.isFirst()){
                    cursor.moveToPrevious();
                    name.setText(cursor.getString(0));
                    address.setText(cursor.getString(1));
                    phone.setText(cursor.getString(2));
                    total.setText(cursor.getString(3));
                }
            }
        });
    }
}
