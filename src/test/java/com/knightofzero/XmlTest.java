package com.knightofzero;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class XmlTest {
    private static Xml xmlFile;


    @Test
    void getFileWrongLocation(){
        try {
            new Xml("dataFiles/DoesNotExist.xml");
        } catch (SAXException | IOException |ParserConfigurationException e) {
            System.out.println(e.getMessage());
            assertTrue(true);
        }
    }
    @Test
    void getFileCorrectLocation(){
        try {
            xmlFile = new Xml("dataFiles/TestFile.xml");
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
            fail();
        } catch (SAXException e) {
            e.printStackTrace();
            fail();
        } catch (IOException e) {
            e.printStackTrace();
            fail();
        }
    }
    void SetupPlayers(){
        xmlFile.setW_Player1("Alexander");
        xmlFile.setW_Player2("Rubben");
        xmlFile.setZ_Player1("Willem");
        xmlFile.setZ_Player2("Jochen");
    }
    @Test
    void testPlayerNames() {
        SetupPlayers();
        assert ("Alexander".equalsIgnoreCase(xmlFile.getW_Player1()));
        assert ("Rubben".equalsIgnoreCase(xmlFile.getW_Player2()));
        assert ("Willem".equalsIgnoreCase(xmlFile.getZ_Player1()));
        assert ("Jochen".equalsIgnoreCase(xmlFile.getZ_Player2()));
    }
    @Test
    void testNumberOfPlayers() {
        assert (xmlFile.getNumberofPlayers()==5);
    }
    @Test
    void TestPlayerInformation(){

    }

    @Test
    void makegame() {
    }
}