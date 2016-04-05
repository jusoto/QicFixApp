package com.qicfix.qicfixapp.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.qicfix.qicfixapp.R;
import com.qicfix.qicfixapp.entity.Customer;
import com.qicfix.qicfixapp.entity.ServiceType;
import com.qicfix.qicfixapp.entity.Serviceman;
import com.qicfix.qicfixapp.entity.ServicemanTypeRel;
import com.qicfix.qicfixapp.entity.User;

/**
 * Created by Juan on 3/27/2015.
 */
public class Database extends SQLiteOpenHelper {

    public static final String AUTHORITY = "com.qicfix.qicfixapp";
    public static final String SCHEME = "content://";
    public static final String SLASH = "/";
    public static final String DATABASE_NAME = R.string.database_name + ".db";
    public static final int DATABASE_VERSION = 1;

    public Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Method is called during creation of the database
    @Override
    public void onCreate(SQLiteDatabase db) {
        System.out.println("*********************************************");
        System.out.println("************* BEGIN DB CREATION *************");
        System.out.println("*********************************************");
        db.execSQL(User.Data.CREATE_TABLE);
        db.execSQL(Customer.Data.CREATE_TABLE);
        db.execSQL(Serviceman.Data.CREATE_TABLE);
        db.execSQL(ServiceType.Data.CREATE_TABLE);
        db.execSQL(ServicemanTypeRel.Data.CREATE_TABLE);
        System.out.println("*********************************************");
        System.out.println("************** END DB CREATION **************");
        System.out.println("*********************************************");
    }

    // Method is called during an upgrade of the database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        System.out.println("*********************************************");
        System.out.println("*************** BEGIN DB ERASE **************");
        System.out.println("*********************************************");
        db.execSQL(Customer.Data.DELETE_TABLE);
        System.out.println("*********************************************");
        System.out.println("**************** END DB ERASE ***************");
        System.out.println("*********************************************");
        System.out.println("*********************************************");
        System.out.println("******* INCIO DEL PROCESO DE BORRADO ********");
        System.out.println("******** OLD VERSION " + oldVersion + "**************");
        onCreate(db);
    }


}
