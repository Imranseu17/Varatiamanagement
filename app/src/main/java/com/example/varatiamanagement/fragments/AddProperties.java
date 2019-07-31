package com.example.varatiamanagement.fragments;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.varatiamanagement.R;
import com.example.varatiamanagement.databinding.FragmentAddPropertiesBinding;


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

        fragmentAddPropertiesBinding = DataBindingUtil.
                inflate(inflater,R.layout.fragment_add_properties,
                        container,false);
        View view = fragmentAddPropertiesBinding.getRoot();

        return view;
    }

}
