package com.qicfix.qicfixapp.Tower;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.qicfix.qicfixapp.R;
import com.qicfix.qicfixapp.model.Tower;
import com.qicfix.qicfixapp.util.CustomThread;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class TowerProfileForm extends AppCompatActivity {

    private String token = "";
    private String url = "";
    private String email = "";
    private int id;
    String DEFAULT = "N/A";

    EditText profileEmail, pass, confirmPass, fname, lname, phone, streetAddress, city, state, zipcode, company;
    TextView error;
    Button button;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tower_profile_form);

        //pull data stored on the device
        SharedPreferences sharedPreferences = getSharedPreferences("USERDATA", Context.MODE_PRIVATE);
        token = sharedPreferences.getString("TOKEN", DEFAULT);
        url = sharedPreferences.getString("URL", DEFAULT);
        email = sharedPreferences.getString("EMAIL", DEFAULT);
        id = sharedPreferences.getInt("ID", -1);

        //Initialize all the Edit Texts with the istration form
        profileEmail = (EditText) findViewById(R.id.emailForm);
        pass = (EditText) findViewById(R.id.passwordForm);
        confirmPass = (EditText) findViewById(R.id.confirmPassForm);
        fname = (EditText) findViewById(R.id.firstNameForm);
        lname = (EditText) findViewById(R.id.lastNameForm);
        phone = (EditText) findViewById(R.id.phoneForm);
        streetAddress = (EditText) findViewById(R.id.addressForm);
        city = (EditText) findViewById(R.id.cityForm);
        state = (EditText) findViewById(R.id.stateForm);
        zipcode = (EditText) findViewById(R.id.zipcodeForm);
        company = (EditText) findViewById(R.id.companyNameForm);
        error = (TextView) findViewById(R.id.invalidInput);

        //Auto fill all the fields with User data
        Intent i = getIntent();
        profileEmail.setText(email);
        fname.setText(i.getStringExtra("FNAME"));
        lname.setText(i.getStringExtra("LNAME"));
        phone.setText(i.getStringExtra("PHONE"));
        streetAddress.setText(i.getStringExtra("STREET"));
        city.setText(i.getStringExtra("CITY"));
        state.setText(i.getStringExtra("STATE"));
        zipcode.setText(i.getStringExtra("ZIP"));
        company.setText(i.getStringExtra("COMPANY"));
        button = (Button) findViewById(R.id.formButton);
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
            //Tower list made for GSON
            List<Tower> list = new ArrayList<>();
            Tower parameters = new Tower();

            //add form fields as Attributes for Tower
            parameters.setEmail(profileEmail.getText().toString());
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
            System.out.println(Tower.toJson(list));
            String response = CustomThread.CustomThreadHttpPut(
                    "http://www.qicfixit.com:8080/api/tower?id=" + id + "&email="+ email + "&token="+token, Tower.toJson(list));

            System.out.println(response);
            //if true is returned then it runs this
            if (response.contains("true")) {
                Intent towerHome = new Intent(this, TowerHome.class);
                towerHome.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(towerHome);
                //if false is returned it shows error
            } else {
                error.setVisibility(View.VISIBLE);
            }
            //if null is returned it shows error
        } else {
            error.setVisibility(View.VISIBLE);
        }

    }
}
