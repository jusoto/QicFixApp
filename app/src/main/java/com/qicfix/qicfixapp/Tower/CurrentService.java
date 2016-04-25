package com.qicfix.qicfixapp.Tower;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.qicfix.qicfixapp.R;
import com.qicfix.qicfixapp.util.CustomThread;

public class CurrentService extends AppCompatActivity {

    private String token = "";
    private String url = "";
    private String email = "";
    String DEFAULT = "N/A";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_service);

        //pull data stored on the device
        SharedPreferences sharedPreferences = getSharedPreferences("USERDATA", Context.MODE_PRIVATE);
        token = sharedPreferences.getString("TOKEN", DEFAULT);
        url = sharedPreferences.getString("URL", DEFAULT);
        email = sharedPreferences.getString("EMAIL", DEFAULT);

        //gets a string response of all the services
        //String services = CustomThread.CustomThreadHttpGet(url + "service/active?email=" + email + "&token=" + token);
        String services = CustomThread.CustomThreadHttpGet(url + "tower?id=1&email=" + email + "&token=" + token);

        Log.d("SERVICES", services);
    }
}
