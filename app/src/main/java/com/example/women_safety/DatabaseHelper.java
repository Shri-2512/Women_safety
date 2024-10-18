package com.example.women_safety;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper
{
    private static final String Database_name="WomenSafetyDB";
    private static final int Database_Version=1;
    private static final String USERS_LOGIN = "users_login";
    private static final String USERS_SIGNUP = "users_signup";
    // private static final String TABLE_SAFETY_ALERTS = "alert_control";

    public DatabaseHelper(Context context) {
        super(context, Database_name, null, Database_Version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create users_login table
        String CREATE_USERS_LOGIN_TABLE = "CREATE TABLE userlogin (user_id INTEGER PRIMARY KEY AUTOINCREMENT, email TEXT, password TEXT)";
        db.execSQL(CREATE_USERS_LOGIN_TABLE);

        // Create users_signup table
        String CREATE_USERS_SIGNUP_TABLE = "CREATE TABLE usersignup (user_id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, email TEXT, password TEXT)";
        db.execSQL(CREATE_USERS_SIGNUP_TABLE);

       /* String CREATE_ALERT_CONTROL_TABLE = "CREATE TABLE " + TABLE_SAFETY_ALERTS + " (alert_id INTEGER PRIMARY KEY AUTOINCREMENT, alert_type TEXT, alert_description TEXT, date DATETIME DEFAULT CURRENT_TIMESTAMP)";
        db.execSQL(CREATE_ALERT_CONTROL_TABLE);*/
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS userlogin");
        db.execSQL("DROP TABLE IF EXISTS usersignup");
        //db.execSQL("DROP TABLE IF EXISTS " + TABLE_SAFETY_ALERTS);

        onCreate((db));

    }
    // Function to insert data into users_signup table
    public boolean insertUserData(String name, String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();  // Open the database for writing
        ContentValues contentValues = new ContentValues();  // Create an object to hold key-value pairs

        // Add values to ContentValues
        contentValues.put("name", name);
        contentValues.put("email", email);
        contentValues.put("password", password);

        // Insert the values into the users_signup table
        long result = db.insert("usersignup", null, contentValues);

        // Check if the insert was successful; -1 means an error occurred
        return result !=-1;
    }

    // Method to check if login credentials are valid
    public boolean checkLogin(String email, String password) {
        SQLiteDatabase db = this.getReadableDatabase();  // Open the database for reading

        // Query the users_signup table to check for a matching email and password
        Cursor cursor = db.rawQuery("SELECT * FROM usersignup WHERE email = ? AND password = ?", new String[]{email, password});

        // Check if any matching records exist
        if (cursor.getCount() > 0) {
            return true;  // Login successful
        }
        else {
            return false;  // Login failed
            //
        }

    }

}
