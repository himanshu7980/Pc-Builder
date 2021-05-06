package com.example.pcbuilder;

import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.fragment.app.ListFragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

public class MainDashboard extends Fragment implements ClickInterface{

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    RecyclerView recyclerView;
    ArrayList<Model> DataHolder;

    public MainDashboard() {
        // Required empty public constructor
    }

    public static MainDashboard newInstance(String param1, String param2) {
        MainDashboard fragment = new MainDashboard();
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
        View view = inflater.inflate(R.layout.fragment_main_dashboard, container, false);
        recyclerView = view.findViewById(R.id.RecView);
        DataHolder = new ArrayList<>();

        Model obj1 = new Model(R.drawable.processor,"Processor");
        DataHolder.add(obj1);

        Model obj2 = new Model(R.drawable.motherboard,"Motherboard");
        DataHolder.add(obj2);

        Model obj3 = new Model(R.drawable.ram,"RAM");
        DataHolder.add(obj3);

        Model obj4 = new Model(R.drawable.drive,"Storage");
        DataHolder.add(obj4);

        Model obj5 = new Model(R.drawable.grapics,"Graphics Card");
        DataHolder.add(obj5);

        Model obj6 = new Model(R.drawable.fan,"CPU Cooler");
        DataHolder.add(obj6);

        Model obj7 = new Model(R.drawable.cpu,"Cases");
        DataHolder.add(obj7);

        Model obj8 = new Model(R.drawable.power,"Power Supply");
        DataHolder.add(obj8);

        recyclerView.setAdapter(new RecyclerAdapter(DataHolder, this));
        return view;
    }

