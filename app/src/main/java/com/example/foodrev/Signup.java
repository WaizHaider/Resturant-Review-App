package com.example.foodrev;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class Signup extends AppCompatActivity
{
    EditText uName,email,city,Phone,passw;
    Button Sup;
    private FirebaseAuth auth;
    private DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen
        setContentView(R.layout.activity_signup);
        uName = (EditText) findViewById(R.id.uName);
        email = (EditText) findViewById(R.id.email);
        city = (EditText) findViewById(R.id.city);
        Phone = (EditText) findViewById(R.id.Phone);
        passw = (EditText) findViewById(R.id.passw);
        Sup = (Button) findViewById(R.id.Sup);
        auth = FirebaseAuth.getInstance();
        Sup.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                final String Email = email.getText().toString().trim();
                final String UName = uName.getText().toString().trim();
                final String City = city.getText().toString().trim();
                final String phone = Phone.getText().toString().trim();
                final String Pass = passw.getText().toString().trim();
                if(TextUtils.isEmpty(UName))
                {
                    uName.setError("User Name Is Required!");
                    return;
                }
                if(TextUtils.isEmpty(Email))
                {
                    email.setError("Email Is Required!");
                    return;
                }
                if(TextUtils.isEmpty(City))
                {
                    city.setError("City Is Required!");
                    return;
                }
                if(TextUtils.isEmpty(phone))
                {
                    Phone.setError("Phone Number Is Required!");
                    return;
                }
                if(TextUtils.isEmpty(Pass))
                {
                    passw.setError("Password Is Required!");
                    return;
                }
                else
                {
                    auth.createUserWithEmailAndPassword(Email,Pass).addOnCompleteListener(
                            new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task)
                                {
                                    if (!task.isSuccessful())
                                    {
                                        String error = task.getException().toString();
                                        Toast.makeText(getApplicationContext(),"Error: "+error,Toast.LENGTH_LONG).show();
                                    }
                                    else
                                    {
                                        String currentUserID = auth.getCurrentUser().getUid();
                                        databaseReference = FirebaseDatabase.getInstance("https://food-rev-default-rtdb.firebaseio.com/").getReference().child("Users").
                                                child(currentUserID);
                                        HashMap userInfo = new HashMap();
                                        userInfo.put("id",currentUserID);
                                        userInfo.put("User Name",UName);
                                        userInfo.put("Email",Email);
                                        userInfo.put("City",City);
                                        userInfo.put("Password",Pass);
                                        userInfo.put("Phone Number",phone);
                                        databaseReference.updateChildren(userInfo).addOnCompleteListener(new OnCompleteListener()
                                        {
                                            @Override
                                            public void onComplete(@NonNull Task task)
                                            {
                                                if (task.isSuccessful())
                                                {
                                                    Toast.makeText(getApplicationContext(),"Data Set Successfully",Toast.LENGTH_LONG).show();
                                                }
                                                else
                                                {
                                                    Toast.makeText(getApplicationContext(),task.getException().toString(),Toast.LENGTH_LONG).show();
                                                }
                                                finish();
                                            }
                                        });
                                    }
                                }
                            });
                }
            }
        });
    }
}