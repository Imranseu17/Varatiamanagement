package com.example.varatiamanagement.activity;

import android.app.ProgressDialog;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.varatiamanagement.R;
import com.example.varatiamanagement.dao.UserDao;
import com.example.varatiamanagement.database.UserDatabase;
import com.example.varatiamanagement.databinding.ActivityLoginBinding;
import com.example.varatiamanagement.model.User;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class LoginActivity extends AppCompatActivity {

    ActivityLoginBinding activityLoginBinding;
    private UserDatabase database;

    private UserDao userDao;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        activityLoginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Check User...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setProgress(0);


        database = Room.databaseBuilder(this, UserDatabase.class, "mi-database.db")
                .allowMainThreadQueries()
                .build();

        userDao = database.getUserDao();

        activityLoginBinding. tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,
                        SignUPActivity.class));
            }
        });

        activityLoginBinding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!emptyValidation()) {
                    progressDialog.show();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            User user = userDao.getUser(activityLoginBinding.inputLayoutPhone.getEditText()
                                            .getText().toString().trim(),
                                    activityLoginBinding.inputLayoutPassword.getEditText().getText().toString()
                                            .trim());
                            if(user!=null){
                                startActivity(new Intent(LoginActivity.this,
                                        HomeActivity.class));
                                finish();
                                activityLoginBinding.inputLayoutPhone.getEditText().setText("");
                                activityLoginBinding.inputLayoutPassword.getEditText().setText("");
                            }else{
                                Toast.makeText(LoginActivity.this, "Unregistered user, " +
                                                "or incorrect",
                                        Toast.LENGTH_SHORT).show();
                            }
                            progressDialog.dismiss();
                        }
                    }, 1000);

                }else{
                    Toast.makeText(LoginActivity.this, "Empty Fields",
                            Toast.LENGTH_SHORT).show();
                }

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

    private boolean emptyValidation() {
        if (TextUtils.isEmpty(activityLoginBinding.inputLayoutPhone.getEditText().getText().toString().trim()) ||
                TextUtils.isEmpty(activityLoginBinding.inputLayoutPassword.getEditText().getText().toString().trim())) {
            return true;
        }else {
            return false;
        }
    }
}
