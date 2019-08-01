package com.example.varatiamanagement.activity;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.varatiamanagement.R;
import com.example.varatiamanagement.adapter.OwnerPropertiesAdapter;
import com.example.varatiamanagement.callbacks.OnOwnerItemClickListener;
import com.example.varatiamanagement.databinding.ActivityOwnerRentsBinding;
import com.example.varatiamanagement.model.OwnerProperty;
import com.example.varatiamanagement.utils.SharedDataSaveLoad;
import com.example.varatiamanagement.viewModel.OwnerPropertyViewModel;

import java.util.List;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;


public class OwnerRentsActivity extends AppCompatActivity {

    private OwnerPropertyViewModel ownerPropertyViewModel;

    public static final int EDIT_NOTE_REQUEST = 1;

   ActivityOwnerRentsBinding activityOwnerRentsBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityOwnerRentsBinding = DataBindingUtil.setContentView(OwnerRentsActivity.this,
                R.layout.activity_owner_rents);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Owner Properties List");


        activityOwnerRentsBinding. recyclerView.setLayoutManager(new LinearLayoutManager(this));
       activityOwnerRentsBinding. recyclerView.setHasFixedSize(true);

        final OwnerPropertiesAdapter propertiesAdapter = new OwnerPropertiesAdapter();
        activityOwnerRentsBinding. recyclerView.setAdapter(propertiesAdapter);

        ownerPropertyViewModel = ViewModelProviders.of(this).get(OwnerPropertyViewModel.class);
        ownerPropertyViewModel.getListLiveData().observe(this,
                new Observer<List<OwnerProperty>>() {
            @Override
            public void onChanged(@Nullable List<OwnerProperty> ownerProperties) {
                //update RecyclerView
                propertiesAdapter.submitList(ownerProperties);
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder,
                                  @NonNull RecyclerView.ViewHolder viewHolder1) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

                ownerPropertyViewModel.delete(propertiesAdapter.getOwnerPropertyAt
                        (viewHolder.getAdapterPosition()));
                Toast.makeText( OwnerRentsActivity.this,"Deleted",Toast.LENGTH_SHORT).show();

            }
        }).attachToRecyclerView(activityOwnerRentsBinding. recyclerView);

        propertiesAdapter.setOnItemClickListener(new OnOwnerItemClickListener() {
            @Override
            public void onItemClick(OwnerProperty ownerProperty) {
                Intent intent = new Intent(OwnerRentsActivity.this,
                        EditOwnerPropertiesActivity.class);
                intent.putExtra(EditOwnerPropertiesActivity.ID, ownerProperty.getId());
                intent.putExtra(EditOwnerPropertiesActivity.NAME, ownerProperty.getName());
                intent.putExtra(EditOwnerPropertiesActivity.TYPE, ownerProperty.getType());
                intent.putExtra(EditOwnerPropertiesActivity.ADDRESS, ownerProperty.getAddress());
                intent.putExtra(EditOwnerPropertiesActivity.DESCRIPTION, ownerProperty.getDescription());


                startActivityForResult(intent, EDIT_NOTE_REQUEST);
            }
        });


    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);



         if (requestCode == EDIT_NOTE_REQUEST && resultCode == RESULT_OK) {
            int id = data.getIntExtra(EditOwnerPropertiesActivity.ID, -1);

            if (id == -1) {
                Toast.makeText(this, "can't be updated", Toast.LENGTH_SHORT).show();
                return;
            }

            String name = data.getStringExtra(EditOwnerPropertiesActivity.NAME);
            String type = data.getStringExtra(EditOwnerPropertiesActivity.TYPE);
            String address = data.getStringExtra(EditOwnerPropertiesActivity.ADDRESS);
            String description = data.getStringExtra(EditOwnerPropertiesActivity.DESCRIPTION);


            OwnerProperty ownerProperty = new OwnerProperty(name,type,address,description);
            ownerProperty.setId(id);
            ownerPropertyViewModel.update(ownerProperty);

            Toast.makeText(this, "updated", Toast.LENGTH_SHORT).show();
        }

        else
            Toast.makeText(this,"not updated ",Toast.LENGTH_SHORT).show();
    }




}
