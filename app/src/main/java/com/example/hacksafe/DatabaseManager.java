package com.example.hacksafe;


import android.provider.BaseColumns;


public final class DatabaseManager {
    private DatabaseManager(){
    }

        public static class UserData implements BaseColumns{                        //The design of the database and all
        public static final String TableName = "UserData";                          //its fields
        public static final String LaunchDate = "Date";
        public static final String LaunchTime = "Time";
        public static final String Points = "Points";

        public static final String CreateTable = "CREATE TABLE IF NOT EXISTS " +
                TableName + " (" +
                _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +          //If the database doesn't exist, insert it with
                LaunchDate + " INTEGER, " +                             //These fields
                LaunchTime + " INTEGER, " +
                Points + " INTEGER" + ")";
    }



}
