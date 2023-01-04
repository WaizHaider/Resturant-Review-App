package com.example.foodrev;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

public class MainMenu extends AppCompatActivity
{
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    List<ModelClass> userList;
    Adapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main_menu);
        ViewPager viewpager = (ViewPager) findViewById(R.id.viewpager);
        ImageAdapter adapterView = new ImageAdapter(this);
        viewpager.setAdapter(adapterView);
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
                if(modelClass.getFastFood().equals("FastFood"))
                {
                    showToast("Clicked!");
                    Intent intent = new Intent(MainMenu.this,FastFood.class);
                    startActivity(intent);
                }
                if(modelClass.getFastFood().equals("Sea Food"))
                {
                    showToast("Clicked!");
                    Intent intent = new Intent(MainMenu.this,SeaFood.class);
                    startActivity(intent);
                }
                if(modelClass.getFastFood().equals("Desi Food"))
                {
                    showToast("Clicked!");
                    Intent intent = new Intent(MainMenu.this,DesiFood.class);
                    startActivity(intent);
                }
                if(modelClass.getFastFood().equals("Ice Cream"))
                {
                    showToast("Clicked!");
                    Intent intent = new Intent(MainMenu.this,Icecream.class);
                    startActivity(intent);
                }
                if(modelClass.getFastFood().equals("Chinese Food"))
                {
                    showToast("Clicked!");
                    Intent intent = new Intent(MainMenu.this,Chinesefood.class);
                    startActivity(intent);
                }
                if(modelClass.getFastFood().equals("Turkish Food"))
                {
                    showToast("Clicked!");
                    Intent intent = new Intent(MainMenu.this,ItalianFood.class);
                    startActivity(intent);
                }
                if(modelClass.getFastFood().equals("Bakery"))
                {
                    showToast("Clicked!");
                    Intent intent = new Intent(MainMenu.this,Bakery.class);
                    startActivity(intent);
                }
                if(modelClass.getFastFood().equals("Thai Food"))
                {
                    showToast("Clicked!");
                    Intent intent = new Intent(MainMenu.this,ThaiFood.class);
                    startActivity(intent);
                }
            }
        });
        recyclerView.setAdapter(adapter);// set data in adapter class
        adapter.notifyDataSetChanged();
    }

    public void logout(View view)
    {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(MainMenu.this,Logout.class);
        startActivity(intent);
    }

    private void initData()
    {
        userList = new ArrayList<>();
        userList.add(new ModelClass(R.drawable.menu1,"FastFood"));
        userList.add(new ModelClass(R.drawable.menu3,"Sea Food"));
        userList.add(new ModelClass(R.drawable.menu45,"Desi Food"));
        userList.add(new ModelClass(R.drawable.menu6,"Ice Cream"));
        userList.add(new ModelClass(R.drawable.menu7,"Chinese Food"));
        userList.add(new ModelClass(R.drawable.menu8,"Turkish Food"));
        userList.add(new ModelClass(R.drawable.menu9,"Italian Food"));
        userList.add(new ModelClass(R.drawable.menu5,"Bakery"));
        userList.add(new ModelClass(R.drawable.menu10,"Thai Food"));
    }
    private void showToast(String message){
        Toast.makeText(this,message,Toast.LENGTH_LONG).show();
    }
}