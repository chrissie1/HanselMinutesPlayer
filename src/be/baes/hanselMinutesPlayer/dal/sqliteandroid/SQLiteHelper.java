package be.baes.hanselMinutesPlayer.dal.sqliteandroid;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import be.baes.hanselMinutesPlayer.Constants;

public class SQLiteHelper extends SQLiteOpenHelper {

    public SQLiteHelper(Context context) {
        super(context, Constants.DATABASE_NAME, null, Constants.DATABASE_VERSION);
    }

    /* (non-Javadoc)
      * @see be.baes.notes.Dal.DatabaseHelper#onCreate(android.database.sqlite.SQLiteDatabase)
      */
    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i(Constants.LOG_ID, "Creating database");
        db.execSQL(Constants.DATABASE_CREATE);
    }

    /* (non-Javadoc)
      * @see be.baes.notes.Dal.DatabaseHelper#onUpgrade(android.database.sqlite.SQLiteDatabase, int, int)
      */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.i(Constants.LOG_ID,"upgrading database");
        db.execSQL(Constants.DROP_TABLE);
        onCreate(db);
    }

    public SQLiteDatabase getWritableDatabase()
    {
        Log.i(Constants.LOG_ID, "getWritableDatabase");
        return super.getWritableDatabase();
    }

    public void close()
    {
        Log.i(Constants.LOG_ID, "close");
        super.close();
    }
}
