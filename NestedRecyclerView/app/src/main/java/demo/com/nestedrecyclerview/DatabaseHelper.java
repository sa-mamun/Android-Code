package demo.com.nestedrecyclerview;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    Context context;

    public static final String DATABASE_NAME = "test.db";
    public static final int DATABASE_VERSION = 5;

    public static final String TABLE_TYPE = "table_type";
    public static final String TABLE_NAME = "table_name";


    public static final String COL_ID = "_id";
    public static final String COL_TASK_TYPE = "taskType";
    public static final String COL_TASK_TIME = "taskTime";

    public static final String COL_NAME_ID = "nameId";
    public static final String COL_TASK_NAME = "taskName";
    public static final String COL_STATUS = "status";
    public static final String COL_TYPE_ID = "typeId";

    public static final String  CREATE_TABLE = "create table "+TABLE_TYPE+" ( "+COL_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, " +COL_TASK_TYPE+" TEXT, "+COL_TASK_TIME+" TEXT "+" )";

    public static final String  CREATE_TABLE_TWO = "create table "+TABLE_NAME+" ( "+COL_NAME_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, " +COL_TASK_NAME+" TEXT, "+COL_STATUS+" INTEGER, "+COL_TYPE_ID+" INTEGER, "+"FOREIGN KEY ("+COL_TYPE_ID+") REFERENCES "+TABLE_TYPE+"("+COL_ID+"))";


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
        db.execSQL("drop table if exists " + TABLE_TYPE);
        db.execSQL("drop table if exists " + TABLE_NAME);
        this.onCreate(db);
    }
}
