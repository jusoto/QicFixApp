package com.qicfix.qicfixapp.Tower;

import android.app.DialogFragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.qicfix.qicfixapp.Tower.ServiceDialog;

import org.json.JSONObject;

import java.util.Date;
import java.util.List;

public class TowerServiceScreen extends Fragment {

    private String token = "";
    private String url = "";
    private String email = "";
    String DEFAULT = "N/A";
    private int code;

    private int[] cid;
    private String[] cityPickup;
    private String[] addressPickup;
    private String[] cityDestination;
    private String[] addressDestination;

    ListView list;

    private static final String ARG_SECTION_NUMBER = "section_number";
    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static TowerServiceScreen newInstance(int sectionNumber) {
        TowerServiceScreen fragment = new TowerServiceScreen();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public TowerServiceScreen() {
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View serviceListView = inflater.inflate(R.layout.activity_tower_service_screen, container, false);

        //pull data stored on the device
        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences("USERDATA", Context.MODE_PRIVATE);
        token = sharedPreferences.getString("TOKEN", DEFAULT);
        url = sharedPreferences.getString("URL", DEFAULT);
        email = sharedPreferences.getString("EMAIL", DEFAULT);

        retrieveServices(serviceListView);

        return serviceListView;

    }

    /**
     * retrieves the list of services and puts them in a list table
     */
    private void retrieveServices(View view) {

        //gets a string response of all the services
        String services = CustomThread.CustomThreadHttpGet(url + "service?email=" + email + "&location=null&token=" + token);

        Log.d("SERVICES", services);

        final List<Service> foo = fromJsonUser(services);

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
        ServiceList adapter = new ServiceList(this.getActivity(), cid, cityPickup, addressPickup, cityDestination, addressDestination);
        list = (ListView) view.findViewById(R.id.serviceList);
        list.setAdapter(adapter);

        final ServiceDialog sd = new ServiceDialog();

        //Enables each list row to be clickable
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Service service = foo.get(position);
                Bundle args = new Bundle();
                args.putString("ADDRESSPICKUP", service.getStreetAddressPickup());
                args.putString("CITYPICKUP", service.getCityPickup());
                args.putString("STATEPICKUP", service.getStatePickup());
                args.putString("ZIPPICKUP", service.getZipcodePickup());
                args.putString("ADDRESSDEST", service.getStreetAddressDestination());
                args.putString("CITYDEST", service.getCityDestination());
                args.putString("STATEDEST", service.getStateDestination());
                args.putString("ZIPDEST", service.getZipcodeDestination());
                args.putInt("CID", service.getClientId());
                args.putInt("SID", service.getId());
                sd.setArguments(args);

                sd.show(getActivity().getFragmentManager(), "Stuff");

//                courseView.putExtra("CID", cid[position]);
//                courseView.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
//                startActivity(courseView);
            }
        });

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
