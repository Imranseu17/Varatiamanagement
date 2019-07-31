package com.example.varatiamanagement.activity;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.varatiamanagement.R;
import com.example.varatiamanagement.adapter.ViewPagerAdapter;
import com.example.varatiamanagement.databinding.ActivityPropertiesBinding;
import com.example.varatiamanagement.fragments.AddOwnerProperties;
import com.example.varatiamanagement.fragments.AddProperties;
import com.example.varatiamanagement.fragments.OwnerRents;
import com.example.varatiamanagement.fragments.Rents;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class PropertiesActivity extends AppCompatActivity {

    ActivityPropertiesBinding propertiesBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       propertiesBinding = DataBindingUtil.setContentView(this,
               R.layout.activity_properties);
        setSupportActionBar(propertiesBinding.toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Properties");

        setupViewPager(propertiesBinding.viewpager);
        propertiesBinding.tabs.setupWithViewPager(propertiesBinding.viewpager);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new AddProperties(),getString(R.string.add_properties));
        adapter.addFragment(new Rents(), getString(R.string.rents));
        viewPager.setAdapter(adapter);
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
