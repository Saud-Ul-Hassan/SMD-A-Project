package com.example.travelicious;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class AddTrip extends AppCompatActivity implements View.OnClickListener{

    String name;
    ProgressDialog progressDialog;
    DatabaseReference ref;
    private EditText country;
    private EditText hotel1;
    private EditText hotel2;
    private EditText hotel3;
    private EditText hname;
    private EditText desc;
    private EditText price;
    private Button add;
    String c,h1,h2,h3,d,p,hn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_trip);

        Intent i=getIntent();
        name=i.getStringExtra("Name");
        progressDialog=new ProgressDialog(this);
        country=(EditText) findViewById(R.id.country);
        hotel1=(EditText) findViewById(R.id.hotel1);
        hotel2=(EditText) findViewById(R.id.hotel2);
        hotel3=(EditText) findViewById(R.id.hotel3);
        hname=(EditText) findViewById(R.id.name);
        desc=(EditText) findViewById(R.id.desc);
        price=(EditText) findViewById(R.id.price);
        add=(Button) findViewById(R.id.addHotel);
        add.setOnClickListener(this);
        //country.setText(name);


    }

    @Override
    public void onClick(View v) {
        if(v==add)
        {
            progressDialog.setMessage("Adding Trip");
            progressDialog.show();
            c=country.getText().toString();
           // h1=hotel1.getText().toString();
            //h2=hotel2.getText().toString();
            //h3=hotel3.getText().toString();
            hn=hname.getText().toString();
            d=desc.getText().toString() ;
            p=price.getText().toString();
            h1="https://firebasestorage.googleapis.com/v0/b/travel-1997.appspot.com/o/Hotels%2Fleman1.jpg?alt=media&token=2a249339-c1df-4130-b3b4-453f1c52a6f4";
            h2="https://firebasestorage.googleapis.com/v0/b/travel-1997.appspot.com/o/Hotels%2Fleman2.jpg?alt=media&token=af6b4922-106e-4d6f-9026-12717edf4936";
            h3="https://firebasestorage.googleapis.com/v0/b/travel-1997.appspot.com/o/Hotels%2Fleman4.jpg?alt=media&token=29c13547-f493-4b2b-9901-521194ffbedc";
            //TripClass h=new TripClass(hn,d,p,h1,h2,h3);
            //ref= FirebaseDatabase.getInstance().getReference("Hotels").child(c);
//            TripBooking post = new TripBooking(usr, name, cntr, img);
            ref= FirebaseDatabase.getInstance().getReference("Hotels");

            HashMap<String, Object> result = new HashMap<>();
            result.put("Description", d);
            result.put("charges", p);
            result.put("name", hn);
            result.put("profilePic1", h1);
            result.put("profilePic2", h2);
            result.put("profilePic3", h3);
            ref.child(c).child(hn).updateChildren(result);
//            ref.child(p).child(hn).push().setValue(h);
            finish();
        }
    }
}
