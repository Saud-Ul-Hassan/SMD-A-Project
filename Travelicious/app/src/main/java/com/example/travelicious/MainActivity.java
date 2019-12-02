package com.example.travelicious;

import android.content.Intent;

import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.String;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button flights;
    private Button destinations;
    private Button userHotels;
    private Button buttonRegister;
    private Button buttonLogin;
    private Button addImage;
    private Button buttonHotel;
    private Button restaurant;
    private TextView messageWelcome;
    TextView nam;
    String name;
    ExampleBroadcastReceiver exampleBroadcastReceiver=new ExampleBroadcastReceiver();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent i=getIntent();
        name=i.getStringExtra("Name");
       // nam=(TextView) findViewById(R.id.edit);

        //flights=(Button) findViewById(R.id.flights);
        //restaurant=(Button) findViewById(R.id.restaurants);
        //destinations=(Button) findViewById(R.id.destinations);
        //userHotels=(Button) findViewById(R.id.userHotels);
        buttonRegister=(Button) findViewById(R.id.registerButton);
        buttonLogin=(Button) findViewById(R.id.loginButton);
        buttonHotel=(Button) findViewById(R.id.hotels);
        messageWelcome=(TextView) findViewById(R.id.welcomemessage);
        addImage=(Button)findViewById(R.id.AdminSignin);

        flights.setOnClickListener(this);
        addImage.setOnClickListener(this);
        buttonRegister.setOnClickListener(this);
        restaurant.setOnClickListener(this);
        buttonHotel.setOnClickListener(this);
        buttonLogin.setOnClickListener(this);
        userHotels.setOnClickListener(this);
        destinations.setOnClickListener(this);
        addImage.setOnClickListener(this);
        nam.setText(name);
    }

@Override
protected void onStart() {

    super.onStart();
    IntentFilter filter=new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
    registerReceiver(exampleBroadcastReceiver,filter);
}
@Override
protected void onStop() {

    super.onStop();
    unregisterReceiver(exampleBroadcastReceiver);
}

@Override
    public void onClick(View v) {
        if(v == buttonRegister)
        {
            startActivity(new Intent(this,Signup.class));
        }
        if(v == buttonLogin)
        {
            Toast.makeText(this,"Image Added in Database",Toast.LENGTH_SHORT);
            //Will open login activity
            startActivity(new Intent(this,LoginActivity.class));
        }
        if(v==addImage)
        {
            startActivity(new Intent(this,AdminSignin.class));

        }
        if(v==buttonHotel)
        {
            //Deneme dene = new Deneme(4,"Mustafa");
            Intent i = new Intent(this, Trip.class);
            i.putExtra("Nam", name);
            startActivity(i);
            //startActivity(new Intent(this,Trip.class));
        }
        if(v==restaurant)
        {
           // startActivity(new Intent(this,Restaurants.class));
        }
        if(v==destinations)
        {
          //  startActivity(new Intent(this,Dest.class));
        }
        if(v==userHotels)
        {
            if(name==null)
            {
                Toast.makeText(this,"Kindly Sing in first to view trips",Toast.LENGTH_SHORT);
                startActivity(new Intent(this,LoginActivity.class));
            }
            else
            {
                Intent i = new Intent(this, UserBookedTrips.class);
                i.putExtra("Name", name);
                startActivity(i);
            }
        }
        if(v==flights)
        {
           // Intent i = new Intent(this, Flights.class);
          //  i.putExtra("Name", name);
           // startActivity(i);
        }
    }
}














































