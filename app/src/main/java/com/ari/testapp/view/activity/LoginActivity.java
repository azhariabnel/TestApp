package com.ari.testapp.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.ari.testapp.data.Preferences;
import com.ari.testapp.R;

public class LoginActivity extends AppCompatActivity {

    private EditText mViewUser,mEmailUser, mViewPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mViewUser=findViewById(R.id.fullname);
        mEmailUser = findViewById(R.id.username);
        mViewPassword =findViewById(R.id.password);

        mViewPassword.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_NULL) {
                    checkField();
                    return true;
                }
                return false;
            }
        });

        findViewById(R.id.login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkField();
            }
        });

        findViewById(R.id.register).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(),RegisterActivity.class));
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (Preferences.getLoggedInStatus(getBaseContext())){
            startActivity(new Intent(getBaseContext(),MainActivity2.class));
            finish();
        }
    }
    private void checkField() {
        mViewUser.setError(null);
        mEmailUser.setError(null);
        mViewPassword.setError(null);
        View focus = null;
        boolean cancel = false;

        String user = mViewUser.getText().toString();
        String email = mEmailUser.getText().toString();
        String password = mViewPassword.getText().toString();

        if (TextUtils.isEmpty(user)){
            mViewUser.setError("This field is required");
            focus = mViewUser;
            cancel = true;
        }else if(!checkUser(user)){
            mViewUser.setError("This Username is not found");
            focus = mViewUser;
            cancel = true;
        }

        if (TextUtils.isEmpty(email)){
            mEmailUser.setError("This field is required");
            focus = mEmailUser;
            cancel = true;
        }else if(!checkEmail(email)){
            mEmailUser.setError("This Username is not found");
            focus = mEmailUser;
            cancel = true;
        }

        if (TextUtils.isEmpty(password)){
            mViewPassword.setError("This field is required");
            focus = mViewPassword;
            cancel = true;
        }else if (!checkPassword(password)){
            mViewPassword.setError("This password is incorrect");
            focus = mViewPassword;
            cancel = true;
        }

        if (cancel) focus.requestFocus();
        else signIn();
    }

    private void signIn() {
        Preferences.setLoggedInUser(getBaseContext(),Preferences.getRegisteredUser(getBaseContext()));
        Preferences.setLoggedInEmail(getBaseContext(), Preferences.getRegisteredEmail(getBaseContext()));
        Preferences.setLoggedInStatus(getBaseContext(),true);
        startActivity(new Intent(getBaseContext(), MainActivity2.class));finish();
    }

    private boolean checkPassword(String password) {
        return password.equals(Preferences.getRegisteredPass(getBaseContext()));
    }

    private boolean checkEmail(String email) {
        return email.equals(Preferences.getRegisteredEmail(getBaseContext()));

    }

    private boolean checkUser(String user) {
        return user.equals(Preferences.getRegisteredUser(getBaseContext()));
    }
}