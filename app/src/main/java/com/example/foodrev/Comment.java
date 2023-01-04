package com.example.foodrev;

import android.os.Parcel;
import android.os.Parcelable;
import android.widget.EditText;

public class Comment{
    String comText;
    String user;
    Comment()
    {

    }

    public Comment(String comText,String user)
    {
        this.comText = comText;
        this.user = user;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }




    public String getComText() {
        return comText;
    }

    public void setComText(String comText) {
        this.comText = comText;
    }

}
