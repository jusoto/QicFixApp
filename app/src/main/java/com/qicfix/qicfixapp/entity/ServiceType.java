package com.qicfix.qicfixapp.entity;

import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;
import android.provider.BaseColumns;
import android.widget.TextView;

import com.qicfix.qicfixapp.db.Database;
import com.qicfix.qicfixapp.util.Utility;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Juan on 2/9/2016.
 */
public class ServiceType {

    private Integer idserviceType;
    private String name;
    private String description;

    public ServiceType() {
    }

    public Integer getIdserviceType() {
        return idserviceType;
    }

    public void setIdserviceType(Integer idserviceType) {
        this.idserviceType = idserviceType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /*public List<ServiceType> selectAll(){
        List<ServiceType> list = new ArrayList<ServiceType>();
        String sql;
        ResultSet rs = null;

        sql = "SELECT idservice_type, name, description FROM service_type";

        Database db = new Database();
        try {
            db.Connect();
            db.setStatement();
            rs = db.ExecuteQuery(sql);
            while (rs.next()) {
                ServiceType serviceType = new ServiceType();
                serviceType.setName(rs.getString("name"));
                serviceType.setIdserviceType(rs.getString("idservice_type") != null ? rs.getInt("idservice_type") : null);
                serviceType.setDescription(rs.getString("description"));
                list.add(serviceType);
            }
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    System.out.println(ex.toString());
                }
            }
            try {
                db.Close();
            } catch (SQLException ex) {
                System.out.println(ex.toString());
            }
        }

        return list;
    }*/

    public List<ServiceType> convertFromJson(String jsonString){
        List<ServiceType> users = new ArrayList<>();
        ServiceType obj;

        try {
            JSONObject json = new JSONObject(jsonString);
            //JSONObject dataObject = json.getJSONObject("users");
            //JSONArray items = dataObject.getJSONArray("users");
            JSONArray items = json.getJSONArray(Data.TABLE_NAME);

            for (int i = 0; i < items.length(); i++) {
                JSONObject item = items.getJSONObject(i);
                obj = new ServiceType();
                obj.setName(item.getString(Data.KEY_NAME));
                users.add(obj);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public String toString() {
        return this.name;
    }

    public void updateTable(Context context, String resp) {

    }

    public static final class Data implements BaseColumns {

        /* Do not allow this class to be instantiated */
        private Data() {
        }

        public static final String TABLE_NAME = "user";
        public static final String KEY_EMAIL = "email";
        public static final String KEY_PASSWORD = "password";
        public static final String KEY_NAME = "name";
        public static final String KEY_ADDRESS = "address";
        public static final String KEY_DOB = "dob";

        /*
         * URI definitions
         */

        /**
         * The content style URI
         */
        public static final Uri CONTENT_URI = Uri.parse(Database.SCHEME + Database.AUTHORITY + Database.SLASH + TABLE_NAME);

        /**
         * The content URI base for a single row. An ID must be appended.
         */
        public static final Uri CONTENT_ID_URI_BASE = Uri.parse(Database.SCHEME + Database.AUTHORITY + Database.SLASH + TABLE_NAME + Database.SLASH);

        /**
         * The default sort order for this table
         */
        public static final String DEFAULT_SORT_ORDER = KEY_NAME + " ASC";

        /*
         * MIME type definitions
         */

        /**
         * The MIME type of {@link #CONTENT_URI} providing rows
         */
        public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "";
        //"/vnd.com.marylandtransitcommuters.agency";

        /**
         * The MIME type of a {@link #CONTENT_URI} single row
         */
        public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "";
        //"/vnd.com.marylandtransitcommuters.agency";

        /**
         * SQL Statement to create the routes table
         */
        public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " ("
                + KEY_EMAIL + " TEXT,"
                + KEY_PASSWORD + " TEXT,"
                + KEY_NAME + " TEXT,"
                + KEY_ADDRESS + " TEXT,"
                + KEY_DOB + " TEXT"
                + ");";

        /**
         * SQL statement to delete the table
         */
        public static final String DELETE_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;

        /**
         * Array of all the columns. Makes for cleaner code
         */
        public static final String[] KEY_ARRAY = {
                KEY_NAME,
                KEY_EMAIL,
                KEY_PASSWORD,
                KEY_ADDRESS,
                KEY_DOB
        };

    }

}
