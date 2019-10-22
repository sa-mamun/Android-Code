package demo2.com.sqlitefirst;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;


public class StudentDatabaseSource {

    StudentDatabaseHelper studentDatabaseHelper;
    SQLiteDatabase sqLiteDatabase;
    StudentModel studentModel;

    public StudentDatabaseSource(Context context)
    {
        studentDatabaseHelper = new StudentDatabaseHelper(context);
    }

    public void open()
    {
        sqLiteDatabase = studentDatabaseHelper.getWritableDatabase();
    }

    public void close()
    {
        studentDatabaseHelper.close();
    }

    public boolean addStudent(StudentModel studentModel)
    {
        this.open();

        ContentValues contentValues = new ContentValues();
        contentValues.put(studentDatabaseHelper.COL_NAME, studentModel.getName());
        contentValues.put(studentDatabaseHelper.COL_AGE, studentModel.getAge());
        contentValues.put(studentDatabaseHelper.COL_ADDRESS, studentModel.getAddress());

        long insertedRow = sqLiteDatabase.insert(studentDatabaseHelper.STUDENT_TABLE, null, contentValues);
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


    public boolean updateStudent(StudentModel studentModel)
    {
        this.open();

        ContentValues contentValues = new ContentValues();
        contentValues.put(studentDatabaseHelper.COL_NAME, studentModel.getName());
        contentValues.put(studentDatabaseHelper.COL_AGE, studentModel.getAge());
        contentValues.put(studentDatabaseHelper.COL_ADDRESS, studentModel.getAddress());

        int updatedRow = sqLiteDatabase.update(studentDatabaseHelper.STUDENT_TABLE, contentValues, studentDatabaseHelper.COL_ID + " =?", new String[]{String.valueOf(studentModel.getId())});

        this.close();

        if (updatedRow > 0)
        {
            return true;
        }
        else
        {
            return false;
        }

    }


    public ArrayList<StudentModel> getAllStudent()
    {
        this.open();

        ArrayList<StudentModel> arrayList = new ArrayList<>();

        Cursor cursor = sqLiteDatabase.query(StudentDatabaseHelper.STUDENT_TABLE, null, null, null, null, null, null, null);

        if (cursor.moveToFirst())
        {
            do {
                String name = cursor.getString(cursor.getColumnIndex(studentDatabaseHelper.COL_NAME));
                int age = cursor.getInt(cursor.getColumnIndex(studentDatabaseHelper.COL_AGE));
                String address = cursor.getString(cursor.getColumnIndex(studentDatabaseHelper.COL_ADDRESS));
                int id = cursor.getInt(cursor.getColumnIndex(studentDatabaseHelper.COL_ID));

                StudentModel studentModel = new StudentModel(id, name, age, address);

                arrayList.add(studentModel);

            }
            while (cursor.moveToNext());
        }
        this.close();
        cursor.close();
        return arrayList;
    }

    public boolean deleteStudent(StudentModel studentModel)
    {
        this.open();

        int deletedRow = sqLiteDatabase.delete(studentDatabaseHelper.STUDENT_TABLE, studentDatabaseHelper.COL_ID+ " =?", new String[]{String.valueOf(studentModel.getId())});

        this.close();

        if (deletedRow > 0)
        {
            return true;
        }
        else {
            return false;
        }
    }



}
