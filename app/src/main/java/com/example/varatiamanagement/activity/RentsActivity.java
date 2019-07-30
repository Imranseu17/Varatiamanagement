package com.example.varatiamanagement.activity;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.MenuItem;

import com.example.varatiamanagement.R;
import com.example.varatiamanagement.adapter.OwnerPropertiesAdapter;
import com.example.varatiamanagement.dao.OwnerPropertyDao;
import com.example.varatiamanagement.database.OwnerPropertyDatabase;
import com.example.varatiamanagement.databinding.ActivityRentsBinding;
import com.example.varatiamanagement.model.OwnerProperty;

import java.util.ArrayList;
import java.util.List;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class RentsActivity extends AppCompatActivity {

    ActivityRentsBinding activityRentsBinding;
    ArrayList<OwnerProperty> ownerPropertyArrayList = new ArrayList<>();
    OwnerPropertiesAdapter ownerPropertiesAdapter;
    private OwnerPropertyDatabase database;

    private OwnerPropertyDao ownerPropertyDao;
    List<OwnerProperty> ownerProperties;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initialize();
        if(ownerProperties.isEmpty()){
            ownerPropertiesAdapter.notifyDataSetChanged();
        }
    }

    public void initialize(){
        activityRentsBinding = DataBindingUtil.setContentView(RentsActivity.this,
                R.layout.activity_rents);
        activityRentsBinding.recyclerView.setHasFixedSize(true);
        activityRentsBinding.recyclerView.setLayoutManager
                (new LinearLayoutManager(this));
        ownerPropertiesAdapter = new OwnerPropertiesAdapter(ownerPropertyArrayList);
        activityRentsBinding.recyclerView.setAdapter(ownerPropertiesAdapter);
        database = Room.databaseBuilder(this,
                OwnerPropertyDatabase.class, "owner-database.db")
                .allowMainThreadQueries()
                .build();
        ownerPropertyDao = database.getOwnerPropertyDao();
        ownerProperties=
                ownerPropertyDao.getAllOwnerProperties();
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
}
