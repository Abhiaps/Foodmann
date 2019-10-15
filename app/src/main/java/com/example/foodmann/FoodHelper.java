package com.example.foodmann;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class FoodHelper extends SQLiteOpenHelper {
    public static final String database="Food.db";
    public static final String table="Food";
    public static final String col1="name";
    public static final String col2="link";
    public static final String col3="menu";
    public static final String col4="price";
    public static final String col5="discount";
    public static final String col6="discription";

    public FoodHelper(Context context) {
        super(context,database, null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table Food(name TEXT,link TEXT,menu TEXT,price TEXT,discount TEXT,discription TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Food");
        onCreate(db);
    }
    public boolean insertData(String name,String link,String menu,String price,String discount,String discription){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(col1,name);
        cv.put(col2,link);
        cv.put(col3,menu);
        cv.put(col4,price);
        cv.put(col5,discount);
        cv.put(col6,discription);
        long result=db.insert(table,null,cv);
        if(result==-1)
            return false;
        else
            return true;
    }
    public Cursor allData(){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res=db.rawQuery("select * from Food",null);
        return res;
    }

    public void cleanData()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(table, null, null);
    }
}
