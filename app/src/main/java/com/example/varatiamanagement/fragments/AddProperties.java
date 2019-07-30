package com.example.varatiamanagement.fragments;


import android.app.Activity;
import android.app.ProgressDialog;
import android.arch.persistence.room.Room;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.varatiamanagement.R;
import com.example.varatiamanagement.activity.LoginActivity;
import com.example.varatiamanagement.activity.SignUPActivity;
import com.example.varatiamanagement.dao.OwnerPropertyDao;
import com.example.varatiamanagement.dao.UserDao;
import com.example.varatiamanagement.database.OwnerPropertyDatabase;
import com.example.varatiamanagement.database.UserDatabase;
import com.example.varatiamanagement.databinding.FragmentAddPropertiesBinding;
import com.example.varatiamanagement.enumClass.Type;
import com.example.varatiamanagement.model.OwnerProperty;
import com.example.varatiamanagement.model.User;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddProperties extends Fragment {

    FragmentAddPropertiesBinding fragmentAddPropertiesBinding;
    private ProgressDialog progressDialog;
    private OwnerPropertyDao ownerPropertyDao;


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

        return view;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        fragmentAddPropertiesBinding.spinner.setAdapter(new ArrayAdapter<Type>(getActivity(),
                android.R.layout.simple_spinner_item, Type.values()));

        progressDialog = new ProgressDialog(getContext());
        progressDialog.setCancelable(false);
        progressDialog.setMessage("saving...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setProgress(0);

        ownerPropertyDao = Room.databaseBuilder(getContext(),
                OwnerPropertyDatabase.class, "owner-database.db")
                .allowMainThreadQueries()
                .build()
                .getOwnerPropertyDao();

        fragmentAddPropertiesBinding.buttonAddNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isEmpty()) {

                    progressDialog.show();

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            OwnerProperty ownerProperty = new OwnerProperty(fragmentAddPropertiesBinding.name.getEditText().getText().
                                    toString().trim(),fragmentAddPropertiesBinding.spinner.getSelectedItem().toString(),
                                    fragmentAddPropertiesBinding.description.getEditText().
                                            getText().toString().trim(),
                                    fragmentAddPropertiesBinding.address.getEditText().
                                            getText().toString().trim());
                            ownerPropertyDao.insert(ownerProperty);
                            progressDialog.dismiss();
                            Toast.makeText(getContext(), "Data  Saved", Toast.LENGTH_SHORT).show();
                            fragmentAddPropertiesBinding.name.getEditText().setText("");
                            fragmentAddPropertiesBinding.address.getEditText().setText("");
                            fragmentAddPropertiesBinding.description.getEditText().setText("");
                        }
                    }, 1000);

                } else {
                    Toast.makeText(getContext(), "Data Not Saved", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean isEmpty() {

        hideKeyboard(getActivity());
        if(!validateName())
            return true;
        if(!validateAddress())
            return true;
        if(!validateDescription())
            return true;
        else
            return false;

    }

    private boolean validateName() {
        String name = fragmentAddPropertiesBinding.name.getEditText().getText().toString().trim();
        if (name.isEmpty()) {
           fragmentAddPropertiesBinding.name.setError(getString(R.string.err_name));
            requestFocus(fragmentAddPropertiesBinding.name);
            return false;
        }  else {
            fragmentAddPropertiesBinding.name.setErrorEnabled(false);

        }

        return true;
    }

    private boolean validateAddress() {
        String name = fragmentAddPropertiesBinding.address.getEditText().getText().toString().trim();
        if (name.isEmpty()) {
            fragmentAddPropertiesBinding.address.setError(getString(R.string.err_address));
            requestFocus(fragmentAddPropertiesBinding.address);
            return false;
        }  else {
           fragmentAddPropertiesBinding.address.setErrorEnabled(false);

        }

        return true;
    }

    private boolean validateDescription() {
        String name = fragmentAddPropertiesBinding.description.getEditText().getText().toString().trim();
        if (name.isEmpty()) {
            fragmentAddPropertiesBinding.description.setError(getString(R.string.err_description));
            requestFocus(fragmentAddPropertiesBinding.description);
            return false;
        }  else {
            fragmentAddPropertiesBinding.description.setErrorEnabled(false);

        }

        return true;
    }

    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = activity.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
           getActivity().getWindow().
                   setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }
}
