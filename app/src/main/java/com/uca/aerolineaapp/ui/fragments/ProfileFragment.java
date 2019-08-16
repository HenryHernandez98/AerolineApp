package com.uca.aerolineaapp.ui.fragments;

import android.support.v4.app.Fragment;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tumblr.remember.Remember;
import com.uca.aerolineaapp.R;
import com.uca.aerolineaapp.constants.Constants;

import java.util.Objects;


public class ProfileFragment extends Fragment {
    TextView username;
    TextView name;
    TextView birthDate;
    TextView email;
    TextView nationality;


    public ProfileFragment() {
        // Required empty public constructor
    }

    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_profile, container, false);
        initView(view);
        initActions();
        return view;

    }

    private void initActions() {
        name.setText(Remember.getString(Constants.NAME,""));
        username.setText(Remember.getString(Constants.USERNAME,""));
        birthDate.setText(Remember.getString(Constants.BIRTH_DATE,""));
        email.setText(Remember.getString(Constants.EMAIL,""));
    }

    private void initView(View view) {
        name= view.findViewById(R.id.nameTextView);
        username= view.findViewById(R.id.username_textview);
        birthDate=view.findViewById(R.id.text_birth_date);
        email = view.findViewById(R.id.edit_text_email);
    }

}
