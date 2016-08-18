package modulo5.ddam.markmota.tk.space.sql;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

/**
 * Created by markmota on 8/18/16.
 */
public class MySqliteHelper  extends SQLiteOpenHelper {
    private final static String LOG_TAG ="SQLiteHelper_log";

    private final static String DATABASE_NAME ="apps_database";
    private final static int DATABASE_VERSION=1;
    public static final String APP_TABLE_NAME ="img_favorites";
    public static final String COLUMN_ID = BaseColumns._ID;
    public static final String APP_COLUMN_IMG = "image";
    public static final String APP_COLUMN_DESC = "description";
    public static final String APP_COLUMN_DATE = "date";
    public static final String APP_COLUMN_TITLE = "title";
    public static final String APP_COLUMN_CREATION = "creation";


    private static final String APP_CREATE_TABLE ="create table "+APP_TABLE_NAME+
            "("+COLUMN_ID+" integer primary key autoincrement,"+
            APP_COLUMN_IMG+" text not null,"+  // Url to the image
            APP_COLUMN_DESC+ " text  null,"+ // Image description could me null
            APP_COLUMN_TITLE+ " text not null,"+  // Image title
            APP_COLUMN_DATE+ " text not null,"+  // Image date
            APP_COLUMN_CREATION+ " text not null)";  // Timestamp of the reg creation


    private static final String APP_DROP_TABLE ="drop table "+APP_TABLE_NAME;


    public MySqliteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(APP_CREATE_TABLE);
            Log.d(LOG_TAG,"Success  creating table for the first time");
        }catch (SQLException e){
            Log.d(LOG_TAG,"Error creating table for the first time : " + e);
        }


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // We are tasting, so we can drop and create all the tables in the database, losing all the data stored.
        // This has to change if we have distributed versions of the app
        try {
            db.execSQL(APP_DROP_TABLE);
            db.execSQL(APP_CREATE_TABLE);
            Log.d(LOG_TAG,"Success drooping and creating table");
        }catch (SQLException e ){
            Log.d(LOG_TAG,"Error drooping or creating table : " + e);
        }

    }
}
