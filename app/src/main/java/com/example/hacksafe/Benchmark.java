package com.example.hacksafe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Benchmark extends AppCompatActivity {
    private String result;
    private Button compute;
    private String ecorp;
    private String HashValue;
    private String scoreSHA;
    private String MD5Val;
    private String scoreMD;
    private Long tsStart;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_benchmark);
        compute = (Button) findViewById(R.id.btn1);
        result = String.valueOf((TextView) findViewById(R.id.textView2));
        ecorp = getResources().getString(R.string.ecorp);
    }

    public void execSHA1Hash(String password) {
        result = "/n Initialising SHA-1 processing power generation /n";                /* The SHA-1 encryption function*/
        MessageDigest SHA1md = null;
        try {
            SHA1md = MessageDigest.getInstance("SHA-1");
        } catch (NoSuchAlgorithmException e1) {
            Log.e("Benchmark", "Error initialising SHA-1 encryption");                  /*If it can't be run for any reason send the error message*/
            result = "Error initialising SHA-1 encryption /n";
        }
        try {
            SHA1md.update(password.getBytes("ASCII"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        result = "Executing byte to hex translation /n";
        byte[] data = SHA1md.digest();
        StringBuffer sb = new StringBuffer();
        String hex = null;

        hex = Base64.encodeToString(data, 0, data.length, 0);

        sb.append(hex);
        HashValue = sb.toString();
        result = "SHA-1 processing power generation complete /n";
    }

    public void execMD5Hash(String password) {
        try {
            MessageDigest digest = java.security.MessageDigest.getInstance("MD5");
            digest.update(password.getBytes());
            byte messageDigest[] = digest.digest();
            StringBuffer MD5Hash = new StringBuffer();
            for (int m = 0; m < messageDigest.length; m++) {
                String d = Integer.toHexString(0xFF & messageDigest[m]);
                while (d.length() < 2)
                    d = "0" + d;
                MD5Hash.append(d);
            }
        }
        catch (NoSuchAlgorithmException e) {
            Log.e("Benchmark", "Error initialising MD5");
        }
    }

    public void onBeginClick (View view) {

        tsStart = System.nanoTime();
        for (Integer s = 0; s<30000; s++) {
            execSHA1Hash(ecorp);
        }
        Long ttSHA = System.nanoTime() - tsStart;
        scoreSHA = ttSHA.toString();

        tsStart = System.nanoTime();
        for (Integer m = 0; m<30000; m++) {
            execMD5Hash(ecorp);
        }
        Long ttMD = System.nanoTime() - tsStart;
        scoreMD = ttMD.toString();


    }
}