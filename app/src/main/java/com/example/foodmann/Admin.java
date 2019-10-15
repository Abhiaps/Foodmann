package com.example.foodmann;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Admin extends AppCompatActivity {
EditText user,pass;
TextView chance;
int counter=5;
Button submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
    user=(EditText)findViewById(R.id.username);
    pass=(EditText)findViewById(R.id.password);
    chance=(TextView)findViewById(R.id.chances);
    submit=(Button)findViewById(R.id.submit);
    submit.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(user.getText().toString().equals("admin")&&pass.getText().toString().equals("admin"))
            {
                Intent i=new Intent(Admin.this,database.class);
                startActivity(i);
            }
            else
            {
                Toast.makeText(Admin.this,"WRONG CREDENTIALS",Toast.LENGTH_SHORT).show();
                chance.setText(Integer.toString(--counter));

            }
            if(counter==0)
                submit.setEnabled(false);
        }
    });
    }

}
