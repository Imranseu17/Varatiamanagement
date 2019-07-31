package com.example.varatiamanagement.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.varatiamanagement.R;
import com.example.varatiamanagement.databinding.ActivityEditOwnerPropertiesBinding;
import com.example.varatiamanagement.enumClass.Type;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class EditOwnerPropertiesActivity extends AppCompatActivity {

    public static final String ID =
            "com.codinginflow.architectureexample.ID";
    public static final String NAME =
            "com.codinginflow.architectureexample.NAME";
    public static final String TYPE =
            "com.codinginflow.architectureexample.TYPE";
    public static final String ADDRESS =
            "com.codinginflow.architectureexample.ADDRESS";
    public static final String DESCRIPTION =
            "com.codinginflow.architectureexample.DESCRIPTION";

    ActivityEditOwnerPropertiesBinding activityEditOwnerPropertiesBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityEditOwnerPropertiesBinding = DataBindingUtil.
                setContentView(EditOwnerPropertiesActivity.this,
                        R.layout.activity_edit_owner_properties);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Edit Owner Properties");

        Intent intent = getIntent();
        final ArrayAdapter<Type> spinnerArrayAdapter = new ArrayAdapter<Type>(
                this,R.layout.support_simple_spinner_dropdown_item,Type.values());

        spinnerArrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);

        if (intent.hasExtra(ID)) {
            setTitle("Edit Note");
            activityEditOwnerPropertiesBinding.name.getEditText().setText
                    (intent.getStringExtra(NAME));
            activityEditOwnerPropertiesBinding.spinner.setAdapter(spinnerArrayAdapter);
            activityEditOwnerPropertiesBinding.address.getEditText().setText
                    (intent.getStringExtra(ADDRESS));
            activityEditOwnerPropertiesBinding.description.getEditText().setText
                    (intent.getStringExtra(DESCRIPTION));
        } else {
            setTitle("Save Note");
        }
    }

    private void saveNote(){

        String name = activityEditOwnerPropertiesBinding.name.getEditText().
                getText().toString().trim();
        String type = activityEditOwnerPropertiesBinding.spinner.getSelectedItem()
                .toString().trim();
        String address = activityEditOwnerPropertiesBinding.address.getEditText().
                getText().toString().trim();
        String description = activityEditOwnerPropertiesBinding.description.getEditText().
                getText().toString().trim();

        if(name.isEmpty() || type.isEmpty() || address.isEmpty() || description.isEmpty()){
            Toast.makeText(this," Please insert a title and description",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        Intent data = new Intent();
        data.putExtra(NAME,name);
        data.putExtra(TYPE,type);
        data.putExtra(ADDRESS,address);
        data.putExtra(DESCRIPTION,description);


        int id = getIntent().getIntExtra(ID,-1);
        if(id != -1)
            data.putExtra(ID,id);

        setResult(RESULT_OK,data);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.edit_note_menu,menu);
        return true;
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
            case R.id.edit_note:
                saveNote();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }





}