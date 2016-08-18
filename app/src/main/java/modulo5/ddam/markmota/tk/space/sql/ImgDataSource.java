package modulo5.ddam.markmota.tk.space.sql;

/**
 * Created by markmota on 8/18/16.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import java.util.List;

import modulo5.ddam.markmota.tk.space.model.ModelImg;


/**
 * Created by markmota on 6/27/16.
 */
public class ImgDataSource {
    private final SQLiteDatabase db;

    public ImgDataSource(Context context) {
        MySqliteHelper helper = new MySqliteHelper(context);
        db=helper.getWritableDatabase();
    }
    public boolean saveItem(ModelImg modelImg)
    {


        String image=modelImg.image ;
        String description=modelImg.description;
        String date=modelImg.date ;
        String title= modelImg.title  ;
        String creation= modelImg.creation ;



        // Fist we search if there is an image with the same url in the database
        String[] fields_to_recover = new String[] {MySqliteHelper.COLUMN_ID};
        String[] args = new String[] {image};
        String where= MySqliteHelper.APP_COLUMN_IMG+"=? ";
        Cursor cursor =db.query(MySqliteHelper.APP_TABLE_NAME,fields_to_recover,where,args,null,null,null);
        if (!cursor.moveToNext())
        {
            ContentValues contentValues = new ContentValues();
            contentValues.put(MySqliteHelper.APP_COLUMN_IMG,image);
            contentValues.put(MySqliteHelper.APP_COLUMN_DESC,description);
            contentValues.put(MySqliteHelper.APP_COLUMN_DATE,date);
            contentValues.put(MySqliteHelper.APP_COLUMN_TITLE,title);
            contentValues.put(MySqliteHelper.APP_COLUMN_CREATION,creation);
            db.insert(MySqliteHelper.APP_TABLE_NAME,null,contentValues);
            return true;
        }
        return false;
    }



    public List<ModelImg> getAllItems()
    {
        List<ModelImg> modelItemList = new ArrayList<>();

        Cursor cursor =db.query(MySqliteHelper.APP_TABLE_NAME,null,null,null,null,null,MySqliteHelper.APP_COLUMN_CREATION+" DESC");
        while (cursor.moveToNext())
        {
            int id=cursor.getInt(cursor.getColumnIndexOrThrow(MySqliteHelper.COLUMN_ID));
            String image=cursor.getString(cursor.getColumnIndexOrThrow(MySqliteHelper.APP_COLUMN_IMG));
            String description=cursor.getString(cursor.getColumnIndexOrThrow(MySqliteHelper.APP_COLUMN_DESC));
            String date=cursor.getString(cursor.getColumnIndexOrThrow(MySqliteHelper.APP_COLUMN_DATE));
            String title=cursor.getString(cursor.getColumnIndexOrThrow(MySqliteHelper.APP_COLUMN_TITLE));
            String creation= cursor.getString(cursor.getColumnIndexOrThrow(MySqliteHelper.APP_COLUMN_CREATION));


            ModelImg modelImg = new ModelImg(id,image,description,date,title,creation);

            modelItemList.add(modelImg);
        }

        return modelItemList;
    }

    public ModelImg getInfoItem(String image){

        ModelImg modelImg;

        String[] fields_to_recover = new String[] {
                MySqliteHelper.COLUMN_ID,
                MySqliteHelper.APP_COLUMN_IMG,
                MySqliteHelper.APP_COLUMN_DESC,
                MySqliteHelper.APP_COLUMN_DATE,
                MySqliteHelper.APP_COLUMN_TITLE,
                MySqliteHelper.APP_COLUMN_CREATION
        };
        String[] args = new String[] {String.valueOf(image)};
        String where= MySqliteHelper.APP_COLUMN_CREATION+"=? ";
        Cursor cursor =db.query(MySqliteHelper.APP_TABLE_NAME,fields_to_recover,where,args,null,null,null);
        if (cursor.moveToNext())
        {

            int id=cursor.getInt(cursor.getColumnIndexOrThrow(MySqliteHelper.COLUMN_ID));
            String imageBase=cursor.getString(cursor.getColumnIndexOrThrow(MySqliteHelper.APP_COLUMN_IMG));
            String description=cursor.getString(cursor.getColumnIndexOrThrow(MySqliteHelper.APP_COLUMN_DESC));
            String date=cursor.getString(cursor.getColumnIndexOrThrow(MySqliteHelper.APP_COLUMN_DATE));
            String title=cursor.getString(cursor.getColumnIndexOrThrow(MySqliteHelper.APP_COLUMN_TITLE));
            String creation= cursor.getString(cursor.getColumnIndexOrThrow(MySqliteHelper.APP_COLUMN_CREATION));

            modelImg = new ModelImg(id,imageBase,description,date,title,creation);

            return  modelImg;
        }
        else{
            return null;
        }
    }
    public void deleteItem(String image){
        String[] args = new String[] {String.valueOf(image)};
        String where= MySqliteHelper.APP_COLUMN_IMG+"=? ";
        db.delete(MySqliteHelper.APP_TABLE_NAME,where,args);
    }




}
