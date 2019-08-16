package com.uca.aerolineaapp.ui.activities;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.uca.aerolineaapp.R;
import com.uca.aerolineaapp.api.Api;
import com.uca.aerolineaapp.models.Identity;
import com.uca.aerolineaapp.models.Login;
import com.uca.aerolineaapp.models.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SingUpActivity extends AppCompatActivity {

    private EditText userName;
    private EditText password;
    private EditText email;
    private EditText name;
    private EditText lastName;
    private Spinner role;
    private Spinner nationality;
    private EditText birthPlace;
    private EditText passport;
    private EditText issueDate;
    private EditText expDate;
    private EditText birthDate;
    private EditText identification;
    private EditText sex;
    private Spinner countryCode;
    private Button signUpButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sing_up);
        initViews();
        initAction();
    }
    public void   initAction(){
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                postUser();
            }
        });

    }
    public void initViews(){
        userName = findViewById(R.id.edit_text_user_name);
        password = findViewById(R.id.edit_text_password);
        email = findViewById(R.id.edit_text_email);
        name = findViewById(R.id.edit_text_name);
        lastName = findViewById(R.id.edit_text_last_name);
        role = findViewById(R.id.spinner_roles);
        nationality = findViewById(R.id.spinner_nationality);
        birthPlace = findViewById(R.id.edit_text_birth_place);
        passport = findViewById(R.id.edit_text_passport);
        issueDate = findViewById(R.id.edit_text_issue_passport_date);
        expDate = findViewById(R.id.edit_text_exp_passport_date);
        birthDate = findViewById(R.id.edit_text_birth_date);
        identification = findViewById(R.id.edit_text_identification);
        sex = findViewById(R.id.edit_text_sex);
        countryCode = findViewById(R.id.spinner_country_code);
        signUpButton = findViewById(R.id.create_account);
    }

    private void postUser() {
        String UserName = userName.getText().toString();
        String Password = password.getText().toString();
        String Email = email.getText().toString();
        String Name = name.getText().toString();
        String LastName = lastName.getText().toString();
        String Role = role.toString();
        String Nationality = nationality.toString();
        String BirthPlace = birthPlace.getText().toString();
        String Passport = passport.getText().toString();
        String IssueDate = issueDate.getText().toString();
        String ExpDate = expDate.getText().toString();
        String BirthDate = birthDate.getText().toString();
        String Identification = identification.getText().toString();
        String Sex = sex.getText().toString();
        String CountryCode = password.toString();

        if(UserName.equals(" ")|| Password.equals("") || Email.equals("") || Name.equals("") || LastName.equals("") || Role.equals("") || BirthPlace.equals("")
                || Passport.equals("") || IssueDate.equals("") || ExpDate.equals("") || BirthDate.equals("") || Identification.equals("") || Sex.equals("")
            || Nationality.equals("Seleccionar...") || CountryCode.equals("Seleccionar...")) {
            Toast.makeText(getApplicationContext(),"Can't leave empty fields",Toast.LENGTH_SHORT).show();
        }else {
            final User user = new User();
            final Identity identity = new Identity();
            final Login login = new Login();

            login.setUserName(userName.getText().toString());
            user.setEmail(email.getText().toString());
            login.setPassword(password.getText().toString());
            user.setName(name.getText().toString());
            user.setLastName(lastName.getText().toString());
            user.setRole(role.toString());
            identity.setNationality(nationality.toString());
            identity.setBirthPlace(birthPlace.getText().toString());
            identity.setPassportNumber(passport.getText().toString());
            identity.setIssuePassPortDate(issueDate.getText().toString());
            identity.setExpPassportDate(expDate.getText().toString());
            identity.setBirthDate(birthDate.getText().toString());
            identity.setIdentification(identification.getText().toString());
            identity.setSex(sex.getText().toString());
            identity.setCountryCode(countryCode.toString());

            Call<Identity> identityCall = Api.instance().saveIdentities(identity);
            identityCall.enqueue(new Callback<Identity>() {
                @Override
                public void onResponse(@NonNull Call<Identity> call, @NonNull Response<Identity> response) {
                    if(response.code()==200){
                        assert response.body() != null;
                        Identity id = new Identity();
                        id.setNationality(nationality.toString());
                        id.setBirthPlace(birthPlace.getText().toString());
                        id.setPassportNumber(passport.getText().toString());
                        id.setIssuePassPortDate(issueDate.getText().toString());
                        id.setExpPassportDate(expDate.getText().toString());
                        id.setBirthDate(birthDate.getText().toString());
                        id.setIdentification(identification.getText().toString());
                        id.setSex(sex.getText().toString());
                        id.setCountryCode(countryCode.toString());


                        Toast.makeText(getApplicationContext(), "Success to Register Identity", Toast.LENGTH_LONG).show();
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "Fail register" +
                                "", Toast.LENGTH_LONG).show();
                    }

                }

                @Override
                public void onFailure(@NonNull Call<Identity> call, @NonNull Throwable t) {
                    Log.e("ERROR", "Identity Failed");
                    Toast.makeText(getApplicationContext(), "Fail identity", Toast.LENGTH_LONG).show();
                }
            });

            Call<Login> loginCall = Api.instance().saveLogin(login);
            loginCall.enqueue(new Callback<Login>() {
                @Override
                public void onResponse(@NonNull Call<Login> call, @NonNull Response<Login> response) {
                    if (response.isSuccessful()) {
                        Toast.makeText(getApplicationContext(), "Success to Register Login", Toast.LENGTH_LONG).show();
                        assert response.body() != null;
                        Login log = new Login();
                        log.setUserName(userName.getText().toString());
                        log.setPassword(password.getText().toString());


                    } else {
                        Toast.makeText(getApplicationContext(), "Login Failed", Toast.LENGTH_SHORT).show();

                    }
                }

                @Override
                public void onFailure(@NonNull Call<Login> call, @NonNull Throwable t) {
                    Log.e("ERROR", "Login Failed");
                    Toast.makeText(getApplicationContext(), "Fail LOgin", Toast.LENGTH_LONG).show();
                }
            });


            Call<User> userCall = Api.instance().saveUser(user);
            userCall.enqueue(new Callback<User>() {
                @Override
                public void onResponse(@NonNull Call<User> call, @NonNull Response<User> response) {
                    if(response.code()==200){
                        User us = new User();
                        us.setEmail(email.getText().toString());
                        us.setName(name.getText().toString());
                        us.setLastName(lastName.getText().toString());
                        us.setRole(role.toString());


                        Toast.makeText(getApplicationContext(), "Success to create user", Toast.LENGTH_LONG).show();


                    }
                    else{
                        Toast.makeText(getApplicationContext(), "Fail user", Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(@NonNull Call<User> call, @NonNull Throwable t) {
                    Log.e("ERROR", "User Failed");
                    Toast.makeText(getApplicationContext(), "Fail user", Toast.LENGTH_LONG).show();
                }
            });
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
