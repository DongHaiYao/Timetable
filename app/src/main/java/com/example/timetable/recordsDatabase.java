package com.example.timetable;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class recordsDatabase extends SQLiteOpenHelper {
    static final String TABLE_NAME = "recorddatas";
    static final String NAME="courseName";
    static final String EXPECT="expctTime";
    static final String ACTUL="actulTime";
    static final String DATA="data";
    static final String ID="_id";
    public recordsDatabase(Context context) {
        super(context, "recordsDatas.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+TABLE_NAME+"("+ID +" INTEGER PRIMARY KEY AUTOINCREMENT,"+NAME+
                " TEXT NOT NULL, "+EXPECT+" TEXT NOT NULL, "+ACTUL+" TEXT NOT NULL, "+DATA+" TEXT NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}