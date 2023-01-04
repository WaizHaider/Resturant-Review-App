package com.example.foodrev;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Addcomment extends AppCompatActivity
{
    EditText comText1,user1;
    Button postBtn;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    Comment comment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen
        setContentView(R.layout.activity_addcomment);
        comText1 = findViewById(R.id.comText1);
        user1 = findViewById(R.id.user1);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Comment1");
        comment = new Comment();
        postBtn = findViewById(R.id.postBtn);
        postBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String comText = comText1.getText().toString();
                String user = user1.getText().toString();
                if (TextUtils.isEmpty(comText) && TextUtils.isEmpty(user)) {
                    Toast.makeText(Addcomment.this, "Please add some data.", Toast.LENGTH_SHORT).show();
                } else
                {
                    addDatatoFirebase(comText, user);
                    Intent intent = new Intent(Addcomment.this,TehzeebReview.class);
                    startActivity(intent);
                }
            }
        });
    }
        public void addDatatoFirebase(String comText,String user)
        {
            comment.setComText(comText);
            comment.setUser(user);
            databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot)
            {
                databaseReference.setValue(comment);
                Toast.makeText(Addcomment.this, "data added", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error)
            {
                Toast.makeText(Addcomment.this, "Fail to add data " + error, Toast.LENGTH_SHORT).show();
            }
        });
    }
}