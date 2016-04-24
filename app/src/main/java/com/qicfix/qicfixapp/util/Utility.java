/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.qicfix.qicfixapp.util;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

/**
 *
 * @author Juan
 */
public class Utility {

    private static Pattern pattern;
    private static Matcher matcher;
    public static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    public static final String DATE_FORMAT_STRING = "yyyy-MM-dd'T'HH:mm:ss.S";
    public static final String DATE_FORMAT_STRING_SHORT = "yyyy-MM-dd";
    public static final String GOOGLE_MAPS_API_KEY = "AIzaSyAhOv2MNrliIM1BrBBOdgzD2Fip1rzUkHQ";
    public static final String LOGIN_PATH = "/login";
    public static final String USER_PATH = "/user";
    //public static final String USER_RETRIEVE_PATH = "/user/retrieve";
    public static final String USER_BLOCK_PATH = "/user/block";
    public static final String SERVICE_CREATE_PATH = "/service";
    public static final String TOWER_CREATE_PATH = "/tower";
    public static final String TOWER_BY_EMAIL_PATH = "/tower/email";
    public static final String USER_CREATE_PATH = "/user";
    public static final String USER_ID_BY_EMAIL_PATH = "/user/id";
    public static final String TOWER_PATH = "/tower";
    public static final String CLIENT_BY_EMAIL_PATH = "/client/email";
    public static final String USER_BY_EMAIL_PATH = "/user/email";

    public static boolean checkSession(String sessionKey) {
        boolean resp;

        resp = !(sessionKey == null || sessionKey.equals(""));

        return resp;
    }

    public static Date StringToDate(String strDate) {
        SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT_STRING);
        Date date = null;

        try {
            date = format.parse(strDate);
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        return date;
    }

    public static String ConvertirDateToString(Date date) {
        SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT_STRING);
        String strDate = null;

        try {
            strDate = format.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return strDate;
    }

    /*public static boolean validateUserFormat(String user) {
        boolean resp = true;
        if ((user.length() < 4 || user.contains(" "))) {
            resp = false;
        }
        return resp;
    }*/
    public static boolean validatePassFormat(String pass) {
        boolean resp = true;

        if ((pass.length() < 4 || pass.contains(" "))) {
            resp = false;
        }

        return resp;
    }

    public static boolean validateEmailFormat(String email) {
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static String generateToken() {
        String token = "123";

        return token;
    }

    public static class JsonDateDeserializer implements JsonDeserializer<Date> {

        @Override
        public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            final DateFormat df = new SimpleDateFormat("MMM dd, yyyy");
            try {
                return df.parse(json.getAsString());
            } catch (final java.text.ParseException e) {
                e.printStackTrace();
                return null;
            }
        }
    }

}