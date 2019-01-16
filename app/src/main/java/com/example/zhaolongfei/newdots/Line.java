/*
  author: Longfei Zhao u5976992,
          Yexiao Lin u6257745
 */

package com.example.zhaolongfei.newdots;
import android.graphics.Canvas;
import android.graphics.Paint;

public class Line {
    static Paint[] paints;
    //    Line Info
    float x1, x2, y1, y2;

    Line(float x1, float y1, float x2, float y2) {
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
    }

    public void draw(Canvas canvas, int type) {
        paints[type].setStrokeWidth((float) 20.0);
        canvas.drawLine( this.x1, this.y1, this.x2, this.y2, paints[type]);
    }
}
