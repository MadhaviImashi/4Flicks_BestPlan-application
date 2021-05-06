package com.example.a4flicksapp;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


public class calorCalTest {

    private  calorCalcu calorcalcu;


    @Before
    public void setup(){
        calorcalcu=new calorCalcu();
    }

    @Test
    public void testAddcal(){
        float res= calorcalcu.addcal(1000,1000,2000);
        assertEquals(4000.0,res,0.000001);
    }
    @Test
    public void testAddcal2(){
        float res= calorcalcu.addcal(900,1100,600);
        assertEquals(2600.0,res,0.000001);
    }
    @Test
    public void testAddcal3(){
        float res= calorcalcu.addcal(500,800,600);
        assertEquals(1900.0,res,0.000001);
    }
}
