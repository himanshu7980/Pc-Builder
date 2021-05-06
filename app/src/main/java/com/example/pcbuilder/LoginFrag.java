package com.example.pcbuilder;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
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
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.HashMap;
import java.util.Map;

public class LoginFrag extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;

    TextInputLayout Email, Password;
    Button LoginBtn;
    TextView ForgotPass, NewUser;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    public LoginFrag() {
        // Required empty public constructor
    }

    public static LoginFrag newInstance(String param1, String param2) {
        LoginFrag fragment = new LoginFrag();
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

        View view = inflater.inflate(R.layout.fragment_login, container, false);
        Email = view.findViewById(R.id.user_email);
        Password =view.findViewById(R.id.user_pass);
        LoginBtn = view.findViewById(R.id.login_btn);
        ForgotPass = view.findViewById(R.id.forgot_pass);
        NewUser = view.findViewById(R.id.reg_new_user);

        sharedPreferences = this.getActivity().getSharedPreferences("login", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();


        if(sharedPreferences.contains("loginEmail") && sharedPreferences.contains("loginPass")){
            startActivity(new Intent(getActivity(), MainActivity.class));
            getActivity().finish();
        }

        LoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               login();
            }
        });

        ForgotPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .setCustomAnimations(R.anim.slide_from_right, R.anim.slideout_from_left)
                        .replace(R.id.loginFrag, new ForgotFrag())
                        .commit();
            }
        });

        NewUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .setCustomAnimations(R.anim.slide_from_right, R.anim.slideout_from_left)
                        .replace(R.id.loginFrag, new RegFrag())
                        .commit();
            }
        });
        return view;
    }
    public void login(){
        String URL = "http://192.168.43.220:5000/login";
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("App", "Response is "+ response.toString());
                try {
                    if(response.equals("Success")) {
                        editor.putString("loginEmail", Email.getEditText().getText().toString());
                        editor.putString("loginPass", Password.getEditText().getText().toString());
                        editor.commit();
                        startActivity(new Intent(getActivity(), MainActivity.class));
                    }

                    if(response.equals("Failure")) {
                        Toast.makeText(getContext(), "Enter Valid Email & Password", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), "Something Wrong  " + error.toString(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("Key_Email", Email.getEditText().getText().toString());
                params.put("Key_Password", Password.getEditText().getText().toString());
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }
}