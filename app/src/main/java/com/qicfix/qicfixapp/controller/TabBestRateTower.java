package com.qicfix.qicfixapp.controller;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import com.qicfix.qicfixapp.entity.Serviceman;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Juan on 2/9/2016.
 */
public class TabBestRateTower extends Activity {
    @Override
     protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Double positionX = 0.;
        Double positionY = 0.;
        ListView lsv = new ListView(this);
        Serviceman obj = new Serviceman();
        List<Serviceman> list = obj.selectNearest(positionX, positionY);
        setContentView(lsv);
    }


}
