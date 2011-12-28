package be.baes.hanselMinutesPlayer.dal.sqliteandroid;

import android.app.Application;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import be.baes.hanselMinutesPlayer.Constants;
import be.baes.hanselMinutesPlayer.dal.PodCastAdapter;
import be.baes.hanselMinutesPlayer.model.PodCast;
import com.google.inject.Inject;

import java.util.ArrayList;
import java.util.List;

public class PodCastSQLiteAdapter implements PodCastAdapter {
    SQLiteHelper dbHelper;
    @Inject Application context;
    private SQLiteDatabase db;

    public PodCastSQLiteAdapter()
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

   @Override
    public synchronized List<PodCast> getAllItems() {
       Log.i(Constants.LOG_ID, "Getting all items.");
       return getAllItems(null,null);
    }

    @Override
    public synchronized boolean updatePodCast(PodCast podCast) {
        Log.i(Constants.LOG_ID,"Updating podcast");
        open();
        ContentValues updateValues = createContentValues(podCast);
        String[] whereArgs = {podCast.getLink()};
        boolean result = db.update(Constants.TABLE_PODCASTS, updateValues, Constants.PODCASTS_COLUMN_LINK + "=?",whereArgs )>0;
        dbHelper.close();
        return result;
    }

    @Override
    public synchronized long insertPodCast(PodCast podCast) {
        Log.i(Constants.LOG_ID,"Creating podcast");
        open();
        ContentValues initValues = createContentValues(podCast);
        long result = db.insert(Constants.TABLE_PODCASTS, null, initValues);
        dbHelper.close();
        return result;
    }

    @Override
    public synchronized boolean deleteAll() {
        Log.i(Constants.LOG_ID,"Delete all podcasts");
        open();
        boolean result = db.delete(Constants.TABLE_PODCASTS, null, null)>0;
        dbHelper.close();
        return result;
    }

    @Override
    public synchronized int numberOfPodcasts() {
        int result;
        Log.i(Constants.LOG_ID, "Count all podcasts");
        open();
        Cursor c = db.rawQuery(String.format("Select count(*) from %s", Constants.TABLE_PODCASTS), null);
        c.moveToFirst();
        result = c.getInt(0);
        dbHelper.close();
        return result;
    }

    @Override
    public synchronized List<PodCast> getAllItems(Integer pageFrom, Integer pageTo) {
        List<PodCast> result = null;
        Log.i(Constants.LOG_ID,"Fetching all podcasts");
        open();
        String limit = "";
        if(pageFrom!=null && pageTo!=null) 
            limit = pageFrom.toString() + ", " + pageTo.toString();
        Cursor cursor = db.query(Constants.TABLE_PODCASTS, new String[]{Constants.PODCASTS_COLUMN_TITLE, Constants.PODCASTS_COLUMN_PUBDATE, Constants.PODCASTS_COLUMN_LINK, Constants.PODCASTS_COLUMN_MP3LINK, Constants.PODCASTS_COLUMN_DESCRIPTION}, null, null, null, null, "substr(" + Constants.PODCASTS_COLUMN_MP3LINK + ",-22) DESC",limit);
        if(cursor != null)
        {
            result = new ArrayList<PodCast>();
            while (cursor.moveToNext())
            {
                PodCast podCast = new PodCast(cursor.getString(0),cursor.getString(1),cursor.getString(2), cursor.getString(3), cursor.getString(4));
                result.add(podCast);
            }
            cursor.close();
        }
        dbHelper.close();
        return result;
    }

    private ContentValues createContentValues(PodCast podCast) {
        ContentValues values = new ContentValues();
        values.put(Constants.PODCASTS_COLUMN_TITLE, podCast.getTitle());
        values.put(Constants.PODCASTS_COLUMN_LINK, podCast.getLink());
        values.put(Constants.PODCASTS_COLUMN_MP3LINK, podCast.getMP3Link());
        values.put(Constants.PODCASTS_COLUMN_PUBDATE, podCast.getPubDate());
        values.put(Constants.PODCASTS_COLUMN_DESCRIPTION, podCast.getDescription());
        return values;
    }
}
