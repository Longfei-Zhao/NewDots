/*
  author: Hao He u6153769
 */
package com.example.zhaolongfei.newdots;

import org.junit.Test;

import static org.junit.Assert.*;

public class DotUnitTest {
    @Test
    public void i_isCorrect(){assertEquals(2, new Dot(2, 3, 100, 200, 2).i);}
    @Test
    public void j_isCorrect(){assertEquals(3, new Dot(2, 3, 100, 200, 2).j);}
    @Test
    public void x_isCorrect(){assertEquals(100, (int) new Dot(2, 3, 100, 200, 2).x);}
    @Test
    public void y_isCorrect(){assertEquals(200, (int) new Dot(2, 3, 100, 200, 2).y);}

}
