package com.example.foodrev;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class DesiFood extends AppCompatActivity
{
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    List<ModelClass> userList;
    Adapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen
        setContentView(R.layout.activity_desi_food);
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
                showToast(modelClass.getFastFood());
            }
        });
        recyclerView.setAdapter(adapter);// set data in adapter class
        adapter.notifyDataSetChanged();
    }
    private void initData()
    {
        userList = new ArrayList<>();
        userList.add(new ModelClass(R.drawable.desifood1,"Chatta’s"));
        userList.add(new ModelClass(R.drawable.desifood2,"Dilli Darwaza"));
        userList.add(new ModelClass(R.drawable.desifood3,"Khoka Khola"));
        userList.add(new ModelClass(R.drawable.desifood4,"Des Pardes"));
        userList.add(new ModelClass(R.drawable.desifood5,"Chikachino"));
        userList.add(new ModelClass(R.drawable.desifood6,"Bismillah Tikka and Chargha"));
        userList.add(new ModelClass(R.drawable.desifood7,"Hot n Chilli"));
        userList.add(new ModelClass(R.drawable.desifood8,"Savor Foods"));
    }
    private void showToast(String message){
        Toast.makeText(this,message,Toast.LENGTH_LONG).show();
    }
}