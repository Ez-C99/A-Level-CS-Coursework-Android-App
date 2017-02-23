package com.example.hacksafe;

import android.app.ActivityManager;
import android.os.Bundle;
import android.widget.TextView;

public class ScoreMod extends Benchmark {
    public Integer modScore;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_benchmark);
        result = (TextView) findViewById(R.id.txtTerminal);
    }

    ScoreMod() {
        modify();
    }


    public void modify() {
        if (benchDone) {                                                                        //Check if inherited boolean done then modify
            modScore = benchScore;                                                              //scores
            processingpower = (modScore + modRAM(benchScore)) / 2;
        }

    }

    public Integer modRAM(int noModRAM){
        ActivityManager.MemoryInfo RAM = new ActivityManager.MemoryInfo();
        ActivityManager activityManager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);         //The function that gets the available RAM and
        activityManager.getMemoryInfo(RAM);                                                             //modifies the benchmark score accordingly
        double availableRAM = RAM.availMem / 0x100000L;
        double percentageRAM = RAM.availMem / RAM.totalMem;  //show percentage of available RAM
        double RAMScore =  percentageRAM * noModRAM;
        return (int) RAMScore;
    }

    //public void modStorage(){
    //    StorageVolume used = new StorageVolume();
    //}

    //public void modBattery(int noModBattery){

    //}

    //public void modTemp(int noModTemp){

    //}
}
