package com.example.pcbuilder;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

public class DetailsPage extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;

    ImageView imageView;
    Button btn;
    TextView Name, Price, Type, Specs, Factor, Platform;

    public DetailsPage() {
        // Required empty public constructor
    }

    public static DetailsPage newInstance(String param1, String param2) {
        DetailsPage fragment = new DetailsPage();
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
       View view = inflater.inflate(R.layout.fragment_details_page, container, false);
        Bundle result = this.getArguments();
        String id = result.getString("id");
        String name = result.getString("name");
        String image = result.getString("image");
        String price = result.getString("price");
        String factor = result.getString("factor");
        String platform = result.getString("platform");
        String type = result.getString("type");
        String specs = result.getString("specs");
        String power = result.getString("power");

        Name = view.findViewById(R.id.prod_name);
        imageView = view.findViewById(R.id.prod_img);
        Price = view.findViewById(R.id.prod_price);
        Factor = view.findViewById(R.id.prod_factor);
        Platform = view.findViewById(R.id.prod_platform);
        Type = view.findViewById(R.id.prod_type);
        Specs = view.findViewById(R.id.prod_specs);
        btn = view.findViewById(R.id.add_to_build);

        Name.setText(name);
        Glide.with(getActivity()).load("http://192.168.43.220/images/"+image).into(imageView);
        Price.setText(price);
        Type.setText(type);
        Factor.setText(factor);
        Platform.setText(platform);
        Specs.setText(specs);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQL db = new SQL(getContext());
                String result = db.insert(id, name, type, factor, platform, price, specs, image, power);
                Toast.makeText(getContext(), result, Toast.LENGTH_SHORT).show();
            }
        });

       return view;
    }
}