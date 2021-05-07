package com.example.a4flicksapp;

import org.junit.Before;
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

    private com.example.a4flicksapp.bmiCalculator bmiCalculator;

    @Before
    public void setup() {
        bmiCalculator = new bmiCalculator(); //create an object of bmi java class
    }

    @Test
    public void testBMIvalue(){
        float result = bmiCalculator.calculateBMI(50, 150);
        assertEquals(33.3, result, 0.000001);
    }

    @Test
    public void testWeightCategory(){
        String result = bmiCalculator.getWeightCategory(17);
        assertEquals("Underweight", result);
    }
}