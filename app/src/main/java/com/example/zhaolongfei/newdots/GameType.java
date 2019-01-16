/*
  author: Hanshan Zhang u5975228
 */
package com.example.zhaolongfei.newdots;

public class GameType {
    String type;
    int m,n;
    int[] dots;
    int remainValue;

    public int getRemainValue() {
        return remainValue;
    }

    public void setRemainValue(int remainValue) {
        this.remainValue = remainValue;
    }

    public GameType() {

    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getM() {
        return m;
    }

    public void setM(int m) {
        this.m = m;
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public int[] getDots() {
        return dots;
    }

    public void setDots(int[] dots) {
        this.dots = dots;
    }
}
