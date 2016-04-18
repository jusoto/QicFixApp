package com.qicfix.qicfixapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.qicfix.qicfixapp.util.CustomThread;

import org.json.JSONException;
import org.json.JSONObject;

public class Login extends AppCompatActivity {

    EditText email, pass;
    TextView error;
    private String token;
    int userId, code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = (EditText) findViewById(R.id.loginEmail);
        pass = (EditText) findViewById(R.id.loginPassword);
        error = (TextView) findViewById(R.id.invalidCredentials);
    }

    /**
     * Executes login by calling new thread from class perform login
     *
     */
    public void login(View v) throws JSONException {

        //creates JSONObject with username and password as parameters

        //sends response off to thread and gets a return back
        String response = CustomThread.CustomThreadHttpGet(
                "http://www.qicfixit.com:8080/api/login?email=" + email.getText().toString() +
                        "&password=" + pass.getText().toString());

        System.out.println(response);
        if(response != null) {
            processResponse(response);
        } else {
            error.setVisibility(View.VISIBLE);
        }
    }

    /**
     * Stores the login data to the private local storage and loads next activity
     */

    private void processResponse(String response) {
        //Privately stores user information to app local storage
        SharedPreferences sharedPreferences = getSharedPreferences("USERDATA", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        //stores the email
        editor.putString("EMAIL", email.getText().toString());
        //stores the token
        editor.putString("TOKEN", response);
        //stores URL
        editor.putString("URL", "http://www.qicfixit.com:8080/api/");
        editor.apply();

        error.setVisibility(View.INVISIBLE);
        //creates new Intent and passes it the user id
        Intent loadProfile = new Intent(getApplication(), TowerServiceScreen.class);

        startActivity(loadProfile);
        finish();

    }
}
