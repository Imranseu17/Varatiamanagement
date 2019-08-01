package com.example.varatiamanagement.adapter;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.recyclerview.extensions.ListAdapter;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.varatiamanagement.R;
import com.example.varatiamanagement.callbacks.OnOwnerItemClickListener;
import com.example.varatiamanagement.databinding.OwnerPropertiesitemBinding;
import com.example.varatiamanagement.model.OwnerProperty;

public class OwnerPropertiesAdapter extends
        ListAdapter<OwnerProperty, OwnerPropertiesAdapter.OwnerPropertiesHolder> {


    private OnOwnerItemClickListener listener;

    public OwnerPropertiesAdapter() {
        super(DIFE_CALLBACK);
    }

    public static final DiffUtil.ItemCallback<OwnerProperty> DIFE_CALLBACK =
            new DiffUtil.ItemCallback<OwnerProperty>() {
        @Override
        public boolean areItemsTheSame(@NonNull OwnerProperty oldNoteItem,
                                       @NonNull OwnerProperty newNoteItem) {
            return oldNoteItem.getId() == newNoteItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull OwnerProperty oldPropertiesItem,
                                          @NonNull OwnerProperty newPropertiesItem) {
            return oldPropertiesItem.getName().equals(newPropertiesItem.getName())
                    && oldPropertiesItem.getType().equals(newPropertiesItem.getType())
                    && oldPropertiesItem.getAddress().equals(newPropertiesItem.getAddress())
                    && oldPropertiesItem.getDescription().equals(newPropertiesItem.getDescription());
        }
    };


    @NonNull
    @Override
    public OwnerPropertiesAdapter.OwnerPropertiesHolder
    onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        OwnerPropertiesitemBinding itemView = DataBindingUtil.
                inflate(LayoutInflater.from(viewGroup.getContext()),
                        R.layout.owner_propertiesitem,viewGroup,false);


        return new OwnerPropertiesHolder(itemView);
    }



    @Override
    public void onBindViewHolder(@NonNull OwnerPropertiesHolder ownerPropertiesHolder, int i) {

        OwnerProperty ownerProperty = getItem(i);
        ownerPropertiesHolder.ownerPropertiesitemBinding.name.
                setText("Name:      "+ownerProperty.getName());
        ownerPropertiesHolder.ownerPropertiesitemBinding.type.
                setText("Type:     "+ ownerProperty.getType());
        ownerPropertiesHolder.ownerPropertiesitemBinding.address.
                setText("Address:    "+ownerProperty.getAddress());
        ownerPropertiesHolder.ownerPropertiesitemBinding.description.
                setText("Description: "+ownerProperty.getDescription());

    }



    public OwnerProperty getOwnerPropertyAt(int position){

        return getItem(position);
    }

    class OwnerPropertiesHolder extends RecyclerView.ViewHolder{

        OwnerPropertiesitemBinding ownerPropertiesitemBinding;

        public OwnerPropertiesHolder
                (OwnerPropertiesitemBinding propertiesitemBinding) {
            super(propertiesitemBinding.getRoot());
            ownerPropertiesitemBinding = propertiesitemBinding;

            ownerPropertiesitemBinding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    int position = getAdapterPosition();
                    if(listener != null && position != RecyclerView.NO_POSITION)
                    {
                        listener.onItemClick(getItem(position));
                    }

                }
            });
        }
    }

    public void setOnItemClickListener(OnOwnerItemClickListener listener){

        this.listener = listener;

    }

}
