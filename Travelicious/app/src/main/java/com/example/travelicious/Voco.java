package com.example.travelicious;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class Voco extends AppCompatActivity implements View.OnClickListener {


    String name;
    private TextView a;
    private TextView desc;
    private ImageButton image1;
    private  ImageButton image2;
    private ImageButton image3;
    private TextView charges;
    private TripClass p;
    DatabaseReference reference,demoref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voco);
        Intent i=getIntent();
        name=i.getStringExtra("Name");

        charges=(TextView) findViewById(R.id.charges);
        a=(TextView) findViewById(R.id.a);
        desc=(TextView) findViewById(R.id.desc);
        image1=(ImageButton) findViewById(R.id.mainImage);
        image2=(ImageButton) findViewById(R.id.mainImage1);
        image3=(ImageButton) findViewById(R.id.mainImage2);
        reference= FirebaseDatabase.getInstance().getReference("Hotels").child("Dubai").child("Voco");


        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot userDataSnapshot:dataSnapshot.getChildren())
                {
                    p=dataSnapshot.getValue(TripClass.class);
                }
                desc.setText(p.getDesc());
                charges.setText(p.getCharges());
                Picasso.get().load(p.getProfilePic1()).into(image1);
                Picasso.get().load(p.getProfilePic2()).into(image2);
                Picasso.get().load(p.getProfilePic3()).into(image3);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(Voco.this,"OOPS.......Something is wrong",Toast.LENGTH_SHORT).show();
            }
        });
        image1.setOnClickListener(this);
        image2.setOnClickListener(this);
        image3.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v==image1)
        {

            TripBooking hb=new TripBooking(name,"Kanuhura","Maldives",p.getProfilePic1());
            if(name==null)
            {
                Toast.makeText(Voco.this,"Please Sign in first to Book the Trip",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this,LoginActivity.class));
            }
            else
            {
                Intent i = new Intent(this, BookTrip.class);
                i.putExtra("Trip", "Voco");
                i.putExtra("Country", "Dubai");
                i.putExtra("User", name);
                i.putExtra("Image", p.getProfilePic1());
                startActivity(i);
            }
         //   startActivity(new Intent(this,BookTrip.class));
        }
        if(v==image2)
        {

        }
        if(v==image3)
        {

        }
    }
}
