package com.example.foodmann;

import android.Manifest;
import android.app.AlertDialog;

import com.example.foodmann.Database.Database;
import com.firebase.ui.database.FirebaseRecyclerAdapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.FirebaseRecyclerViewAdapter;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;

public class Home1 extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,LocationListener {
    private static final int REQUEST_LOCATION = 1;
    private DrawerLayout draw;
    private ActionBarDrawerToggle toggle;
    DatabaseReference cancellation, reviews;
    FirebaseDatabase database, database1, database2;
    DatabaseReference category,banner;
    RecyclerView recycler;
    EditText foodsupplier;
    RecyclerView recyclerbanner;
ImageView special;
    DatabaseReference data;
    Button search;
    ArrayList<String> url;
    FirebaseRecyclerAdapter<category, MenuViewHolder> adapter;
    LocationManager locationManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home1);




        foodsupplier = (EditText) findViewById(R.id.foodsupplier);
        banner=FirebaseDatabase.getInstance().getReference("Banner");
        special=(ImageView)findViewById(R.id.special);
recyclerbanner = (RecyclerView)findViewById(R.id.recyclerbanner);
url=new ArrayList<String>();
recyclerbanner.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,true));
banner.addValueEventListener(new ValueEventListener() {
    @Override
    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
        url.clear();
        for(DataSnapshot ds:dataSnapshot.getChildren()){
            url.add(ds.child("Url").getValue().toString());
        }
        recyclerbanner.setAdapter(new BannerAdapter(url));
    }

    @Override
    public void onCancelled(@NonNull DatabaseError databaseError) {

    }
});



        if (ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(Home1.this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION}, 101);

        }

        getLocation();
        database1=FirebaseDatabase.getInstance();


        cancellation=database1.getReference("Cancellations");
        database2=FirebaseDatabase.getInstance();
        reviews=database2.getReference("Reviews");
        search=(Button)findViewById(R.id.search);

        draw=(DrawerLayout)findViewById(R.id.drawer);
        data=FirebaseDatabase.getInstance().getReference("category/");
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for(DataSnapshot ds:dataSnapshot.getChildren())
                        {
                            String a=ds.child("owner").getValue(String.class);
                            if(a.equals(foodsupplier.getText().toString())){
                                Intent i=new Intent(Home1.this,Searched.class);
                                i.putExtra("owner",a);
                                startActivity(i);
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
        special.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Home1.this,Special.class);
                startActivity(i);
            }
        });
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
        loadMenu();
    }

    private void loadMenu() {
         adapter=new FirebaseRecyclerAdapter<category,MenuViewHolder>(category.class,R.layout.menu_item,MenuViewHolder.class,category){
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
             holder.time.setText(""+(num111*60/40000)+" minutes");     //calculating time considering average speed 40km/hr
             Picasso.get().load(category.getImage())
                     .into(holder.img);
             final category clickitem=category;
             holder.setItemClickListener(new itemClickListener() {
                 @Override
                 public void onClick(View view, int position, boolean isLongClick) {
                     Intent i=new Intent(Home1.this,Foodlist.class);
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
                Intent i=new Intent(Home1.this,Welcome.class);
                startActivity(i);break;
            case R.id.c:showMessage("ABOUT","sdfajhgskajfhas\nsjgafjgsdjjsdf\najsgfashgd");break;
            case R.id.b:Intent j=new Intent(Home1.this,Home.class);
            startActivity(j);break;
            case R.id.d:Intent k=new Intent(Home1.this,Cart.class);
            startActivity(k);break;
            case R.id .e:Intent l=new Intent(Intent.ACTION_VIEW);
                l.setData(Uri.parse("geo:"+Common.lat+","+Common.lon));
                Intent chooser=Intent.createChooser(l,"Launch Maps");
                if(ActivityCompat.checkSelfPermission(Home1.this, ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(Home1.this,new String[]{ACCESS_FINE_LOCATION},REQUEST_LOCATION);

                }
                else startActivity(chooser);
                break;
            case R.id.f:showAlertDialog();
        }
        return false;

    }

    void getLocation() {
        try {
            locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 5000, 5, (LocationListener) this);
        }
        catch(SecurityException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onLocationChanged(Location location) {

        getSupportActionBar().setTitle("Latitude: " + location.getLatitude() + "\n Longitude: " + location.getLongitude());
Common.lat=location.getLatitude();
Common.lon=location.getLongitude();
        try {
            Geocoder geocoder = new Geocoder(this, Locale.getDefault());
            List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
            String a=addresses.get(0).getAddressLine(0);
            Common.full=a;
           getSupportActionBar().setTitle(a);
        }catch(Exception e)
        {

        }

    }

    @Override
    public void onProviderDisabled(String provider) {
        Toast.makeText(Home1.this, "Please Enable GPS and Internet", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    private void showAlertDialog() {
        android.support.v7.app.AlertDialog.Builder alertDialog=new android.support.v7.app.AlertDialog.Builder(Home1.this);
        alertDialog.setTitle("One More Step!!");
        alertDialog.setMessage("NOTE:100% CANCELLATION FEE WILL BE CHARGED\nENTER FOOD DETAILS: ");
        final EditText edtAddress=new EditText(Home1.this);
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
    @Override
    public void onBackPressed(){
        android.support.v7.app.AlertDialog.Builder alertDialog=new android.support.v7.app.AlertDialog.Builder(Home1.this);
        alertDialog.setTitle("One More Step!!");
        alertDialog.setMessage("PLEASE ENTER STAR BEFORE EXITING:");
        final EditText edtAddress=new EditText(Home1.this);
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
                if(Integer.parseInt(edtAddress.getText().toString())<=5) {
                    showD(edtAddress.getText().toString());
                }
                else showMessage("ERROR","ENTER STARS OUT OF 5");
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
    private void showD(final String star) {
        android.support.v7.app.AlertDialog.Builder alertDialog=new android.support.v7.app.AlertDialog.Builder(Home1.this);
        alertDialog.setTitle("One More Step!!");
        alertDialog.setMessage("PLEASE ENTER REVIEWS: ");
        final EditText edtAddress=new EditText(Home1.this);
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
                revies cancellations=new revies(
                        edtAddress.getText().toString(),star
                );
                reviews.child(String.valueOf(System.currentTimeMillis()))
                        .setValue(cancellations);


                showMessage("THANK YOU","PLEASE VISIT AGAIN LATER");
                showExit();
            }
        });
        alertDialog.setNegativeButton("EXIT", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                showExit();
            }
        });
        alertDialog.show();

    }
    private void showExit(){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setMessage("THANK YOU PLEASE VISIT AGAIN\nARE YOU SURE YOU WANT TO EXIT???")
                .setCancelable(false)
                .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
ActivityCompat.finishAffinity(Home1.this);

                    }
                })
                .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        AlertDialog alertDialog=builder.create();
        alertDialog.show();
    }
    public void showMessage(String title,String Message){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }

}
