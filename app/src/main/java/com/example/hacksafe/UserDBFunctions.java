package com.example.hacksafe;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.Calendar;

import static android.content.ContentValues.TAG;


public class UserDBFunctions extends AppCompatActivity {

    private void saveToDB() {
        SQLiteDatabase database = new DatabaseHelper(this).getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DatabaseManager.UserData.TableName, "UserData");

        try {
            Calendar calendar = Calendar.getInstance();
            int date = calendar.get(Calendar.DATE);
            int time = calendar.get(Calendar.HOUR_OF_DAY);
            values.put(DatabaseManager.UserData.LaunchDate, date);
            values.put(DatabaseManager.UserData.LaunchTime, time);
        }
        catch (Exception e) {
            Log.e(TAG, "Error", e);
            return;
        }
        ScoreMod DBScoreMod = new ScoreMod();
        int dbpoints = DBScoreMod.gameprocessingpower;
        values.put(String.valueOf(DatabaseManager.UserData.Points), dbpoints);

        long newRowId = database.insert(DatabaseManager.UserData.TableName, null, values);
    }
}
