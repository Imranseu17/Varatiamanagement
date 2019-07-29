package com.example.varatiamanagement.fragments;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.example.varatiamanagement.R;
import com.example.varatiamanagement.databinding.FragmentAddPropertiesBinding;
import com.example.varatiamanagement.enumClass.Type;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddProperties extends Fragment {

    FragmentAddPropertiesBinding fragmentAddPropertiesBinding;


    public AddProperties() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        fragmentAddPropertiesBinding = DataBindingUtil.
                inflate(inflater,R.layout.fragment_add_properties,container,false);

        View view = fragmentAddPropertiesBinding.getRoot();
        fragmentAddPropertiesBinding.spinner.setAdapter(new ArrayAdapter<Type>(getActivity(),
                android.R.layout.simple_spinner_item, Type.values()));

        return view;

    }

}
