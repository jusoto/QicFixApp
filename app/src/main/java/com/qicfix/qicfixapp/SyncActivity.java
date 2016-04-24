package com.qicfix.qicfixapp;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.qicfix.qicfixapp.entity.Customer;
import com.qicfix.qicfixapp.entity.ServiceType;
import com.qicfix.qicfixapp.entity.Serviceman;
import com.qicfix.qicfixapp.entity.ServicemanTypeRel;
import com.qicfix.qicfixapp.entity.User;
import com.qicfix.qicfixapp.util.Utility;

import org.apache.commons.io.IOUtils;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by Juan on 2/9/2016.
 */

//Activity to update all system tables in device
public class SyncActivity extends Activity {

    View rootView;
    Button btnSync;
    TextView txtInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sync);
        txtInfo = (TextView) findViewById(R.id.txtInfo);

        //Button to run Sync
        btnSync = (Button) findViewById(R.id.btnSync);
        btnSync.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url;
                btnSync.setEnabled(false);
                txtInfo.setText("Sincronizando...\n");
                url = "http://www.qicfixit.com:8080/ws/user";
                updateTable(url, User.Data.TABLE_NAME);
                url = "http://www.qicfixit.com:8080/ws/customer";
                updateTable(url, Customer.Data.TABLE_NAME);
                url = "http://www.qicfixit.com:8080/ws/serviceman";
                updateTable(url, Serviceman.Data.TABLE_NAME);
                url = "http://www.qicfixit.com:8080/ws/serviceType";
                updateTable(url, ServiceType.Data.TABLE_NAME);
                url = "http://www.qicfixit.com:8080/ws/serviceTypeRel";
                updateTable(url, ServicemanTypeRel.Data.TABLE_NAME);
            }
        });

    }

    private void updateTable(String url, String tableName){

        if (true) {
            try {
                new DownloadWebpageTask(getBaseContext(), tableName).execute(url).get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
    }

    private class DownloadWebpageTask extends AsyncTask<String, Void, String> {

        Context context;
        TextView txtInfo;
        String tableName;

        public DownloadWebpageTask(Context context, String tableName) {
            this.context = context;
            this.txtInfo = (TextView) findViewById(R.id.txtInfo);
            this.tableName = tableName;
        }

        @Override
        protected void onPreExecute() {
            txtInfo.setText(txtInfo.getText() + "\nLoad in progress...");
        }

        @Override
        protected String doInBackground(String... params) {
            HttpURLConnection urlConnection = null;
            String resp = null;
            try {
                urlConnection = (HttpURLConnection) new URL(params[0]).openConnection();
                InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                resp = IOUtils.toString(in, "UTF-8");
            } catch (IOException e1) {
                e1.printStackTrace();
            } finally {
                urlConnection.disconnect();
            }
            return resp;
        }

        @Override
        protected void onPostExecute(String resp) {
            final LayoutInflater inflater = getLayoutInflater();

            if (resp!=null) {
                if(tableName=="user"){
                    User user = new User();
                    user.updateTable(context, resp);
                }
                if(tableName=="customer"){
                    Customer customer = new Customer();
                    customer.updateTable(context, resp);
                }
                if(tableName=="serviceman"){
                    Serviceman serviceman = new Serviceman();
                    serviceman.updateTable(context, resp);
                }
                if(tableName=="serviceType"){
                    ServiceType serviceType = new ServiceType();
                    serviceType.updateTable(context, resp);
                }
                if(tableName=="servicemanTypeRel"){
                    ServicemanTypeRel servicemanTypeRel = new ServicemanTypeRel();
                    servicemanTypeRel.updateTable(context, resp);
                }
                txtInfo.setText(txtInfo.getText() + tableName + " synced\n\n");

            }else{
                txtInfo.setText(txtInfo.getText() + "Error trying to reach Server.\n\n");
            }
        }

    }

}

