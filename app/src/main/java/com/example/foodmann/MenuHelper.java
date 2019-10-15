package com.example.foodmann;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MenuHelper extends SQLiteOpenHelper {
    public static final String database="Menu.db";
    public static final String table="Menu";
    public static final String col1="name";
    public static final String col2="link";


    public MenuHelper(Context context) {
        super(context,database, null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table Menu(name TEXT,link TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Menu");
        onCreate(db);
    }
    public boolean insertData(String name,String link){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(col1,name);
        cv.put(col2,link);
        long result=db.insert(table,null,cv);
        if(result==-1)
            return false;
        else
            return true;
    }
    public Cursor allData(){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res=db.rawQuery("select * from Menu",null);
        return res;
    }

    public void cleanData()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(table, null, null);
    }
}
