package com.example.foodmann;

import android.Manifest;
import android.app.AlertDialog;
import com.firebase.ui.database.FirebaseRecyclerAdapter;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.firebase.ui.FirebaseRecyclerViewAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class Searched extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private static final int REQUEST_LOCATION =1 ;
    private DrawerLayout draw;
    private ActionBarDrawerToggle toggle;
    FirebaseDatabase database;
    DatabaseReference category,cancellation;
    RecyclerView recycler;
    String owner;
    EditText foodsupplier;
    FirebaseRecyclerAdapter<category,MenuViewHolder> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searched);
        owner=getIntent().getStringExtra("owner");
        foodsupplier=(EditText)findViewById(R.id.foodsupplier);
        cancellation=FirebaseDatabase.getInstance().getReference("Cancellations");
        draw=(DrawerLayout)findViewById(R.id.drawer);
        recycler=(RecyclerView)findViewById(R.id.recycler);
        RecyclerView.LayoutManager layoutManager;
        toggle=new ActionBarDrawerToggle(this,draw,R.string.open,R.string.close);
        draw.addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        NavigationView navigation=(NavigationView)findViewById(R.id.navigation);
        navigation.setNavigationItemSelectedListener(this);
        database=FirebaseDatabase.getInstance();
        category=database.getReference("category");
        recycler.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(this);
        recycler.setLayoutManager(layoutManager);
        loadMenu(owner);
    }

    private void loadMenu(String owner) {
        adapter=new FirebaseRecyclerAdapter<category,MenuViewHolder>(category.class,R.layout.menu_item,MenuViewHolder.class,category.orderByChild("owner").equalTo(owner)){
            @Override
            protected void populateViewHolder(MenuViewHolder holder,category category,int position){
                Location sp=new Location("la");
                sp.setLatitude(Common.lat);
                sp.setLongitude(Common.lon);
                Location ep=new Location("lb");
                ep.setLatitude(Double.parseDouble(category.getLatitude()));
                ep.setLongitude(Double.parseDouble(category.getLongitude()));
                int num111= (int) sp.distanceTo(ep);
                holder.txt.setText(category.getName());
                holder.description.setText(category.getOwner());
                holder.id.setText(category.getStars());
                holder.add.setText(category.getAddress());
                holder.distance.setText(Integer.toString(num111/1000)+" Km");
                holder.time.setText(""+(num111*60/40000)+" minutes");
                Picasso.get().load(category.getImage())
                        .into(holder.img);
                final category clickitem=category;
                holder.setItemClickListener(new itemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                        Intent i=new Intent(Searched.this,Foodlist.class);
                        Bundle bundle=new Bundle();
                        bundle.putString("CategoryId",adapter.getRef(position).getKey());
                        i.putExtras(bundle);
                        startActivity(i);
                    }
                });
            }

        };
        recycler.setAdapter(adapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (toggle.onOptionsItemSelected(item))
            return true;

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId())
        {
            case R.id.a:
                Intent i=new Intent(Searched.this,Welcome.class);
                startActivity(i);break;
            case R.id.c:showMessage("ABOUT","sdfajhgskajfhas\nsjgafjgsdjjsdf\najsgfashgd");break;
            case R.id.b:Intent j=new Intent(Searched.this,Home.class);
                startActivity(j);break;
            case R.id.d:Intent k=new Intent(Searched.this,Cart.class);
                startActivity(k);break;
            case R.id.e:Intent l=new Intent(Intent.ACTION_VIEW);
                l.setData(Uri.parse("geo:47.4925,19.0513"));
                Intent chooser=Intent.createChooser(l,"Launch Maps");
                if(ActivityCompat.checkSelfPermission(Searched.this, Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(Searched.this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},REQUEST_LOCATION);

                }
                else startActivity(chooser);
                break;
            case R.id.f:showAlertDialog();
        }
        return false;
    }
    public void showMessage(String title,String Message){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
    private void showAlertDialog() {
        android.support.v7.app.AlertDialog.Builder alertDialog=new android.support.v7.app.AlertDialog.Builder(Searched.this);
        alertDialog.setTitle("One More Step!!");
        alertDialog.setMessage("NOTE:100% CANCELLATION FEE WILL BE CHARGED\nENTER FOOD DETAILS: ");
        final EditText edtAddress=new EditText(Searched.this);
        LinearLayout.LayoutParams lp=new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
        );
        edtAddress.setLayoutParams(lp);
        alertDialog.setView(edtAddress);
        alertDialog.setIcon(R.drawable.ic_shopping_cart_black_24dp);
        alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Cancelletions cancellations=new Cancelletions(
                        edtAddress.getText().toString()
                );
                cancellation.child(String.valueOf(System.currentTimeMillis()))
                        .setValue(cancellations);


                showMessage("CANCELLED","THANK YOU\nYOUR CANCELLATION REQUEST RECORDED\nIT WILL BE PROCESSED SOON");
                finish();
            }
        });
        alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        alertDialog.show();

    }
}
