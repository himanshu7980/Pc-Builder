package com.example.pcbuilder;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    Context context;
    ArrayList<CartModel> CartItem;
    int Power = 0;


    public CartAdapter(Context context, ArrayList<CartModel> cartItem) {
        this.context = context;
        this.CartItem = cartItem;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_design,parent,false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        CartModel item = CartItem.get(position);
        holder.name.setText(item.getName());
        holder.type.setText(item.getType());
        holder.price.setText(item.getPrice());
        Glide.with(context).load("http://192.168.43.220/images/"+item.getImage()).into(holder.imageView);

        int ProductPower = Integer.valueOf(item.getPower());
        Power += ProductPower;
        try{
            SQL db = new SQL(context);
            Cursor result= db.Find("Power Supply");
            result.moveToNext();
            int Pow = Power - Integer.valueOf(result.getString(8));
            if (Integer.valueOf(result.getString(8)) <= Pow) {
                    Toast.makeText(context, "Choose power supply More then " + Integer.valueOf(Pow) + "W", Toast.LENGTH_LONG).show();
            }
        } catch (Exception e){
            e.printStackTrace();
        }


        holder.Del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQL db = new SQL(context);
                Integer row = db.Delete(item.getName());
                if (row > 0){
                    Toast.makeText(context, "Item Removed", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(context, "Item Can't Remove", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public int TotalPrice(){
        int TotalPrice = 0;
        for(int i = 0 ; i < CartItem.size(); i++) {
            TotalPrice += Integer.valueOf(CartItem.get(i).getPrice());
        }
        return TotalPrice;
    }

    public int TotalPower(){
        int TotalPower = 0;
        for(int i = 0 ; i < CartItem.size(); i++) {
            TotalPower += Integer.valueOf(CartItem.get(i).getPower());
        }
        return TotalPower;
    }
    @Override
    public int getItemCount() {
        return CartItem.size();
    }

    public class CartViewHolder extends RecyclerView.ViewHolder {
        TextView name, type, price;
        ImageView imageView, Del;
        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.ItemName);
            price = itemView.findViewById(R.id.ItemPrice);
            type = itemView.findViewById(R.id.ItemType);
            imageView = itemView.findViewById(R.id.ItemImg);
            Del = itemView.findViewById(R.id.Del);
        }
    }
}
