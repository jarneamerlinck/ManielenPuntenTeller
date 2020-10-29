package com.knightofzero;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PointsTest {
    private static Points punten;

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
        assert (punten.getW_Points()==0);
        assert (punten.getZ_Points()==0);
        assert (punten.getZ_Points()==0);
        assert (punten.getZ_Wins()==0);
    }


    @Test
    void getW_Points() {
        assert (punten.getW_Points()==0);
    }

    @Test
    void addW_Points() {
        punten.addW_Points(15,false,false);
        assert (punten.getW_Points()==15);
    }

    @Test
    void test60() {
        punten.addW_Points(30,false,false);
        assert (punten.getW_Points()==60);
    }
    @Test
    void testMee() {
        punten.addW_Points(1,true,false);
        assert (punten.getW_Points()==2);
        punten.addZ_Points(8,true,false);
        assert (punten.getZ_Points()==16);
    }
    @Test
    void testTegen() {
        assert (punten.getW_Points()==0&&punten.getZ_Points()==0);
        punten.addW_Points(6,true,true);
        assert (punten.getW_Points()==4*6);

        punten.addZ_Points(1,false,false);
        punten.addZ_Points(3,true,true);
        assert (punten.getZ_Points()==1+3*4);
    }
    @Test
    void testDubbelNormaal() {
        punten.addW_Points(11,false,false);
        punten.addZ_Points(15,false,false);
        assert (punten.getZ_Points()==15);
        assert (punten.getW_Points()==11);
        punten.addW_Points(0,false,false);
        punten.addZ_Points(8,false,false);
        assert (punten.getZ_Points()==16+15);
        assert (punten.getW_Points()==11);
    }

    @Test
    void getW_Wins() {
    }

    @Test
    void getZ_Wins() {
    }

}