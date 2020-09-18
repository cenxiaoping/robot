package com.fly.tour.main;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }
    @Test
    public void test(){

        Integer a=1000;
        int b=1000;
        if (a==1000){
            System.err.println("a==b");
        }else {
            System.err.println("a!=b");
        }


    }


}