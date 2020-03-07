package com.topjal.loginregisterui;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    Context context;

    public static final String DATABASE_NAME = "user.db";
    public static final int DATABASE_VERSION = 3;

    public static final String REGISTER_TABLE = "register_table";
    public static final String LOGIN_TABLE = "login_table";

    public static final String COL_ID = "_id";
    public static final String COL_USER_NAME = "userName";
    public static final String COL_USER_EMAIL = "userEmail";
    public static final String COL_USER_PASSWORD = "userPassword";

    public static final String COL_LOG_ID = "logId";
    public static final String COL_REG_ID = "regId";
    public static final String COL_DATE = "date";
    public static final String COL_TIME = "time";

    public static final String  CREATE_TABLE = "create table "+REGISTER_TABLE+" ( "+COL_ID+" INTEGER PRIMARY KEY AUTOINCREMENT," +
            " "+COL_USER_NAME+" TEXT, "+COL_USER_EMAIL+" TEXT, "+COL_USER_PASSWORD+" TEXT"+" )";

    public static final String CREATE_TABLE_TWO = "create table "+LOGIN_TABLE+" ( "+COL_LOG_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "
            +COL_DATE+" TEXT, "+COL_TIME+" TEXT, "+COL_REG_ID+" INTEGER, "+"FOREIGN KEY ("+COL_REG_ID+") REFERENCES "+REGISTER_TABLE+"("+COL_ID+"))";




    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
        db.execSQL(CREATE_TABLE_TWO);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + REGISTER_TABLE);
        db.execSQL("drop table if exists " + LOGIN_TABLE);
        this.onCreate(db);
    }
}
