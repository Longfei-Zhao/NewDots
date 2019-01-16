/*
  author: Hao He u6153769
 */
package com.example.zhaolongfei.newdots;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GameUnitText {
    @Test
    public void currentValue_isCorrect(){assertEquals(1, new Game(new int[2][2], 1).getCurrentValue());}
}
