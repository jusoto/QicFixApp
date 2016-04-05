package com.qicfix.qicfixapp.controller;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

/**
 * Created by Juan on 2/9/2016.
 */
public class TabNearestTower extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ListView lsv = new ListView(this);
        setContentView(lsv);
    }
}
