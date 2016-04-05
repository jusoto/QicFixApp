package com.qicfix.qicfixapp.util;

/**
 * Created by Juan on 3/22/2015.
 */

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.View;

import org.apache.commons.io.IOUtils;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import java.io.InputStream;
import java.io.StringReader;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/**
 * Class which has Utility methods
 */
public class Utility {

    private static final long MIN_TIME_BW_UPDATES = 1;
    private static final float MIN_DISTANCE_CHANGE_FOR_UPDATES = 1;
    //private DatabaseHelper DBHelper;
    //private SQLiteDatabase db;

    private static Pattern pattern;
    private static Matcher matcher;
    //Email Pattern
    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private static final String DATE_FORMAT_STRING = "yyyy-MM-dd'T'HH:mm:ss.SSSZZZZZ";
    private static final String DATE_FORMAT_STRING_SHORT = "yyyy-MM-dd";

    public Utility() {

    }

    /**
     * Validate Email with regular expression
     *
     * @param email
     * @return true for Valid Email and false for Invalid Email
     */
    public static boolean validateEmail(String email) {
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);
        return matcher.matches();

    }

    /**
     * Checks for Null String object
     *
     * @param txt
     * @return true for not null and false for null String object
     */
    public static boolean isNotNull(String txt) {
        return txt != null && txt.trim().length() > 0 ? true : false;
    }

    public static boolean isNull(String txt) {
        return txt == null || txt.trim().length() == 0 ? true : false;
    }

    public static Document convertStringToDocument(String xml) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        Document doc = null;
        InputSource is;
        try {
            builder = factory.newDocumentBuilder();
            is = new InputSource(new StringReader(xml));
            doc = builder.parse(is);
            return doc;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return doc;
    }

    //convert String to Fecha
    public static String ObtenerFechaActual() {
        SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT_STRING);
        String fecha = null;

        try {
            fecha = format.format(new Date());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return fecha;
    }

    //convert String to Fecha
    public static Date convertStringToDate(String dtStart) {
        SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT_STRING);
        Date fecha = null;

        try {
            fecha = format.parse(dtStart);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return fecha;
    }

    //convert String to Fecha
    public static String convertDateToString(Date dtStart) {
        SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT_STRING);
        String fecha = null;

        try {
            fecha = format.format(dtStart);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return fecha;
    }

    public static boolean validateUser(String user) {
        boolean respuesta = true;

        if ((user.length() < 4 || user.contains(" "))) {
            respuesta = false;
        }

        return respuesta;
    }

    public static boolean validatePass(String pass) {
        boolean respuesta = true;

        if ((pass.length() < 4 || pass.contains(" "))) {
            respuesta = false;
        }

        return respuesta;
    }

    public static String ObtenerFechaMinima(String meses_anteriores) {
        SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT_STRING_SHORT);
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, (-1) * Integer.parseInt(meses_anteriores));
        Date temp = cal.getTime();
        String result = format.format(temp);
        return result;
    }

    public static String ObtenerFechaMaxLoggedIn(String fecha, String max_dias) {
        SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT_STRING);
        Date dateParsed = null;
        Calendar cal;
        try {
            dateParsed = format.parse(fecha);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        cal = Calendar.getInstance();
        cal.setTime(dateParsed);
        cal.add(Calendar.DAY_OF_MONTH, Integer.parseInt(max_dias));
        Date temp = cal.getTime();
        String result = format.format(temp);
        return result;
    }

    public static boolean FechaAMayorFechaB(String fechaA, String fechaB) {

        SimpleDateFormat dfDate = new SimpleDateFormat(DATE_FORMAT_STRING);

        boolean b = false;

        try {
            if (dfDate.parse(fechaA).after(dfDate.parse(fechaB))) {
                b = true;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return b;
    }

    public static int convertLongToInt(long l) {
        if (l < Integer.MIN_VALUE || l > Integer.MAX_VALUE) {
            throw new IllegalArgumentException
                    (l + " valor entero fuera de rango.");
        }
        return (int) l;
    }

    public static Location getLocation(Context context) {
        LocationManager locationManager = (LocationManager) context.getSystemService(context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        String bestProvider = locationManager.getBestProvider(criteria, false);
        Location location = null;
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            location = locationManager.getLastKnownLocation(bestProvider);
        }
        return location;
    }

    public static Location getLocation2(Context context) {
        LocationManager locationManager;
        Location location = null;
        boolean isGPSEnabled;
        boolean isNetworkEnabled;

        try {
            locationManager = (LocationManager) context.getSystemService(context.LOCATION_SERVICE);

            // getting GPS status
            isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);

            // getting network status
            isNetworkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

            if (!isGPSEnabled && !isNetworkEnabled) {
                // no network provider is enabled
            } else {
                //this.canGetLocation = true;
                if (isNetworkEnabled) {
                    if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                        // TODO: Consider calling
                        //    ActivityCompat#requestPermissions
                        // here to request the missing permissions, and then overriding
                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                        //                                          int[] grantResults)
                        // to handle the case where the user grants the permission. See the documentation
                        // for ActivityCompat#requestPermissions for more details.
                        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, MIN_TIME_BW_UPDATES, MIN_DISTANCE_CHANGE_FOR_UPDATES, new MyLocationListener());
                    }
                    Log.d("Network", "Network Enabled");
                    if (locationManager != null) {
                        location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                    }
                }
                // if GPS Enabled get lat/long using GPS Services
                if (isGPSEnabled) {
                    if (location == null) {
                        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, MIN_TIME_BW_UPDATES, MIN_DISTANCE_CHANGE_FOR_UPDATES, new MyLocationListener());
                        Log.d("GPS", "GPS Enabled");
                        if (locationManager != null) {
                            location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                        }
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return location;
    }

    public static Date convertShortStringToDate(String dtStart) {
        SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT_STRING_SHORT);
        Date fecha = null;

        try {
            fecha = new java.sql.Date(format.parse(dtStart).getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return fecha;
    }

    public static String convertDateToStringShort(Date dtStart) {
        SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT_STRING_SHORT);
        String fecha = null;

        try {
            fecha = format.format(dtStart);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return fecha;
    }

    public static String FormatoDecimal(Double numero){
        String respuesta = "";
        DecimalFormat df = new DecimalFormat("#.##");
        df.setRoundingMode(RoundingMode.CEILING);
        try {
            respuesta = df.format(numero);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return respuesta;
    }

    private static class MyLocationListener implements LocationListener {

        public void onLocationChanged(Location loc) {
            String longi = "" + loc.getLongitude();
            String lat = "" + loc.getLatitude();
            String message = String.format(
                    "New Location \n Longitude: %1$s \n Latitude: %2$s \n %3$s ",
                    longi,
                    lat
            );
            //Toast.makeText(LbsGeocodingActivity.this, message, Toast.LENGTH_LONG).show();
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }

    }

    public static boolean getNetworkStatus(Context context) {
        boolean resp = false;
        ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            resp = true;
        }
        return resp;
    }




}