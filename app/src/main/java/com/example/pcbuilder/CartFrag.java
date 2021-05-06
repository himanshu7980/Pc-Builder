package com.example.pcbuilder;

import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class CartFrag extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    RecyclerView recyclerView;
    ArrayList<CartModel> CartItem;
    TextView Amount, Power;
    public CartFrag() {
        // Required empty public constructor
    }

    public static CartFrag newInstance(String param1, String param2) {
        CartFrag fragment = new CartFrag();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cart, container, false);
        recyclerView = view.findViewById(R.id.CartRecView);
        Amount = view.findViewById(R.id.totalAmt);
        Power = view.findViewById(R.id.Pow);
        CartItem = new ArrayList<>();
        SQL db = new SQL(getContext());
        Cursor result = db.Display();
        while (result.moveToNext()) {
            CartModel model = new CartModel(result.getString(1), result.getString(7),
                    result.getString(2), result.getString(5), result.getString(8));
                CartItem.add(model);
        }
        CartAdapter cartAdapter = new CartAdapter(getContext() ,CartItem);
        recyclerView.setAdapter(cartAdapter);
        Amount.setText(String.valueOf(cartAdapter.TotalPrice()));
        Power.setText(String.valueOf(cartAdapter.TotalPower()));
        return view;
    }
}