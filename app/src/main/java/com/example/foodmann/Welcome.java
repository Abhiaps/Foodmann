package com.example.foodmann;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Welcome extends AppCompatActivity {
Button signin,signout,admin,vendor;
ImageView admin1;
ImageView notifications;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
    signin=(Button)findViewById(R.id.signin);

    signout=(Button)findViewById(R.id.signout);

    notifications=(ImageView) findViewById(R.id.notifications);
    notifications.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent i=new Intent(Welcome.this,notifications.class);
            startActivity(i);
        }
    });
    vendor=(Button)findViewById(R.id.vendor);
    vendor.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent i=new Intent(Welcome.this,Listyourfood.class);
            startActivity(i);
        }
    });
    admin=(Button)findViewById(R.id.admin);
    admin1=(ImageView)findViewById(R.id.admin1);
    signin.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent i=new Intent(Welcome.this,Signin.class);
            startActivity(i);
        }
    });
    signout.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent i=new Intent(Welcome.this, Signup.class);
            startActivity(i);
        }
    });
    admin.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent i=new Intent(Welcome.this,Admin.class);
            startActivity(i);
        }
    });
        admin1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Welcome.this,Admin.class);
                startActivity(i);
            }
        });
    }
}
