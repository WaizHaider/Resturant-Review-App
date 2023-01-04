package com.example.foodrev;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import java.lang.invoke.LambdaConversionException;
import java.util.List;

public class CommentAdapter extends FirebaseRecyclerAdapter<
        Comment, CommentAdapter.personsViewholder> {

    public CommentAdapter(
            @NonNull FirebaseRecyclerOptions<Comment> options) {
        super(options);
    }

    // Function to bind the view in Card view(here
    // "person.xml") iwth data in
    // model class(here "person.class")
    @Override
    protected void
    onBindViewHolder(@NonNull personsViewholder holder,
                     int position, @NonNull Comment model) {

        // Add firstname from model class (here
        // "person.class")to appropriate view in Card
        // view (here "person.xml")
        holder.comText.setText(model.getComText());

        // Add lastname from model class (here
        // "person.class")to appropriate view in Card
        // view (here "person.xml")
        holder.user.setText(model.getUser());

    }


    // Function to tell the class about the Card view (here
    // "person.xml")in
    // which the data will be shown
    @NonNull
    @Override
    public personsViewholder
    onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.commentdesign, parent, false);
        return new CommentAdapter.personsViewholder(view);
    }


    // Sub Class to create references of the views in Crad
    // view (here "person.xml")
    class personsViewholder extends RecyclerView.ViewHolder {
        TextView comText, user;

        public personsViewholder(@NonNull View itemView) {
            super(itemView);

            comText = itemView.findViewById(R.id.comText);
            user = itemView.findViewById(R.id.user);
        }
    }

}