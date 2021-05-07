package com.example.a4flicksapp.TestsForBMI;

import com.example.a4flicksapp.bmiCalculator;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestsForBMIfunction {

    public class bmiCalculatorUnitTest {

        private com.example.a4flicksapp.bmiCalculator bmiCalculator;

        @Before
        public void setup() {
            bmiCalculator = new bmiCalculator();
        }

        @Test
        public void testWeightCategory(){
            String result = bmiCalculator.getWeightCategory(17);
            assertEquals("Underweight", result);
        }

    }

}
