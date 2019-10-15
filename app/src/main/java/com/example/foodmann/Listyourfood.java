package com.example.foodmann;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Listyourfood extends AppCompatActivity {
EditText fname,lname,email,phone,pin,pass;
Button submit;
RadioGroup group;
TextView sign;
RadioButton button;
int counter=0;
DatabaseReference data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listyourfood);
        fname=(EditText)findViewById(R.id.fname);
        lname=(EditText)findViewById(R.id.lname);
        pass=(EditText)findViewById(R.id.pass) ;
        sign=(TextView)findViewById(R.id.sign);
        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Listyourfood.this,Vendor_Sign.class);
                startActivity(i);
            }
        });
        email=(EditText)findViewById(R.id.email);
        phone=(EditText)findViewById(R.id.phone);
        pin=(EditText)findViewById(R.id.pin);
        submit=(Button)findViewById(R.id.submit);
        group=(RadioGroup)findViewById(R.id.group);
        data= FirebaseDatabase.getInstance().getReference("vendor");
        data.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists())
                    counter= (int) dataSnapshot.getChildrenCount();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id=group.getCheckedRadioButtonId();
                button=(RadioButton)findViewById(id);
                vendor vendor=new vendor(fname.getText().toString(),lname.getText().toString(),phone.getText().toString(),email.getText().toString(),pin.getText().toString(),""+button.getText(),pass.getText().toString());
                data.child(Integer.toString(counter+1)).setValue(vendor);
                Toast.makeText(Listyourfood.this,"REGISTERED",Toast.LENGTH_LONG).show();

            }
        });

    }
}
