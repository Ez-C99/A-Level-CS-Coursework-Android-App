package com.example.hacksafe;

import android.app.ActivityManager;
import java.util.Calendar;

import static android.content.Context.ACTIVITY_SERVICE;

public class ScoreMod extends Benchmark {

    public static Integer gameprocessingpower;
    private Integer noMod;


    public Integer modify() {
        noMod = Benchmark.benchScore;
        gameprocessingpower = modTime(); /*+ modRAM()) / 2;*/       //Get an average of all the mods
        return gameprocessingpower;
        }


    public Integer modRAM(){
        ActivityManager.MemoryInfo RAM = new ActivityManager.MemoryInfo();      //Create a new instance of the Activity Manager of RAM
        ActivityManager activityManager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
        activityManager.getMemoryInfo(RAM);                                        //Retrieve the RAM
        double availableRAM = RAM.availMem / 0x100000L;                            //Convert the data to MB
        double percentageRAM = RAM.availMem / RAM.totalMem;                        //Calculate percentage of available RAM
        double RAMScore =  percentageRAM * noMod;               //Get an average to use as the modified score
        return (int) RAMScore;                                  //Return the score to where it was caled from
    }

    public Integer modTime() {
        Calendar today = Calendar.getInstance();            //Create a new Calendar instance to acces data
        int hour = today.get(Calendar.HOUR_OF_DAY);         //Get the hour of the day
        if(hour > 22 && hour <3){
            long TimeScore = (long) (0.5 * noMod);          //If the hour is very late in the night, half the user's score
            return (int) TimeScore;
        }

        return noMod;               //Otherwise return the user's normal score
    }


    //public void modStorage(){
    //    StorageVolume used = new StorageVolume();


    //public void modBattery(int noModBattery){

    //}

    //public void modTemp(int noModTemp){

    //}
}
