package com.example.sensorco_001;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static String DATABASE_NAME = "sensor_co";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_USER = "users";
    private static final String KEY_ID = "id";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_PASSWORD = "password";

    private static final String CREATE_TABLE_USER = "CREATE TABLE " + TABLE_USER + "(" + KEY_ID +
            "INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_USERNAME + "TEXT, " + KEY_PASSWORD + "TEXT)";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

        Log.d("table", CREATE_TABLE_USER);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_USER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    db.execSQL("DROP TABLE IF EXISTS '" + TABLE_USER +"'");
    onCreate(db);
    }

    public long addUserDetail(String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        // Creating content values
        ContentValues values = new ContentValues();
        values.put(KEY_USERNAME, username);
        values.put(KEY_PASSWORD, password);
        // insert row in students table
        long insert = db.insert(TABLE_USER, null, values);

        return insert;
    }
    public int updateUser(int id, String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();

        // Creating content values
        ContentValues values = new ContentValues();
        values.put(KEY_USERNAME, username);
        values.put(KEY_PASSWORD, password);
        // update row in students table base on students.is value
        return db.update(TABLE_USER, values, KEY_ID + " = ?",
                new String[]{String.valueOf(id)});
    }

    public void deleteUSer(int id) {
        // delete row in students table based on id
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_USER, KEY_ID + " = ?",
                new String[]{String.valueOf(id)});
    }
}
