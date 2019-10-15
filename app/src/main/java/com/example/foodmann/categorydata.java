package com.example.foodmann;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class categorydata extends AppCompatActivity {
EditText name,link;
Button nx,px;
MenuHelper db;
Cursor cursor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categorydata);
        name=(EditText)findViewById(R.id.name);
        link=(EditText)findViewById(R.id.link);
        nx=(Button)findViewById(R.id.next);
        px=(Button)findViewById(R.id.previous);
        db=new MenuHelper(this);
        cursor=db.allData();
        cursor.moveToFirst();
        name.setText(cursor.getString(0));
        link.setText(cursor.getString(1));
        nx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!cursor.isLast()){
                    cursor.moveToNext();
                    name.setText(cursor.getString(0));
                    link.setText(cursor.getString(1));
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
                }
            }
        });
    }
}
