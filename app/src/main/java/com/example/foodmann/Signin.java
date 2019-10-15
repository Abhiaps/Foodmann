package com.example.foodmann;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Signin extends AppCompatActivity {
EditText phone,pass;
Button signin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
    phone=(EditText)findViewById(R.id.phone);
    pass=(EditText)findViewById(R.id.pass);
    signin=(Button)findViewById(R.id.signin);

        FirebaseDatabase data=FirebaseDatabase.getInstance();
        final DatabaseReference table=data.getReference("user");
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                table.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    user user=dataSnapshot.child(phone.getText().toString()).getValue(user.class);
                    if(dataSnapshot.child(phone.getText().toString()).exists())

                    {user.setPhone(phone.getText().toString());
                        if(user.getPassword().equals(pass.getText().toString())) {
                        Intent i = new Intent(Signin.this, Home1.class);
                        Common.currentuser=user;
                        startActivity(i);
                    }
                    else
                        Toast.makeText(Signin.this,"WRONG CREDENTIALS",Toast.LENGTH_LONG).show();
                    }
                    else Toast.makeText(Signin.this,"NOT REGISTERED",Toast.LENGTH_LONG).show();
                    }



                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
    }
}
