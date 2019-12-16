package demo.com.easylist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

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

    //Method for adding state info
    public boolean addState(int check, int taskId)
    {
        this.open();

        ContentValues contentValues = new ContentValues();
        contentValues.put(databaseHelper.COL_STATE_CHECK, check);
        contentValues.put(databaseHelper.COL_TASK_ID, taskId);

        long insertedRow = sqLiteDatabase.insert(databaseHelper.STATE_TABLE, null, contentValues);
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


    //Update State Info
    public boolean updateState(int check, int taskId)
    {
        this.open();

        ContentValues contentValues = new ContentValues();
        contentValues.put(databaseHelper.COL_STATE_CHECK, check);
        contentValues.put(databaseHelper.COL_TASK_ID, taskId);

        long updatedRow = sqLiteDatabase.update(databaseHelper.STATE_TABLE, contentValues, databaseHelper.COL_TASK_ID+" = ?", new String[]{String.valueOf(taskId)});
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



    public ArrayList<StateModel> getAllState()
    {
        this.open();

        ArrayList<StateModel> arrayList = new ArrayList<>();

        Cursor cursor = sqLiteDatabase.query(databaseHelper.STATE_TABLE, null, null, null, null, null, null, null);

        if (cursor.moveToFirst())
        {
            do {
                int stateId = cursor.getInt(cursor.getColumnIndex(databaseHelper.COL_STATE_ID));
                int stateInfo = cursor.getInt(cursor.getColumnIndex(databaseHelper.COL_STATE_CHECK));
                int stateTaskId = cursor.getInt(cursor.getColumnIndex(databaseHelper.COL_STATE_ID));

                StateModel stateModel = new StateModel(stateId, stateInfo, stateTaskId);

                arrayList.add(stateModel);

            }
            while (cursor.moveToNext());
        }
        this.close();
        cursor.close();
        return arrayList;
    }


    //For Getting Task and State table Join Value
    public ArrayList<TaskModel> getSelectedType(String type, String time)
    {
        this.open();

        ArrayList<TaskModel> arrayList = new ArrayList<>();

        String query = "select "+databaseHelper.COL_ID+", "+databaseHelper.COL_TASK_NAME+", "+databaseHelper.COL_TASK_TYPE+", "
                +databaseHelper.COL_TASK_TIME+", "+databaseHelper.COL_STATE_CHECK+" from "+databaseHelper.TASK_TABLE+" INNER JOIN "+databaseHelper.STATE_TABLE+" ON "
                +databaseHelper.COL_TASK_TYPE+" = '"+type+"' AND "+databaseHelper.COL_TASK_TIME+" = '"+time+"' AND "
                +databaseHelper.COL_ID+" = "+databaseHelper.COL_STATE_ID;

        Cursor cursor = sqLiteDatabase.rawQuery(query, null);

        if (cursor.moveToFirst())
        {
            do {
                String taskName = cursor.getString(cursor.getColumnIndex(databaseHelper.COL_TASK_NAME));
                String taskType = cursor.getString(cursor.getColumnIndex(databaseHelper.COL_TASK_TYPE));
                String taskTime = cursor.getString(cursor.getColumnIndex(databaseHelper.COL_TASK_TIME));
                int id = cursor.getInt(cursor.getColumnIndex(databaseHelper.COL_ID));
                int status = cursor.getInt(cursor.getColumnIndex(databaseHelper.COL_STATE_CHECK));

                boolean stat;
                Log.e("Status in source", String.valueOf(status) );
                if (status == 1)
                {
                    stat = true;
                }
                else {
                    stat = false;
                }

                TaskModel taskModel = new TaskModel(id, taskName, taskType, taskTime, stat);

                arrayList.add(taskModel);

            }
            while (cursor.moveToNext());
        }
        this.close();
        cursor.close();
//        Log.e("Returning Arraylist", String.valueOf(arrayList.size()) );
        return arrayList;
    }


    //Delete selected Task
    public boolean deleteSelectedTask(TaskModel taskModel)
    {
        this.open();

        int deletedRow = sqLiteDatabase.delete(databaseHelper.TASK_TABLE, databaseHelper.COL_ID+ " =?", new String[]{String.valueOf(taskModel.getTaskId())});

        this.close();

        if (deletedRow > 0)
        {
            return true;
        }
        else {
            return false;
        }
    }


    public ArrayList<String> getDistinctType(String time)
    {
        this.open();

        ArrayList<String> arrayList = new ArrayList<>();

        String query = "select DISTINCT "+databaseHelper.COL_TASK_TYPE+" from "+databaseHelper.TASK_TABLE+" where "
                +databaseHelper.COL_TASK_TIME+" = '"+time+"'";
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);

        if (cursor.moveToFirst())
        {
            do {
                String taskType = cursor.getString(cursor.getColumnIndex(databaseHelper.COL_TASK_TYPE));

                arrayList.add(taskType);

            }
            while (cursor.moveToNext());
        }
        this.close();
        cursor.close();
        return arrayList;
    }

    public String getTodayTaskNameByType(String type, String time)
    {
        this.open();

        ArrayList<String> arrayList = new ArrayList<>();

        String query = "select "+databaseHelper.COL_TASK_NAME+" from "+databaseHelper.TASK_TABLE+" where "+databaseHelper.COL_TASK_TYPE+" = '"+type+"' AND "
                +databaseHelper.COL_TASK_TIME+" = '"+time+"'";
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);

        if (cursor.moveToFirst())
        {
            do {
                String taskType = cursor.getString(cursor.getColumnIndex(databaseHelper.COL_TASK_NAME));

                arrayList.add(taskType);

            }
            while (cursor.moveToNext());
        }
        Log.e("By Type Size", String.valueOf(arrayList.size()));
        this.close();
        cursor.close();
        return String.valueOf(arrayList.size());
    }


    //Delete selected State
    public boolean deleteState(int taskId)
    {
        this.open();

        int deletedRow = sqLiteDatabase.delete(databaseHelper.STATE_TABLE, databaseHelper.COL_TASK_ID+ " =?", new String[]{String.valueOf(taskId)});

        this.close();

        if (deletedRow > 0)
        {
            return true;
        }
        else {
            return false;
        }
    }


    //Delete selected State
    public boolean deleteAllByType(String typeName, String time)
    {
        this.open();

        int deletedRow = sqLiteDatabase.delete(databaseHelper.TASK_TABLE, databaseHelper.COL_TASK_TYPE+ " =? AND "
                +databaseHelper.COL_TASK_TIME+" =? ", new String[]{typeName, time+""});


        this.close();

        if (deletedRow > 0)
        {
            return true;
        }
        else {
            return false;
        }

    }



    public void deleteTableData()
    {
        this.open();
        sqLiteDatabase.execSQL("delete from "+ databaseHelper.TASK_TABLE);
        this.close();
    }

    public void deleteStateData()
    {
        this.open();
        sqLiteDatabase.execSQL("delete from "+ databaseHelper.STATE_TABLE);
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

    //For Upcoming Task

    public boolean addTaskType(RvOneModel rvOneModel)
    {
        this.open();

        ContentValues contentValues = new ContentValues();
        contentValues.put(databaseHelper.COL_UP_TASK_TYPE, rvOneModel.getTaskType());
        contentValues.put(databaseHelper.COL_UP_TASK_TIME, rvOneModel.getTaskTime());

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
        contentValues.put(databaseHelper.COL_UP_TASK_NAME, rvTwoModel.getTaskName());
        contentValues.put(databaseHelper.COL_UP_STATUS, rvTwoModel.isStatus());
        contentValues.put(databaseHelper.COL_UP_TYPE_NAME, rvTwoModel.getTypeName());
        contentValues.put(databaseHelper.COL_UP_TYPE_ID, rvTwoModel.getTypeId());

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
        contentValues.put(databaseHelper.COL_UP_TASK_NAME, name);
//        contentValues.put(databaseHelper.COL_TYPE_ID, rvTwoModel.getTypeId());

        long updatedRow = sqLiteDatabase.update(databaseHelper.TABLE_NAME, contentValues, databaseHelper.COL_UP_NAME_ID+" = ?", new String[]{String.valueOf(nameId)});
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
        contentValues.put(databaseHelper.COL_UP_STATUS, status);
//        contentValues.put(databaseHelper.COL_TYPE_ID, rvTwoModel.getTypeId());

        long updatedRow = sqLiteDatabase.update(databaseHelper.TABLE_NAME, contentValues, databaseHelper.COL_UP_NAME_ID+" = ?", new String[]{String.valueOf(nameId)});
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

        String query = "select * from "+databaseHelper.TABLE_TYPE+" where "+databaseHelper.COL_UP_TASK_TYPE+" = '"+type+"'";
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);

        if (cursor.moveToFirst())
        {
            do {
                String taskType = cursor.getString(cursor.getColumnIndex(databaseHelper.COL_UP_TASK_TYPE));
                String taskTime = cursor.getString(cursor.getColumnIndex(databaseHelper.COL_UP_TASK_TIME));
                int id = cursor.getInt(cursor.getColumnIndex(databaseHelper.COL_UP_ID));

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

        String query = "select * from "+databaseHelper.TABLE_NAME+" where "+databaseHelper.COL_UP_TYPE_ID+" = '"+selectedId+"'";
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);

        if (cursor.moveToFirst())
        {
            do {
                String taskName = cursor.getString(cursor.getColumnIndex(databaseHelper.COL_UP_TASK_NAME));
                String typeName = cursor.getString(cursor.getColumnIndex(databaseHelper.COL_UP_TYPE_NAME));
                int status = cursor.getInt(cursor.getColumnIndex(databaseHelper.COL_UP_STATUS));
                int typeId = cursor.getInt(cursor.getColumnIndex(databaseHelper.COL_UP_TYPE_ID));
                int id = cursor.getInt(cursor.getColumnIndex(databaseHelper.COL_UP_NAME_ID));

                RvTwoModel rvTwoModel = new RvTwoModel(id, taskName, status, typeName, typeId);

                arrayList.add(rvTwoModel);

            }
            while (cursor.moveToNext());
        }
        this.close();
        cursor.close();
        return arrayList;
    }


    public boolean deleteName(int taskId)
    {
        this.open();

        int deletedRow = sqLiteDatabase.delete(databaseHelper.TABLE_NAME, databaseHelper.COL_UP_NAME_ID+ " =?", new String[]{String.valueOf(taskId)});

        this.close();

        if (deletedRow > 0)
        {
            return true;
        }
        else {
            return false;
        }
    }

    public ArrayList<String> getDistinctTaskType()
    {
        this.open();

        ArrayList<String> arrayList = new ArrayList<>();

        String query = "select DISTINCT "+databaseHelper.COL_UP_TASK_TYPE+" from "+databaseHelper.TABLE_TYPE;
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);

        if (cursor.moveToFirst())
        {
            do {
                String taskType = cursor.getString(cursor.getColumnIndex(databaseHelper.COL_UP_TASK_TYPE));

                arrayList.add(taskType);

            }
            while (cursor.moveToNext());
        }
        this.close();
        cursor.close();
        return arrayList;
    }

    public String getTaskNameByType(String name)
    {
        this.open();

        ArrayList<String> arrayList = new ArrayList<>();

        String query = "select "+databaseHelper.COL_UP_TASK_NAME+" from "+databaseHelper.TABLE_NAME+" where "+databaseHelper.COL_UP_TYPE_NAME+" = '"+name+"'";
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);

        if (cursor.moveToFirst())
        {
            do {
                String taskType = cursor.getString(cursor.getColumnIndex(databaseHelper.COL_UP_TASK_NAME));

                arrayList.add(taskType);

            }
            while (cursor.moveToNext());
        }
        Log.e("By Type Size", String.valueOf(arrayList.size()));
        this.close();
        cursor.close();
        return String.valueOf(arrayList.size());
    }


    public boolean deleteUpAllByType(String typeName)
    {
        this.open();

        int deletedRow = sqLiteDatabase.delete(databaseHelper.TABLE_TYPE, databaseHelper.COL_UP_TASK_TYPE+ " =? ",
                new String[]{typeName});


        if (deletedRow > 0)
        {
            int deleteTask = sqLiteDatabase.delete(databaseHelper.TABLE_NAME, databaseHelper.COL_UP_TYPE_NAME+" =? ",
                    new String[]{typeName});

            this.close();

            if (deleteTask > 0)
            {
                return true;
            }
            else{
                return false;
            }
        }
        else {
            return false;
        }

    }

    public boolean deleteUpAllById(int id, String date)
    {
        this.open();

        int deletedDate = sqLiteDatabase.delete(databaseHelper.TABLE_TYPE, databaseHelper.COL_UP_TASK_TIME+ " =? ",
                new String[]{date});


        if (deletedDate > 0)
        {
            int deleteTask = sqLiteDatabase.delete(databaseHelper.TABLE_NAME, databaseHelper.COL_UP_TYPE_ID+" =? ",
                    new String[]{String.valueOf(id)});

            this.close();

            if (deleteTask > 0)
            {
                return true;
            }
            else{
                return false;
            }
        }
        else {
            return false;
        }

    }



//    public ArrayList<RvTwoModel> getAllFromTableName()
//    {
//        this.open();
//
//        ArrayList<RvTwoModel> arrayList = new ArrayList<>();
//
//        Cursor cursor = sqLiteDatabase.query(databaseHelper.TABLE_NAME, null, null, null, null, null, null, null);
//
//        if (cursor.moveToFirst())
//        {
//            do {
//                String taskName = cursor.getString(cursor.getColumnIndex(databaseHelper.COL_UP_TASK_NAME));
//                String typeName = cursor.getString(cursor.getColumnIndex(databaseHelper.COL_UP_TYPE_NAME));
//                int status = cursor.getInt(cursor.getColumnIndex(databaseHelper.COL_UP_STATUS));
//                int typeId = cursor.getInt(cursor.getColumnIndex(databaseHelper.COL_UP_TYPE_ID));
//                int id = cursor.getInt(cursor.getColumnIndex(databaseHelper.COL_UP_NAME_ID));
//
//                RvTwoModel rvTwoModel = new RvTwoModel(id, taskName, status, typeName, typeId);
//
//                arrayList.add(rvTwoModel);
//
//            }
//            while (cursor.moveToNext());
//        }
//        this.close();
//        cursor.close();
//        return arrayList;
//    }


}
