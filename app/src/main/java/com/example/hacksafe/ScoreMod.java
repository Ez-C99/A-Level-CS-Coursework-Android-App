package com.example.hacksafe;

import android.app.ActivityManager;
import java.util.Calendar;

public class ScoreMod extends Benchmark {

    public Integer gameprocessingpower;
    private Integer noMod;


    public Integer modify() {
        /*Benchmark benchmod = new Benchmark();
        noMod = benchmod.benchScore;
        gameprocessingpower = modTime();
        return gameprocessingpower;*/
        return 0;
        }




    public Integer modRAM(){
        ActivityManager.MemoryInfo RAM = new ActivityManager.MemoryInfo();
        ActivityManager activityManager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);     //The function that gets the available RAM and
        activityManager.getMemoryInfo(RAM);                                                         //modifies the benchmark score accordingly
        double availableRAM = RAM.availMem / 0x100000L;
        double percentageRAM = RAM.availMem / RAM.totalMem;  //show percentage of available RAM
        double RAMScore =  percentageRAM * noMod;
        return (int) RAMScore;
    }

    public Integer modTime() {
        Calendar today = Calendar.getInstance();
        int hour = today.get(Calendar.HOUR_OF_DAY);
        if(hour > 22 && hour <3){
            long TimeScore = (long) (0.5 * noMod);
            return (int) TimeScore;
        }

        return noMod;
    }


    //public void modStorage(){
    //    StorageVolume used = new StorageVolume();


    //public void modBattery(int noModBattery){

    //}

    //public void modTemp(int noModTemp){

    //}
}
