package com.example.travelicious;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.sqlite.SQLiteDatabase;
import android.media.Image;
import android.net.ConnectivityManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {


    ExampleBroadcastReceiver exampleBroadcastReceiver=new ExampleBroadcastReceiver();
    private String uname;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;
    private Button buttonSignin;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private TextView textViewSignup;
    private ImageView imgv;
    private  TextView txtv;
    private Image employeeOne;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        firebaseAuth= FirebaseAuth.getInstance();
        final DatabaseReference databaseArtists;
        progressDialog=new ProgressDialog(this);
        editTextEmail=(EditText) findViewById(R.id.mail);
        editTextPassword=(EditText) findViewById(R.id.password);
        buttonSignin=(Button) findViewById(R.id.buttonSignin);
        imgv=(ImageView)findViewById(R.id.img);
        txtv=(TextView)findViewById(R.id.txt);
        textViewSignup=(TextView) findViewById(R.id.textViewSignup);
        buttonSignin.setOnClickListener(this);
        textViewSignup.setOnClickListener(this);

    }

    private void userLogin()
    {
       final String  email=editTextEmail.getText().toString().trim();
       final String password=editTextPassword.getText().toString().trim();

       if(TextUtils.isEmpty(email))
       {
            //Email is empty
          Toast.makeText(this,"Please Enter the Email",Toast.LENGTH_SHORT);
            return;
        }
        if(TextUtils.isEmpty(password))
        {
            //PAssword is empty
            Toast.makeText(this,"Please Enter the Password",Toast.LENGTH_SHORT);
            return;
        }

        //database=db.getReadableDatabase();
        progressDialog.show();
        progressDialog.setMessage("Signing in");
        final DatabaseReference dR = FirebaseDatabase.getInstance().getReference("RegisteredUsers");
        progressDialog.setMessage("Signing in");
        progressDialog.show();
        firebaseAuth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        if(task.isSuccessful())
                        {
                            finish();
                            Toast.makeText(getApplicationContext(), "Successfully Signed in", Toast.LENGTH_LONG).show();
                            uname=email;
                            //Intent i = new Intent(this, MainActivity.class);
                            //i.putExtra("Name",uname);
                            //startActivity(i);
                            if(email.equals("zeeshan@gmail.com"))
                            {
                                StartActivity("Zeeshan");
                            }
                            else if(email.equals("hamza@gmail.com"))
                            {
                                StartActivity("Hamza");
                            }
                            //startActivity(new Intent(getApplicationContext(),ProfileActivity.class));
                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(), "Password or EMail is incorrect", Toast.LENGTH_LONG).show();
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        }
                    }
                });
    }
    @Override
    public void onClick(View v) {
        if(v==buttonSignin)
        {
            userLogin();
        }
        if(v==textViewSignup)
        {
            finish();
            startActivity(new Intent(this,MainActivity.class));
        }
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
    public void StartActivity(String uname)
    {
        Intent i=new Intent(this,MainActivity.class);
        i.putExtra("Name",uname);
        startActivity(i);
    }
}
