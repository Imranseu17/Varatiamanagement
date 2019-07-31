package com.example.varatiamanagement.fragments;


import android.app.Activity;
import android.app.ProgressDialog;
import android.arch.persistence.room.Room;
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
import com.example.varatiamanagement.dao.PropertiesDao;
import com.example.varatiamanagement.database.OwnerPropertyDatabase;
import com.example.varatiamanagement.database.PropertiesDatabase;
import com.example.varatiamanagement.databinding.FragmentAddPropertiesBinding;
import com.example.varatiamanagement.enumClass.RentBy;
import com.example.varatiamanagement.enumClass.RentPriceType;
import com.example.varatiamanagement.enumClass.Status;
import com.example.varatiamanagement.enumClass.Type;
import com.example.varatiamanagement.model.OwnerProperty;
import com.example.varatiamanagement.model.Properties;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddProperties extends Fragment {

   FragmentAddPropertiesBinding fragmentAddPropertiesBinding;
    private ProgressDialog progressDialog;
    private PropertiesDao propertiesDao;


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

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        fragmentAddPropertiesBinding.numberOfOwnerProperty.
                setAdapter(new ArrayAdapter<Type>(getActivity(),
                android.R.layout.simple_spinner_item, Type.values()));
        fragmentAddPropertiesBinding.rentPriceType.
                setAdapter(new ArrayAdapter<RentPriceType>(getActivity(),
                android.R.layout.simple_spinner_item, RentPriceType.values()));
        fragmentAddPropertiesBinding.rentBy.setAdapter(new ArrayAdapter<RentBy>(getActivity(),
                android.R.layout.simple_spinner_item, RentBy.values()));
        fragmentAddPropertiesBinding.type.setAdapter(new ArrayAdapter<Type>(getActivity(),
                android.R.layout.simple_spinner_item, Type.values()));
        fragmentAddPropertiesBinding.status.setAdapter(new ArrayAdapter<Status>(getActivity(),
                android.R.layout.simple_spinner_item, Status.values()));


        progressDialog = new ProgressDialog(getContext());
        progressDialog.setCancelable(false);
        progressDialog.setMessage("saving...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setProgress(0);

        propertiesDao = Room.databaseBuilder(getContext(),
                PropertiesDatabase.class, "properties-database.db")
                .allowMainThreadQueries()
                .build()
                .getPropertiesDao();

        fragmentAddPropertiesBinding.buttonAddNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isEmpty()) {

                    progressDialog.show();

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            Properties properties =
                                    new Properties(fragmentAddPropertiesBinding.numberOfOwnerProperty.
                                            getId(),fragmentAddPropertiesBinding.type.
                                            getSelectedItem().toString(),
                                            fragmentAddPropertiesBinding.description.
                                                    getEditText().getText().toString(),
                                            fragmentAddPropertiesBinding.name.getEditText().
                                                    getText().toString(),
                                            fragmentAddPropertiesBinding.address.
                                                    getEditText().getText().toString(),
                                          Double.parseDouble(fragmentAddPropertiesBinding.rentAmount.
                                                  getEditText().getText().toString()),
                                            fragmentAddPropertiesBinding.status.
                                            getSelectedItem().toString(),
                                            fragmentAddPropertiesBinding.rentBy.
                                                    getSelectedItem().toString(),
                                            fragmentAddPropertiesBinding.rentPriceType.
                                                    getSelectedItem().toString(),
                                            fragmentAddPropertiesBinding.members.
                                                    getEditText().getText().toString()
                                            );
                            propertiesDao.insert(properties);
                            progressDialog.dismiss();
                            Toast.makeText(getContext(), "Data  Saved", Toast.LENGTH_SHORT).show();
                            fragmentAddPropertiesBinding.name.getEditText().setText("");
                            fragmentAddPropertiesBinding.address.getEditText().setText("");
                           fragmentAddPropertiesBinding.description.getEditText().setText("");
                           fragmentAddPropertiesBinding.members.getEditText().setText("");
                           fragmentAddPropertiesBinding.rentAmount.getEditText().setText("");
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
        if(!validateMembers())
            return true;
        if(!validateRentAmount())
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

    private boolean validateMembers() {
        String name = fragmentAddPropertiesBinding.members.getEditText().getText().toString().trim();
        if (name.isEmpty()) {
            fragmentAddPropertiesBinding.members.setError(getString(R.string.err_members));
            requestFocus(fragmentAddPropertiesBinding.members);
            return false;
        }  else {
            fragmentAddPropertiesBinding.members.setErrorEnabled(false);

        }

        return true;
    }

    private boolean validateRentAmount() {
        String name = fragmentAddPropertiesBinding.rentAmount.getEditText().getText().toString().trim();
        if (name.isEmpty()) {
            fragmentAddPropertiesBinding.rentAmount.setError(getString(R.string.err_rentAmount));
            requestFocus(fragmentAddPropertiesBinding.rentAmount);
            return false;
        }  else {
            fragmentAddPropertiesBinding.rentAmount.setErrorEnabled(false);

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
