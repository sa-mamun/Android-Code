package demo.com.easylist;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    Context context;

    public static final String DATABASE_NAME = "easyList.db";
    public static final int DATABASE_VERSION = 11;

    public static final String TASK_TABLE = "task_table";
    public static final String STATE_TABLE = "state_table";

    public static final String TABLE_TYPE = "table_type";
    public static final String TABLE_NAME = "table_name";

    public static final String COL_ID = "_id";
    public static final String COL_TASK_NAME = "taskName";
    public static final String COL_TASK_TYPE = "taskType";
    public static final String COL_TASK_TIME = "taskTime";

    public static final String COL_STATE_ID = "stateId";
    public static final String COL_STATE_CHECK = "stateCheck";
    public static final String COL_TASK_ID = "taskId";

    public static final String COL_UP_ID = "up_id";
    public static final String COL_UP_TASK_TYPE = "taskTypeUp";
    public static final String COL_UP_TASK_TIME = "taskTimeUp";

    public static final String COL_UP_NAME_ID = "nameId";
    public static final String COL_UP_TASK_NAME = "taskNameUp";
    public static final String COL_UP_STATUS = "status";
    public static final String COL_UP_TYPE_NAME = "TypeNameUp";
    public static final String COL_UP_TYPE_ID = "typeId";

    public static final String  CREATE_TABLE = "create table "+TASK_TABLE+" ( "+COL_ID+" INTEGER PRIMARY KEY AUTOINCREMENT," +
            " "+COL_TASK_NAME+" TEXT, "+COL_TASK_TYPE+" TEXT, "+COL_TASK_TIME+" TEXT"+" )";

    public static final String CREATE_TABLE_TWO = "create table "+STATE_TABLE+" ( "+COL_STATE_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "
            +COL_STATE_CHECK+" BOOL, "+COL_TASK_ID+" INTEGER, "+"FOREIGN KEY ("+COL_TASK_ID+") REFERENCES "+TASK_TABLE+"("+COL_ID+"))";

    public static final String  CREATE_UP_TABLE = "create table "+TABLE_TYPE+" ( "+COL_UP_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "
            +COL_UP_TASK_TYPE+" TEXT, "+COL_UP_TASK_TIME+" TEXT "+" )";

    public static final String  CREATE_UP_TABLE_TWO = "create table "+TABLE_NAME+" ( "+COL_UP_NAME_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "
            +COL_UP_TASK_NAME+" TEXT, "+COL_UP_STATUS+" INTEGER, "+COL_UP_TYPE_NAME+" TEXT, "+COL_UP_TYPE_ID+" INTEGER, "+"FOREIGN KEY ("+COL_UP_TYPE_ID+") REFERENCES "
            +TABLE_TYPE+"("+COL_UP_ID+"))";


    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
        db.execSQL(CREATE_TABLE_TWO);
        db.execSQL(CREATE_UP_TABLE);
        db.execSQL(CREATE_UP_TABLE_TWO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + TASK_TABLE);
        db.execSQL("drop table if exists " + STATE_TABLE);
        db.execSQL("drop table if exists " + TABLE_TYPE);
        db.execSQL("drop table if exists " + TABLE_NAME);
        this.onCreate(db);
    }
}
