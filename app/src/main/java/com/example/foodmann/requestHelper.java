package com.example.foodmann;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class requestHelper extends SQLiteOpenHelper {
    public static final String database="Request.db";
    public static final String table="Request";
    public static final String col1="name";
    public static final String col2="address";
    public static final String col3="phone";
    public static final String col4="total";


    public requestHelper(Context context) {
        super(context,database, null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table Request(name TEXT,address TEXT,phone TEXT,total TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Request");
        onCreate(db);
    }
    public boolean insertData(String name,String address,String phone,String total){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(col1,name);
        cv.put(col2,address);
        cv.put(col3,phone);
        cv.put(col4,total);
        long result=db.insert(table,null,cv);
        if(result==-1)
            return false;
        else
            return true;
    }
    public Cursor allData(){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res=db.rawQuery("select * from Request",null);
        return res;
    }

    public void cleanData()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(table, null, null);
    }
}
