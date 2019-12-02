package com.example.travelicious;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class BookTrip extends AppCompatActivity implements View.OnClickListener{

    String name,usr,cntr,img;
    ProgressDialog progressDialog;
    DatabaseReference ref;
    private TextView country;
    private TextView nam;
    private TextView user;
    private EditText rooms;
    private EditText member;
    private EditText in;
    private EditText out;
    private Button add;
    String r,m,i,o;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_hotel);

        Intent i=getIntent();
        name=i.getStringExtra("Trip");
        usr=i.getStringExtra("User");
        cntr=i.getStringExtra("Country");
        img=i.getStringExtra("Image");

        progressDialog=new ProgressDialog(this);
        country=(TextView) findViewById(R.id.country);
        nam=(TextView) findViewById(R.id.name);
        user=(TextView) findViewById(R.id.user);
        rooms=(EditText) findViewById(R.id.rooms);
        member=(EditText) findViewById(R.id.members);
        in=(EditText) findViewById(R.id.in);
        out=(EditText) findViewById(R.id.out);
        add=(Button) findViewById(R.id.book);
        add.setOnClickListener(this);
        //country.setText(name);
        country.setText(cntr);
        nam.setText(name);
        user.setText(usr);
    }

    @Override
    public void onClick(View v) {
        if(v==add)
        {
            progressDialog.setMessage("Adding Trip");
            progressDialog.show();
            TripBooking h=new TripBooking(usr,name,cntr,img);
            r=rooms.getText().toString();
            m=member.getText().toString();
            i=in.getText().toString();
            o=out.getText().toString();
            ref= FirebaseDatabase.getInstance().getReference("BookedHotels");
           // String key = ref.child("BookedHotels").push().getKey();
            TripBooking post = new TripBooking(usr, name, cntr, img);
            HashMap<String, Object> result = new HashMap<>();
            result.put("UserName", usr);
            result.put("HotelName", name);
            result.put("CountryName", cntr);
            result.put("image", img);
            result.put("rooms", r);
            result.put("members", m);
            result.put("checkIn", i);
            result.put("checkOut", o);

            ref.child(usr).child(name).updateChildren(result);
            // Map<String, Object> postValues = post.toMap();

            //Map<String, Object> childUpdates = new HashMap<>();
            //childUpdates.put("/posts/" , postValues);
            //childUpdates.put("/user-posts/" + usr + "/", postValues);

            //ref.updateChildren(childUpdates);
            //ref.push().setValue(h);
            //startActivity(new Intent(this,MainActivity.class));
            //ref.push().child("UserName").setValue(h.getUserName());
            //ref.push().child("HotelName").setValue(h.getHotelName());
            //ref.push().child("CountryName").setValue(h.getCountryName());
            //ref.push().child("image").setValue(h.getImage());

            finish();
        }
    }
}
