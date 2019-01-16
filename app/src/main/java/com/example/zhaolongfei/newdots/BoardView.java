/*
  author: Hanshan Zhang u5975228,
          Longfei Zhao u5976992
 */
package com.example.zhaolongfei.newdots;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class BoardView extends View {

    // Constant
    final static private int maxM = 8;
    final static private int maxN = 8;
    final double speed = 0.001;

    // Game Info
    private int m = 2;
    private int n = 2;
    private int types = 6;
    private int level = 1;
    private Dot[][] dots;

    // Display Rule
    final private int span = 150;
    private int startX;
    private int startY;

    // game current
    private List<Dot> pathDots;
    private List<Dot> reomveList;
    private Dot currentDot;
    private float x = -1f;
    private float y = -1f;
    private GameState state;
    private boolean findeCycle = false;
    private Game game;
    Random rand;
    int[][] fall;
    int type;


    public BoardView(Context c, AttributeSet as) {
        super(c, as);


        pathDots = new ArrayList<>();
        currentDot = null;
        reomveList = new ArrayList<>();
        rand = new Random();

        // Creat Paints
        int[] colors = getResources().getIntArray(R.array.colors);
        Dot.paints = new Paint[types + 1];
        Line.paints = new Paint[types + 1];
        for (int i = 0; i <= types; i++) {
            Dot.paints[i] = new Paint();
            Dot.paints[i].setColor(colors[i]);
            Line.paints[i] = new Paint();
            Line.paints[i].setColor(colors[i]);
        }
    }

    public void loadGame(Game game){
        this.game =game;
        m = game.m;
        n = game.n;
        invalidate();
    }
    @Override
    protected void onDraw(final Canvas canvas) {
        super.onDraw(canvas);
        if (dots!=null){
            drawLines(canvas);
            drawDots(canvas);
        }
    }
    private void drawDots(Canvas canvas) {
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dots[i][j].draw(canvas);
            }
        }
    }
    private void drawLines(Canvas canvas){
        // draw line from current dot to touch x,y
        if (x != -1 && y != -1 && currentDot != null) {
            type = currentDot.type;
            Line line = new Line(currentDot.x, currentDot.y, x, y);
            line.draw(canvas, type);
        }
        // draw line between list
        if (pathDots.size() > 0) {
            type = pathDots.get(0).type;
            for (int i = 1; i < pathDots.size(); i++) {
                Line line = new Line(pathDots.get(i - 1).x, pathDots.get(i - 1).y,
                        pathDots.get(i).x, pathDots.get(i).y);
                line.draw(canvas, type);
            }
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int w = getMeasuredWidth();
        int h = getMeasuredHeight();
        // Get width and Height
        Log.i("On Measure","a1");
        startX = w / 2 - span * (m - 1) / 2;
        startY = h / 2 - span * (n - 1) / 2;
        if (dots==null){
            dots = new Dot[m][n];
        }
        Random rand = new Random();
        state = GameState.PLAYING;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (dots[i][j]==null){
                    int tempType = game.initaldots[i][j];
                    if (tempType==-1){
                        tempType = rand.nextInt(types);
                    }
                    dots[i][j] = new Dot(i, j,startX + span * i, startY + span * j, tempType);
                }else {
                    dots[i][j].setX(startX + span * i);
                    dots[i][j].setY(startY + span * j);
                }

            }
        }

    }

    public boolean onTouchEvent(MotionEvent event) {
        int clicki = (int) Math.ceil((event.getX() - this.startX + this.span / 2) / this.span) - 1;
        int clickj = (int) Math.ceil((event.getY() - this.startY + this.span / 2) / this.span) - 1;
        Dot getedDot = null;

        if (clicki >= 0 && clickj >= 0 && clicki < m && clickj < n && game.gameState == GameState.PLAYING) {
            getedDot = dots[clicki][clickj];

            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                AddDottoPath(getedDot);
            }
            if (event.getAction() == MotionEvent.ACTION_MOVE) {
                AddDottoPath(getedDot);
            }
        }
        if (event.getAction() != MotionEvent.ACTION_UP) {
            x = event.getX();
            y = event.getY();
        } else {
            x = -1f;
            y = -1f;
        }
        if (event.getAction() == MotionEvent.ACTION_UP) {

            this.OneMoveFinished();
        }

        invalidate();
        return true;
    }

    private void AddDottoPath(Dot d) {
        if (pathDots.size() == 0) {
            pathDots.add(d);
        } else {
            if (currentDot.ifAddPath(d)) {
                if (pathDots.contains(d)) {
                    if (pathDots.indexOf(d) == pathDots.size() - 2) {
                        //remove the last one
                        pathDots.remove(pathDots.size() - 1);
                    } else {
                        // finde a cycle
                        this.findeCycle = true;
                        pathDots.add(d);
                    }
                } else {
                    pathDots.add(d);
                }
            }
        }
        currentDot = pathDots.get(pathDots.size() - 1);
    }
    private void OneMoveFinished() {
        reomveList = new ArrayList<>();
        if (pathDots.size() > 1) {

            Dot firstDot = pathDots.get(0);
            if (findeCycle) {
                for (int i = 0; i < this.m; i++) {
                    for (int j = 0; j < this.n; j++) {
                        if (firstDot.type == dots[i][j].type) {
                            reomveList.add(dots[i][j]);
                        }
                    }
                }

            } else {
                reomveList = new ArrayList<>(pathDots);
            }
            findeCycle = false;
        }
        pathDots = new ArrayList<>();
        this.currentDot = null;
        if (reomveList.size()>0){
            game.update((int) Math.pow(reomveList.size(),2)*10);
        }
        removeEffect(reomveList);
        if (!checkNextMove()){
            game.endGame();
        }

    }
    private boolean checkNextMove(){
        boolean hasNextmove = false;

        for (int i= 0;i<m;i++){
            for (int j =0 ;j <n;j++){
                Dot current = dots[i][j];
                ArrayList<Dot> nextDots = new ArrayList<>();
                if (i==0){
                    if (j==0){
                        nextDots.add(dots[i+1][j]);
                        nextDots.add(dots[i][j+1]);
                    }else if (j==n-1){
                        nextDots.add(dots[i][j-1]);
                        nextDots.add(dots[i+1][j]);
                    }else {
                        nextDots.add(dots[i+1][j]);
                        nextDots.add(dots[i][j-1]);
                        nextDots.add(dots[i][j+1]);
                    }
                }else if (i==m-1){
                    if (j==0){
                        nextDots.add(dots[i-1][j]);
                        nextDots.add(dots[i][j+1]);
                    }else if (j==n-1){
                        nextDots.add(dots[i-1][j]);
                        nextDots.add(dots[i][j-1]);
                    }else {
                        nextDots.add(dots[i-1][j]);
                        nextDots.add(dots[i][j-1]);
                        nextDots.add(dots[i][j+1]);
                    }
                }else {
                    if (j==0){
                        nextDots.add(dots[i-1][j]);
                        nextDots.add(dots[i+1][j]);
                        nextDots.add(dots[i][j+1]);
                    }else if (j==n-1){
                        nextDots.add(dots[i-1][j]);
                        nextDots.add(dots[i+1][j]);
                        nextDots.add(dots[i][j-1]);
                    }else{
                        nextDots.add(dots[i-1][j]);
                        nextDots.add(dots[i+1][j]);
                        nextDots.add(dots[i][j-1]);
                        nextDots.add(dots[i][j+1]);
                    }
                }
                for (Dot a : nextDots){
                    if (a.type==current.type){
                        hasNextmove =true;
                        return hasNextmove;
                    }
                }
            }
        }
        return hasNextmove;
    }
    private void removeEffect(List<Dot> removelist) {
        fall = new int[m][n];
        int[] newDots = new int[m];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                fall[i][j] = 0;
            }
            newDots[i] = 0;
        }
        for (Dot dot : removelist) {
            for (int j=0; j< dot.j; j++) {
                fall[dot.i][j] += 1;
            }
            newDots[dot.i] += 1;
        }

        for (int i = 0; i < m; i++) {
            for (int j = n - 1; j >= 0; j--) {
                int tempJ = j + fall[i][j];
                dots[i][tempJ] = new Dot(dots[i][j]);
                dots[i][tempJ].j = tempJ;
                fall[i][tempJ] = fall[i][j];
            }
            for (int k = 0; k < newDots[i]; k++) {
                int tempType = rand.nextInt(types);
                dots[i][k] = new Dot(i, k,startX + span * i, startY + span * (k - newDots[i]), tempType);
                fall[i][k] = newDots[i];
            }
        }

        for(int k = 0; k < span / speed; k++) {
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    dots[i][j].fall(fall[i][j], speed);
                }
            }
            invalidate();
        }
    }

    public void setLevel(int level) {
        this.level = level;
    }
}