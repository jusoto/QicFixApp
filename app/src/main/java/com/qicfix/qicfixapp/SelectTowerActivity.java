package com.qicfix.qicfixapp;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

import com.qicfix.qicfixapp.controller.TabBestRateTower;
import com.qicfix.qicfixapp.controller.TabNearestTower;

/**
 * Created by Juan on 2/9/2016.
 */
public class SelectTowerActivity extends TabActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_tower);

        // create the TabHost that will contain the Tabs
        TabHost tabHost = (TabHost)findViewById(android.R.id.tabhost);


        TabSpec tab1 = tabHost.newTabSpec("Nearest");
        TabSpec tab2 = tabHost.newTabSpec("Best Rated");

        // Set the Tab name and Activity
        // that will be opened when particular Tab will be selected
        tab1.setIndicator("Tab1");
        tab1.setContent(new Intent(this, TabNearestTower.class));

        tab2.setIndicator("Tab2");
        tab2.setContent(new Intent(this, TabBestRateTower.class));

        /** Add the tabs  to the TabHost to display. */
        tabHost.addTab(tab1);
        tabHost.addTab(tab2);
    }
}
