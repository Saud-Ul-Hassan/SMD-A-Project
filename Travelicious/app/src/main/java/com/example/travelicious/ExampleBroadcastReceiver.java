package com.example.travelicious;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.widget.Toast;

public class ExampleBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent.ACTION_BOOT_COMPLETED.equals(intent.getAction()))
        {
            Toast.makeText(context,"BOOT COMPLETED",Toast.LENGTH_SHORT).show();
        }
        if(ConnectivityManager.CONNECTIVITY_ACTION.equals(intent.getAction()))
        {
            boolean noConnectivity=intent.getBooleanExtra(ConnectivityManager.EXTRA_NO_CONNECTIVITY,false);
            //Toast.makeText(context,"CONNECTIVITY CHANGED",Toast.LENGTH_SHORT).show();
            if(noConnectivity)
            {
                Toast.makeText(context,"DISCONNECTED",Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(context,"CONNECTED",Toast.LENGTH_SHORT).show();
            }
        }
    }
}
