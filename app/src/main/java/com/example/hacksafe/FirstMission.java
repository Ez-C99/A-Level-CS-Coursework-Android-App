package com.example.hacksafe;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class FirstMission extends Benchmark {
    private TextView introtext;
    private Button firstmissionstart;
    private Button[] actions = new Button[4];
    private TextView[] descriptions = new TextView[4];
    private Button complete;
    public TextView remainingpp;
    private  Boolean hackcomplete;
    private  Integer actioncount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intent = getIntent();                                                                //The back end of changing the activity which
        String value = intent.getStringExtra("key");                                                //Isn't working
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_mision);
        getIntent();
        introtext = (TextView) findViewById(R.id.txtIntro);
        actions[0] = (Button) findViewById(R.id.btnScan);
        actions[1] = (Button) findViewById(R.id.btnFirewall);
        actions[2] = (Button) findViewById(R.id.btnPwCrack);
        actions[3] = (Button) findViewById(R.id.btnRootkit);
        actions[4] = (Button) findViewById(R.id.btnBOT);
        firstmissionstart = (Button) findViewById(R.id.btnMissionStart);
        descriptions[0] = (TextView) findViewById(R.id.txtScan);
        descriptions[1] = (TextView) findViewById(R.id.txtFirewall);
        descriptions[2] = (TextView) findViewById(R.id.txtPwCrack);
        descriptions[3] = (TextView) findViewById(R.id.txtRootkit);
        descriptions[4] = (TextView) findViewById(R.id.txtBOT);
        complete = (Button) findViewById(R.id.btnComplete);
        remainingpp = (TextView) findViewById(R.id.txtCheckPP);

    }

    public FirstMission() throws InterruptedException {
        super();
        while (hackcomplete = false){
            complete.setVisibility(View.INVISIBLE);
            remainingpp.setText("Processing power left: " + processingpower);
        }
        for (int b=0; b == actions.length; b++) {
            actions[b].setVisibility(View.INVISIBLE);
        }
        for (int t=0; t == descriptions.length; t++){
            descriptions[t].setVisibility(View.INVISIBLE);
        }
        introtext.setText("[Retr0]Congratulations hacker, you have been chosen to be tested for membership of F-Sec\n\n[Retr0]I will be using this encrypted IRC channel to communicate with you and guide you through your mission\n\n[Retr0]As you know, EvilCorp is the biggest conglomerate in the world, dominating all the markets it owns. Little do people know that they take advantage of this to spy on everyone, whether innocent or guilty, collecting all the information into huge resources to be sold to the highest bidders\n\n[Retr0]Not only is this wrong in every way, but they act above the law\n...\n...\n...\n[Retr0]We have retrieved the IP of one of their data mining servers in your area. Your mission is to take the necessary steps to hack into the server, delete the illegal data and add it to our arsenal\n\n[Retr0]Click the execute button below to continue");
    }

    public void onBeginClick(View view){
        introtext.setVisibility(View.INVISIBLE);
        firstmissionstart.setVisibility(View.INVISIBLE);
        for (int b=0; b == actions.length; b++) {
            actions[b].setVisibility(View.VISIBLE);
        }
        for (int t=0; t == descriptions.length; t++){
            descriptions[t].setVisibility(View.VISIBLE);
        }
    }

    public void ActionClick(View view){
        actioncount++;
        if (actioncount >= 5){
            hackcomplete = true;
            }
    }

}

