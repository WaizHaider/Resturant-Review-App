package com.example.foodrev;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.viewHolder> {
    private List<ModelClass> userList;
    private itemClickListener mitemClickListener;
    public Adapter(List<ModelClass> userList, itemClickListener itemClickListener)
    {
        this.userList = userList;
        this.mitemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public Adapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.menudesign, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.viewHolder holder, int position)
    {
        int resource = userList.get(position).getIv();
        String name = userList.get(position).getFastFood();
        holder.itemView.setOnClickListener(view ->{
            mitemClickListener.itemClickListener(userList.get(position));
        } );
        holder.setData(resource, name);
    }

    @Override
    public int getItemCount()
    {
        return userList.size();
    }

    public interface itemClickListener
    {
        void itemClickListener(ModelClass modelClass);
    }

    public class viewHolder extends RecyclerView.ViewHolder
    {
        private ImageView iv;
        private TextView fastFood;

        public viewHolder(@NonNull View itemView)
        {
            super(itemView);
            iv = itemView.findViewById(R.id.iv);
            fastFood = itemView.findViewById(R.id.fastFood);
        }
        public void setData(int resource, String name)
        {
            iv.setImageResource(resource);
            fastFood.setText(name);
        }
    }
}