package com.example.pcbuilder;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQL extends SQLiteOpenHelper
{
    public static final String DB_NAME = "MyCart";

    public SQL(Context context)
    {
        super(context, DB_NAME, null, 3);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL("CREATE TABLE CartItem (Id VARCHAR PRIMARY KEY, " +
                "Name TEXT, " +
                "Type TEXT, " +
                "Factor TEXT, " +
                "Platform TEXT, " +
                "Price TEXT, " +
                "Specs TEXT, " +
                "Image TEXT, " +
                "Power TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS CartItem");
        onCreate(db);
    }

    public String insert(String id, String name, String type, String factor, String platform, String price, String specs, String image, String power)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("Id", id);
        values.put("Name", name);
        values.put("Type", type);
        values.put("Factor", factor);
        values.put("Platform", platform);
        values.put("Price", price);
        values.put("Specs", specs);
        values.put("Image", image);
        values.put("Power", power);
        long result = db.insert("CartItem", null, values);
        if (result==-1)
            return "Failed";
        else
            return "Item Added";
    }

    public Cursor Display()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM CartItem" , null);
        return cursor;
    }

    public Cursor Select(String type)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM CartItem WHERE Type= '"+ type +"'" , null);
        return cursor;
    }

    public Integer Delete(String name)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("CartItem", "Name = ?", new String[]{name});
    }

    public Cursor Find(String type)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM CartItem WHERE Type= '"+ type +"'" , null);
        return cursor;
    }
}

