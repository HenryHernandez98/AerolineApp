package com.uca.aerolineaapp.ui.activities;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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
    private EditText role;
    private Spinner nationality;
    private EditText passport;
    private EditText issueDate;
    private EditText expDate;
    private EditText birthDate;
    private EditText identification;
    private EditText sex;
    private Spinner countryCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sing_up);
        initViews();
    }
    public void   initAction(){

    }
    public void initViews(){
        userName = findViewById(R.id.edit_text_user_name);
        password = findViewById(R.id.edit_text_password);
        email = findViewById(R.id.edit_text_email);
        name = findViewById(R.id.edit_text_name);
        lastName = findViewById(R.id.edit_text_last_name);
        role = findViewById(R.id.edit_text_role);
        nationality = findViewById(R.id.spinner_nationality);
        passport = findViewById(R.id.edit_text_passport);
        issueDate = findViewById(R.id.edit_text_issue_passport_date);
        expDate = findViewById(R.id.edit_text_exp_passport_date);
        birthDate = findViewById(R.id.edit_text_birth_date);
        identification = findViewById(R.id.edit_text_identification);
        sex = findViewById(R.id.edit_text_sex);
        countryCode = findViewById(R.id.spinner_country_code);
    }

    private void postUser() {
        String UserName = String.valueOf(userName.getText().toString());
        String Password = String.valueOf(password.getText().toString());
        String Email = String.valueOf(email.getText().toString());
        String Name = String.valueOf(name.getText().toString());
        String LastName = String.valueOf(lastName.getText().toString());
        String Role = String.valueOf(role.getText().toString());
        String Nationality = String.valueOf(nationality.toString());
        String Passport = String.valueOf(passport.getText().toString());
        String IssueDate = String.valueOf(issueDate.getText().toString());
        String ExpDate = String.valueOf(expDate.getText().toString());
        String BirthDate = String.valueOf(birthDate.getText().toString());
        String Identification = String.valueOf(identification.getText().toString());
        String Sex = String.valueOf(sex.getText().toString());
        String CountryCode = String.valueOf(password.toString());

        if(UserName.equals(" ")|| Password.equals("") || Email.equals("") || Name.equals("") || LastName.equals("") || Role.equals("")
                || Passport.equals("") || IssueDate.equals("") || ExpDate.equals("") || BirthDate.equals("") || Identification.equals("") || Sex.equals("")
            || Nationality.equals("Seleccionar...") || CountryCode.equals("Seleccionar...")) {
            Toast.makeText(getApplicationContext(),"Can't leave empty fields",Toast.LENGTH_SHORT).show();
        }else {
            User user = new User();
            Identity identity = new Identity();
            Login login = new Login();

            login.setUserName(userName.getText().toString());
            user.setEmail(email.getText().toString());
            login.setPassword(password.getText().toString());
            user.setName(name.getText().toString());
            user.setLastName(lastName.getText().toString());
            user.setRole(role.getText().toString());
            identity.setNationality(nationality.toString());
            identity.setPassportNumber(passport.getText().toString());
            identity.setIssuePassPortDate(issueDate.getText().toString());
            identity.setExpPassportDate(expDate.getText().toString());
            identity.setBirthDate(birthDate.getText().toString());
            identity.setIdentification(identification.getText().toString());
            identity.setSex(sex.getText().toString());
            identity.setCountryCode(countryCode.toString());

            Call<Identity> identityCall = Api.instance().saveIdentities(identity);
            Call<Login> loginCall = Api.instance().saveLogin(login);
            Call<User> userCall = Api.instance().saveUser(user);

            identityCall.enqueue(new Callback<Identity>() {
                @Override
                public void onResponse(Call<Identity> call, Response<Identity> response) {

                }

                @Override
                public void onFailure(Call<Identity> call, Throwable t) {

                }
            });



            userCall.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    if (response.isSuccessful()) {
                        Toast.makeText(getApplicationContext(), "Success to Register", Toast.LENGTH_LONG).show();

                        User user = new User();
                        user.setName(name.getText().toString());
                        user.setLastName(lastName.getText().toString());
                        user.setEmail(email.getText().toString());
                        user.setRole(role.getText().toString());
                        user.setIdIdentity(response.body().getIdIdentity());
                        user.seet

                        Call<ContactsContract.Profile> profileCall = Api.instance().createProfile(profile);
                        profileCall.enqueue(new Callback<ContactsContract.Profile>() {
                            @Override
                            public void onResponse(@NonNull Call<ContactsContract.Profile> call, @NonNull Response<ContactsContract.Profile> response) {
                                if (response.isSuccessful()) {
                                    Toast.makeText(getApplicationContext(), "Perfil creado", Toast.LENGTH_SHORT).show();
                                    assert response.body() != null;
                                    profile.setId(response.body().getId());
                                } else {
                                    Toast.makeText(getApplicationContext(), "An error occur while register was doing", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(@NonNull Call<ContactsContract.Profile> call, @NonNull Throwable t) {
                                Log.e("Err", "Error to show");
                            }
                        });
                        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(getApplicationContext(), "An error occur while register was doing", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    Log.e("Err", "Error to show");
                }
            });

        }
    }
}
