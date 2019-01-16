/*
  author: Hao He u6153769
 */
package com.example.zhaolongfei.newdots;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LineUnitTest {
    @Test
    public void x1_isCorrect(){assertEquals(1, (int) new Line(1, 2, 3, 4).x1);}
    @Test
    public void y1_isCorrect(){assertEquals(2, (int) new Line(1, 2, 3, 4).x2);}
    @Test
    public void x2_isCorrect(){assertEquals(3, (int) new Line(1, 2, 3, 4).y1);}
    @Test
    public void y2_isCorrect(){assertEquals(4, (int) new Line(1, 2, 3, 4).y2);}}
