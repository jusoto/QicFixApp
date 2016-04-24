package com.qicfix.qicfixapp.Tower;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.qicfix.qicfixapp.R;
import com.qicfix.qicfixapp.ServiceList;
import com.qicfix.qicfixapp.model.Service;
import com.qicfix.qicfixapp.util.CustomThread;
import com.qicfix.qicfixapp.util.Utility;

import org.json.JSONObject;

import java.util.Date;
import java.util.List;

public class TowerServiceScreen extends AppCompatActivity {

    private String token = "";
    private String url = "";
    private String email = "";
    String DEFAULT = "N/A";
    private JSONObject jsonResponse = null;
    private int code;

    private int[] cid;
    private String[] cityPickup;
    private String[] addressPickup;
    private String[] cityDestination;
    private String[] addressDestination;

    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tower_service_screen);

        //pull data stored on the device
        SharedPreferences sharedPreferences = getSharedPreferences("USERDATA", Context.MODE_PRIVATE);
        token = sharedPreferences.getString("TOKEN", DEFAULT);
        url = sharedPreferences.getString("URL", DEFAULT);
        email = sharedPreferences.getString("EMAIL", DEFAULT);

        retrieveServices();

    }

    /**
     * retrieves the list of services and puts them in a list table
     */
    private void retrieveServices() {

        //gets a string response of all the services
        String services = CustomThread.CustomThreadHttpGet(url + "service?email=" + email + "&location=null&token=" + token);

        Log.d("SERVICES", services);

        List<Service> foo = fromJsonUser(services);

        //initialize the length of the arrays
        cid = new int[foo.size()];
        cityDestination = new String[foo.size()];
        cityPickup = new String[foo.size()];
        addressDestination = new String[foo.size()];
        addressPickup = new String[foo.size()];

        //loop through all the JSON objects
        for (int i = 0; i < foo.size(); i++) {
            //gets Client Id
            cid[i] = foo.get(i).getClientId();
            //gets Client Pick up location
            cityPickup[i] = foo.get(i).getCityPickup();
            //gets client pick up address
            addressPickup[i] = foo.get(i).getStreetAddressPickup();
            //gets Client Destination location
            cityDestination[i] = foo.get(i).getCityDestination();
            //gets Clients destination address
            addressDestination[i] = foo.get(i).getStreetAddressDestination();
        }

        //Created custom Adapter and applied it to list view
        ServiceList adapter = new ServiceList(this, cid, cityPickup, addressPickup, cityDestination, addressDestination);
        list = (ListView) findViewById(R.id.serviceList);
        list.setAdapter(adapter);

        //Enables each list row to be clickable
//        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Intent courseView = new Intent(getApplication(), AdminCourseView.class);
//                courseView.putExtra("CID", cid[position]);
//                courseView.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
//                startActivity(courseView);
//            }
//        });

    }

    /**
     * Turns json into a gson Service object
     *
     * @param json
     * @return List<Service>
     * @throws JsonSyntaxException
     */
    public static List<Service> fromJsonUser(String json) throws JsonSyntaxException {
        Gson gson = new GsonBuilder().registerTypeAdapter(Date.class, new Utility.JsonDateDeserializer()).create();
        List<Service> list = gson.fromJson(json, new TypeToken<List<Service>>() {
        }.getType());
        return list;
    }

}
