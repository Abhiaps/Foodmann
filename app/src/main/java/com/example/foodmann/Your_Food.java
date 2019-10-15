package com.example.foodmann;

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

public class Your_Food extends AppCompatActivity {
EditText name,link,iname,ilink,iprice,idiscount,idescription,owner,number,addr,longitude,latitude;
Button upload;
int menu=0,menu1=0;
String num111="";
DatabaseReference data,data1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your__food);
        name=(EditText)findViewById(R.id.name);
        link=(EditText)findViewById(R.id.link);
        longitude=(EditText)findViewById(R.id.longitude);
        latitude=(EditText)findViewById(R.id.latitude);
        iname=(EditText)findViewById(R.id.iname);
        addr=(EditText)findViewById(R.id.addr);
        ilink=(EditText)findViewById(R.id.ilink);
        number=(EditText)findViewById(R.id.number);
        iprice=(EditText)findViewById(R.id.iprice);
        idiscount=(EditText)findViewById(R.id.idiscount);
        owner=(EditText)findViewById(R.id.owner);
        idescription=(EditText)findViewById(R.id.idescription);
        upload=(Button)findViewById(R.id.upload);
        data= FirebaseDatabase.getInstance().getReference("category");
        data1=FirebaseDatabase.getInstance().getReference("food");
        data.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists())
                    menu= (int) dataSnapshot.getChildrenCount();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        data1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists())
                    menu1= (int) dataSnapshot.getChildrenCount();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                category1 category=new category1(name.getText().toString(),link.getText().toString(),owner.getText().toString(),addr.getText().toString(),"",latitude.getText().toString(),longitude.getText().toString());
                data.child(Integer.toString(menu+1)).setValue(category);
                food11111 food=new food11111(iname.getText().toString(),ilink.getText().toString(),idescription.getText().toString(),iprice.getText().toString(),idiscount.getText().toString(),Integer.toString(menu+1),number.getText().toString(),num111);
                data1.child(Integer.toString(menu+1)).setValue(food);
                Toast.makeText(Your_Food.this,"REGISTERED",Toast.LENGTH_LONG).show();
            }
        });
    }
}
