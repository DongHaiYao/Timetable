package com.example.timetable;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class classDatabase extends SQLiteOpenHelper {
    static final String TABLE_NAME = "classdatas";
    static final String CLASSNAME="className";
    static final String ROOMNUM="roomNum";
    static final String CLASSNUM="classNum";
    private static final String ID="_id";
    classDatabase(Context context){
        super(context, "classDatas.db", null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+ TABLE_NAME +"("+ID +" INTEGER PRIMARY KEY AUTOINCREMENT,"
        +CLASSNAME+" TEXT NOT NULL,"+ROOMNUM+" TEXT NOT NULL,"+CLASSNUM+" INTENGER)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}