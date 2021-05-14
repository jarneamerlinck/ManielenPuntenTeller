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
        punten.increasePoints(setResultsOfW(15, false, false));
        assert (punten.getPointsOfWe()==15);
    }

    @Test
    void test60() {
        punten.increasePoints(setResultsOfW(30, false, false));
        assert (punten.getPointsOfWe()==60);
    }
    @Test
    void testMee() {
        punten.increasePoints(setResultsOfW(2, false, false));
        assert (punten.getPointsOfWe()==2);
        punten.increasePoints(setResultsOfZ(8, true, false));
        assert (punten.getPointsOfThem()==16);
    }
    @Test
    void testTegen() {
        assert (punten.getPointsOfWe()==0&&punten.getPointsOfThem()==0);
        punten.increasePoints(setResultsOfW(6, true, true));
        assert (punten.getPointsOfWe()==4*6);

        punten.increasePoints(setResultsOfZ(1, false, false));
        punten.increasePoints(setResultsOfZ(3, true, true));

        assert (punten.getPointsOfThem()==1+3*4);
    }
    @Test
    void testDubbelNormaal() {
        punten.increasePoints(setResultsOfW(11, false, false));
        punten.increasePoints(setResultsOfZ(15, false, false));

        assert (punten.getPointsOfThem()==15);
        assert (punten.getPointsOfWe()==11);

        punten.increasePoints(setResultsOfW(0, false, false));
        punten.increasePoints(setResultsOfZ(8, false, false));
        assert (punten.getPointsOfThem()==8*2+15);
        assert (punten.getPointsOfWe()==11);
    }
    @Test
    void testDubbelMee() {
        punten.increasePoints(setResultsOfW(11, false, false));
        punten.increasePoints(setResultsOfZ(15, false, false));
        assert (punten.getPointsOfThem()==15);
        assert (punten.getPointsOfWe()==11);
        punten.increasePoints(setResultsOfW(0, false, false));
        punten.increasePoints(setResultsOfZ(8, true, false));
        assert (punten.getPointsOfThem()==8*2*2+15);
        assert (punten.getPointsOfWe()==11);
    }
    @Test
    void testDubbelTegen() {
        punten.increasePoints(setResultsOfW(11, false, false));
        punten.increasePoints(setResultsOfZ(15, false, false));
        assert (punten.getPointsOfThem()==15);
        assert (punten.getPointsOfWe()==11);
        punten.increasePoints(setResultsOfW(0, false, false));
        punten.increasePoints(setResultsOfZ(8, true, true));
        assert (punten.getPointsOfThem()==8*2*2+15);
        assert (punten.getPointsOfWe()==11);
    }



    //@Test
    void getZ_Wins() {
    }
    SetResults setResultsOfW(int score, boolean mee, boolean tegen){
        SetResults setResults = new SetResults();
        setResults.setScore(30);
        setResults.setMee(false);
        setResults.setTegen(false);
        setResults.setWho("w");
        return setResults;
    }
    SetResults setResultsOfZ(int score, boolean mee, boolean tegen){
        SetResults setResults = new SetResults();
        setResults.setScore(30);
        setResults.setMee(false);
        setResults.setTegen(false);
        setResults.setWho("z");
        return setResults;
    }

    //TODO test file XML voor namen toe te kunnen voegen

}