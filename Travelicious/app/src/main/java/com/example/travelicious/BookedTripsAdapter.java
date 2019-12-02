package com.example.travelicious;



import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

import com.google.firebase.database.DatabaseReference;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class BookedTripsAdapter extends RecyclerView.Adapter<BookedTripsAdapter.MyViewHolder> {

    DatabaseReference rootref,demoref;
    Context context;
    ArrayList<TripBooking> profiles;
    private OnItemClickListener mListener;
    public BookedTripsAdapter(Context context, ArrayList<TripBooking> profiles) {
        this.context = context;
        this.profiles = profiles;

    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.tripcardview,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int i)
    {

        holder.name.setText(profiles.get(i).getUserName());
        holder.r.setText(profiles.get(i).getRooms());
        holder.m.setText(profiles.get(i).getMembers());
        holder.i.setText(profiles.get(i).getCheckIn());
        holder.o.setText(profiles.get(i).getCheckOut());

        holder.hotel.setText(profiles.get(i).getHotelName());
        holder.country.setText(profiles.get(i).getCountryName());
        Picasso.get().load(profiles.get(i).getImage()).into(holder.profilepic);

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

        TextView hotel,name,country,r,m,i,o;
        ImageView profilepic;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            r=(TextView) itemView.findViewById(R.id.rooms);
            m=(TextView) itemView.findViewById(R.id.members);
            i=(TextView) itemView.findViewById(R.id.in);
            o=(TextView) itemView.findViewById(R.id.out);

            hotel=(TextView) itemView.findViewById(R.id.hotel);
            name=(TextView) itemView.findViewById(R.id.name);
            country=(TextView) itemView.findViewById(R.id.country);
            profilepic=(ImageView) itemView.findViewById(R.id.profilePic);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

        }
    }

}

