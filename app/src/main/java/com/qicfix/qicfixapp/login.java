package com.qicfix.qicfixapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.qicfix.qicfixapp.Tower.CurrentService;
import com.qicfix.qicfixapp.Tower.TowerHome;
import com.qicfix.qicfixapp.util.CustomThread;

public class Login extends Fragment {

    EditText email, pass;
    TextView error;
    private String token;
    int userId, code;

    private static final String ARG_SECTION_NUMBER = "section_number";
    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static Login newInstance(int sectionNumber) {
        Login fragment = new Login();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public Login() {
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View loginView = inflater.inflate(R.layout.activity_login, container, false);

        email = (EditText) loginView.findViewById(R.id.loginEmail);
        pass = (EditText) loginView.findViewById(R.id.loginPassword);
        error = (TextView) loginView.findViewById(R.id.invalidCredentials);
        Button button = (Button) loginView.findViewById(R.id.loginButton);
        button.setOnClickListener(new View.OnClickListener()
        {
            /**
             * Executes login by calling new thread from class perform login
             *
             */
            @Override
            public void onClick(View v)
            {
                String response = CustomThread.CustomThreadHttpGet(
                        "http://www.qicfixit.com:8080/api/login?email=" + email.getText().toString() +
                                "&password=" + pass.getText().toString());

                System.out.println(response);
                if(response.length()>10) {
                    processResponse(response);
                } else {
                    error.setVisibility(View.VISIBLE);
                }
            }
        });
        return loginView;
    }

    /**
     * Stores the login data to the private local storage and loads next activity
     */

    private void processResponse(String response) {
        //Privately stores user information to app local storage
        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences("USERDATA", Context.MODE_PRIVATE);
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
        Intent loadProfile = new Intent(getActivity(), TowerHome.class);
        loadProfile.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(loadProfile);

    }
}
