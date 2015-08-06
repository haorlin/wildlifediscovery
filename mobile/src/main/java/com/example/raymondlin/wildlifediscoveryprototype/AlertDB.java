//package com.example.raymondlin.wildlifediscoveryprototype;
//
//import java.util.ArrayList;
//import android.content.ContentValues;
//import android.content.Context;
//import android.database.Cursor;
//import android.database.DatabaseUtils;
//import android.database.sqlite.SQLiteOpenHelper;
//import android.database.sqlite.SQLiteDatabase;
//
//public class AlertDB extends SQLiteOpenHelper {
//
//    public static final String DATABASE_NAME = "Alerts.db";
//    public static final String CONTACTS_TABLE_NAME = "alerts";
//    public static final String CONTACTS_COLUMN_ID = "id";
//    public static final String CONTACTS_COLUMN_ANIMAL = "animal";
//    public static final String CONTACTS_COLUMN_NOTE = "note";
//    public static final String CONTACTS_COLUMN_RADIUS = "radius";
//
//    public AlertDB(Context context)
//    {
//        super(context, DATABASE_NAME , null, 1);
//    }
//
//    @Override
//    public void onCreate(SQLiteDatabase db) {
//        // TODO Auto-generated method stub
//        db.execSQL("create table alerts ("
//                + "id integer primary key,"
//                + "animal text,"
//                + "note text,"
//                + "radius integer" + ");");
//    }
//
//    @Override
//    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        // TODO Auto-generated method stub
//        db.execSQL("DROP TABLE IF EXISTS alerts");
//        onCreate(db);
//    }
//
//    public boolean insertAlert (String animal, String note, int radius)
//    {
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put("animal", animal);
//        contentValues.put("note", note);
//        contentValues.put("radius", radius);
//        db.insert("alerts", null, contentValues);
//        return true;
//    }
//
//    public Cursor getData(int id){
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor res =  db.rawQuery( "select * from alerts where id="+id+"", null );
//        return res;
//    }
//
//    public int numberOfRows(){
//        SQLiteDatabase db = this.getReadableDatabase();
//        int numRows = (int) DatabaseUtils.queryNumEntries(db, CONTACTS_TABLE_NAME);
//        return numRows;
//    }
//
//    public boolean updateAlert (Integer id, String animal, String note, int radius)
//    {
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put("animal", animal);
//        contentValues.put("note", note);
//        contentValues.put("radius", radius);
//        db.update("alerts", contentValues, "id = ? ", new String[] { Integer.toString(id) } );
//        return true;
//    }
//
//    public Integer deleteAlert (Integer id)
//    {
//        SQLiteDatabase db = this.getWritableDatabase();
//        return db.delete("alerts",
//                "id = ? ",
//                new String[] { Integer.toString(id) });
//    }
//
//    public ArrayList<String> getAllAnimals()
//    {
//        ArrayList<String> array_list = new ArrayList<String>();
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor res =  db.rawQuery( "select * from alerts", null );
//        res.moveToFirst();
//
//        while(res.isAfterLast() == false){
//            array_list.add(res.getString(res.getColumnIndex(CONTACTS_COLUMN_ANIMAL)));
//            res.moveToNext();
//        }
//        return array_list;
//    }
//
//    public ArrayList<Integer> getAllRadius()
//    {
//        ArrayList<Integer> array_list = new ArrayList<Integer>();
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor res =  db.rawQuery( "select * from alerts", null );
//        res.moveToFirst();
//
//        while(res.isAfterLast() == false){
//            array_list.add(Integer.parseInt(res.getString(res.getColumnIndex(CONTACTS_COLUMN_RADIUS))));
//            res.moveToNext();
//        }
//        return array_list;
//    }
//
//    public ArrayList<String> getAllNotes()
//    {
//        ArrayList<String> array_list = new ArrayList<String>();
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor res =  db.rawQuery( "select * from alerts", null );
//        res.moveToFirst();
//
//        while(res.isAfterLast() == false){
//            array_list.add(res.getString(res.getColumnIndex(CONTACTS_COLUMN_NOTE)));
//            res.moveToNext();
//        }
//        return array_list;
//    }
//
//    public ArrayList<Integer> getAllID()
//    {
//        ArrayList<Integer> array_list = new ArrayList<Integer>();
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor res =  db.rawQuery( "select * from alerts", null );
//        res.moveToFirst();
//
//        while(res.isAfterLast() == false){
//            array_list.add(Integer.parseInt(res.getString(res.getColumnIndex(CONTACTS_COLUMN_ID))));
//            res.moveToNext();
//        }
//        return array_list;
//    }
//}

package com.example.raymondlin.wildlifediscoveryprototype;

import java.util.ArrayList;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;

public class AlertDB extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Alerts.db";
    public static final String CONTACTS_TABLE_NAME = "alerts";
    public static final String CONTACTS_COLUMN_ID = "id";
    public static final String CONTACTS_COLUMN_ANIMAL = "animal";
    public static final String CONTACTS_COLUMN_NOTE = "note";
    public static final String CONTACTS_COLUMN_RADIUS = "radius";

    public AlertDB(Context context)
    {
        super(context, DATABASE_NAME , null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL("create table alerts ("
                + "id integer primary key autoincrement,"
                + "animal text,"
                + "note text,"
                + "radius integer" + ");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS alerts");
        onCreate(db);
    }

    public boolean insertAlert (String animal, String note, int radius)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("animal", animal);
        contentValues.put("note", note);
        contentValues.put("radius", radius);
        db.insert("alerts", null, contentValues);
        return true;
    }

    public Integer deleteAlert (Integer id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("alerts",
                "id = ? ",
                new String[] { Integer.toString(id) });
    }

    public ArrayList<String> getAllAnimals()
    {
        ArrayList<String> array_list = new ArrayList<String>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from alerts", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_list.add(res.getString(res.getColumnIndex(CONTACTS_COLUMN_ANIMAL)));
            res.moveToNext();
        }
        return array_list;
    }

    public ArrayList<Integer> getAllRadius()
    {
        ArrayList<Integer> array_list = new ArrayList<Integer>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from alerts", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_list.add(Integer.parseInt(res.getString(res.getColumnIndex(CONTACTS_COLUMN_RADIUS))));
            res.moveToNext();
        }
        return array_list;
    }

    public ArrayList<String> getAllNotes()
    {
        ArrayList<String> array_list = new ArrayList<String>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from alerts", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_list.add(res.getString(res.getColumnIndex(CONTACTS_COLUMN_NOTE)));
            res.moveToNext();
        }
        return array_list;
    }

    public ArrayList<Integer> getAllID()
    {
        ArrayList<Integer> array_list = new ArrayList<Integer>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from alerts", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_list.add(Integer.parseInt(res.getString(res.getColumnIndex(CONTACTS_COLUMN_ID))));
            res.moveToNext();
        }
        return array_list;
    }

    public ArrayList<Integer> findID(String animal_name) {

        ArrayList<Integer> array_list = new ArrayList<Integer>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "SELECT * FROM alerts WHERE animal=" + "\"" + animal_name + "\"", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_list.add(Integer.parseInt(res.getString(res.getColumnIndex(CONTACTS_COLUMN_ID))));
            res.moveToNext();
        }

        return array_list;
    }


}