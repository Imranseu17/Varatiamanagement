package com.example.varatiamanagement.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.example.varatiamanagement.R;
import com.example.varatiamanagement.dao.UserDao;
import com.example.varatiamanagement.database.UserDatabase;
import com.example.varatiamanagement.databinding.ActivitySignUpBinding;
import com.example.varatiamanagement.model.User;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class SignUPActivity extends AppCompatActivity {

    ActivitySignUpBinding activitySignUpBinding;
    Animation frombottom, fromtop;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    private ProgressDialog progressDialog;
    private UserDao userDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        activitySignUpBinding = DataBindingUtil.setContentView(this,
                R.layout.activity_sign_up);
        frombottom = AnimationUtils.loadAnimation(this,R.anim.frombottom);
        fromtop = AnimationUtils.loadAnimation(this,R.anim.fromtop);
        activitySignUpBinding. btnjoin.startAnimation(frombottom);

        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Registering...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setProgress(0);

        activitySignUpBinding.textView2.startAnimation(fromtop);
        activitySignUpBinding.name.startAnimation(fromtop);
        activitySignUpBinding.email.startAnimation(fromtop);
        activitySignUpBinding.password.startAnimation(fromtop);
        activitySignUpBinding.address.startAnimation(fromtop);
        activitySignUpBinding.nID.startAnimation(fromtop);
        activitySignUpBinding.phoneNumber.startAnimation(fromtop);

        userDao = Room.databaseBuilder(this, UserDatabase.class, "mi-database.db")
                .allowMainThreadQueries()
                .build()
                .getUserDao();

        activitySignUpBinding.btnjoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isEmpty()) {

                    progressDialog.show();

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            User user = new User(activitySignUpBinding.name.getEditText().getText().
                                    toString().trim(), activitySignUpBinding.email.getEditText().
                                    getText().toString().trim(),activitySignUpBinding.address.getEditText().
                                    getText().toString().trim(),activitySignUpBinding.nID.getEditText().
                                    getText().toString().trim(),activitySignUpBinding.phoneNumber.getEditText().
                                    getText().toString().trim(),
                                    activitySignUpBinding.password.getEditText()
                                    .getText().toString().trim());
                            userDao.insert(user);
                            progressDialog.dismiss();
                            Toast.makeText(SignUPActivity.this, "Data  Saved", Toast.LENGTH_SHORT).show();
                            activitySignUpBinding.name.getEditText().setText("");
                            activitySignUpBinding.email.getEditText().setText("");
                            activitySignUpBinding.address.getEditText().setText("");
                            activitySignUpBinding.nID.getEditText().setText("");
                            activitySignUpBinding.phoneNumber.getEditText().setText("");
                            activitySignUpBinding.password.getEditText().setText("");
                            startActivity(new Intent(SignUPActivity.this,LoginActivity.class));
                        }
                    }, 1000);

                } else {
                    Toast.makeText(SignUPActivity.this, "Data Not Saved", Toast.LENGTH_SHORT).show();
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

    private boolean isEmpty() {

        hideKeyboard(this);
        if(!validateName())
            return true;
        if(!validateEmail())
            return true;
        if(!validatePassword())
            return true;
        if(!validateAddress())
            return true;
        if(!validateNID())
            return true;
        if(!validatePhoneNumber())
            return true;

        else
            return false;

    }

    private boolean validateName() {
        String name = activitySignUpBinding.name.getEditText().getText().toString().trim();
        if (name.isEmpty()) {
            activitySignUpBinding.name.setError(getString(R.string.err_name));
            requestFocus(activitySignUpBinding.name);
            return false;
        }  else {
            activitySignUpBinding.name.setErrorEnabled(false);

        }

        return true;
    }

    private boolean validateEmail() {
        String email = activitySignUpBinding.email.getEditText().getText().toString().trim();
        if (email.isEmpty()) {
            activitySignUpBinding.email.setError(getString(R.string.err_email));
            requestFocus(activitySignUpBinding.email);
            return false;
        } else if (!email.matches(emailPattern)) {
            activitySignUpBinding.email.setError(getString(R.string.valid_email));
            requestFocus(activitySignUpBinding.email);
            return false;
        } else {
            activitySignUpBinding.email.setErrorEnabled(false);

        }

        return true;
    }


    private boolean validatePassword() {
        String password = activitySignUpBinding.password.getEditText().getText().toString().trim();
        if (password.isEmpty()) {
            activitySignUpBinding.password.setError(getString(R.string.err_password));
            requestFocus(activitySignUpBinding.password);
            return false;
        } else if (password.length()<6) {
            activitySignUpBinding.password.setError(getString(R.string.valid_password));
            requestFocus(activitySignUpBinding.password);
            return false;
        } else {
            activitySignUpBinding.password.setErrorEnabled(false);

        }

        return true;
    }

    private boolean validateAddress() {
        String name = activitySignUpBinding.address.getEditText().getText().toString().trim();
        if (name.isEmpty()) {
            activitySignUpBinding.address.setError(getString(R.string.err_address));
            requestFocus(activitySignUpBinding.address);
            return false;
        }  else {
            activitySignUpBinding.address.setErrorEnabled(false);

        }

        return true;
    }

    private boolean validateNID() {
        String name = activitySignUpBinding.nID.getEditText().getText().toString().trim();
        if (name.isEmpty()) {
            activitySignUpBinding.nID.setError(getString(R.string.err_nID));
            requestFocus(activitySignUpBinding.nID);
            return false;
        }  else {
            activitySignUpBinding.nID.setErrorEnabled(false);

        }

        return true;
    }

    private boolean validatePhoneNumber() {
        String name = activitySignUpBinding.phoneNumber.getEditText().getText().toString().trim();
        if (name.isEmpty()) {
            activitySignUpBinding.phoneNumber.setError(getString(R.string.err_phoneNumber));
            requestFocus(activitySignUpBinding.phoneNumber);
            return false;
        }  else {
            activitySignUpBinding.phoneNumber.setErrorEnabled(false);

        }

        return true;
    }





    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = activity.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }
}
