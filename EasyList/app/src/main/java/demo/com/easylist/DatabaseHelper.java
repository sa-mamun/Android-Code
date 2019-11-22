package demo.com.easylist;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    Context context;

    public static final String DATABASE_NAME = "easyList.db";
    public static final int DATABASE_VERSION = 2;

    public static final String TASK_TABLE = "task_table";

    public static final String COL_ID = "_id";
    public static final String COL_TASK_NAME = "taskName";
    public static final String COL_TASK_TYPE = "taskType";
    public static final String COL_TASK_TIME = "taskTime";

    public static final String  CREATE_TABLE = "create table "+TASK_TABLE+" ( "+COL_ID+" INTEGER PRIMARY KEY AUTOINCREMENT," +
            " "+COL_TASK_NAME+" TEXT, "+COL_TASK_TYPE+" TEXT, "+COL_TASK_TIME+" TEXT"+" )";


    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + TASK_TABLE);
        this.onCreate(db);
    }
}
