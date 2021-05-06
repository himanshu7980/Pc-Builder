package com.example.pcbuilder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyAdapter>
{
    ArrayList<Model> DataHolder;
    ClickInterface clickInterface;

    public RecyclerAdapter(ArrayList<Model> dataHolder, ClickInterface clickInterface) {
        this.DataHolder = dataHolder;
        this.clickInterface = clickInterface;
    }

    @NonNull
    @Override
    public MyAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_design,parent,false);
        return new MyAdapter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter holder, int position) {
        holder.imageView.setImageResource(DataHolder.get(position).getImage());
        holder.textView.setText(DataHolder.get(position).getName());
    }

    @Override
    public int getItemCount()
    {
        return DataHolder.size();
    }

    public class MyAdapter extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;
        public MyAdapter(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.CardImg);
            textView = itemView.findViewById(R.id.CardText);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickInterface.onItemClick(getAdapterPosition());
                }
            });
        }
    }
}
