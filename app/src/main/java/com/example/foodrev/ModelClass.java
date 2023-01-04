package com.example.foodrev;

import android.view.View;

public class ModelClass
{
    int iv;
    String fastFood;

    public ModelClass(int iv, String fastFood)
    {
        this.iv = iv;
        this.fastFood = fastFood;
    }

    public int getIv() {
        return iv;
    }

    public String getFastFood() {
        return fastFood;
    }
}
