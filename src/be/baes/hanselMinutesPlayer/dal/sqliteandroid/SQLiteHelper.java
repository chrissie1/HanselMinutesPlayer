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
        db.execSQL(Constants.CREATE_TABLE_PODCASTS);
        db.execSQL(Constants.CREATE_TABLE_SETTINGS);
    }

    /* (non-Javadoc)
      * @see be.baes.notes.Dal.DatabaseHelper#onUpgrade(android.database.sqlite.SQLiteDatabase, int, int)
      */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.i(Constants.LOG_ID,"upgrading database");
        if(oldVersion <= 3)
        {
            db.execSQL(Constants.DROP_TABLE_PODCASTS);
            db.execSQL(Constants.CREATE_TABLE_PODCASTS);
        }
        if(oldVersion <= 5)
        {
            db.execSQL(Constants.DROP_TABLE_SETTINGS);
            db.execSQL(Constants.CREATE_TABLE_SETTINGS);
            db.execSQL(Constants.INSERT_SETTINGS);
        }
    }

    public SQLiteDatabase getWritableDatabase()
    {
        Log.i(Constants.LOG_ID, "getWritableDatabase");
        try
        {
            return super.getWritableDatabase();
        }
        catch (Exception e)
        {
            return null;
        }
    }

    public void close()
    {
        Log.i(Constants.LOG_ID, "close");
        super.close();
    }
}
