package com.example.foodmann;

import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class notifications extends AppCompatActivity {
TextView noti,counter;
Button nx,px;
DatabaseReference data,mdata;
int count=1;
int total;
int num;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);
        noti=(TextView) findViewById(R.id.noti);
        counter=(TextView) findViewById(R.id.counter);
        nx=(Button)findViewById(R.id.next);
        px=(Button)findViewById(R.id.previous);

        data= FirebaseDatabase.getInstance().getReference("notification/");
        mdata=FirebaseDatabase.getInstance().getReference("notification");
        mdata.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists())
                    total= (int) dataSnapshot.getChildrenCount();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        data.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            for(DataSnapshot ds:dataSnapshot.getChildren()){
                String notif=ds.child("notification").getValue(String.class);
                noti.setText(notif);
                counter.setText(Integer.toString(count));
                px.setEnabled(false);
                if(total==1)
                    nx.setEnabled(false);
                break;
            }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        nx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;
                num=0;
                data.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for(DataSnapshot ds:dataSnapshot.getChildren()){
                            String notif=ds.child("notification").getValue(String.class);
                            num++;
                            if(num==count)
                            {if(count==total)
                            {
                                noti.setText(notif);
                                counter.setText(Integer.toString(count));
                                px.setEnabled(true);
                                nx.setEnabled(false);
                                break;}
                              else{  noti.setText(notif);
                                counter.setText(Integer.toString(count));
                                px.setEnabled(true);
                                break;}
                            }

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
        px.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count--;
                num=0;
                data.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for(DataSnapshot ds:dataSnapshot.getChildren()){
                            String notif=ds.child("notification").getValue(String.class);
                            num++;
                            if(num==count)
                            {if(count==1)
                            {noti.setText(notif);
                                counter.setText(Integer.toString(count));
                                nx.setEnabled(true);
                                px.setEnabled(false);
                                break;
                               }
                            else
                            {noti.setText(notif);
                                counter.setText(Integer.toString(count));
                                nx.setEnabled(true);
                                break;}
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
