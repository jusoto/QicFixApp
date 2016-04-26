package com.qicfix.qicfixapp.Tower;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;

import com.qicfix.qicfixapp.MainActivity;
import com.qicfix.qicfixapp.R;
import com.qicfix.qicfixapp.util.CustomThread;

/**
 * Created by stevefoo on 4/26/16.
 */
public class LogoutDialog extends DialogFragment {

    private String token = "";
    private String url = "";
    private String email = "";
    String DEFAULT = "N/A";

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        //pull data stored on the device
        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences("USERDATA", Context.MODE_PRIVATE);
        token = sharedPreferences.getString("TOKEN", DEFAULT);
        url = sharedPreferences.getString("URL", DEFAULT);
        email = sharedPreferences.getString("EMAIL", DEFAULT);

        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle("Logout");
        builder.setMessage("Are you sure you want to Logout?");

        builder
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        String profile = CustomThread.CustomThreadHttpGet(url + "logout?email=" + email + "&token=" + token);
                        System.out.println(profile);
                        Intent login = new Intent(getActivity(), MainActivity.class);
                        login.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(login);
                        getActivity().finish();
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dismiss();
                    }

                });

                        // Create the AlertDialog object and return it
        return builder.create();
    }


}
