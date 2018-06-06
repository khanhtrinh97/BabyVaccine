package com.example.vuongphusanhhoangthien.babyvaccine;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class MyDatabase extends SQLiteOpenHelper {
    private static final String DATABAS_NAME = "alarm_manager";
    private static final int DATABAS_VERSION = 1;
    private static final String TABLE_NAME = "alarm";
    private static final String ALARM_ID = "alarm_id";
    private static final String ALARM_NAME = "alarm_name";
    private static final String ALARM_TIME = "alarm_time";

    public MyDatabase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABAS_NAME, null, DATABAS_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + ALARM_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + ALARM_NAME + "TEXT, "
                + ALARM_TIME + "TEXT )";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void addAlarm(Alarm alarm) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ALARM_NAME, alarm.getAlarm_name());
        values.put(ALARM_TIME, alarm.getAlarm_time());
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public List<Alarm> getAllAlarm() {
        List<Alarm> alarmList = new ArrayList<Alarm>();
        String selectQuery = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Alarm alarm = new Alarm();
                alarm.setAlarm_name(cursor.getString(0));
                alarm.setAlarm_time(cursor.getString(1));
                alarmList.add(alarm);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return alarmList;

    }
}

