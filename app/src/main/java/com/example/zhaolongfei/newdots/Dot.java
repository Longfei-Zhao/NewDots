/*
  author: Hanshan Zhang u5975228,
          Longfei Zhao u5976992,
          Yexiao Lin u6257745
 */

package com.example.zhaolongfei.newdots;
import android.graphics.Canvas;
import android.graphics.Paint;

public class Dot{
    final static int maxType = 6;
    private final static int radius = 40;
    static Paint[] paints;
    int i, j;
    float x, y;
    int type;

    public void setX(float x) {
        this.x = x;
    }
    public void setY(float y) {
        this.y = y;
    }


    Dot(Dot dot) {
        this.i = dot.i;
        this.j = dot.j;
        this.x = dot.x;
        this.y = dot.y;
        this.type = dot.type;
    }

    Dot(int i, int j, float x, float y, int type) {
        this.i = i;
        this.j = j;
        this.x = x;
        this.y = y;
        this.type = type;
    }

    public void draw(Canvas canvas) {
        canvas.drawCircle( this.x, this.y, radius, paints[this.type]);
    }

    public boolean ifAddPath(Dot o) {
        return type == o.type && (Math.abs(i - o.i) + Math.abs(j - o.j)) == 1;
    }

    public void fall(int i, double speed) {
        y += speed * i;
    }
}
