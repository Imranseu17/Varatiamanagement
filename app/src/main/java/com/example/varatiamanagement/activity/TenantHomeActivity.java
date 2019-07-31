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
import com.example.varatiamanagement.databinding.ActivityTenantHomeBinding;
import com.example.varatiamanagement.utils.ChooseAlertDialog;
import com.example.varatiamanagement.utils.PromptDialog;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class TenantHomeActivity extends AppCompatActivity {
    
    ActivityTenantHomeBinding tenantHomeBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       tenantHomeBinding = DataBindingUtil.setContentView(this,R.layout.activity_tenant_home);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Tenant Home");
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.menu);

        tenantHomeBinding.myRents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tenantHomeBinding.lineProperties.
                        setBackgroundColor(ContextCompat.
                                getColor(TenantHomeActivity.this, R.color.green));
                tenantHomeBinding.lineRents.
                        setBackgroundColor(ContextCompat.
                                getColor(TenantHomeActivity.this, R.color.black_overlay));
                tenantHomeBinding.lineTransaction.
                        setBackgroundColor(ContextCompat.
                                getColor(TenantHomeActivity.this, R.color.black_overlay));

                startActivity(new Intent(TenantHomeActivity.this, PropertiesActivity.class));

            }
        });

        tenantHomeBinding.cardRents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tenantHomeBinding.lineProperties.
                        setBackgroundColor(ContextCompat.
                                getColor(TenantHomeActivity.this, R.color.black_overlay));
                tenantHomeBinding.lineRents.
                        setBackgroundColor(ContextCompat.
                                getColor(TenantHomeActivity.this, R.color.green));
                tenantHomeBinding.lineTransaction.
                        setBackgroundColor(ContextCompat.
                                getColor(TenantHomeActivity.this, R.color.black_overlay));

                startActivity(new Intent(TenantHomeActivity.this, RentsActivity.class));

            }
        });

        tenantHomeBinding.cardTransaction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tenantHomeBinding.lineProperties.
                        setBackgroundColor(ContextCompat.
                                getColor(TenantHomeActivity.this, R.color.black_overlay));
                tenantHomeBinding.lineRents.
                        setBackgroundColor(ContextCompat.
                                getColor(TenantHomeActivity.this, R.color.black_overlay));
                tenantHomeBinding.lineTransaction.
                        setBackgroundColor(ContextCompat.
                                getColor(TenantHomeActivity.this, R.color.green));

                startActivity(new Intent(TenantHomeActivity.this, TransactionActivity.class));

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
                        startActivity(new Intent(TenantHomeActivity.this,LoginActivity.class));
                        dialog.dismiss();
                        finish();

                    }
                }).show();
    }
}
