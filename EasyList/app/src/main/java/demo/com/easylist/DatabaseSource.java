package demo.com.easylist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class DatabaseSource {

    DatabaseHelper databaseHelper;
    SQLiteDatabase sqLiteDatabase;
    TaskModel taskModel;

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

    public boolean addTask(TaskModel taskModel)
    {
        this.open();

        ContentValues contentValues = new ContentValues();
        contentValues.put(databaseHelper.COL_TASK_NAME, taskModel.getTaskName());
        contentValues.put(databaseHelper.COL_TASK_TYPE, taskModel.getTaskType());
        contentValues.put(databaseHelper.COL_TASK_TIME, taskModel.getTaskTime());

        long insertedRow = sqLiteDatabase.insert(databaseHelper.TASK_TABLE, null, contentValues);
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

    public ArrayList<TaskModel> getAllTask(String type, String time)
    {
        this.open();

        ArrayList<TaskModel> arrayList = new ArrayList<>();

//        Cursor cursor = sqLiteDatabase.query(databaseHelper.TASK_TABLE, null, null, null, null, null, null, null);
        String query = "select * from "+databaseHelper.TASK_TABLE+" where "+databaseHelper.COL_TASK_TYPE+" = '"+type+"' AND "
                +databaseHelper.COL_TASK_TIME+" = '"+time+"'";
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);

        if (cursor.moveToFirst())
        {
            do {
                String taskName = cursor.getString(cursor.getColumnIndex(databaseHelper.COL_TASK_NAME));
                String taskType = cursor.getString(cursor.getColumnIndex(databaseHelper.COL_TASK_TYPE));
                String taskTime = cursor.getString(cursor.getColumnIndex(databaseHelper.COL_TASK_TIME));
                int id = cursor.getInt(cursor.getColumnIndex(databaseHelper.COL_ID));

                TaskModel taskModel = new TaskModel(id, taskName, taskType, taskTime);

                arrayList.add(taskModel);

            }
            while (cursor.moveToNext());
        }
        this.close();
        cursor.close();
        return arrayList;
    }

    public ArrayList<TaskModel> getSelectedType(String type, String time)
    {
        this.open();

        ArrayList<TaskModel> arrayList = new ArrayList<>();

        String selection = databaseHelper.COL_TASK_TYPE+" LIKE ? AND LIKE ? ";
        String[] selection_args = {type, time};

        Cursor cursor = sqLiteDatabase.query(databaseHelper.TASK_TABLE, null, selection,
                selection_args, null, null, null, null);

        if (cursor.moveToFirst())
        {
            do {
                String taskName = cursor.getString(cursor.getColumnIndex(databaseHelper.COL_TASK_NAME));
                String taskType = cursor.getString(cursor.getColumnIndex(databaseHelper.COL_TASK_TYPE));
                String taskTime = cursor.getString(cursor.getColumnIndex(databaseHelper.COL_TASK_TIME));
                int id = cursor.getInt(cursor.getColumnIndex(databaseHelper.COL_ID));

                TaskModel taskModel = new TaskModel(id, taskName, taskType, taskTime);

                arrayList.add(taskModel);

            }
            while (cursor.moveToNext());
        }
        this.close();
        cursor.close();
        return arrayList;
    }

    public void deleteTableData()
    {
        this.open();
        sqLiteDatabase.execSQL("delete from "+ databaseHelper.TASK_TABLE);
        this.close();
    }

}
