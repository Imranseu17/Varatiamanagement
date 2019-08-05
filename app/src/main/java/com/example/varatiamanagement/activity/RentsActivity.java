package com.example.varatiamanagement.activity;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.varatiamanagement.R;
import com.example.varatiamanagement.adapter.PropertiesAdapter;
import com.example.varatiamanagement.callbacks.OnPropertiesClickListener;
import com.example.varatiamanagement.databinding.ActivityRentsBinding;
import com.example.varatiamanagement.model.OwnerProperty;
import com.example.varatiamanagement.model.Properties;
import com.example.varatiamanagement.viewModel.PropertiesViewModel;

import java.util.List;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class RentsActivity extends AppCompatActivity {

    ActivityRentsBinding rentsBinding;
    private PropertiesViewModel propertiesViewModel;

    public static final int EDIT_NOTE_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        rentsBinding = DataBindingUtil.setContentView(RentsActivity.this,
                R.layout.activity_rents);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Properties List");


        rentsBinding. recyclerView.setLayoutManager(new LinearLayoutManager(this));
        rentsBinding. recyclerView.setHasFixedSize(true);

        final PropertiesAdapter propertiesAdapter = new PropertiesAdapter();
        rentsBinding. recyclerView.setAdapter(propertiesAdapter);

        propertiesViewModel = ViewModelProviders.of(this).
                get(PropertiesViewModel.class);
        propertiesViewModel.getListLiveData().observe(this,
                new Observer<List<Properties>>() {
                    @Override
                    public void onChanged(@Nullable List<Properties> properties) {
                        //update RecyclerView
                        propertiesAdapter.submitList(properties);

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

                propertiesViewModel.delete(propertiesAdapter.getPropertyAt
                        (viewHolder.getAdapterPosition()));
                Toast.makeText( RentsActivity.this,"Deleted",Toast.LENGTH_SHORT).show();

            }
        }).attachToRecyclerView(rentsBinding. recyclerView);

        propertiesAdapter.setOnItemClickListener(new OnPropertiesClickListener() {
            @Override
            public void onItemClick(Properties properties) {
                Intent intent = new Intent(RentsActivity.this,
                        EditPropertiesActivity.class);
                intent.putExtra(EditPropertiesActivity.ID ,
                        properties.getPropertyId());
                intent.putExtra(EditPropertiesActivity.OWNERPROPERTYID ,
                        properties.getOwnerPropertyID());
                intent.putExtra(EditPropertiesActivity.TYPE ,
                        properties.getType());
                intent.putExtra(EditPropertiesActivity.DESCRIPTION,
                        properties.getDescription());
                intent.putExtra(EditPropertiesActivity.NAME ,
                        properties.getName());
                intent.putExtra(EditPropertiesActivity.ADDRESS ,
                        properties.getAddress());
                intent.putExtra(EditPropertiesActivity.RENTAMOUNT ,
                        properties.getRentAmount());
                intent.putExtra(EditPropertiesActivity.STATUS ,
                        properties.getStatus());
                intent.putExtra(EditPropertiesActivity.RENTBY ,
                        properties.getRentBy());
                intent.putExtra(EditPropertiesActivity.RENTPRICETYPE ,
                        properties.getRentPriceType());
                intent.putExtra(EditPropertiesActivity.RENTMEMBERS ,
                        properties.getRentMembers());


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
    protected void onActivityResult(int requestCode, int resultCode,  Intent data) {
        super.onActivityResult(requestCode, resultCode, data);



        if (requestCode == EDIT_NOTE_REQUEST && resultCode == RESULT_OK) {
            int id = data.getIntExtra(EditOwnerPropertiesActivity.ID, -1);

            if (id == -1) {
                Toast.makeText(this, "can't be updated", Toast.LENGTH_SHORT).show();
                return;
            }


            int ownerproperty_id = data.getIntExtra(EditPropertiesActivity.OWNERPROPERTYID,0) ;
            String type = data.getStringExtra(""+EditPropertiesActivity.TYPE);
            String description = data.getStringExtra(EditPropertiesActivity.DESCRIPTION);
            String name = data.getStringExtra(EditPropertiesActivity.NAME);
            String address = data.getStringExtra(EditPropertiesActivity.ADDRESS);

            double rent_amount = data.getDoubleExtra(EditPropertiesActivity.RENTAMOUNT,0);
            String status = data.getStringExtra(EditPropertiesActivity.STATUS);
            String rentBy = data.getStringExtra(EditPropertiesActivity.RENTBY);
            String rentPricetype = data.getStringExtra(EditPropertiesActivity.RENTPRICETYPE);
            int rentMembers = data.getIntExtra(EditPropertiesActivity.RENTMEMBERS,0);



            Properties properties = new Properties(ownerproperty_id,type,description,name,address
                    ,rent_amount,status,rentBy,rentPricetype,rentMembers);
            properties.setPropertyId(id);
            propertiesViewModel.update(properties);

            Toast.makeText(this, "updated", Toast.LENGTH_SHORT).show();
        }

        else
            Toast.makeText(this,"not updated ",Toast.LENGTH_SHORT).show();
    }
}
