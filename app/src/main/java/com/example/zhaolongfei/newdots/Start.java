/*
  author: Hanshan Zhang u5975228,
          Longfei Zhao u5976992
 */
package com.example.zhaolongfei.newdots;

import android.content.Intent;
import android.content.res.AssetManager;
import android.content.res.TypedArray;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Start extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        //Button start = (Button) findViewById(R.id.button_start);
        dealwithDataFromScore();
    }
    public void startLevel(View v) {
        Intent start = new Intent(Start.this,GameActivity.class);
        ArrayList<GameType> gameTypeArrayList= Utility.jsonParse(Utility.readJson(this),"Infinite");
        Bundle data = new Bundle();
        GameType gameType= gameTypeArrayList.get(0);
        data.putInt("m",gameType.getM());
        data.putInt("n",gameType.getN());
        data.putIntArray("Dots",gameType.getDots());
        data.putString("Type",gameType.getType());
        Log.i("read current",Integer.toString(gameType.getRemainValue()));
        data.putInt("Remain",gameType.getRemainValue());
        start.putExtras(data);
        startActivity(start);
    }
    public void startStep(View view){
        Intent game = new Intent(Start.this,GameActivity.class);
        Bundle data = new Bundle();
        ArrayList<GameType> gameTypeArrayList= Utility.jsonParse(Utility.readJson(this),"Step");
        GameType gameType = gameTypeArrayList.get(0);
        int m =gameType.getM();
        int n =gameType.getN();
        int [] dots = Utility.creatDots(m,n);
        data.putInt("m",m);
        data.putInt("n",n);
        data.putIntArray("Dots",dots);
        data.putString("Type","STEP");
        data.putInt("Remain",gameType.getRemainValue());
        game.putExtras(data);
        startActivity(game);
        Log.i("Start","STEP");
    }
    public void startTime(View view){
        Intent game = new Intent(Start.this,GameActivity.class);
        Bundle data = new Bundle();
        ArrayList<GameType> gameTypeArrayList= Utility.jsonParse(Utility.readJson(this),"Time");
        GameType gameType = gameTypeArrayList.get(0);
        int m =gameType.getM();
        int n =gameType.getN();
        int [] dots = Utility.creatDots(m,n);
        data.putInt("m",m);
        data.putInt("n",n);
        data.putIntArray("Dots",dots);
        data.putString("Type","TIME");
        data.putInt("Remain",gameType.getRemainValue());
        game.putExtras(data);
        startActivity(game);
        Log.i("Start","TIME");
        Log.i("m,n",Integer.toString(m)+" "+ Integer.toString(n));
        Log.i("Length",Integer.toString(dots.length));
    }

    public void startRecord(View view)
        {
        Intent record = new Intent(Start.this,RecordActivity.class);
        startActivity(record);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent); //store the new intent
        dealwithDataFromScore();
    }
    private void dealwithDataFromScore(){
        Intent intent = getIntent();
    }


}
