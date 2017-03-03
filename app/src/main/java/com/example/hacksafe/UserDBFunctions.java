package com.example.hacksafe;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import java.util.Calendar;

import static android.content.ContentValues.TAG;


public class UserDBFunctions extends AppCompatActivity {

    public void saveToDB() {
        SQLiteDatabase database = new DatabaseHelper(this).getWritableDatabase();   //New instance of SQL database

        ContentValues values = new ContentValues();
        values.put(DatabaseManager.UserData.TableName, "UserData");     //Insert TableName into right field

        try {
            Calendar calendar = Calendar.getInstance();
            int date = calendar.get(Calendar.DATE);                     //Retrieve date and hour of day and put into
            int time = calendar.get(Calendar.HOUR_OF_DAY);              //the right field. Soon to be moved to separate
            values.put(DatabaseManager.UserData.LaunchDate, date);      //module
            values.put(DatabaseManager.UserData.LaunchTime, time);
        }
        catch (Exception e) {
            Log.e(TAG, "Error", e);             //Give error if it doesn't work
            return;
        }
        int dbpoints = ScoreMod.gameprocessingpower;
        values.put(String.valueOf(DatabaseManager.UserData.Points), dbpoints);

        long newRowId = database.insert(DatabaseManager.UserData.TableName, null, values);
    }
}
