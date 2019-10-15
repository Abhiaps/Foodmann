package com.example.foodmann;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.foodmann.Database.Database;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Vendor_Sign extends AppCompatActivity {
EditText phoneNo,pass;
DatabaseReference data;
int num=0;
Button signin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendor__sign);
        phoneNo=(EditText)findViewById(R.id.phone);
        pass=(EditText)findViewById(R.id.pass);
        signin=(Button)findViewById(R.id.signin);
        data= FirebaseDatabase.getInstance().getReference("vendor/");
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for(DataSnapshot ds:dataSnapshot.getChildren())
                        {
                            String password=ds.child("password").getValue(String.class);
                            String phone=ds.child("phone").getValue(String.class);
                            if(password.equals(pass.getText().toString())&&phone.equals(phoneNo.getText().toString())){
                                Intent i=new Intent(Vendor_Sign.this,Your_Food.class);
                                startActivity(i);
                                num++;
                            }
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
    }
}
