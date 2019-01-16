/*
  author: Hanshan Zhang u5975228,
          Longfei Zhao u5976992
 */

package com.example.zhaolongfei.newdots;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class GameActivity extends AppCompatActivity implements MyObserver{
    Game game;
    TextView scoreString,scoreValue,remainString,remainValue;
    int step,remainTime;
    CountDownTimer countDownTimer;
    String type;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_game);
        BoardView boardview = findViewById(R.id.boardview);


        Intent fromstart = getIntent();


        Bundle gameSetting = fromstart.getExtras();
        scoreString = findViewById(R.id.ScoreString);
        scoreValue = findViewById(R.id.ScoreValue);
        remainString = findViewById(R.id.RemainString);
        remainValue = findViewById(R.id.RemainValue);

        scoreString.setText("Score");


        type = gameSetting.getString("Type");
        int m = gameSetting.getInt("m");
        int n = gameSetting.getInt("n");
        int [][] dots = get2DArray(m,n,gameSetting.getIntArray("Dots"));
        Log.i("GET TYPE",type);
        if (type.equals("TIME")){
            Log.i("TYPE","TIME");
            remainTime = gameSetting.getInt("Remain");
            game =new TimeGame(dots,remainTime);
            remainString.setText("TIME");
        }else if (type.equals("STEP")){
            Log.i("TYPE","STEP");
            step = gameSetting.getInt("Remain");
            game = new Game(dots,step);
            remainString.setText("STEP");
        }else if (type.equals("INFINITE")){
            Log.i("TYPE","INFINITE");
            remainTime = gameSetting.getInt("Remain");
            game =new InfiniteGame(dots,remainTime);
            remainString.setText("STEP");
        }

        game.registerObserver(this);
        boardview.loadGame(game);

        scoreValue.setText(Integer.toString(game.score));
        remainValue.setText(Integer.toString(game.currentValue));

        Bundle data = getIntent().getExtras();
        int level = data.getInt("level");
        boardview.setLevel(level);

        if (type.equals("TIME")){
            countDownTimer = new CountDownTimer(game.getCurrentValue(),1000) {

                public void onTick(long millisUntilFinished) {
                    game.update();
                }

                public void onFinish() {
                    game.update();
                }
            }.start();
        }
    }
    public void goScore(){
        Intent start = new Intent(GameActivity.this,Score.class);
        Bundle data = new Bundle();
        data.putInt("Score",game.score);
        data.putString("Type",type);
        start.putExtras(data);
        startActivity(start);
        finish();
    }

    @Override
    public void update() {
        if (game.gameState==GameState.DONE){
            if (type.equals("TIME")){
                countDownTimer.cancel();
            }
            goScore();
        }

        remainValue.setText(Integer.toString(game.currentValue));
        scoreValue.setText(Integer.toString(game.score));
    }

    @Override
    public void update(int score) {

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (type.equals("TIME")) {
            countDownTimer.cancel();
        }
    }
    public int[][] get2DArray(int m, int n, int[] dots){
        int [][] result = new int[m][n];
        Log.i("get m n ",Integer.toString(m)+" "+ Integer.toString(n)+" "+
        Integer.toString(dots.length));
        int index_x = 0;
        int index_y = 0;
        for (int i =0;i<m*n;i++){
            result[i/n][i%n]=dots[i];
        }
        return result;
    }
}
