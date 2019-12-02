package com.example.travelicious;



import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    DatabaseReference rootref,demoref;
    Context context;
    ArrayList<Profile> profiles;
    private OnItemClickListener mListener;
    public MyAdapter(Context context, ArrayList<Profile> profiles) {
        this.context = context;
        this.profiles = profiles;

    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.cardview,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int i) {

        holder.name.setText(profiles.get(i).getName());
        holder.email.setText(profiles.get(i).getEmail());
        Picasso.get().load(profiles.get(i).getProfilePic()).into(holder.profilepic);
        final Profile t=new Profile(profiles.get(i).getName(),profiles.get(i).getEmail(),profiles.get(i).getProfilePic());
        rootref= FirebaseDatabase.getInstance().getReference().child(profiles.get(i).getName());
        //demoref=rootref.child("users").child(profiles.get(i).getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //opendialog(t);
                rootref.push().setValue(t);
                Intent intent = new Intent(context, Signup.class);
                intent.putExtra("event",t);
                context.startActivity(intent);//.startActivityForResult(intent,1);
            }
        });
    }

    @Override
    public int getItemCount() {

        return profiles.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }
    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {

        TextView name,email;
        ImageView profilepic;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name=(TextView) itemView.findViewById(R.id.name);
            email=(TextView) itemView.findViewById(R.id.email);
            profilepic=(ImageView) itemView.findViewById(R.id.profilePic);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

        }
    }

}

