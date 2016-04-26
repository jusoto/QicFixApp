package com.qicfix.qicfixapp;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.qicfix.qicfixapp.model.Tower;
import com.qicfix.qicfixapp.util.CustomThread;
import com.qicfix.qicfixapp.util.Utility;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Registration extends Fragment {

    EditText email, pass, confirmPass, fname, lname, phone, streetAddress, city, state, zipcode, company;
    TextView error;
    Button button;

    private static final String ARG_SECTION_NUMBER = "section_number";

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static Registration newInstance(int sectionNumber) {
        Registration fragment = new Registration();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public Registration() {
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View registrationView = inflater.inflate(R.layout.activity_registration, container, false);
        //Initialize all the Edit Texts with the registration form
        email = (EditText) registrationView.findViewById(R.id.emailRegForm);
        pass = (EditText) registrationView.findViewById(R.id.passwordRegForm);
        confirmPass = (EditText) registrationView.findViewById(R.id.confirmPassRegForm);
        fname = (EditText) registrationView.findViewById(R.id.firstNameRegForm);
        lname = (EditText) registrationView.findViewById(R.id.lastNameRegForm);
        phone = (EditText) registrationView.findViewById(R.id.phoneRegForm);
        streetAddress = (EditText) registrationView.findViewById(R.id.addressRegForm);
        city = (EditText) registrationView.findViewById(R.id.cityRegForm);
        state = (EditText) registrationView.findViewById(R.id.stateRegForm);
        zipcode = (EditText) registrationView.findViewById(R.id.zipcodeRegForm);
        company = (EditText) registrationView.findViewById(R.id.companyNameRegForm);
        error = (TextView) registrationView.findViewById(R.id.invalidInput);
        button = (Button) registrationView.findViewById(R.id.registrationButton);
        button.setOnClickListener(new View.OnClickListener() {
            /**
             * Executes registration by validating input in the fields
             *
             */
            @Override
            public void onClick(View v) {
                    validateInput();
            }
        });
        return registrationView;
    }

    /**
     * Makes sure all input fields are not blank and that passwrd and confirm passwrd are the same
     * @throws JSONException
     */
    public void validateInput() {
        if (email.length() != 0 && pass.length() != 0 && confirmPass.length() != 0 &&
                fname.length() != 0 && lname.length() != 0 && phone.length() != 0 &&
                streetAddress.length() != 0 && city.length() != 0 && state.length() != 0 &&
                zipcode.length() != 0 && company.length() != 0 &&
                pass.getText().toString().equals(confirmPass.getText().toString())) {
            List<Tower> list = new ArrayList<>();
            Tower parameters = new Tower();
            parameters.setEmail(email.getText().toString());
            parameters.setPassword(pass.getText().toString());
            parameters.setFname(fname.getText().toString());
            parameters.setLname(lname.getText().toString());
            parameters.setPhone(phone.getText().toString());
            parameters.setStreetAddress(streetAddress.getText().toString());
            parameters.setState(state.getText().toString());
            parameters.setCity(city.getText().toString());
            parameters.setZipcode(zipcode.getText().toString());
            parameters.setCompanyName(company.getText().toString());
            parameters.setPermitNumber("null");

            list.add(parameters);
            String response = CustomThread.CustomThreadHttpPost(
                    "http://www.qicfixit.com:8080/api/tower", toJson(list));

            System.out.println(response);
            //if true is returned then it runs this
            if (response.contains("true")) {
                Intent login = new Intent(getActivity(), MainActivity.class);
                login.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(login);
                getActivity().finish();
                //if false is returned it shows error
            } else {
                error.setVisibility(View.VISIBLE);
            }
        //if null is returned it shows error
        } else {
            error.setVisibility(View.VISIBLE);
        }

    }

    public static String toJson(List<Tower> list) {
        Gson gson = new GsonBuilder().setDateFormat(Utility.DATE_FORMAT_STRING_SHORT).create();
        String gsonString = gson.toJson(list, new TypeToken<List<Tower>>() {
        }.getType());
        return gsonString;
    }
}
