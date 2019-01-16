/*
  author: Hanshan Zhang u5975228,
          Longfei Zhao u5976992
 */
package com.example.zhaolongfei.newdots;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

public class Level extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level);
    }

    public void  startGame(View view){
        Intent start = new Intent(Level.this,GameActivity.class);
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
        finish();
    }
}
