package com.example.varatiamanagement.adapter;

import android.databinding.DataBindingUtil;
import android.databinding.adapters.AdapterViewBindingAdapter;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.varatiamanagement.R;
import com.example.varatiamanagement.databinding.OwnerPropertiesitemBinding;
import com.example.varatiamanagement.model.OwnerProperty;

import java.util.ArrayList;

public class OwnerPropertiesAdapter extends
        RecyclerView.Adapter<OwnerPropertiesAdapter.ViewHolder> {


    ArrayList<OwnerProperty> ownerPropertyArrayList ;

    public OwnerPropertiesAdapter(ArrayList<OwnerProperty> ownerPropertyArrayList) {
        this.ownerPropertyArrayList = ownerPropertyArrayList;
    }


    @NonNull
    @Override
    public OwnerPropertiesAdapter.ViewHolder onCreateViewHolder
            (@NonNull ViewGroup viewGroup, int i) {
        OwnerPropertiesitemBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(viewGroup.getContext()),
                R.layout.owner_propertiesitem, viewGroup, false);

        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        OwnerProperty current = ownerPropertyArrayList.get(i);
        viewHolder.ownerPropertiesitemBinding.name.setText("Name: "+current.getName());
        viewHolder.ownerPropertiesitemBinding.address.
                setText("Address: "+current.getAddress());
        viewHolder.ownerPropertiesitemBinding.description.
                setText("Description: "+current.getAddress());
        viewHolder.ownerPropertiesitemBinding.type.
                setText("Type: "+current.getType());

    }

    @Override
    public int getItemCount() {
        if(ownerPropertyArrayList.isEmpty())
            return 0;
        else
            return ownerPropertyArrayList.size();
    }

    public class  ViewHolder extends RecyclerView.ViewHolder{


        OwnerPropertiesitemBinding ownerPropertiesitemBinding;

        public ViewHolder(OwnerPropertiesitemBinding ownerPropertiesitemBinding) {
            super(ownerPropertiesitemBinding.getRoot());
            this.ownerPropertiesitemBinding = ownerPropertiesitemBinding;
        }
    }


}
