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
import com.example.varatiamanagement.callbacks.OnPropertiesClickListener;
import com.example.varatiamanagement.databinding.PropetiesitemBinding;
import com.example.varatiamanagement.model.Properties;

public class PropertiesAdapter extends
        ListAdapter<Properties, PropertiesAdapter.PropertiesHolder> {

    private OnPropertiesClickListener listener;

    public PropertiesAdapter() {
        super(DIFE_CALLBACK);
    }

    public static final DiffUtil.ItemCallback<Properties> DIFE_CALLBACK =
            new DiffUtil.ItemCallback<Properties>() {
                @Override
                public boolean areItemsTheSame(@NonNull Properties oldNoteItem,
                                               @NonNull Properties newNoteItem) {
                    return oldNoteItem.getPropertyId() == newNoteItem.getPropertyId();
                }

                @Override
                public boolean areContentsTheSame(@NonNull Properties oldPropertiesItem,
                                                  @NonNull Properties newPropertiesItem) {
                    return (oldPropertiesItem.getOwnerPropertyID() == newPropertiesItem.getOwnerPropertyID()
                            && oldPropertiesItem.getType().equals(newPropertiesItem.getType())
                            && oldPropertiesItem.getDescription().equals(newPropertiesItem.getDescription())
                            && oldPropertiesItem.getName().equals(newPropertiesItem.getName())
                            && oldPropertiesItem.getAddress().equals(newPropertiesItem.getAddress())
                            && oldPropertiesItem.getRentAmount() == newPropertiesItem.getRentAmount()
                            && oldPropertiesItem.getStatus().equals(newPropertiesItem.getStatus())
                            && oldPropertiesItem.getRentBy().equals(newPropertiesItem.getRentBy())
                            && oldPropertiesItem.getRentPriceType().equals(newPropertiesItem
                            .getRentPriceType()) && oldPropertiesItem.getRentMembers().equals(
                                    newPropertiesItem.getRentMembers()));
                }
            };


    @NonNull
    @Override
    public PropertiesAdapter.PropertiesHolder
    onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        PropetiesitemBinding itemView = DataBindingUtil.
                inflate(LayoutInflater.from(viewGroup.getContext()),
                        R.layout.propetiesitem,
                        viewGroup,false);


        return new PropertiesAdapter.PropertiesHolder(itemView);
    }



    @Override
    public void onBindViewHolder(@NonNull PropertiesHolder propertiesHolder, int i) {

        Properties properties = getItem(i);
        propertiesHolder.propetiesitemBinding.numberOfOwnerProperty.
                setText("NumberOfOwnerProperty:      "+properties.getOwnerPropertyID());
        propertiesHolder.propetiesitemBinding.type.
                setText("Type:      "+properties.getType());
        propertiesHolder.propetiesitemBinding.description.
                setText("Description:      "+properties.getDescription());
        propertiesHolder.propetiesitemBinding.numberOfOwnerProperty.
                setText("Name:      "+properties.getName());
        propertiesHolder.propetiesitemBinding.numberOfOwnerProperty.
                setText("Address:      "+properties.getAddress());
        propertiesHolder.propetiesitemBinding.rentAmount.
                setText("RentAmount:      "+properties.getRentAmount());
        propertiesHolder.propetiesitemBinding.status.
                setText("Status:      "+properties.getStatus());
        propertiesHolder.propetiesitemBinding.rentBy.
                setText("RentBy:      "+properties.getRentBy());
        propertiesHolder.propetiesitemBinding.rentPriceType.
                setText("RentPriceType:      "+properties.getRentPriceType());
        propertiesHolder.propetiesitemBinding.members.
                setText("RentMembers:      "+properties.getRentMembers());




    }



    public Properties getPropertyAt(int position){

        return getItem(position);
    }

    class PropertiesHolder extends RecyclerView.ViewHolder{

        PropetiesitemBinding propetiesitemBinding;

        public PropertiesHolder
                (PropetiesitemBinding p) {
            super(p.getRoot());
            propetiesitemBinding = p;

            propetiesitemBinding.getRoot().setOnClickListener(new View.OnClickListener() {
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

    public void setOnItemClickListener(OnPropertiesClickListener listener){

        this.listener = listener;

    }
}
