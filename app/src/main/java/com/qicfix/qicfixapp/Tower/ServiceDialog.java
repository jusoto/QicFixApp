package com.qicfix.qicfixapp.Tower;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.qicfix.qicfixapp.R;

/**
 * Created by stevefoo on 4/25/16.
 */
public class ServiceDialog extends DialogFragment {

    Button cancel, accept;
    TextView pickup, destination, serviceID;

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static ServiceDialog newInstance() {
        ServiceDialog fragment = new ServiceDialog();
        Bundle args = new Bundle();
//        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View serviceView=inflater.inflate(R.layout.dialog_fragment_service_accept, container, false);

        pickup = (TextView) serviceView.findViewById(R.id.servicePickup);
        destination = (TextView) serviceView.findViewById(R.id.serviceDestination);
        serviceID = (TextView) serviceView.findViewById(R.id.serviceID);

        Bundle mArgs = getArguments();

        getDialog().setTitle("Service request from Client:" + mArgs.getInt("CID"));

        String pickAddr = mArgs.getString("ADDRESSPICKUP");
        String pickCity = mArgs.getString("CITYPICKUP");
        String pickState = mArgs.getString("STATEPICKUP");
        String pickZip = mArgs.getString("ZIPPICKUP");
        String destAddr = mArgs.getString("ADDRESSDEST");
        String destCity = mArgs.getString("CITYDEST");
        String destState = mArgs.getString("STATEDEST");
        String destZip = mArgs.getString("ZIPDEST");
        int sid = mArgs.getInt("SID");

        serviceID.setText(Integer.toString(sid));
        pickup.setText(pickAddr + " " + pickCity + ", "+ pickState + " " + pickZip);
        destination.setText(destAddr + " " + destCity + ", "+ destState + " " + destZip);

        cancel = (Button) serviceView.findViewById(R.id.cancel);
        accept = (Button) serviceView.findViewById(R.id.acceptService);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        return serviceView;
    }
}
