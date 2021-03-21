package com.example.hivapp.ui.store;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.hivapp.R;

public class StoreFragment extends Fragment {


    private CardView item1,item2,item3,item4,item5,item6,item7;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_store, container, false);
        //final TextView textView = root.findViewById(R.id.text_notifications);
        item1=view.findViewById(R.id.item1);
        item2=view.findViewById(R.id.item2);
        item3=view.findViewById(R.id.item3);
        item4=view.findViewById(R.id.item4);
        item5=view.findViewById(R.id.item5);
        item6=view.findViewById(R.id.item6);
        item7=view.findViewById(R.id.item7);

        item2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getActivity().getSupportFragmentManager();
                Bundle arguments = new Bundle();
                arguments.putString("category","Non-nucleoside Reverse Transcriptase Inhibitors");
                Fragment_CategoryDetails fragment_categoryDetails = new Fragment_CategoryDetails();
                fragment_categoryDetails.setArguments(arguments);
                fm.beginTransaction().addToBackStack(null).replace(R.id.fragment_container,fragment_categoryDetails).commit();
            }
        });

        item1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getActivity().getSupportFragmentManager();
                Bundle arguments = new Bundle();
                arguments.putString("category","Nucleotide Reverse Transcriptase Inhibitors");
                Fragment_CategoryDetails fragment_categoryDetails = new Fragment_CategoryDetails();
                fragment_categoryDetails.setArguments(arguments);
                fm.beginTransaction().addToBackStack(null).replace(R.id.fragment_container,fragment_categoryDetails).commit();
            }
        });
        item3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getActivity().getSupportFragmentManager();
                Bundle arguments = new Bundle();
                arguments.putString("category","Protease Inhibitors");
                Fragment_CategoryDetails fragment_categoryDetails = new Fragment_CategoryDetails();
                fragment_categoryDetails.setArguments(arguments);
                fm.beginTransaction().addToBackStack(null).replace(R.id.fragment_container,fragment_categoryDetails).commit();
            }
        });

        item4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getActivity().getSupportFragmentManager();
                Bundle arguments = new Bundle();
                arguments.putString("category","Integrase Inhibitors");
                Fragment_CategoryDetails fragment_categoryDetails = new Fragment_CategoryDetails();
                fragment_categoryDetails.setArguments(arguments);
                fm.beginTransaction().addToBackStack(null).replace(R.id.fragment_container,fragment_categoryDetails).commit();
            }
        });

        item5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getActivity().getSupportFragmentManager();
                Bundle arguments = new Bundle();
                arguments.putString("category","Fusion Inhibitors");
                Fragment_CategoryDetails fragment_categoryDetails = new Fragment_CategoryDetails();
                fragment_categoryDetails.setArguments(arguments);
                fm.beginTransaction().addToBackStack(null).replace(R.id.fragment_container,fragment_categoryDetails).commit();
            }
        });
        item6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getActivity().getSupportFragmentManager();
                Bundle arguments = new Bundle();
                arguments.putString("category","gp120 Attachment Inhibitor");
                Fragment_CategoryDetails fragment_categoryDetails = new Fragment_CategoryDetails();
                fragment_categoryDetails.setArguments(arguments);
                fm.beginTransaction().addToBackStack(null).replace(R.id.fragment_container,fragment_categoryDetails).commit();
            }
        });
        item7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getActivity().getSupportFragmentManager();
                Bundle arguments = new Bundle();
                arguments.putString("category","CCR5 Antagonist");
                Fragment_CategoryDetails fragment_categoryDetails = new Fragment_CategoryDetails();
                fragment_categoryDetails.setArguments(arguments);
                fm.beginTransaction().addToBackStack(null).replace(R.id.fragment_container,fragment_categoryDetails).commit();
            }
        });




        return view;
    }
}