package com.example.pcbuilder;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Response;
import com.bumptech.glide.Glide;

import org.json.JSONArray;

import java.util.ArrayList;

public class DisplayAdapter extends RecyclerView.Adapter<DisplayAdapter.viewHolder> {

    Context context;
    ArrayList<DataModel> DataHolder;

    public DisplayAdapter(Context context, ArrayList<DataModel> dataHolder) {
        this.context = context;
        this.DataHolder = dataHolder;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.display_design, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        DataModel Product = DataHolder.get(position);
        holder.name.setText(Product.getName());
        holder.price.setText(Product.getPrice());
        Glide.with(context).load("http://192.168.43.220/images/"+Product.getImage()).into(holder.imgView);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DetailsPage detailsPage = new DetailsPage();
                Bundle bundle = new Bundle();
                bundle.putString("id", Product.getId());
                bundle.putString("name", Product.getName());
                bundle.putString("image", Product.getImage());
                bundle.putString("price", Product.getPrice());
                bundle.putString("factor", Product.getFactor());
                bundle.putString("platform", Product.getPlatform());
                bundle.putString("type", Product.getType());
                bundle.putString("specs", Product.getSpecs());
                bundle.putString("power", Product.getPower());
                detailsPage.setArguments(bundle);
                AppCompatActivity activity = (AppCompatActivity) v.getContext();
               activity.getSupportFragmentManager().beginTransaction()
                       .setCustomAnimations(R.anim.slide_from_right, R.anim.slideout_from_right, R.anim.slide_from_right, R.anim.slideout_from_right)
                       .replace(R.id.displayFrag, detailsPage)
                       .addToBackStack(null).commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return DataHolder.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{
        TextView name, price;
        ImageView imgView;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.prod_name);
            price = itemView.findViewById(R.id.prod_price);
            imgView = itemView.findViewById(R.id.prod_img);
        }
    }
}
