package com.example.foodrev;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

public class ItalianFood extends AppCompatActivity
{
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    List<ModelClass> userList;
    Adapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen
        setContentView(R.layout.activity_italian_food);
        initData(); // Will Take Data Every Time Whenever The App Started
        initRecylerView();// set Values In Recycler View
    }
    private void initRecylerView()
    {
        recyclerView = findViewById(R.id.recyclerview);
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new Adapter(userList, new Adapter.itemClickListener()
        {
            @Override
            public void itemClickListener(ModelClass modelClass)
            {
                showToast(modelClass.getFastFood()+"Clicked!");
            }
        });
        recyclerView.setAdapter(adapter);// set data in adapter class
        adapter.notifyDataSetChanged();
    }
    private void initData()
    {
        userList = new ArrayList<>();
        userList.add(new ModelClass(R.drawable.chinesefood1,"Asian Wok"));
        userList.add(new ModelClass(R.drawable.chinesefood2,"Golden Dragon"));
        userList.add(new ModelClass(R.drawable.chinesefood3,"Chinatown Restaurant"));
        userList.add(new ModelClass(R.drawable.chinesefood4,"Dynasty Chinese Restaurant"));
        userList.add(new ModelClass(R.drawable.chinesefood5,"Heng Chang"));
        userList.add(new ModelClass(R.drawable.chinesefood6,"Hutong"));
    }
    private void showToast(String message){
        Toast.makeText(this,"Clicked",Toast.LENGTH_LONG).show();
    }
    public void logout(View view)
    {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(ItalianFood.this,Logout.class);
        startActivity(intent);
    }
}