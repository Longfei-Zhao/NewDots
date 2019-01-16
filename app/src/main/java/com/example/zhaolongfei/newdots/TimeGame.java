/*
  author: Longfei Zhao u5976992
 */
package com.example.zhaolongfei.newdots;

import android.util.Log;

public class TimeGame extends Game {
    private int countDownInterval = 1;
    public TimeGame(int[][] initaldots, int currentValue) {
        super(initaldots, currentValue);
    }

    @Override
    public int getCurrentValue() {
        return super.getCurrentValue()*1000;
    }

    @Override
    public void update(int score) {
        this.score += score;
        Log.i("Score", Integer.toString(this.score));
        notifyObservers();

    }
    public void update(){
        this.currentValue -= this.countDownInterval;
        Log.i("CountDown",Integer.toString(currentValue));
        if (this.currentValue==0){
            gameState=GameState.DONE;
        }
        notifyObservers();
    }

    private void notifyObservers() {
        for (MyObserver o : observers) o.update();
    }

}
