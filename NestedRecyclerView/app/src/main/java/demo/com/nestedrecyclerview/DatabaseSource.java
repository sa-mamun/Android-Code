package demo.com.nestedrecyclerview;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

public class DatabaseSource {

    DatabaseHelper databaseHelper;
    SQLiteDatabase sqLiteDatabase;
    RvOneModel rvOneModel;
    RvTwoModel rvTwoModel;

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

    public boolean addTaskType(RvOneModel rvOneModel)
    {
        this.open();

        ContentValues contentValues = new ContentValues();
        contentValues.put(databaseHelper.COL_TASK_TYPE, rvOneModel.getTaskType());
        contentValues.put(databaseHelper.COL_TASK_TIME, rvOneModel.getTaskTime());

        long insertedRow = sqLiteDatabase.insert(databaseHelper.TABLE_TYPE, null, contentValues);
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

    public boolean addTaskName(RvTwoModel rvTwoModel)
    {
        this.open();

        ContentValues contentValues = new ContentValues();
        contentValues.put(databaseHelper.COL_TASK_NAME, rvTwoModel.getTaskName());
        contentValues.put(databaseHelper.COL_STATUS, rvTwoModel.isStatus());
        contentValues.put(databaseHelper.COL_TYPE_ID, rvTwoModel.getTypeId());

        long insertedRow = sqLiteDatabase.insert(databaseHelper.TABLE_NAME, null, contentValues);
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

    //Update Task Name
    public boolean updateName(String name, int nameId)
    {
        this.open();

        ContentValues contentValues = new ContentValues();
        contentValues.put(databaseHelper.COL_TASK_NAME, name);
//        contentValues.put(databaseHelper.COL_TYPE_ID, rvTwoModel.getTypeId());

        long updatedRow = sqLiteDatabase.update(databaseHelper.TABLE_NAME, contentValues, databaseHelper.COL_NAME_ID+" = ?", new String[]{String.valueOf(nameId)});
        this.close();

        if (updatedRow > 0)
        {
            Log.e("Insdie update", "Updated" );
            return true;
        }
        else
        {
            Log.e("Insdie update", "Failed" );
            return false;
        }
    }


    //Update Task Status
    public boolean updateStatus(int status, int nameId)
    {
        this.open();

        ContentValues contentValues = new ContentValues();
        contentValues.put(databaseHelper.COL_STATUS, status);
//        contentValues.put(databaseHelper.COL_TYPE_ID, rvTwoModel.getTypeId());

        long updatedRow = sqLiteDatabase.update(databaseHelper.TABLE_NAME, contentValues, databaseHelper.COL_NAME_ID+" = ?", new String[]{String.valueOf(nameId)});
        this.close();

        if (updatedRow > 0)
        {
            Log.e("Status update", "Updated" );
            return true;
        }
        else
        {
            Log.e("Insdie update", "Failed" );
            return false;
        }
    }


    public ArrayList<RvOneModel> getTaskType(String type)
    {
        this.open();

        ArrayList<RvOneModel> arrayList = new ArrayList<>();

        String query = "select * from "+databaseHelper.TABLE_TYPE+" where "+databaseHelper.COL_TASK_TYPE+" = '"+type+"'";
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);

        if (cursor.moveToFirst())
        {
            do {
                String taskType = cursor.getString(cursor.getColumnIndex(databaseHelper.COL_TASK_TYPE));
                String taskTime = cursor.getString(cursor.getColumnIndex(databaseHelper.COL_TASK_TIME));
                int id = cursor.getInt(cursor.getColumnIndex(databaseHelper.COL_ID));

                RvOneModel rvOneModel = new RvOneModel(id, taskType, taskTime);

                arrayList.add(rvOneModel);

            }
            while (cursor.moveToNext());
        }
        this.close();
        cursor.close();
        return arrayList;
    }


    public ArrayList<RvTwoModel> getTaskName(int selectedId)
    {
        this.open();

        ArrayList<RvTwoModel> arrayList = new ArrayList<>();

        String query = "select * from "+databaseHelper.TABLE_NAME+" where "+databaseHelper.COL_TYPE_ID+" = '"+selectedId+"'";
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);

        if (cursor.moveToFirst())
        {
            do {
                String taskName = cursor.getString(cursor.getColumnIndex(databaseHelper.COL_TASK_NAME));
                int status = cursor.getInt(cursor.getColumnIndex(databaseHelper.COL_STATUS));
                int typeId = cursor.getInt(cursor.getColumnIndex(databaseHelper.COL_TYPE_ID));
                int id = cursor.getInt(cursor.getColumnIndex(databaseHelper.COL_NAME_ID));

                RvTwoModel rvTwoModel = new RvTwoModel(id, taskName, status, typeId);

                arrayList.add(rvTwoModel);

            }
            while (cursor.moveToNext());
        }
        this.close();
        cursor.close();
        return arrayList;
    }


    public void deleteTableType()
    {
        this.open();
        sqLiteDatabase.execSQL("delete from "+ databaseHelper.TABLE_TYPE);
        this.close();
    }

    public void deleteTableName()
    {
        this.open();
        sqLiteDatabase.execSQL("delete from "+ databaseHelper.TABLE_NAME);
        this.close();
    }

    //Checking if database table is exist
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


}
