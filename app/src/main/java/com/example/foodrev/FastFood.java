package com.example.foodrev;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class FastFood extends AppCompatActivity
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
        setContentView(R.layout.activity_fast_food);
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
                if(modelClass.getFastFood().equals("Tehzeeb"))
                {
                    showToast(modelClass.getIv()+" Clicked!");
                    Intent intent = new Intent(FastFood.this,TehzeebReview.class);
                    startActivity(intent);
                }
            }
        });
        recyclerView.setAdapter(adapter);// set data in adapter class
        adapter.notifyDataSetChanged();
    }
    private void initData()
    {
        userList = new ArrayList<>();
        userList.add(new ModelClass(R.drawable.fastfood1,"Tehzeeb"));
        userList.add(new ModelClass(R.drawable.fastfood11,"Pizza Hut"));
        userList.add(new ModelClass(R.drawable.fastfood3,"Cheezious"));
        userList.add(new ModelClass(R.drawable.fastfood4,"KFC"));
        userList.add(new ModelClass(R.drawable.fastfood5,"Papa Jone's Pizza"));
        userList.add(new ModelClass(R.drawable.fastfood6,"Howdy"));
        userList.add(new ModelClass(R.drawable.fastfood7,"Hardee's"));
        userList.add(new ModelClass(R.drawable.fastfood8,"Subway"));
        userList.add(new ModelClass(R.drawable.fastfood9,"Ranchers"));
        userList.add(new ModelClass(R.drawable.fastfood10,"McDonald's"));

    }
    private void showToast(String message){
        Toast.makeText(this,"Clicked",Toast.LENGTH_LONG).show();
    }
}