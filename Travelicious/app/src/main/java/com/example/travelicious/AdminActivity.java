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

public class AdminActivity extends AppCompatActivity implements View.OnClickListener{

    private Button buttonAddHotel;
    private Button buttonAddRestaurant;
    private Button buttonAddDestination;
    private TextView messageWelcome;

    ExampleBroadcastReceiver exampleBroadcastReceiver=new ExampleBroadcastReceiver();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        buttonAddHotel=(Button) findViewById(R.id.addHotel);
       // buttonAddRestaurant=(Button) findViewById(R.id.addRestaurant);
        messageWelcome=(TextView) findViewById(R.id.welcomemessage);
        //buttonAddDestination=(Button)findViewById(R.id.addDestination);
        buttonAddDestination.setOnClickListener(this);
        buttonAddHotel.setOnClickListener(this);
        buttonAddRestaurant.setOnClickListener(this);
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
        if(v == buttonAddHotel)
        {
            startActivity(new Intent(this, AddTrip.class));
        }
        if(v == buttonAddRestaurant)
        {
            Toast.makeText(this,"Image Added in Database",Toast.LENGTH_SHORT);
            //Will open login activity
            startActivity(new Intent(this,LoginActivity.class));
        }
        if(v==buttonAddDestination)
        {
            startActivity(new Intent(this, trips.class));
        }

    }
}