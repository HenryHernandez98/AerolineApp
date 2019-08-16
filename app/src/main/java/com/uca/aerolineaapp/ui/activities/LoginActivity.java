package com.uca.aerolineaapp.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.tumblr.remember.Remember;
import com.uca.aerolineaapp.R;
import com.uca.aerolineaapp.api.Api;
import com.uca.aerolineaapp.constants.Constants;
import com.uca.aerolineaapp.models.AccessToken;
import com.uca.aerolineaapp.models.Login;
import com.uca.aerolineaapp.models.LoginRequest;
import com.uca.aerolineaapp.models.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LoginActivity extends AppCompatActivity {
    private Button signIn;
    private Button signUp;
    private EditText username;
    private EditText password;
    private AccessToken token;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        /*Remember*/
        Remember.init(getApplicationContext(),"com.uca.aerolineaapp.ui.activities");


        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        initView();
        initAction();
        validateSession();
    }
    public  void initView(){
    signIn = findViewById(R.id.signIn);
    signUp = findViewById(R.id.signUp);
    username = findViewById(R.id.username);
    password = findViewById(R.id.password);
    }

    public  void  initAction(){
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SingUpActivity.class);
                startActivity(intent);
            }
        });

    }


    private void login() {


        String email2 = (username.getText().toString());
        String pass2 = (password.getText().toString());
        if (email2.equals("") || pass2.equals("")) {
            Toast.makeText(getApplicationContext(), "Can't leave empty fields", Toast.LENGTH_SHORT).show();
        } else {
            Login loginRequest = new Login();
            loginRequest.setUserName(username.getText().toString());
            loginRequest.setPassword(password.getText().toString());
            saveUserData(email2, pass2);
            Call<String> accessTokenCall = Api.instance().token(loginRequest);
            accessTokenCall.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    if (response.isSuccessful()) {
                        Remember.putString("access_token", response.body(), new Remember.Callback() {
                            @Override
                            public void apply(Boolean success) {
                                Toast.makeText(getApplicationContext(), "Success to login", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        });
                    } else {
                        Toast.makeText(getApplicationContext(), "Error chatel", Toast.LENGTH_SHORT).show();
                    }

                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    Log.e("Err", "No se puede:( ", t);
                }
            });
        }

    }
    public void saveUserData(String u, String p){

        SharedPreferences sharedPreferences = getSharedPreferences("credentials", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString("LoginUsername",u);
        editor.putString("LoginPassword",p);

        editor.commit();
    }



    //This method validates that the session is active
    private void validateSession() {
        if (Remember.getString("access_token", "").isEmpty()) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
    }

}
