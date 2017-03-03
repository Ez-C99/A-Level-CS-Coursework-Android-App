package com.example.hacksafe;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static java.lang.Thread.sleep;

public class Benchmark extends AppCompatActivity {

    public TextView result;
    private Button generate;
    private String ecorp;
    private Integer encryptcount;
    private Long timeSHA;
    private Long timeMD;
    public static Integer benchScore;
    public Button Next;
    public Boolean benchDone = false;
    public Integer modScore;
    public Integer processingpower;

    Handler handler1 = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            final Long ttSHA = (Long) msg.obj;
            result.setText("SHA-1 Complete! Time Taken: " + ttSHA.toString());           //The handlers checks both encryption procedures have
            encryptcount++;                                                              //run and run the score output
            timeSHA = ttSHA;
            if (encryptcount==2) {
                scoreOutput();
            }
        }
    };

    Handler handler2 = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            final Long ttMD = (Long) msg.obj;
            result.setText("MD5 Complete! Time Taken: " + ttMD.toString());
            encryptcount++;
            timeMD = ttMD;
            if (encryptcount==2) {
                scoreOutput();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_benchmark);
        generate = (Button) findViewById(R.id.btnStart);
        result = (TextView) findViewById(R.id.txtTerminal);
        ecorp = "Our democracy has been hacked";
        Next = (Button) findViewById(R.id.btnNext);
    }

    /*public void sendMessage(View view)
    {
        Intent gamescreen = new Intent(Benchmark.this, FirstMission.class);
        startActivity(gamescreen);
    }*/

    public void scoreOutput() {

        Long avg = (timeSHA + timeMD) /2;
        benchScore = Math.round(avg);
        //benchDone = true;                     The benchscore is modified by the modify procedure in the ScoreMod class
        ScoreMod scrmd = new ScoreMod();
        int gamepoints = scrmd.modify();
        result.setText("SHA-1 processing power score= " + timeSHA);
        result.setText("MD5 processing power score = "+ timeMD);
        result.setText("[terminal]/Initialising SHA-1 processing power generation...\n...\n... \n[terminal]SHA-1 processing power generation complete. \n \n[terminal]Initialising MD5 processing power generation...\n" +
                "...\n" +
                "... \n" +
                "[terminal]MD5 processing power generation complete.\n[terminal]Benchmark module complete\nBenchmark score = " + benchScore + "\n \n[terminal]Initialising score modification procedures" + "...\n...\n[terminal]Score modification procedures complete\n\n[terminal]Modified score = " + gamepoints);
        /*try {
            sleep(5000);                                                  //There's a 5 second pause allowing the user to see
        } catch (InterruptedException e) {                                //all the information
            e.printStackTrace();
        }
        /*Intent gamescreen = new Intent(Benchmark.this, Game.class);
        startActivity(gamescreen);
        UserDBFunctions dbsave = new UserDBFunctions();                 //Now that all the user data generation is complete
        dbsave.saveToDB(); */                                             //it's logged in the database in a new entry
    }

    public void onBeginClick(View view){

            encryptcount = 0;                   //Encryptcount at 0 let's the system know that none of the
            execSHA1Hash();                     //functions have been run yet
            execMD5Hash();
            result.setText("Encryption algorithms running...");          //Give the user some indication as to what is happening
            //Add iteration for percentage counter

    }

    public void execSHA1Hash() {

        Runnable r = new Runnable() {
            public void run() {
                Long tsLong = System.nanoTime();
                for (Integer s = 0; s < 30000; s++) {           //Run the SHA-1 encryption 30000 times
                    MessageDigest mdSha1 = null;
                    try {
                        mdSha1 = MessageDigest.getInstance("SHA-1");
                    } catch (NoSuchAlgorithmException e1) {
                        Log.e("Benchmark", "Error initializing SHA1");
                    }
                    try {
                        mdSha1.update(ecorp.getBytes("ASCII"));
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    byte[] data = mdSha1.digest();
                    StringBuffer sb = new StringBuffer();
                    String hex = null;
                    hex = Base64.encodeToString(data, 0, data.length, 0);

                    sb.append(hex);
                }

                Long ttLong1 = System.nanoTime() - tsLong;
                Message msg = Message.obtain();                      //The result is passed to the handler
                msg.obj = ttLong1;
                msg.setTarget(handler1);
                msg.sendToTarget();

            }

        };
        Thread newThread = new Thread(r);
        newThread.start();

    }


    public void execMD5Hash() {

        Runnable r = new Runnable() {
            public void run() {
                Long tsLong = System.nanoTime();
                for (Integer m = 0; m < 30000; m++) {       //Run the MD5 function 30000 times
                    try {
                        MessageDigest digest = java.security.MessageDigest.getInstance("MD5");
                        digest.update(ecorp.getBytes());
                        byte messageDigest[] = digest.digest();

                        StringBuffer MD5Hash = new StringBuffer();
                        for (int i = 0; i < messageDigest.length; i++) {
                            String h = Integer.toHexString(0xFF & messageDigest[i]);
                            while (h.length() < 2)
                                h = "0" + h;
                            MD5Hash.append(h);
                        }

                    } catch (NoSuchAlgorithmException e) {
                        Log.e("Benchmark", "Error initializing MD5");
                    }
                }
                Long ttLong2 = System.nanoTime() - tsLong;
                Message msg = Message.obtain();
                msg.obj = ttLong2;                                                                      //Pass  the value to the handler
                msg.setTarget(handler2);
                msg.sendToTarget();

            }
        };
        Thread newThread = new Thread(r);
        newThread.start();

    }

}

