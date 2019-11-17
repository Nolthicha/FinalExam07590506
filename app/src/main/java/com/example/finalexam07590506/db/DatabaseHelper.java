package com.example.finalexam07590506.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.finalexam07590506.model.User;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "db";

    public static final int DATABASE_VERSION = 1;

    public static final String TABLE_USERS = "users";

    public static final String KEY_ID = "id";

    public static final String KEY_NAME = "name";

    public static final String KEY_USER_NAME = "userName";

    public static final String KEY_PASSWORD = "password";

    public static final String SQL_TABLE_USERS = " CREATE TABLE " + TABLE_USERS
            + " ( "
            + KEY_ID + " INTEGER PRIMARY KEY, "
            + KEY_NAME + " TEXT, "
            + KEY_USER_NAME + " TEXT, "
            + KEY_PASSWORD + " TEXT"
            + " ) ";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL(SQL_TABLE_USERS);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void addUser(User user) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(KEY_NAME, user.name);

        values.put(KEY_USER_NAME, user.userName);

        values.put(KEY_PASSWORD, user.password);

        long todo_id = db.insert(TABLE_USERS, null, values);
    }

    public User Authenticate(User user) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_USERS,
                new String[]{KEY_ID, KEY_NAME, KEY_USER_NAME, KEY_PASSWORD},
                KEY_USER_NAME + "=?",
                new String[]{user.userName},
                null, null, null);

        if (cursor != null && cursor.moveToFirst()&& cursor.getCount()>0) {

            User user1 = new User(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3));

            if (user.password.equalsIgnoreCase(user1.password)) {
                return user1;
            }
        }

        return null;
    }

    public boolean isFullNameExists(String fullName) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_USERS,
                new String[]{KEY_ID, KEY_NAME, KEY_USER_NAME, KEY_PASSWORD},
                KEY_USER_NAME + "=?",
                new String[]{fullName},
                null, null, null);

        if (cursor != null && cursor.moveToFirst()&& cursor.getCount()>0) {

            return true;
        }

        return false;
    }
}