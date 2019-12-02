package com.example.travelicious;


import android.app.Activity;
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

public class trips extends Activity {

    ExampleBroadcastReceiver exampleBroadcastReceiver=new ExampleBroadcastReceiver();
    DatabaseReference reference,demoref;
    MyAdapter adapter;
    RecyclerView recyclerView;
    ArrayList<Profile> list;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trips);

        list=new ArrayList<Profile>();
        recyclerView=(RecyclerView)findViewById(R.id.myRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        reference= FirebaseDatabase.getInstance().getReference("profiles");

        //demoref=reference.child("Hamza");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot userDataSnapshot:dataSnapshot.getChildren())
                {

                    Profile p=userDataSnapshot.getValue(Profile.class);
                    list.add(p);

                }
                adapter=new MyAdapter(trips.this,list);
                recyclerView.setAdapter(adapter);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(trips.this,"OOPS.......Something is wrong",Toast.LENGTH_SHORT).show();
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


