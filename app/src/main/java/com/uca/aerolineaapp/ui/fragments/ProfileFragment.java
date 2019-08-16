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
        initView();
        initActions();

    }

    private void initActions() {
        name.setText(Remember.getString(Constants.NAME,""));
        username.setText(Remember.getString(Constants.USERNAME,""));
        birthDate.setText(Remember.getString(Constants.BIRTH_DATE,""));
        email.setText(Remember.getString(Constants.EMAIL,""));
        nationality.setText(Remember.getString(Constants.NATIONALITY,""));
    }

    private void initView() {
        name= Objects.requireNonNull(getView()).findViewById(R.id.nameTextView);
        username= getView().findViewById(R.id.username_textview);
        birthDate=getView().findViewById(R.id.text_birth_date);
        email = getView().findViewById(R.id.edit_text_email);
        nationality = getView().findViewById(R.id.nationality_text_view);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

}
