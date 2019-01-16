/*
  author: Hanshan Zhang u5975228,
          Longfei Zhao u5976992
 */
package com.example.zhaolongfei.newdots;

import android.util.Log;

import java.util.ArrayList;

public class Game implements MyObserver{
    public int[][] initaldots;
    public GameState gameState;
    public ArrayList<MyObserver> observers;
    public int currentValue;
    public int score;
    public int m;
    public int n;

    public Game(int[][] initaldots, int currentValue) {
        this.initaldots = initaldots;
        this.currentValue = currentValue;
        observers = new ArrayList<>();
        m = initaldots.length;
        n = initaldots[0].length;
        score = 0;
        gameState=GameState.PLAYING;
    }

    public int getCurrentValue() {
        return currentValue;
    }

    public void update(int score){
        this.currentValue--;
        this.score += score;
        if (currentValue==0){
            gameState=GameState.DONE;
        }
        Log.i("Current Value",Integer.toString(currentValue));
        Log.i("Score", Integer.toString(this.score));
        notifyObservers();

    }
    public void update(){
    }
    public void endGame(){

        gameState=GameState.DONE;
        notifyObservers();
    }
    public void registerObserver(MyObserver o) {
        observers.add(o);
    }
    private void notifyObservers() {
        for (MyObserver o : observers) o.update();
    }

}
