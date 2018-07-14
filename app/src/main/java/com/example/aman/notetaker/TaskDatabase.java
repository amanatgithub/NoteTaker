package com.example.aman.notetaker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class TaskDatabase extends SQLiteOpenHelper {
    public TaskDatabase(Context context) {
        super(context, "data-db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String s="CREATE TABLE data_table (count INTEGER PRIMARY KEY, head TEXT, data TEXT);";
        sqLiteDatabase.execSQL(s);
        }
  void insert (Data data)
    {
        ContentValues contentValues=new ContentValues();
        contentValues.put("count",data.getNumb());
        contentValues.put("head",data.getHead());
        contentValues.put("data",data.getData());
        getWritableDatabase().insert("data_table",null,contentValues);

     }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public ArrayList<Data> getAllDatas() {
        ArrayList<Data> arrayList=new ArrayList<>();
        Cursor cursor=getReadableDatabase().query(
                "data_table",null,null,null,null,null,null);
        while(cursor.moveToNext())
        { String count=cursor.getString(cursor.getColumnIndex("count"));
        String head=cursor.getString(cursor.getColumnIndex("head"));
         String data=cursor.getString(cursor.getColumnIndex("data"));
         arrayList.add(new Data(count,head,data));
         }
         cursor.close();
        return  arrayList;


    }


}
