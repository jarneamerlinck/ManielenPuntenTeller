package com.knightofzero;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class PointsTest {
    private static Points punten;

    @Test
    void makePoints() {
        Points points = new Points();
    }





    //Old below
    @BeforeAll
    static void beforeAll() {
        punten = new Points(true);
        punten.reset();
    }

    @AfterEach
    void tearDown() {
        punten.reset();
    }

    @Test
    void reset() {
        punten.reset();
        assert (punten.getPointsOfWe()==0);
        assert (punten.getPointsOfThem()==0);
        assert (punten.getPointsOfThem()==0);
        assert (punten.getNumberOfWonGamesOfThem()==0);
    }


    @Test
    void getW_Points() {
        assert (punten.getPointsOfWe()==0);
    }

    @Test
    void addW_Points() {
        punten.increasePointsOfWe(15,false,false);
        assert (punten.getPointsOfWe()==15);
    }

    @Test
    void test60() {
        punten.increasePointsOfWe(30,false,false);
        assert (punten.getPointsOfWe()==60);
    }
    @Test
    void testMee() {
        punten.increasePointsOfWe(1,true,false);
        assert (punten.getPointsOfWe()==2);
        punten.increasePointsOfThem(8,true,false);
        assert (punten.getPointsOfThem()==16);
    }
    @Test
    void testTegen() {
        assert (punten.getPointsOfWe()==0&&punten.getPointsOfThem()==0);
        punten.increasePointsOfWe(6,true,true);
        assert (punten.getPointsOfWe()==4*6);

        punten.increasePointsOfThem(1,false,false);
        punten.increasePointsOfThem(3,true,true);
        assert (punten.getPointsOfThem()==1+3*4);
    }
    @Test
    void testDubbelNormaal() {
        punten.increasePointsOfWe(11,false,false);
        punten.increasePointsOfThem(15,false,false);
        assert (punten.getPointsOfThem()==15);
        assert (punten.getPointsOfWe()==11);
        punten.increasePointsOfWe(0,false,false);//nul dus volgede dubbel
        punten.increasePointsOfThem(8,false,false);
        assert (punten.getPointsOfThem()==8*2+15);
        assert (punten.getPointsOfWe()==11);
    }
    @Test
    void testDubbelMee() {
        punten.increasePointsOfWe(11,false,false);
        punten.increasePointsOfThem(15,false,false);
        assert (punten.getPointsOfThem()==15);
        assert (punten.getPointsOfWe()==11);
        punten.increasePointsOfWe(0,false,false);//nul dus volgede dubbel
        punten.increasePointsOfThem(8,true,false);
        assert (punten.getPointsOfThem()==8*2*2+15);
        assert (punten.getPointsOfWe()==11);
    }
    @Test
    void testDubbelTegen() {
        punten.increasePointsOfWe(11,false,false);
        punten.increasePointsOfThem(15,false,false);
        assert (punten.getPointsOfThem()==15);
        assert (punten.getPointsOfWe()==11);
        punten.increasePointsOfWe(0,false,false);//nul dus volgede dubbel
        punten.increasePointsOfThem(8,true,true);
        assert (punten.getPointsOfThem()==8*2*2+15);
        assert (punten.getPointsOfWe()==11);
    }

    //@Test
    void getW_Wins() {
        //Werkt nog niet zonder xml file
        punten.increasePointsOfWe(11,false,false);
        punten.increasePointsOfThem(6,false,false);
        punten.increasePointsOfThem(25,false,false);
        punten.increasePointsOfWe(5,false,false);
        punten.increasePointsOfThem(30,true,false);
        punten.increasePointsOfWe(15,false,false);
        punten.increasePointsOfThem(5,true,false);

        //W:31,Z:91
    }

    //@Test
    void getZ_Wins() {
    }


    //TODO test file XML voor namen toe te kunnen voegen

}