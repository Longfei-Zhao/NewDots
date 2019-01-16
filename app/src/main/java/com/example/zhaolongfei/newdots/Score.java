/*
  author: Hanshan Zhang u5975228,
          Longfei Zhao u5976992
 */
package com.example.zhaolongfei.newdots;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.preference.PreferenceManager;
import android.widget.TextView;

public class Score extends AppCompatActivity {
    SharedPreferences.Editor editor;
    int newlevel =0 ;
    int newScore;
    String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        newScore= getIntent().getExtras().getInt("Score");
        type = getIntent().getExtras().getString("Type");
        Log.i("Final Score",Integer.toString(newScore));
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        editor= sharedPref.edit();
        int currentHighScore = sharedPref.getInt(type, 0);
        if (currentHighScore< newScore){
            editor.putInt(type, newScore);
            editor.apply();
        }
        Log.i("Final",String.format("Current score %d Current Type %s , newScore %d",
        currentHighScore,type,newScore));
        TextView hValue = findViewById(R.id.hValue);
        hValue.setText(Integer.toString(currentHighScore));
        TextView nValue = findViewById(R.id.nValue);
        nValue.setText(Integer.toString(newScore));
    }
    public void goHome(View view){
        Intent start = new Intent(Score.this,Start.class);
        Bundle data = new Bundle();
        data.putString("Type",type);
        data.putInt("Score",newScore);
        start.putExtras(data);
        startActivity(start);
        finish();
    }
}