    @Override
    public void onItemClick(int position) {
//        Toast.makeText(getActivity(), DataHolder.get(position).getName(), Toast.LENGTH_SHORT).show();
        switch (DataHolder.get(position).getName()) {
            case "Processor":
                SQL db = new SQL(getContext());
                Cursor result = db.Select("Motherboard");
                if (result.getCount()>0)  {
                    result.moveToNext();
                    DisplayFrag displayFrag = new DisplayFrag();
                    Bundle bundle = new Bundle();
                    bundle.putString("Column1", "Type");
                    bundle.putString("Column2", "Platform");
                    bundle.putString("Param1", "Processor");
                    bundle.putString("Param2", result.getString(4));
                    displayFrag.setArguments(bundle);
                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    transaction.setCustomAnimations(R.anim.slide_from_right, R.anim.slideout_from_right, R.anim.slide_from_right, R.anim.slideout_from_right);
                    transaction.replace(R.id.MainFrag, displayFrag);
                    transaction.addToBackStack(null).commit();
                } else {
                    DisplayFrag displayFrag = new DisplayFrag();
                    Bundle bundle = new Bundle();
                    bundle.putString("Column1", "Type");
                    bundle.putString("Column2", "Type");
                    bundle.putString("Param1", "Processor");
                    bundle.putString("Param2", "Processor");
                    displayFrag.setArguments(bundle);
                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    transaction.setCustomAnimations(R.anim.slide_from_right, R.anim.slideout_from_right, R.anim.slide_from_right, R.anim.slideout_from_right);
                    transaction.replace(R.id.MainFrag, displayFrag);
                    transaction.addToBackStack(null).commit();
                }
                break;

            case "Motherboard":
                db = new SQL(getContext());
                result = db.Select("Processor");
                if (result.getCount()>0)  {
                    result.moveToNext();
                    DisplayFrag displayFrag = new DisplayFrag();
                    Bundle bundle = new Bundle();
                    bundle.putString("Column1", "Type");
                    bundle.putString("Column2", "Platform");
                    bundle.putString("Param1", "Motherboard");
                    bundle.putString("Param2", result.getString(4));
                    displayFrag.setArguments(bundle);
                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    transaction.setCustomAnimations(R.anim.slide_from_right, R.anim.slideout_from_right, R.anim.slide_from_right, R.anim.slideout_from_right);
                    transaction.replace(R.id.MainFrag, displayFrag);
                    transaction.addToBackStack(null).commit();
                } else {
                    DisplayFrag displayFrag = new DisplayFrag();
                    Bundle bundle = new Bundle();
                    bundle.putString("Column1", "Type");
                    bundle.putString("Column2", "Type");
                    bundle.putString("Param1", "Motherboard");
                    bundle.putString("Param2", "Motherboard");
                    displayFrag.setArguments(bundle);
                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    transaction.setCustomAnimations(R.anim.slide_from_right, R.anim.slideout_from_right, R.anim.slide_from_right, R.anim.slideout_from_right);
                    transaction.replace(R.id.MainFrag, displayFrag);
                    transaction.addToBackStack(null).commit();
                }
                break;

            case "RAM":
                if(3==3) {
                    DisplayFrag displayFrag = new DisplayFrag();
                    Bundle bundle = new Bundle();
                    bundle.putString("Column1", "Type");
                    bundle.putString("Column2", "Type");
                    bundle.putString("Param1", "RAM");
                    bundle.putString("Param2", "RAM");
                    displayFrag.setArguments(bundle);
                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    transaction.setCustomAnimations(R.anim.slide_from_right, R.anim.slideout_from_right, R.anim.slide_from_right, R.anim.slideout_from_right);
                    transaction.replace(R.id.MainFrag, displayFrag);
                    transaction.addToBackStack(null).commit();
                }
                break;

            case "Storage":
                if(4==4) {
                    DisplayFrag displayFrag = new DisplayFrag();
                    Bundle bundle = new Bundle();
                    bundle.putString("Column1", "Type");
                    bundle.putString("Column2", "Type");
                    bundle.putString("Param1", "Storage");
                    bundle.putString("Param2", "Storage");
                    displayFrag.setArguments(bundle);
                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    transaction.setCustomAnimations(R.anim.slide_from_right, R.anim.slideout_from_right, R.anim.slide_from_right, R.anim.slideout_from_right);
                    transaction.replace(R.id.MainFrag, displayFrag);
                    transaction.addToBackStack(null).commit();
                }
                break;

            case "Graphics Card":
                if(5==5) {
                    DisplayFrag displayFrag = new DisplayFrag();
                    Bundle bundle = new Bundle();
                    bundle.putString("Column1", "Type");
                    bundle.putString("Column2", "Type");
                    bundle.putString("Param1", "Graphics Card");
                    bundle.putString("Param2", "Graphics Card");
                    displayFrag.setArguments(bundle);
                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    transaction.setCustomAnimations(R.anim.slide_from_right, R.anim.slideout_from_right, R.anim.slide_from_right, R.anim.slideout_from_right);
                    transaction.replace(R.id.MainFrag, displayFrag);
                    transaction.addToBackStack(null).commit();
                }
                break;

            case "CPU Cooler":
                if(6==6) {
                    DisplayFrag displayFrag = new DisplayFrag();
                    Bundle bundle = new Bundle();
                    bundle.putString("Column1", "Type");
                    bundle.putString("Column2", "Type");
                    bundle.putString("Param1", "CPU Cooler");
                    bundle.putString("Param2", "CPU Cooler");
                    displayFrag.setArguments(bundle);
                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    transaction.setCustomAnimations(R.anim.slide_from_right, R.anim.slideout_from_right, R.anim.slide_from_right, R.anim.slideout_from_right);
                    transaction.replace(R.id.MainFrag, displayFrag);
                    transaction.addToBackStack(null).commit();
                }
                break;

            case "Power Supply":
                db = new SQL(getContext());
                result = db.Select("Case");
                if (result.getCount()>0)  {
                    result.moveToNext();
                    DisplayFrag displayFrag = new DisplayFrag();
                    Bundle bundle = new Bundle();
                    bundle.putString("Column1", "Type");
                    bundle.putString("Column2", "Factor");
                    bundle.putString("Param1", "Power Supply");
                    bundle.putString("Param2", result.getString(3));
                    displayFrag.setArguments(bundle);
                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    transaction.setCustomAnimations(R.anim.slide_from_right, R.anim.slideout_from_right, R.anim.slide_from_right, R.anim.slideout_from_right);
                    transaction.replace(R.id.MainFrag, displayFrag);
                    transaction.addToBackStack(null).commit();
                } else {
                    DisplayFrag displayFrag = new DisplayFrag();
                    Bundle bundle = new Bundle();
                    bundle.putString("Column1", "Type");
                    bundle.putString("Column2", "Type");
                    bundle.putString("Param1", "Power Supply");
                    bundle.putString("Param2", "Power Supply");
                    displayFrag.setArguments(bundle);
                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    transaction.setCustomAnimations(R.anim.slide_from_right, R.anim.slideout_from_right, R.anim.slide_from_right, R.anim.slideout_from_right);
                    transaction.replace(R.id.MainFrag, displayFrag);
                    transaction.addToBackStack(null).commit();
                }
                break;

            case "Cases":
                db = new SQL(getContext());
                result = db.Select("Motherboard");
                if (result.getCount()>0)  {
                    result.moveToNext();
                    DisplayFrag displayFrag = new DisplayFrag();
                    Bundle bundle = new Bundle();
                    bundle.putString("Column1", "Type");
                    bundle.putString("Column2", "Factor");
                    bundle.putString("Param1", "Case");
                    bundle.putString("Param2", result.getString(3));
                    displayFrag.setArguments(bundle);
                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    transaction.setCustomAnimations(R.anim.slide_from_right, R.anim.slideout_from_right, R.anim.slide_from_right, R.anim.slideout_from_right);
                    transaction.replace(R.id.MainFrag, displayFrag);
                    transaction.addToBackStack(null).commit();
                } else {
                    DisplayFrag displayFrag = new DisplayFrag();
                    Bundle bundle = new Bundle();
                    bundle.putString("Column1", "Type");
                    bundle.putString("Column2", "Type");
                    bundle.putString("Param1", "Case");
                    bundle.putString("Param2", "Case");
                    displayFrag.setArguments(bundle);
                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    transaction.setCustomAnimations(R.anim.slide_from_right, R.anim.slideout_from_right, R.anim.slide_from_right, R.anim.slideout_from_right);
                    transaction.replace(R.id.MainFrag, displayFrag);
                    transaction.addToBackStack(null).commit();
                }
                break;
        }
    }
}