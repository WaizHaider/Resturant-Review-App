package com.example.foodrev;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
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

public class Login extends AppCompatActivity
{


    @Override
    protected void onStart()
    {
        super.onStart();
        auth.addAuthStateListener(authStateListener);
    }
    TextView signup1;
    EditText userName;
    EditText password;
    Button btn;
    TextView tv;
    private ProgressBar progressbar;
    FirebaseAuth auth;
    private FirebaseAuth.AuthStateListener authStateListener;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen
        setContentView(R.layout.activity_login);
        progressbar = (ProgressBar) findViewById(R.id.progressBar);
        auth = FirebaseAuth.getInstance();
        authStateListener = new FirebaseAuth.AuthStateListener()
        {

            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth)
            {

                FirebaseUser user = auth.getCurrentUser();
                if(user != null)
                {
                    Intent intent = new Intent(Login.this,MainMenu.class);
                    startActivity(intent);
                    finish();
                }
            }
        };

        userName = (EditText) findViewById(R.id.userName);
        password = (EditText) findViewById(R.id.password);
        btn = (Button) findViewById(R.id.btn);
        tv = (TextView) findViewById(R.id.tv);

        btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                final String Uname = userName.getText().toString().trim();
                final String pass = password.getText().toString().trim();
                if(TextUtils.isEmpty(Uname))
                {
                    userName.setError("Username is required!");
                    return;
                }
                if(TextUtils.isEmpty(pass))
                {
                    password.setError("Password is required!");
                    return;
                }
                else
                {
                    progressbar.setVisibility(View.VISIBLE);
                    auth.signInWithEmailAndPassword(Uname,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful())
                            {
                                progressbar.setVisibility(View.GONE);
                                Toast.makeText(Login.this,"Login is successful",Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(Login.this,MainMenu.class);
                                startActivity(intent);
                                finish();
                            }
                            else
                            {
                                Toast.makeText(Login.this,task.getException().toString(),Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }
            }
        });
        signup1 = (TextView) findViewById(R.id.signup1);
        signup1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(getApplicationContext(),Signup.class);
                startActivity(intent);
            }
        });
        tv.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(getApplicationContext(),Forget.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStop()
    {
        super.onStop();
        auth.removeAuthStateListener(authStateListener);
    }
}