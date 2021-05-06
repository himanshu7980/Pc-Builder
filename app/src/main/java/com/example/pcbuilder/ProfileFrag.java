package com.example.pcbuilder;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputLayout;

import java.util.HashMap;
import java.util.Map;

public class ProfileFrag extends Fragment {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    TextInputLayout CurrPass, NewPass, ConfNewPass;
    Button ChangeBtn;

    public ProfileFrag() {
        // Required empty public constructor
    }


    public static ProfileFrag newInstance(String param1, String param2) {
        ProfileFrag fragment = new ProfileFrag();
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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        CurrPass =view.findViewById(R.id.currPass);
        NewPass =view.findViewById(R.id.newPass);
        ConfNewPass =view.findViewById(R.id.confNewPass);
        ChangeBtn =view.findViewById(R.id.ChangeBtn);

        ChangeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChangePass();
            }
        });
        return view;
    }
    public void ChangePass(){
        String curr_pass = CurrPass.getEditText().getText().toString();
        String new_pass = NewPass.getEditText().getText().toString();
        String conf_new_pass = ConfNewPass.getEditText().getText().toString();

        if(curr_pass.isEmpty() && new_pass.isEmpty() && conf_new_pass.isEmpty()){
            Toast.makeText(getActivity(), "Fields Can't be Empty", Toast.LENGTH_SHORT).show();
        } else if(!new_pass.equals(conf_new_pass)){
            Toast.makeText(getActivity(), "Password Not Match", Toast.LENGTH_SHORT).show();
        } else {
            String URL = "http://192.168.43.220:5000/reset";
            RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
            StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    CurrPass.getEditText().setText(null);
                    NewPass.getEditText().setText(null);
                    ConfNewPass.getEditText().setText(null);
                    Toast.makeText(getContext(), response.toString(), Toast.LENGTH_SHORT).show();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getContext(), error.toString(), Toast.LENGTH_SHORT).show();
                }
            }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("CurrPass", curr_pass);
                    params.put("NewPass", conf_new_pass);
                    return params;
                }
            };
            requestQueue.add(stringRequest);
        }
    }
}