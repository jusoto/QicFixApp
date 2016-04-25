package com.qicfix.qicfixapp.Tower;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.qicfix.qicfixapp.R;
import com.qicfix.qicfixapp.model.Tower;
import com.qicfix.qicfixapp.util.CustomThread;
import com.qicfix.qicfixapp.util.Utility;

import java.util.List;

public class TowerProfile extends Fragment{

    private String token = "";
    private String url = "";
    private String email = "";
    String DEFAULT = "N/A";

    TextView profileEmail, fname, lname, phone, streetAddress, city, state, zipcode, company;
    Button button;


    private static final String ARG_SECTION_NUMBER = "section_number";
        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static TowerProfile newInstance(int sectionNumber) {
            TowerProfile fragment = new TowerProfile();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }
    
    public TowerProfile() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){

        View profileView = inflater.inflate(R.layout.activity_tower_profile, container, false);

        //pull data stored on the device
        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences("USERDATA", Context.MODE_PRIVATE);
        token = sharedPreferences.getString("TOKEN", DEFAULT);
        url = sharedPreferences.getString("URL", DEFAULT);
        email = sharedPreferences.getString("EMAIL", DEFAULT);

        //Initialize all the Text Views with the profile
        profileEmail = (TextView) profileView.findViewById(R.id.profileEmail);
        fname = (TextView) profileView.findViewById(R.id.profileFirstname);
        lname = (TextView) profileView.findViewById(R.id.profileLastname);
        phone = (TextView) profileView.findViewById(R.id.profilePhone);
        streetAddress = (TextView) profileView.findViewById(R.id.profileAddress);
        city = (TextView) profileView.findViewById(R.id.profileCity);
        state = (TextView) profileView.findViewById(R.id.profileState);
        zipcode = (TextView) profileView.findViewById(R.id.profileZipcode);
        company = (TextView) profileView.findViewById(R.id.profileCompany);
        button = (Button) profileView.findViewById(R.id.editProfileButton);
        button.setOnClickListener(new View.OnClickListener() {
            /**
             * Executes registration by validating input in the fields
             *
             */
            @Override
            public void onClick(View v) {
                editProfile();
            }
        });

        String profile = CustomThread.CustomThreadHttpGet(url + "tower?id=1&email=" + email + "&token=" + token);

        List<Tower> list = Tower.fromJson(profile);

        if(list.size()==1){
            Tower t = list.get(0);
            profileEmail.setText(t.getEmail());
            fname.setText(t.getFname());
            lname.setText(t.getLname());
            phone.setText(t.getPhone());
            streetAddress.setText(t.getStreetAddress());
            city.setText(t.getCity());
            state.setText(t.getState());
            zipcode.setText(t.getZipcode());
            company.setText(t.getCompanyName());

        } else {
            Log.d("ERROR", "ERROR");
        }

        return profileView;
    }

    public void editProfile(){
        Intent profileForm = new Intent(getActivity(), TowerProfileForm.class);

        startActivity(profileForm);

    }

}
