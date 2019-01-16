/*
  author: Hanshan Zhang u5975228,
          Longfei Zhao u5976992
 */
package com.example.zhaolongfei.newdots;

import android.util.Log;

public class InfiniteGame extends Game {
    public InfiniteGame(int[][] initaldots, int currentValue) {
        super(initaldots, currentValue);
    }
    @Override
    public void update(int score) {
        this.score += score;
        this.currentValue +=1;
        Log.i("Score", Integer.toString(this.score));
        notifyObservers();

    }
    public void update(){
        notifyObservers();
    }

    private void notifyObservers() {
        for (MyObserver o : observers) o.update();
    }
}
