package com.example.pcbuilder;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DisplayFrag extends Fragment{

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    RecyclerView recyclerView;
    ArrayList<DataModel> DataHolder;

    public DisplayFrag() {}

    public static DisplayFrag newInstance(String param1, String param2) {
        DisplayFrag fragment = new DisplayFrag();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_display, container, false);
        recyclerView = view.findViewById(R.id.DisplayRecView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        DataHolder = new ArrayList<>();
        DisplayProducts();
        return view;
    }
    public void DisplayProducts(){

        Bundle result = this.getArguments();
        RequestQueue queue = Volley.newRequestQueue(getActivity());
        StringRequest request = new StringRequest(Request.Method.POST, "http://192.168.43.220:5000/display", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray products = new JSONArray(response);
                    for (int i=0; i<products.length(); i++){
                        JSONObject productObject = products.getJSONObject(i);
                        String id = productObject.getString("Id");
                        String name = productObject.getString("Name");
                        String type = productObject.getString("Type");
                        String factor = productObject.getString("Factor");
                        String platform = productObject.getString("Platform");
                        String price = productObject.getString("Price");
                        String specs = productObject.getString("Specs");
                        String image = productObject.getString("Image");
                        String power = productObject.getString("Power");
                        DataHolder.add(new DataModel(id, name, type, factor, platform, price,  specs, image, power));
                    }
                    recyclerView.setAdapter(new DisplayAdapter(getContext(), DataHolder));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), "Something Wrong  "+ error.toString(), Toast.LENGTH_SHORT).show();
            }
        })
        {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("C1", result.getString("Column1"));
                params.put("C2", result.getString("Column2"));
                params.put("P1", result.getString("Param1"));
                params.put("P2", result.getString("Param2"));
                return params;
            }
        };
        queue.add(request);
    }
}