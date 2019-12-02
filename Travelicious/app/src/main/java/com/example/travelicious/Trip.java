package com.example.travelicious;

import android.content.Intent;

import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.lang.String;

public class Trip extends AppCompatActivity implements View.OnClickListener{

    ExampleBroadcastReceiver exampleBroadcastReceiver=new ExampleBroadcastReceiver();

    String name;
    private TextView nam;
    private Button dubai;
    private Button maldives;
    private Button England;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip);
        Intent i=getIntent();
        name=i.getStringExtra("Nam");
        nam=(TextView) findViewById(R.id.edit);
        dubai=(Button) findViewById(R.id.Voco);
        maldives=(Button) findViewById(R.id.Maldives);
        England=(Button) findViewById(R.id.england);
        dubai.setOnClickListener(this);
        maldives.setOnClickListener(this);
        England.setOnClickListener(this);
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
        if(v == dubai)
        {
            Intent i=new Intent(this,Voco.class);
            i.putExtra("Name",name);
            startActivity(i);
        }
        if(v == maldives)
        {

            Intent i=new Intent(this,Kanuhura.class);
            i.putExtra("Name",name);
            startActivity(i);
            //startActivity(new Intent(this,Kanuhura.class));
        }
        if(v==England)
        {
            Intent i=new Intent(this,Leman.class);
            i.putExtra("Name",name);
            startActivity(i);
        }

    }
}