package com.example.pcbuilder;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.util.Patterns;
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
import java.util.regex.Pattern;

public class RegFrag extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public static final Pattern PASSWORD_PATTERN = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\\\S+$).{8,}$");

    private String mParam1;
    private String mParam2;

    TextInputLayout Name, Email, Password;
    Button RegBtn;
    TextView LoginPage;

    public RegFrag() {
        // Required empty public constructor
    }

    public static RegFrag newInstance(String param1, String param2) {
        RegFrag fragment = new RegFrag();
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

        View view = inflater.inflate(R.layout.fragment_reg, container, false);

        Name =view.findViewById(R.id.UserName);
        Email = view.findViewById(R.id.UserEmail);
        Password =view.findViewById(R.id.UserPass);
        RegBtn = view.findViewById(R.id.RegBtn);
        LoginPage = view.findViewById(R.id.LoginPage);

        LoginPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .setCustomAnimations(R.anim.slide_from_left, R.anim.slideout_from_left)
                        .replace(R.id.regFrag, new LoginFrag())
                        .commit();
            }
        });

        RegBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Register();
            }
        });

        return view;
    }
    public void Register(){
        String name = Name.getEditText().getText().toString();
        String email = Email.getEditText().getText().toString();
        String password = Password.getEditText().getText().toString();

        if(name.isEmpty() && email.isEmpty() && password.isEmpty()){
            Toast.makeText(getContext(), "Fields Can't be Empty", Toast.LENGTH_SHORT).show();
        } else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches() && !PASSWORD_PATTERN.matcher(password).matches()){
            Toast.makeText(getContext(), "Enter Valid Email & Password", Toast.LENGTH_SHORT).show();
        } else {
            String URL = "http://192.168.43.220:5000/register";
            RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
            StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Name.getEditText().setText(null);
                    Email.getEditText().setText(null);
                    Password.getEditText().setText(null);
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
                    params.put("Key_Name", name);
                    params.put("Key_Email", email);
                    params.put("Key_Password", password);
                    return params;
                }
            };
            requestQueue.add(stringRequest);
        }
    }
}