package com.example.travelicious;


import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.support.annotation.NonNull;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class UserBookedTrips extends Activity {

    String name;
    ExampleBroadcastReceiver exampleBroadcastReceiver=new ExampleBroadcastReceiver();
    DatabaseReference reference,demoref;
    BookedTripsAdapter adapter;
    RecyclerView recyclerView;
    ArrayList<TripBooking> list;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_booked_hotels);

        Intent i=getIntent();
        name=i.getStringExtra("Name");

        list=new ArrayList<TripBooking>();
        recyclerView=(RecyclerView)findViewById(R.id.myRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        reference= FirebaseDatabase.getInstance().getReference("BookedHotels");
        final DatabaseReference zone1Ref = reference.child(name);
        //demoref=reference.child("Hamza");
        zone1Ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot userDataSnapshot:dataSnapshot.getChildren())
                {
                    String user = userDataSnapshot.child("UserName").getValue(String.class);
                    String hotel = userDataSnapshot.child("HotelName").getValue(String.class);
                    String CountryName = userDataSnapshot.child("CountryName").getValue(String.class);
                    String image = userDataSnapshot.child("image").getValue(String.class);
                    String r=userDataSnapshot.child("rooms").getValue(String.class);
                    String m=userDataSnapshot.child("members").getValue(String.class);
                    String i=userDataSnapshot.child("checkIn").getValue(String.class);
                    String o=userDataSnapshot.child("checkOut").getValue(String.class);

                    //TripBooking p=dataSnapshot.getValue(TripBooking.class);
                    TripBooking p=new TripBooking(user,hotel,CountryName,image,r,m,i,o);
                    list.add(p);

                }
                adapter=new BookedTripsAdapter(UserBookedTrips.this,list);
                recyclerView.setAdapter(adapter);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(UserBookedTrips.this,"OOPS.......Something is wrong",Toast.LENGTH_SHORT).show();
            }
        });
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
}


