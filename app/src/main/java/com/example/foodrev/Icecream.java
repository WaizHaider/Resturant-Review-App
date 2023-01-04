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

public class Icecream extends AppCompatActivity
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
        setContentView(R.layout.activity_icecream);
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
        userList.add(new ModelClass(R.drawable.icecream1,"Kaspas Desserts"));
        userList.add(new ModelClass(R.drawable.icecream2,"The Ice-Factory"));
        userList.add(new ModelClass(R.drawable.icecream3,"The Hot Spot"));
        userList.add(new ModelClass(R.drawable.icecream4,"Cold Stone Creamery"));
        userList.add(new ModelClass(R.drawable.icecream5,"Manolo Gelato"));
        userList.add(new ModelClass(R.drawable.icecream6,"Baskin Robbins"));
        userList.add(new ModelClass(R.drawable.icecream7,"Guilty Pleasures"));
        userList.add(new ModelClass(R.drawable.icecream8,"Gelato Affair"));
    }
    private void showToast(String message){
        Toast.makeText(this,"Clicked",Toast.LENGTH_LONG).show();
    }
}