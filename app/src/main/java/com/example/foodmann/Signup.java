package com.example.foodmann;

import android.os.StrictMode;
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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Random;

public class Signup extends AppCompatActivity {
    EditText phone,name,pass,otp;
    Button signout,send;
    int rnd;
    String z;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        phone=(EditText)findViewById(R.id.phone);
        pass=(EditText)findViewById(R.id.pass);
        name=(EditText)findViewById(R.id.name);
        otp=(EditText)findViewById(R.id.otp);
        send=(Button)findViewById(R.id.send);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(phone.getText().toString().length()==10) {
                    Random random = new Random();
                    rnd = random.nextInt(999999);
                    //Your authentication key
                    String authkey = "271173Aa2sPsaGy6zq5ca86df3";
                    //Multiple mobiles numbers separated by comma
                    String mobiles = phone.getText().toString();
                    //Sender ID,While using route4 sender id should be 6 characters long.
                    String senderId = "611332";
                    //Your message to send, Add URL encoding here.
                    String message = "Your OTP IS " + rnd;
                    //define route
                    String route = "4";

                    //Prepare Url
                    URLConnection myURLConnection = null;
                    URL myURL = null;
                    BufferedReader reader = null;

                    //encoding message
                    String encoded_message = URLEncoder.encode(message);

                    //Send SMS API
                    String mainUrl = "http://api.msg91.com/api/sendhttp.php?";

                    //Prepare parameter string
                    StringBuilder sbPostData = new StringBuilder(mainUrl);
                    sbPostData.append("authkey=" + authkey);
                    sbPostData.append("&mobiles=" + mobiles);
                    sbPostData.append("&message=" + encoded_message);
                    sbPostData.append("&route=" + route);
                    sbPostData.append("&sender=" + senderId);

                    //final string
                    mainUrl = sbPostData.toString();
                    try {
                        //prepare connection
                        myURL = new URL(mainUrl);
                        myURLConnection = myURL.openConnection();
                        myURLConnection.connect();
                        reader = new BufferedReader(new InputStreamReader(myURLConnection.getInputStream()));
                        //reading response
                        String response;
                        while ((response = reader.readLine()) != null)
                            //print response
                            System.out.println(response);

                        //finally close connection
                        reader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                else
                    Toast.makeText(Signup.this,"LENGTH OF PHONE NUMBER SHOULD BE 10",Toast.LENGTH_SHORT).show();

            }
        });
        StrictMode.ThreadPolicy st=new StrictMode.ThreadPolicy.Builder().build();
        StrictMode.setThreadPolicy(st);
        signout=(Button)findViewById(R.id.signout);
        FirebaseDatabase data=FirebaseDatabase.getInstance();
        final DatabaseReference table=data.getReference("user");
        final DatabaseReference mdata=data.getReference();

        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                z = Integer.toString(rnd);
                if(z.equals(otp.getText().toString()))
                {table.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if(dataSnapshot.child(phone.getText().toString()).exists())    {

                    }
                    else
                    {
                        user user=new user(name.getText().toString(),pass.getText().toString());
                        mdata.child("user").child(phone.getText().toString()).setValue(user);
                        Toast.makeText(Signup.this,"REGISTERED",Toast.LENGTH_LONG).show();

                    }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });}
                else Toast.makeText(Signup.this,"PLEASE ENTER CORRECT OTP",Toast.LENGTH_SHORT).show();
            }
        });

    }
}
