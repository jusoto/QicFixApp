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

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Juan on 2/9/2016.
 */
public class ServicemanTypeRel {

    private Integer idserviceType;
    private Integer idserviceman;

    public ServicemanTypeRel() {
    }

    public Integer getIdserviceType() {
        return idserviceType;
    }

    public void setIdserviceType(Integer idserviceType) {
        this.idserviceType = idserviceType;
    }

    public Integer getIdserviceman() {
        return idserviceman;
    }

    public void setIdserviceman(Integer idserviceman) {
        this.idserviceman = idserviceman;
    }

    /*public boolean create() {
        boolean resp = false;

        int parameterIndex = 0;

        String sql = "INSERT INTO serviceman_type_rel (idservice_type, idserviceman)"
                + " VALUES (?,?)";

        Database db = new Database();
        try {
            db.Connect();
            db.setPreparedStatement(sql);
            db.getPreparedStatement().setInt(++parameterIndex, this.getIdserviceType());
            db.getPreparedStatement().setInt(++parameterIndex, this.getIdserviceman());
            db.ExecuteNonQuery();
            resp = true;
        } catch (SQLException ex) {
            Logger.getLogger(ServicemanTypeRel.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (db != null) {
                try {
                    db.Close();
                } catch (SQLException ex) {
                    Logger.getLogger(ServicemanTypeRel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return resp;
    }*/

    public List<ServicemanTypeRel> convertFromJson(String jsonString){
        List<ServicemanTypeRel> users = new ArrayList<>();
        ServicemanTypeRel obj;

        try {
            JSONObject json = new JSONObject(jsonString);
            //JSONObject dataObject = json.getJSONObject("users");
            //JSONArray items = dataObject.getJSONArray("users");
            JSONArray items = json.getJSONArray(Data.TABLE_NAME);

            for (int i = 0; i < items.length(); i++) {
                JSONObject item = items.getJSONObject(i);
                obj = new ServicemanTypeRel();
                obj.setIdserviceman(item.getInt(Data.KEY_IDSERVICEMAN));
                obj.setIdserviceType(item.getInt(Data.KEY_IDSERVICE_TYPE));
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

        public static final String TABLE_NAME = "service_type_rel";
        public static final String KEY_IDSERVICEMAN = "idserviceman";
        public static final String KEY_IDSERVICE_TYPE = "idservice_type";

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
        public static final String DEFAULT_SORT_ORDER = KEY_IDSERVICEMAN + " ASC";

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
                + KEY_IDSERVICEMAN + " TEXT,"
                + KEY_IDSERVICE_TYPE + " TEXT"
                + ");";

        /**
         * SQL statement to delete the table
         */
        public static final String DELETE_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;

        /**
         * Array of all the columns. Makes for cleaner code
         */
        public static final String[] KEY_ARRAY = {
                KEY_IDSERVICEMAN,
                KEY_IDSERVICE_TYPE
        };

    }

}
