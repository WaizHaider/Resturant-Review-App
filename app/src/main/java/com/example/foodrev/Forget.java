package com.example.foodrev;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class Forget extends AppCompatActivity
{
    FirebaseAuth auth;
    Button sBtn;
    private ProgressBar progressbar;
    Button backLogin;
    EditText gEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen
        setContentView(R.layout.activity_forget);
        sBtn = (Button) findViewById(R.id.sBtn);
        backLogin = (Button) findViewById(R.id.backLogin);
        gEmail = (EditText) findViewById(R.id.gEmail);
        progressbar = (ProgressBar) findViewById(R.id.progressBar);
        auth = FirebaseAuth.getInstance();
        sBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                final String email = gEmail.getText().toString().trim();
                if(TextUtils.isEmpty(email))
                {
                    gEmail.setError("Username is required!");
                    return;
                }
                else
                {
                    progressbar.setVisibility(View.VISIBLE);
                    auth.sendPasswordResetEmail(gEmail.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task)
                        {
                            if(task.isSuccessful())
                            {
                                progressbar.setVisibility(View.GONE);
                                Toast.makeText(Forget.this, "Check Your Email to Reset Your Password", Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                Toast.makeText(Forget.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }
    public void onClick(View view)
    {
        Intent intent = new Intent(Forget.this, Login.class);
        startActivity(intent);
    }
}