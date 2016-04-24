package com.qicfix.qicfixapp.entity;

import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;
import android.provider.BaseColumns;

import com.qicfix.qicfixapp.db.Database;
import com.qicfix.qicfixapp.util.Utility;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Juan on 2/9/2016.
 */
public class Serviceman extends User {

    private Integer idserviceman;
    private String email;
    private Double positionX;
    private Double positionY;

    public Serviceman() {
    }

    public Integer getIdserviceman() {
        return idserviceman;
    }

    public void setIdserviceman(Integer idserviceman) {
        this.idserviceman = idserviceman;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Double getPositionX() {
        return positionX;
    }

    public void setPositionX(Double positionX) {
        this.positionX = positionX;
    }

    public Double getPositionY() {
        return positionY;
    }

    public void setPositionY(Double positionY) {
        this.positionY = positionY;
    }

    public List<Serviceman> selectAllServiceman(Context context) {
        List<Serviceman> list = new ArrayList<Serviceman>();
        String sql;
        ResultSet rs = null;

        sql = "SELECT u.name, s.idserviceman, s.email, s.position_x, s.position_y FROM user u, serviceman s"
                + " WHERE s.email=u.email";

        Database db = new Database(context);
        /*try {
            db.Connect();
            db.setStatement();
            rs = db.ExecuteQuery(sql);
            while (rs.next()) {
                Serviceman serviceman = new Serviceman();
                serviceman.setName(rs.getString("name"));
                serviceman.setIdserviceman(rs.getString("idserviceman") != null ? rs.getInt("idserviceman") : null);
                serviceman.setEmail(rs.getString("email"));
                serviceman.setPositionX(rs.getString("position_x") != null ? rs.getDouble("position_x") : null);
                serviceman.setPositionY(rs.getString("position_y") != null ? rs.getDouble("position_y") : null);
                list.add(serviceman);
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
        }*/

        return list;
    }

    public boolean createServiceman(Context context, List<ServiceType> list) {

        boolean resp = false;
        int parameterIndex = 0;
        int id;
        ServicemanTypeRel serviceTypeRel = new ServicemanTypeRel();

        String sql = "INSERT INTO serviceman (email, position_x, position_y)"
                + " VALUES (?,?,?)";

        createUser(context);

        Database db = new Database(context);


        return resp;
    }

    public List<Serviceman> selectNearest(Double positionX, Double positionY) {
        return null;
    }

    public List<Serviceman> convertFromJsonServiceman(String jsonString){
        List<Serviceman> list = new ArrayList<>();
        Serviceman obj;

        try {
            JSONObject json = new JSONObject(jsonString);
            //JSONObject dataObject = json.getJSONObject("users");
            //JSONArray items = dataObject.getJSONArray("users");
            JSONArray items = json.getJSONArray(Data.TABLE_NAME);

            for (int i = 0; i < items.length(); i++) {
                JSONObject item = items.getJSONObject(i);
                obj = new Serviceman();
                obj.setEmail(item.getString(Data.KEY_EMAIL));
                obj.setName(item.getString(Data.KEY_NAME));
                obj.setAddress(item.getString(Data.KEY_ADDRESS));
        //        obj.setDob(Utility.convertStringToDate(item.getString(Data.KEY_DOB)));
                list.add(obj);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
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
