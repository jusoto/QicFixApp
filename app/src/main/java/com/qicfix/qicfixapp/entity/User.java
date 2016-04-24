package com.qicfix.qicfixapp.entity;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.provider.BaseColumns;

import com.qicfix.qicfixapp.db.Database;
import com.qicfix.qicfixapp.util.Utility;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Juan on 2/8/2016.
 */
public class User {
    private String email;
    private String password;
    private String name;
    private String address;
    private Date dob;

    public User() {
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public boolean validateUser(Context context, String email, String pass) {
        boolean respuesta = false;
        String sql;
        Cursor cursor = null;
        SQLiteDatabase db = null;

        sql = "SELECT email, name, address, dob FROM user WHERE email=? AND password=?";

        String[] selectionArgs = {email, pass};

        try {
            db = new Database(context).getReadableDatabase();
            cursor = db.rawQuery(sql, selectionArgs);
        }catch (Exception ex){
            ex.printStackTrace();
        }

        if (cursor.moveToNext()) {
            respuesta = true;
            this.setEmail(cursor.getString(cursor.getColumnIndex(Data.KEY_EMAIL)));
            this.setName(cursor.getString(cursor.getColumnIndex(Data.KEY_NAME)));
            this.setAddress(cursor.getString(cursor.getColumnIndex(Data.KEY_ADDRESS)));
        }

        if (cursor != null) {
            cursor.close();
        }
        if (db != null) {
            db.close();
        }


        return respuesta;
    }

    public boolean createUser(Context context) {

        boolean resp = false;
        int parameterIndex = 0;

        String sql = "INSERT INTO user (name, address, email, password, dob)"
                + " VALUES (?,?,?,?,?)";

        Database db = new Database(context);
        /*try {
            db.getWritableDatabase();
            db.setPreparedStatement(sql);
            db.getPreparedStatement().setString(++parameterIndex, this.getName());
            db.getPreparedStatement().setString(++parameterIndex, this.getAddress());
            db.getPreparedStatement().setString(++parameterIndex, this.getEmail());
            db.getPreparedStatement().setString(++parameterIndex, this.getPassword());
            db.getPreparedStatement().setDate(++parameterIndex, (java.sql.Date) this.getDob());
            db.ExecuteNonQuery();
            resp = true;
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (db != null) {
                try {
                    db.Close();
                } catch (SQLException ex) {
                    Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }*/

        return resp;
    }


    /*public List<User> SelectAll() {
        List<User> list = new ArrayList<User>();
        String sql;
        ResultSet rs = null;

        sql = "SELECT username, name, email, dob FROM user";

        Database db = new Database();
        try {
            db.Connect();
            db.setStatement();
            rs = db.ExecuteQuery(sql);
            while (rs.next()) {
                User user = new User();
                user.setName(rs.getString("nombre") != null ? rs.getString("nombre") : null);
                user.setEmail(rs.getString("email") != null ? rs.getString("email") : null);
                //usuario.setDob(rs.getString("activo")!=null?rs.getBoolean("activo"):null);
                list.add(user);
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

    public List<User> convertFromJson(String jsonString){
        List<User> users = new ArrayList<>();
        User obj;

        try {
            JSONObject json = new JSONObject(jsonString);
            //JSONObject dataObject = json.getJSONObject("users");
            //JSONArray items = dataObject.getJSONArray("users");
            JSONArray items = json.getJSONArray(Data.TABLE_NAME);

            for (int i = 0; i < items.length(); i++) {
                JSONObject item = items.getJSONObject(i);
                obj = new User();
                obj.setEmail(item.getString(Data.KEY_EMAIL));
                obj.setName(item.getString(Data.KEY_NAME));
                obj.setAddress(item.getString(Data.KEY_ADDRESS));
            //    obj.setDob(Utility.convertStringToDate(item.getString(Data.KEY_DOB)));
                users.add(obj);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return users;
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
