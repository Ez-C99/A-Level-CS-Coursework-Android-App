package com.example.hacksafe;

import android.app.ActivityManager;
import android.os.Bundle;
import android.widget.TextView;

public class ScoreMod extends Benchmark {
    public Integer modScore;
    public Integer processingpower;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_benchmark);
        result = (TextView) findViewById(R.id.txtTerminal);
    }

    ScoreMod() {
        modify();
    }

    public void modify() {
        /*if (benchDone == true) {                                                                        //Check if inherited boolean done then modify
            modScore = benchScore;                                                                      //scores
            //modBattery(benchScore);
            processingpower = modScore + modRAM(benchScore) / 2;
            result.setText("...\n...\n[terminal]Score modification procedures complete\n\n[terminal]Modified score = " + processingpower);
        }*/

        result.setText("Testing");
    }

    public Integer modRAM(int noModRAM){
        ActivityManager.MemoryInfo RAM = new ActivityManager.MemoryInfo();
        ActivityManager activityManager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);         //The function that gets the available RAM and
        activityManager.getMemoryInfo(RAM);                                                             //modifies the benchmark score accordingly
        double availableRAM = RAM.availMem / 0x100000L;
        double percentageRAM = RAM.availMem / RAM.totalMem;  //show percentage of available RAM
        Integer RAMScore = ((int) percentageRAM) * noModRAM;
        return RAMScore;
    }

    //public void modStorage(){
    //    StorageVolume used = new StorageVolume();
    //}

    //public void modBattery(int noModBattery){

    //}

    //public void modTemp(int noModTemp){

    //}
}
