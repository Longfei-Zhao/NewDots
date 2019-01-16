/*
  author: Hanshan Zhang u5975228,
          Longfei Zhao u5976992
 */
package com.example.zhaolongfei.newdots;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.preference.PreferenceManager;
import android.widget.TextView;

public class RecordActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        int time = sharedPref.getInt("TIME", 0);
        int step = sharedPref.getInt("STEP", 0);
        int inf = sharedPref.getInt("INFINITE",0);
        TextView timeView  = findViewById(R.id.TimeBestRecord);
        TextView stepView = findViewById(R.id.StepBestRecord);
        TextView infView = findViewById(R.id.InfinityBestRecord);
        timeView.setText(Integer.toString(time));
        stepView.setText(Integer.toString(step));
        infView.setText(Integer.toString(inf));
    }
}
