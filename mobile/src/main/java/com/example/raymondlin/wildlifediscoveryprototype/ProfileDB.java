package com.example.raymondlin.wildlifediscoveryprototype;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

public class ProfileDB extends SQLiteOpenHelper {

    public static abstract class FeedEntry implements BaseColumns {
        public static final String DATABASE_NAME = "profile.db";
        public static final String TABLE_NAME = "entries";
        public static final String COLUMN_NAME_ID = "id";
        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_TIME = "time";
        public static final String COLUMN_NAME_IDENTIFICATION = "identification";
        public static final String COLUMN_NAME_NOTE = "note";
        public static final String COLUMN_NAME_LOCATION = "location";
        public static final String COLUMN_NAME_PHOTO = "photo";
        public static final String COLUMN_NAME_NULLABLE = null;
    }


    public ProfileDB(Context context)
    {
        super(context, FeedEntry.DATABASE_NAME , null, 1);
    }


    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + FeedEntry.TABLE_NAME + " (" +
                    FeedEntry._ID + " INTEGER PRIMARY KEY," +
                    FeedEntry.COLUMN_NAME_NAME + TEXT_TYPE + COMMA_SEP +
                    FeedEntry.COLUMN_NAME_TIME + TEXT_TYPE + COMMA_SEP +
                    FeedEntry.COLUMN_NAME_IDENTIFICATION + TEXT_TYPE + COMMA_SEP +
                    FeedEntry.COLUMN_NAME_NOTE + TEXT_TYPE + COMMA_SEP +
                    FeedEntry.COLUMN_NAME_LOCATION + TEXT_TYPE + COMMA_SEP +
                    FeedEntry.COLUMN_NAME_PHOTO + TEXT_TYPE +
                    " )";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + FeedEntry.TABLE_NAME;

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS entries");
        onCreate(db);
    }

    public boolean insertEntry  (String name, String time, String identification, String note, String location, String photo)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(FeedEntry.COLUMN_NAME_NAME, name);
        values.put(FeedEntry.COLUMN_NAME_TIME, time);
        values.put(FeedEntry.COLUMN_NAME_IDENTIFICATION, identification);
        values.put(FeedEntry.COLUMN_NAME_NOTE, note);
        values.put(FeedEntry.COLUMN_NAME_LOCATION, location);
        values.put(FeedEntry.COLUMN_NAME_PHOTO, photo);
        db.insertWithOnConflict(FeedEntry.TABLE_NAME, FeedEntry.COLUMN_NAME_NULLABLE, values, SQLiteDatabase.CONFLICT_REPLACE);
        return true;
    }

    public Cursor getData(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from entries where id="+id+"", null );
        return res;
    }

    public int numberOfRows(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, FeedEntry.TABLE_NAME);
        return numRows;
    }

    public boolean updateContact (Integer id, String name, String time, String note, String location, String photo)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("photo", photo);
        contentValues.put("time", time);
        contentValues.put("note", note);
        contentValues.put("location", location);
        db.update("entries", contentValues, "id = ? ", new String[] { Integer.toString(id) } );
        return true;
    }

    public Integer deleteContact (Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("entries",
                "id = ? ",
                new String[] { Integer.toString(id) });
    }

    public ArrayList<String> getAllNames() {
        ArrayList<String> arrayList = new ArrayList<String>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from entries", null );
        res.moveToFirst();

        while(!res.isAfterLast()){
            arrayList.add(res.getString(res.getColumnIndex(FeedEntry.COLUMN_NAME_NAME)));
            res.moveToNext();
        }
        return arrayList;
    }

    public ArrayList<String> getAllNotes() {
        ArrayList<String> arrayList = new ArrayList<String>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from entries", null );
        res.moveToFirst();

        while(!res.isAfterLast()){
            arrayList.add(res.getString(res.getColumnIndex(FeedEntry.COLUMN_NAME_NOTE)));
            res.moveToNext();
        }
        return arrayList;
    }

    public ArrayList<String> getAllTimes() {
        ArrayList<String> arrayList = new ArrayList<String>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from entries", null );
        res.moveToFirst();

        while(!res.isAfterLast()){
            arrayList.add(res.getString(res.getColumnIndex(FeedEntry.COLUMN_NAME_TIME)));
            res.moveToNext();
        }
        return arrayList;
    }

    public ArrayList<String> getAllLocations() {
        ArrayList<String> arrayList = new ArrayList<String>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from entries", null );
        res.moveToFirst();

        while(!res.isAfterLast()){
            arrayList.add(res.getString(res.getColumnIndex(FeedEntry.COLUMN_NAME_LOCATION)));
            res.moveToNext();
        }
        return arrayList;
    }

    public ArrayList<String> getAllPhotos() {
        ArrayList<String> arrayList = new ArrayList<String>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from entries", null );
        res.moveToFirst();

        while(!res.isAfterLast()){
            arrayList.add(res.getString(res.getColumnIndex(FeedEntry.COLUMN_NAME_PHOTO)));
            res.moveToNext();
        }
        return arrayList;
    }

    public ArrayList<Integer> getAllIDs() {
        ArrayList<Integer> arrayList = new ArrayList<Integer>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from entries", null );
        res.moveToFirst();

        while(!res.isAfterLast()){
            arrayList.add(res.getInt(res.getColumnIndex(FeedEntry._ID)));
            res.moveToNext();
        }
        return arrayList;
    }

    public ArrayList<String> getAllIdentifications() {
        ArrayList<String> arrayList = new ArrayList<String>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from entries", null );
        res.moveToFirst();

        while(!res.isAfterLast()){
            arrayList.add(res.getString(res.getColumnIndex(FeedEntry.COLUMN_NAME_IDENTIFICATION)));
            res.moveToNext();
        }
        return arrayList;
    }

}