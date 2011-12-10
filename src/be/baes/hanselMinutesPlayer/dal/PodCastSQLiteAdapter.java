package be.baes.hanselMinutesPlayer.dal;

import android.app.Application;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import be.baes.hanselMinutesPlayer.model.PodCast;
import com.google.inject.Inject;

import java.util.ArrayList;
import java.util.List;

public class PodCastSQLiteAdapter implements PodCastAdapter {
    @Inject SQLiteHelper dbHelper;
    @Inject Application context;
    private SQLiteDatabase db;
    public static final String KEY_LINK= "link";
    public static final String KEY_TITLE = "title";
    public static final String KEY_PUBDATE = "pubdate";
    public static final String KEY_MP3LINK = "mp3link";
    public static final String DATABASE_TABLE = "podcasts";

    public PodCastSQLiteAdapter()
    {
        Log.i("cbaes", "New instance of PodCastSQLiteAdapter");
        open();
    }

    private void open() throws SQLException
    {
        if(context == null) Log.i("cbaes", "activity is still null");
        this.dbHelper = new SQLiteHelper(context);
        if(this.dbHelper == null) Log.i("cbaes", "dbHelper is still null");
        Log.i("cbaes", "getWritableDatabase");
        try
        {
            db = dbHelper.getWritableDatabase();
        }
        catch (Exception ex)
        {
            Log.i("cbaes", "getWritableDatabase error: " + ex.getMessage());
        }
    }

    @Override
    protected void finalize() throws Throwable
    {
        Log.i("cbaes", "finalizing PodCastSQLiteAdapter");
        try
        {
            dbHelper.close();        // close open files
        }
        finally
        {
            super.finalize();
        }
    }

   @Override
    public List<PodCast> getAllItems() {
        return getAllItems(null,null);
    }

    @Override
    public boolean updatePodCast(PodCast podCast) {
        Log.i("cbaes","Updating podcast");
        open();
        ContentValues updateValues = createContentValues(podCast);
        String[] whereArgs = {podCast.getLink()};
        boolean result = db.update(DATABASE_TABLE, updateValues, KEY_LINK + "=?",whereArgs )>0;
        dbHelper.close();
        return result;
    }

    @Override
    public long insertPodCast(PodCast podCast) {
        Log.i("cbaes","Creating podcast");
        open();
        ContentValues initValues = createContentValues(podCast);
        long result = db.insert(DATABASE_TABLE, null, initValues);
        dbHelper.close();
        return result;
    }

    @Override
    public boolean deleteAll() {
        Log.i("cbaes","Delete all podcasts");
        open();
        boolean result = db.delete(DATABASE_TABLE, null, null)>0;
        dbHelper.close();
        return result;
    }

    @Override
    public int numberOfPodcasts() {
        int result = 0;
        Log.i("cbaes", "Count all podcasts");
        open();
        Cursor c = db.rawQuery("Select count(*) from " + DATABASE_TABLE, null);
        c.moveToFirst();
        result = c.getInt(0);
        dbHelper.close();
        return result;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<PodCast> getAllItems(Integer pageFrom, Integer pageTo) {
        List<PodCast> result = null;
        Log.i("cbaes","Fetching all podcasts");
        open();
        String limit = "";
        if(pageFrom!=null && pageTo!=null) 
            limit = pageFrom.toString() + ", " + pageTo.toString();
        Cursor cursor = db.query(DATABASE_TABLE, new String[]{KEY_TITLE, KEY_PUBDATE, KEY_LINK, KEY_MP3LINK}, null, null, null, null, "cast(substr(" + KEY_LINK + ",49) as integer)" + " DESC",limit);
        if(cursor != null)
        {
            result = new ArrayList<PodCast>();
            while (cursor.moveToNext())
            {
                result.add(new PodCast(cursor.getString(0),cursor.getString(1),cursor.getString(2), cursor.getString(3)));
            }
            cursor.close();
        }
        dbHelper.close();
        return result;
    }

    private ContentValues createContentValues(PodCast podCast) {
        ContentValues values = new ContentValues();
        values.put(KEY_TITLE, podCast.getTitle());
        values.put(KEY_LINK, podCast.getLink());
        values.put(KEY_MP3LINK, podCast.getMP3Link());
        values.put(KEY_PUBDATE, podCast.getPubDate());
        return values;
    }
}
