package com.example.foodrev;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class TehzeebReview extends AppCompatActivity {
    TextView user;
    EditText uName;
    Button postBtn;
    private RecyclerView recyclerView;
    CommentAdapter adapter; // Create Object of the Adapter class
    DatabaseReference mbase; // Create object of the
    LinearLayoutManager layoutManager;
    ArrayList<Comment> models;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen
        setContentView(R.layout.activity_tehzeeb_review);
        postBtn = findViewById(R.id.postBtn);
        uName = findViewById(R.id.uName);
        user = findViewById(R.id.user);
        ViewPager viewpager = (ViewPager) findViewById(R.id.viewpager);
        ImageAdapter adapterView = new ImageAdapter(this);
        viewpager.setAdapter(adapterView);
        mbase = FirebaseDatabase.getInstance().getReference();
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        FirebaseRecyclerOptions<Comment> options
                = new FirebaseRecyclerOptions.Builder<Comment>()
                .setQuery(mbase, Comment.class)
                .build();
        models = new ArrayList<Comment>();
        adapter = new CommentAdapter(options);
        recyclerView.setAdapter(adapter);
        postBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(TehzeebReview.this,Addcomment.class);
                startActivity(intent);
            }
        });
}

    public void logout(View view)
    {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(TehzeebReview.this, Logout.class);
        startActivity(intent);
    }


    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
    @Override protected void onStart()
    {
        super.onStart();
        adapter.startListening();
    }

    // Function to tell the app to stop getting
    // data from database on stopping of the activity
    @Override protected void onStop()
    {
        super.onStop();
        adapter.stopListening();
    }
}