package com.example.varatiamanagement.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

import com.example.varatiamanagement.R;
import com.example.varatiamanagement.databinding.ActivityOwnerHomeBinding;
import com.example.varatiamanagement.utils.ChooseAlertDialog;
import com.example.varatiamanagement.utils.PromptDialog;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class OwnerHomeActivity extends AppCompatActivity {

    ActivityOwnerHomeBinding ownerHomeBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ownerHomeBinding = DataBindingUtil.setContentView(this,R.layout.activity_owner_home);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Owner Home");
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.menu);

        ownerHomeBinding.cardProperties.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ownerHomeBinding.lineProperties.
                        setBackgroundColor(ContextCompat.
                                getColor(OwnerHomeActivity.this, R.color.green));
                ownerHomeBinding.lineRents.
                        setBackgroundColor(ContextCompat.
                                getColor(OwnerHomeActivity.this, R.color.black_overlay));
                ownerHomeBinding.lineTransaction.
                        setBackgroundColor(ContextCompat.
                                getColor(OwnerHomeActivity.this, R.color.black_overlay));

                startActivity(new Intent(OwnerHomeActivity.this, OwnerPropertiesActivity.class));

            }
        });

        ownerHomeBinding.cardRents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ownerHomeBinding.lineProperties.
                        setBackgroundColor(ContextCompat.
                                getColor(OwnerHomeActivity.this, R.color.black_overlay));
                ownerHomeBinding.lineRents.
                        setBackgroundColor(ContextCompat.
                                getColor(OwnerHomeActivity.this, R.color.green));
                ownerHomeBinding.lineTransaction.
                        setBackgroundColor(ContextCompat.
                                getColor(OwnerHomeActivity.this, R.color.black_overlay));

                startActivity(new Intent(OwnerHomeActivity.this, OwnerRentsActivity.class));

            }
        });

        ownerHomeBinding.cardTransaction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ownerHomeBinding.lineProperties.
                        setBackgroundColor(ContextCompat.
                                getColor(OwnerHomeActivity.this, R.color.black_overlay));
                ownerHomeBinding.lineRents.
                        setBackgroundColor(ContextCompat.
                                getColor(OwnerHomeActivity.this, R.color.black_overlay));
                ownerHomeBinding.lineTransaction.
                        setBackgroundColor(ContextCompat.
                                getColor(OwnerHomeActivity.this, R.color.green));

                startActivity(new Intent(OwnerHomeActivity.this, OwnerTransactionActivity.class));

            }
        });




    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }





    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        showExitDialog();
    }

    public void showExitDialog() {
        new ChooseAlertDialog(this)
                .setDialogType(PromptDialog.DIALOG_TYPE_WARNING)
                .setAnimationEnable(true)
                .setTitleText(R.string.warning)
                .setContentText(R.string.warning_exit_app)
                .setNegativeListener(getString(R.string.no), new ChooseAlertDialog.OnNegativeListener() {
                    @Override
                    public void onClick(ChooseAlertDialog dialog) {
                        dialog.dismiss();
                    }
                })
                .setPositiveListener(getString(R.string.yes), new ChooseAlertDialog.OnPositiveListener() {
                    @Override
                    public void onClick(ChooseAlertDialog dialog) {
                        startActivity(new Intent(OwnerHomeActivity.this,LoginActivity.class));
                        dialog.dismiss();
                        finish();

                    }
                }).show();
    }


}
