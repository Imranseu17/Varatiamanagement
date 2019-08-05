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
            "com.example.varatiamanagement.ID";

    public static final String OWNERPROPERTYID =
            "com.example.varatiamanagement.OWNERPROPERTYID";
    public static final String TYPE =
            "com.example.varatiamanagement.TYPE";
    public static final String DESCRIPTION =
            "com.example.varatiamanagement.DESCRIPTION";
    public static final String NAME =
            "com.example.varatiamanagement.NAME";
    public static final String ADDRESS =
            "com.example.varatiamanagement.ADDRESS";

    public static final String RENTAMOUNT =
            "com.example.varatiamanagement.RENTAMOUNT";
    public static final String STATUS =
            "com.example.varatiamanagement.STATUS";
    public static final String RENTBY =
            "com.example.varatiamanagement.RENTBY";
    public static final String RENTPRICETYPE =
            "com.example.varatiamanagement.ADDRESS";
    public static final String RENTMEMBERS =
            "com.example.varatiamanagement.DESCRIPTION";

    ActivityEditPropertiesBinding editPropertiesBinding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        editPropertiesBinding = DataBindingUtil.setContentView
                (EditPropertiesActivity.this,R.layout.activity_edit_properties);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Edit  Properties");

        Intent intent = this.getIntent();
        Integer[] numberOfOwnerProperty = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        final ArrayAdapter<Integer> numberOfOwnerPropertyspinner =
                new ArrayAdapter<>(this,
                        android.R.layout.simple_spinner_item, numberOfOwnerProperty.clone());
        final ArrayAdapter<Type> typespinner =
                new ArrayAdapter<>(this,
                        android.R.layout.simple_spinner_item, Type.values());
        final ArrayAdapter<Status> statusSpinner =
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
        statusSpinner.
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
                    (""+intent.getStringExtra(DESCRIPTION));
            editPropertiesBinding.name.getEditText().setText
                    (intent.getStringExtra(NAME));
            editPropertiesBinding.rentPriceType.setAdapter(rentPriceType);
            editPropertiesBinding.rentAmount.getEditText().setText
                    (""+intent.getDoubleExtra(RENTAMOUNT,0));
            editPropertiesBinding.status.setAdapter(statusSpinner);
            editPropertiesBinding.rentBy.setAdapter(rentBy);
            editPropertiesBinding.address.getEditText().setText
                    (""+intent.getStringExtra(ADDRESS));
            editPropertiesBinding.members.getEditText().setText
                    (""+intent.getIntExtra(RENTMEMBERS,0));


        } else {
            setTitle("Save Note");
        }
    }

    private void saveNote(){


        int numberOfOwnerProperty  = Integer.parseInt(editPropertiesBinding.numberOfOwnerProperty.getSelectedItem().toString().trim()) ;
        String type = editPropertiesBinding.type.getSelectedItem()
                .toString().trim();
        String description = editPropertiesBinding.description.getEditText().
                getText().toString().trim();
        String name = editPropertiesBinding.name.getEditText().
                getText().toString().trim();
        String address = editPropertiesBinding.address.getEditText().
                getText().toString().trim();
        double rentAmount = Double.parseDouble(editPropertiesBinding.rentAmount.getEditText().
                getText().toString().trim()) ;
        String  status = editPropertiesBinding.status.getSelectedItem().toString().trim();
        String  rentBy = editPropertiesBinding.rentBy.getSelectedItem().toString().trim();
        String  rentPriceType = editPropertiesBinding.rentPriceType.
                getSelectedItem().toString().trim();
        int rentMembers =  Integer.parseInt(editPropertiesBinding.members.getEditText().
                getText().toString().trim()) ;



        if((numberOfOwnerProperty == 0) || type.isEmpty() || description.isEmpty()
               ||name.isEmpty() || address.isEmpty() || (rentAmount == 0)|| status.isEmpty()
                || rentBy.isEmpty() || rentPriceType.isEmpty() || (rentMembers == 0)){
            Toast.makeText(this," Please insert all field of the page",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        Intent data = new Intent();
        data.putExtra(OWNERPROPERTYID,numberOfOwnerProperty);
        data.putExtra(TYPE,type);
        data.putExtra(DESCRIPTION,description);
        data.putExtra(NAME,name);
        data.putExtra(ADDRESS,address);
        data.putExtra(RENTAMOUNT,rentAmount);
        data.putExtra(STATUS,status);
        data.putExtra(RENTBY,rentBy);
        data.putExtra(RENTPRICETYPE,rentPriceType);
        data.putExtra(RENTMEMBERS,rentMembers);


        int id = getIntent().getIntExtra(ID,-1);
        if(id != -1)
            data.putExtra(ID,id);

        setResult(RESULT_OK,data);
        finish();
    }

   int ParseInt(String strNumber) {
        if (strNumber != null && strNumber.length() > 0) {
            try {
                return Integer.parseInt(strNumber);
            } catch(Exception e) {
                return -1;   // or some value to mark this field is wrong. or make a function validates field first ...
            }
        }
        else return 0;
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
