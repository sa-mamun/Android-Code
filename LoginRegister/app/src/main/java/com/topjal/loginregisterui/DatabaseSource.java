package com.topjal.loginregisterui;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.hardware.usb.UsbRequest;
import android.util.Log;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;

public class DatabaseSource {

    DatabaseHelper databaseHelper;
    SQLiteDatabase sqLiteDatabase;
    UserModel userModel;

    public DatabaseSource(Context context)
    {
        databaseHelper = new DatabaseHelper(context);
    }

    public void open()
    {
        sqLiteDatabase = databaseHelper.getWritableDatabase();
    }

    public void close()
    {
        databaseHelper.close();
    }

    public boolean addUserDetails(UserModel userModel)
    {
        this.open();
        boolean status = true;

        status = checkEmail(userModel.getUser_email());

        if (status)
        {
            this.close();
            return false;
        }
        else {

            ContentValues contentValues = new ContentValues();
            contentValues.put(databaseHelper.COL_USER_NAME, userModel.getUser_name());
            contentValues.put(databaseHelper.COL_USER_EMAIL, userModel.getUser_email());
            contentValues.put(databaseHelper.COL_USER_PASSWORD, userModel.getUser_password());

            long insertedRow = sqLiteDatabase.insert(databaseHelper.REGISTER_TABLE, null, contentValues);
            this.close();

            if (insertedRow > 0)
            {
                return true;
            }
            else
            {
                Log.e("check", "Under Status true state" );
                return false;
            }
        }

    }

    public boolean checkEmail(String email)
    {
        this.open();

        String query = "select * from "+databaseHelper.REGISTER_TABLE+" where "+databaseHelper.COL_USER_EMAIL+" = '"+email+"'";
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);

        if (cursor.moveToFirst())
        {
            cursor.close();
            return true;
        }else
        {
            cursor.close();
            return false;
        }

    }


    public ArrayList<UserModel> authentication(String email, String password)
    {
        this.open();

        ArrayList<UserModel> arrayList = new ArrayList<>();

        String query = "select * from "+databaseHelper.REGISTER_TABLE+" where "+databaseHelper.COL_USER_EMAIL+" = '"+email+"' AND "
                +databaseHelper.COL_USER_PASSWORD+" = '"+password+"'";
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);

        if (cursor.moveToFirst())
        {
            do {
                String uername = cursor.getString(cursor.getColumnIndex(databaseHelper.COL_USER_NAME));
                String useremail = cursor.getString(cursor.getColumnIndex(databaseHelper.COL_USER_EMAIL));
                String userpassword = cursor.getString(cursor.getColumnIndex(databaseHelper.COL_USER_PASSWORD));
                int id = cursor.getInt(cursor.getColumnIndex(databaseHelper.COL_ID));

                UserModel userModel = new UserModel(id, uername, useremail, userpassword);

                arrayList.add(userModel);

            }
            while (cursor.moveToNext());
            this.close();
            cursor.close();
            return arrayList;
        }else
        {
            this.close();
            cursor.close();
            return arrayList;
        }

    }


    public boolean tableCheck(String tableName)
    {
        this.open();

        String query = "select DISTINCT tbl_name from sqlite_master where tbl_name = '"+tableName+"'";
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);

        try {
            if (cursor != null)
            {
                if (cursor.getCount() > 0)
                {
                    return true;
                }
            }
        }catch (Exception e){
            Log.e("Catch Error", e.getMessage() );
        }

        this.close();
        cursor.close();
        return false;

    }

    public boolean addLoginTime(TimeModel timeModel)
    {
        this.open();

        ContentValues contentValues = new ContentValues();
        contentValues.put(databaseHelper.COL_DATE, timeModel.getDate());
        contentValues.put(databaseHelper.COL_TIME, timeModel.getTime());
        contentValues.put(databaseHelper.COL_REG_ID, timeModel.getUser_id());

        long insertedRow = sqLiteDatabase.insert(databaseHelper.LOGIN_TABLE, null, contentValues);
        this.close();

        if (insertedRow > 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public ArrayList<TimeModel> getAllInfo(int id)
    {
        this.open();

        ArrayList<TimeModel> arrayList = new ArrayList<>();

//        Cursor cursor = sqLiteDatabase.query(databaseHelper.TASK_TABLE, null, null, null, null, null, null, null);
        String query = "select * from "+databaseHelper.LOGIN_TABLE+" where "+databaseHelper.COL_REG_ID+" = '"+id+"'";

        Cursor cursor = sqLiteDatabase.rawQuery(query, null);

        if (cursor.moveToFirst())
        {
            do {
                String date = cursor.getString(cursor.getColumnIndex(databaseHelper.COL_DATE));
                String time = cursor.getString(cursor.getColumnIndex(databaseHelper.COL_TIME));
                int userId = cursor.getInt(cursor.getColumnIndex(databaseHelper.COL_REG_ID));
                int uid = cursor.getInt(cursor.getColumnIndex(databaseHelper.COL_LOG_ID));

                TimeModel timeModel = new TimeModel(uid, date, time, userId);

                arrayList.add(timeModel);

            }
            while (cursor.moveToNext());
        }
        this.close();
        cursor.close();
        return arrayList;
    }


}
