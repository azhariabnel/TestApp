package com.ari.testapp.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.ari.testapp.data.Preferences;
import com.ari.testapp.R;

public class RegisterActivity extends AppCompatActivity {

    private EditText mViewUser, mViewEmail, mViewPassword, mViewRepassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mViewUser =findViewById(R.id.et_fullnameSignup);
        mViewEmail =findViewById(R.id.et_emailSignUp);
        mViewPassword =findViewById(R.id.et_passwordSignup);
        mViewRepassword =findViewById(R.id.et_passwordSignup2);

        mViewRepassword.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_NULL) {
                    checkField();
                    return true;
                }
                return false;
            }
        });

        findViewById(R.id.button_signupSignup).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkField();
            }
        });
    }

    private void checkField() {
        mViewUser.setError(null);
        mViewEmail.setError(null);
        mViewPassword.setError(null);
        mViewRepassword.setError(null);
        View focus = null;
        boolean cancel = false;

        String user = mViewUser.getText().toString();
        String email = mViewEmail.getText().toString();
        String password = mViewPassword.getText().toString();
        String repassword = mViewRepassword.getText().toString();

        if (TextUtils.isEmpty(user)){
            mViewUser.setError("This field is required");
            focus = mViewUser;
            cancel = true;
        }else if(checkUser(user)){
            mViewUser.setError("This Username is already exist");
            focus = mViewUser;
            cancel = true;
        }

        if (TextUtils.isEmpty(email)){
            mViewEmail.setError("This field is required");
            focus = mViewEmail;
            cancel = true;
        }else if(checkEmail(email)){
            mViewEmail.setError("This Username is already exist");
            focus = mViewEmail;
            cancel = true;
        }

        if (TextUtils.isEmpty(password)){
            mViewPassword.setError("This field is required");
            focus = mViewPassword;
            cancel = true;
        }else if (!checkPassword(password,repassword)){
            mViewRepassword.setError("This password is incorrect");
            focus = mViewRepassword;
            cancel = true;
        }

        if (cancel){
            focus.requestFocus();
        }else{
            Preferences.setRegisteredUser(getBaseContext(),user);
            Preferences.setRegisteredEmail(getBaseContext(),email);
            Preferences.setRegisteredPass(getBaseContext(),password);
            finish();
        }

    }

    private boolean checkUser(String user) {
        return user.equals(Preferences.getRegisteredUser(getBaseContext()));
    }

    private boolean checkEmail(String email) {
        return email.equals(Preferences.getRegisteredEmail(getBaseContext()));
    }

    private boolean checkPassword(String password, String repassword) {
        return password.equals(repassword);
    }
}