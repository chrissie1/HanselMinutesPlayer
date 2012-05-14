package be.baes.hanselMinutesPlayer.dal.sqliteandroid;

import android.app.Application;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import be.baes.hanselMinutesPlayer.Constants;
import be.baes.hanselMinutesPlayer.dal.SettingsAdapter;
import be.baes.hanselMinutesPlayer.model.Settings;
import com.google.inject.Inject;

public class SettingsSQLiteAdapter implements SettingsAdapter {
    SQLiteHelper dbHelper;
    @Inject Application context;
    private SQLiteDatabase db;

    public SettingsSQLiteAdapter()
    {
        Log.i(Constants.LOG_ID, "New instance of PodCastSQLiteAdapter");
    }

    private void open() throws SQLException
    {
        if(context == null) Log.i(Constants.LOG_ID, "activity is still null");
        this.dbHelper = new SQLiteHelper(context);
        try
        {
            Log.i(Constants.LOG_ID, "getWritableDatabase");
            db = dbHelper.getWritableDatabase();
        }
        catch (Exception ex)
        {
            Log.e(Constants.LOG_ID, String.format("Error: %s", ex.getMessage()),ex);
            ex.printStackTrace();
        }
    }

    @Override
    protected void finalize() throws Throwable
    {
        Log.i(Constants.LOG_ID, "finalizing PodCastSQLiteAdapter");
        try
        {
            dbHelper.close();
        }
        finally
        {
            super.finalize();
        }
    }

    private ContentValues createContentValues(Settings settings) {
        ContentValues values = new ContentValues();
        values.put(Constants.SETTINGS_COLUMN_ONROAMING, settings.getOnRoaming().toString());
        return values;
    }

    @Override
    public synchronized long updateOrInsertSettings(Settings settings) {
        Log.i(Constants.LOG_ID,"Updating settings");
        open();
        ContentValues initValues = createContentValues(settings);
        db.delete(Constants.TABLE_SETTINGS,null,null);
        long result = db.insert(Constants.TABLE_SETTINGS, null, initValues);
        dbHelper.close();
        return result;
    }

    @Override
    public synchronized Settings getSettings() {
        Settings result = null;
        Log.i(Constants.LOG_ID,"Fetching Settings");
        open();
        Cursor cursor = db.query(Constants.TABLE_SETTINGS, new String[]{Constants.SETTINGS_COLUMN_ONROAMING}, "", null, null, null, "","");
        if(cursor != null)
        {
            result = new Settings();
            cursor.moveToFirst();
            Log.i(Constants.LOG_ID, "Value of onroaming is:" + cursor.getString(0));
            if(cursor.getString(0).trim().equals("false"))
            {
                Log.i(Constants.LOG_ID, "Setting Value of onroaming to false");
                result.setOnRoaming(false);
            }
            else
            {
                Log.i(Constants.LOG_ID, "Setting Value of onroaming to true");
                result.setOnRoaming(true);
            }
            cursor.close();
        }
        dbHelper.close();
        return result;
    }
}
