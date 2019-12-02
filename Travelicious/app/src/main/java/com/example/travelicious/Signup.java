package com.example.travelicious;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.lang.String;

public class Signup extends AppCompatActivity implements View.OnClickListener{


    private FirebaseAuth firebaseAuth;
    private EditText editTextName;
    private ProgressDialog progressDialog;
    private Button buttonRegister;
    private EditText editTextEmail;
    private EditText getEditTextPassword;
    private TextView textViewSignin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        firebaseAuth=FirebaseAuth.getInstance();
        progressDialog=new ProgressDialog(this);
        buttonRegister=(Button) findViewById(R.id.registerButton);
        editTextEmail=(EditText) findViewById(R.id.mail);
        editTextName=(EditText)findViewById(R.id.name);
        getEditTextPassword=(EditText) findViewById(R.id.password);
        textViewSignin=(TextView) findViewById(R.id.textViewSignin);
        buttonRegister.setOnClickListener(this);
        textViewSignin.setOnClickListener(this);
    }

    private void registerUser()
    {
        final DatabaseReference dR = FirebaseDatabase.getInstance().getReference("RegisteredUsers").child(editTextName.getText().toString().trim());
        final String email=editTextEmail.getText().toString().trim();
        final String password=getEditTextPassword.getText().toString().trim();
        final String name=editTextName.getText().toString().trim();
//        final String confirmpassword=getEditTextConfirmPassword.getText().toString().trim();
        if(TextUtils.isEmpty(email))
        {
            Toast.makeText(this,"Please Enter the Email",Toast.LENGTH_SHORT);
            return;
        }
        if(TextUtils.isEmpty(password))
        {
            Toast.makeText(this,"Please Enter the Password",Toast.LENGTH_SHORT);
            return;
        }
        if(TextUtils.isEmpty(name))
        {
            Toast.makeText(this,"Please Enter the Name",Toast.LENGTH_SHORT);
            return;
        }

        progressDialog.setMessage("Registering User......");
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            User artist = new User(name,email, password);
                            dR.push().setValue(artist);
                            Toast.makeText(Signup.this,"User Registered Successfully",Toast.LENGTH_SHORT).show();
                            finish();
                            startActivity(new Intent(getApplicationContext(),LoginActivity.class));
                        }
                        else
                        {
                            finish();
                            Toast.makeText(Signup.this,"Couldn't Registered......Please try again",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    @Override
    public void onClick(View v) {
        if(v == buttonRegister)
        {
            registerUser();
        }
        if(v == textViewSignin)
        {
            //Will open login activity
            startActivity(new Intent(this,LoginActivity.class));
        }
    }
}
