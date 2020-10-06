package com.ari.testapp.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.ari.testapp.R;
import com.ari.testapp.data.Preferences;
import com.ari.testapp.view.activity.LoginActivity;


public class ProfileFragment extends Fragment {

    private TextView tvNama, tvEmail;
    private Button btLogout;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);

        tvNama = (TextView) root.findViewById(R.id.tv_namaMain);
        tvEmail = (TextView) root.findViewById(R.id.tv_emailMain);
        btLogout = (Button) root.findViewById(R.id.button_logoutMain);

        tvNama.setText(Preferences.getLoggedInUser(this.getContext()));
        tvEmail.setText(Preferences.getLoggedInEmail(this.getContext()));

        btLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Preferences.clearLoggedInUser(getContext());
                startActivity(new Intent(getContext(),LoginActivity.class));

            }
        });
        return root;
    }
}
