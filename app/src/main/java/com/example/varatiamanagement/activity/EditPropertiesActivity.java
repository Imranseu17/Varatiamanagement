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
import com.example.varatiamanagement.databinding.ActivityEditPropertiesBinding;
import com.example.varatiamanagement.enumClass.RentBy;
import com.example.varatiamanagement.enumClass.RentPriceType;
import com.example.varatiamanagement.enumClass.Status;
import com.example.varatiamanagement.enumClass.Type;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class EditPropertiesActivity extends AppCompatActivity {

    public static final String ID =
            "com.codinginflow.architectureexample.ID";

    public static final String OWNERPROPERTYID =
            "com.codinginflow.architectureexample.OWNERPROPERTYID";
    public static final String TYPE =
            "com.codinginflow.architectureexample.TYPE";
    public static final String DESCRIPTION =
            "com.codinginflow.architectureexample.DESCRIPTION";
    public static final String NAME =
            "com.codinginflow.architectureexample.NAME";
    public static final String ADDRESS =
            "com.codinginflow.architectureexample.ADDRESS";

    public static final String RENTAMOUNT =
            "com.codinginflow.architectureexample.RENTAMOUNT";
    public static final String STATUS =
            "com.codinginflow.architectureexample.STATUS";
    public static final String RENTBY =
            "com.codinginflow.architectureexample.RENTBY";
    public static final String RENTPRICETYPE =
            "com.codinginflow.architectureexample.ADDRESS";
    public static final String RENTMEMBERS =
            "com.codinginflow.architectureexample.DESCRIPTION";

    ActivityEditPropertiesBinding editPropertiesBinding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        editPropertiesBinding = DataBindingUtil.setContentView
                (EditPropertiesActivity.this,R.layout.activity_edit_properties);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Edit Owner Properties");

        Intent intent = getIntent();
        Integer[] numberOfOwnerProperty = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        final ArrayAdapter<Integer> numberOfOwnerPropertyspinner =
                new ArrayAdapter<>(this,
                        android.R.layout.simple_spinner_item, numberOfOwnerProperty);
        final ArrayAdapter<Type> typespinner =
                new ArrayAdapter<>(this,
                        android.R.layout.simple_spinner_item, Type.values());
        final ArrayAdapter<Status> status =
                new ArrayAdapter<>(this,
                        android.R.layout.simple_spinner_item, Status.values());
        final ArrayAdapter<RentBy> rentBy =
                new ArrayAdapter<>(this,
                        android.R.layout.simple_spinner_item, RentBy.values());
        final ArrayAdapter<RentPriceType> rentPriceType =
                new ArrayAdapter<>(this,
                        android.R.layout.simple_spinner_item,RentPriceType.values());

        numberOfOwnerPropertyspinner.
                setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        typespinner.
                setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        status.
                setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        rentBy.
                setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        rentPriceType.
                setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);

        if (intent.hasExtra(ID)) {
            setTitle("Edit Note");
            editPropertiesBinding.numberOfOwnerProperty.setAdapter(numberOfOwnerPropertyspinner);
            editPropertiesBinding.type.setAdapter(typespinner);
            editPropertiesBinding.description.getEditText().setText
                    (intent.getStringExtra(DESCRIPTION));
            editPropertiesBinding.name.getEditText().setText
                    (intent.getStringExtra(NAME));
            editPropertiesBinding.address.getEditText().setText
                    (intent.getStringExtra(ADDRESS));
            editPropertiesBinding.rentAmount.getEditText().setText
                    (intent.getIntExtra(RENTAMOUNT,0));
            editPropertiesBinding.numberOfOwnerProperty.setAdapter(numberOfOwnerPropertyspinner);
            editPropertiesBinding.numberOfOwnerProperty.setAdapter(numberOfOwnerPropertyspinner);
            editPropertiesBinding.numberOfOwnerProperty.setAdapter(numberOfOwnerPropertyspinner);
            editPropertiesBinding.numberOfOwnerProperty.setAdapter(numberOfOwnerPropertyspinner);

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
