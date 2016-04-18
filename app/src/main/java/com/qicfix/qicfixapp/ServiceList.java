package com.qicfix.qicfixapp;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by stevefoo on 4/18/16.
 */
public class ServiceList extends ArrayAdapter<String> {

    private final Activity context;
    private int[] cid;
    private String[] cityPickup;
    private String[] addressPickup;
    private String[] cityDestination;
    private String[] addressDestination;

    /**
     * Constructor to use the list adapter
     *
     * @param context Activity
     * @param cid int []
     * @param cityPickup String []
     * @param addressPickup String []
     * @param cityDestination String []
     * @param addressDestination String []
     */
    public ServiceList(Activity context, int[] cid, String[] cityPickup, String[] addressPickup,
                           String [] cityDestination, String [] addressDestination) {
        super(context, R.layout.tower_service_list, cityPickup);

        this.context=context;
        this.cid= cid;
        this.cityPickup = cityPickup;
        this.addressPickup=addressPickup;
        this.cityDestination=cityDestination;
        this.addressDestination = addressDestination;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        View rowView = view;

        if(rowView == null) {
            LayoutInflater inflater = context.getLayoutInflater();
            rowView = inflater.inflate(R.layout.tower_service_list, null, true);
        }

        TextView custId = (TextView) rowView.findViewById(R.id.ClientId);
        TextView pickup = (TextView) rowView.findViewById(R.id.ClientPickup);
        TextView destination = (TextView) rowView.findViewById(R.id.ClientDestination);

        custId.setText(String.valueOf(cid[position]));
        String pick = "Pickup:" + addressPickup[position] + ", " + cityPickup[position];
        pickup.setText(pick);
        String dest = "Destination:" + addressDestination[position] + ", " + cityDestination[position];
        destination.setText(dest);
        return rowView;

    }
}
