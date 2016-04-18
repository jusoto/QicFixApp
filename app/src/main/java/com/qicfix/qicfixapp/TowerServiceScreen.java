package com.qicfix.qicfixapp;

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

import com.qicfix.qicfixapp.util.CustomThread;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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

        SharedPreferences sharedPreferences = getSharedPreferences("USERDATA", Context.MODE_PRIVATE);
        token = sharedPreferences.getString("TOKEN", DEFAULT);
        url = sharedPreferences.getString("URL", DEFAULT);
        email = sharedPreferences.getString("EMAIL", DEFAULT);

        retrieveServices();

    }

    /**
     * retrieves the list of services and puts them in a list table
     */
    private void retrieveServices(){

        //gets a string response of all the services
        String services = CustomThread.CustomThreadHttpGet(url + "service?email=" + email + "&location=null&token=" + token);

        Log.d("SERVICES", services);
        try {
            //initalize jsonObjects
//            jsonResponse = new JSONObject(services);
//            code = jsonResponse.optInt("code");
//            System.out.println(code);
//            if(code == 401) {
//                Toast.makeText(getApplicationContext(), "Unauthorized", Toast.LENGTH_SHORT).show();
//                return;
//            } else if (code == 404) {
//                Toast.makeText(getApplicationContext(), "Page Does Not Exist", Toast.LENGTH_SHORT).show();
//                finish();
//                return;
//            } else if (code == 408) {
//                Toast.makeText(getApplicationContext(), "Connection Timeout", Toast.LENGTH_SHORT).show();
//                finish();
//                return;
//            }
            JSONArray serviceArray = jsonResponse.optJSONArray(services);

            System.out.println("HERE");
            //initialize the length of the arrays
            cid = new int[serviceArray.length()];
            cityDestination = new String[serviceArray.length()];
            cityPickup = new String[serviceArray.length()];
            addressDestination = new String[serviceArray.length()];
            addressPickup = new String[serviceArray.length()];

            //loop through all the JSON objects
            for(int i = 0; i<serviceArray.length();i++){
                JSONObject temp = serviceArray.getJSONObject(i);
                //gets Client Id
                cid[i]=temp.optInt("clientId");
                //gets Client Pick up location
                cityPickup[i]=temp.optString("cityPickup");
                addressPickup[i]=temp.optString("streetAddressPickup");
                //gets Client Destination location
                cityPickup[i]=temp.optString("cityDestination");
                addressPickup[i]=temp.optString("streetAddressDestination");
            }
            //code = jsonResponse.optInt("code");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        //Created custom Adapter and applied it to list view
        System.out.println(cid.toString());
        System.out.println(cityPickup.toString());
        System.out.println(addressPickup.toString());
        System.out.println(cityDestination.toString());
        System.out.println(addressDestination.toString());

        ServiceList adapter=new ServiceList(this, cid, cityPickup, addressPickup, cityDestination, addressDestination);
        list=(ListView)findViewById(R.id.serviceList);
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

}
