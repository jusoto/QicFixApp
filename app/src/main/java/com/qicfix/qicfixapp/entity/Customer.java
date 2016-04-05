package com.qicfix.qicfixapp.entity;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

import com.qicfix.qicfixapp.db.Database;
import com.qicfix.qicfixapp.util.Utility;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Juan on 2/8/2016.
 */
public class Customer extends User {

    private Integer idcustomer;
    private String email;

    public Customer() {
    }

    public Integer getIdcustomer() {
        return idcustomer;
    }

    public void setIdcustomer(Integer idcustomer) {
        this.idcustomer = idcustomer;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    /*public boolean createCustomer() {

        boolean resp = false;
        int parameterIndex = 1;

        String sql = "INSERT INTO customer (email) VALUES (?)";

        if (createUser()) {

            Database db = new Database();
            try {
                db.Connect();
                db.setPreparedStatement(sql);
                db.getPreparedStatement().setString(++parameterIndex, this.getEmail());
                db.ExecuteNonQuery();
                resp = true;
            } catch (SQLException ex) {
                Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                if (db != null) {
                    try {
                        db.Close();
                    } catch (SQLException ex) {
                        Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
        return resp;
    }

    public List<Customer> selectAllCustomer() {
        List<Customer> list = new ArrayList<Customer>();
        String sql;
        ResultSet rs = null;

        sql = "SELECT u.name, c.idcustomer, c.email FROM user u, customer c"
                + " WHERE c.email=u.email";

        DataBase db = new DataBase();
        try {
            db.Connect();
            db.setStatement();
            rs = db.ExecuteQuery(sql);
            while (rs.next()) {
                Customer customer = new Customer();
                customer.setName(rs.getString("name"));
                customer.setIdcustomer(rs.getString("idcustomer") != null ? rs.getInt("email") : null);
                customer.setEmail(rs.getString("email"));
                list.add(customer);
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
    }

    public void selectIdByEmail() {
        String sql;
        ResultSet rs = null;

        sql = "SELECT idcustomer FROM customer WHERE email='" + this.getEmail() + "'";

        DataBase db = new DataBase();
        try {
            db.Connect();
            db.setStatement();
            rs = db.ExecuteQuery(sql);
            if (rs.next()) {
                this.setIdcustomer(rs.getString("idcustomer") != null ? rs.getInt("idcustomer") : null);
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

    }*/

    public List<Customer> convertFromJsonCustomer(String jsonString){
        List<Customer> list = new ArrayList<>();
        Customer obj;

        try {
            JSONObject json = new JSONObject(jsonString);
            //JSONObject dataObject = json.getJSONObject("users");
            //JSONArray items = dataObject.getJSONArray("users");
            JSONArray items = json.getJSONArray(Data.TABLE_NAME);

            for (int i = 0; i < items.length(); i++) {
                JSONObject item = items.getJSONObject(i);
                obj = new Customer();
                obj.setName(item.getString(Data.KEY_IDCUSTOMER));
                obj.setEmail(item.getString(Data.KEY_EMAIL));
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

        public static final String TABLE_NAME = "customer";
        public static final String KEY_IDCUSTOMER = "idcustomer";
        public static final String KEY_EMAIL = "email";

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
        public static final String DEFAULT_SORT_ORDER = KEY_IDCUSTOMER + " ASC";

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
                + KEY_IDCUSTOMER + " INTEGER,"
                + KEY_EMAIL + " TEXT"
                + ");";

        /**
         * SQL statement to delete the table
         */
        public static final String DELETE_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;

        /**
         * Array of all the columns. Makes for cleaner code
         */
        public static final String[] KEY_ARRAY = {
                KEY_IDCUSTOMER,
                KEY_EMAIL
        };

    }
}
