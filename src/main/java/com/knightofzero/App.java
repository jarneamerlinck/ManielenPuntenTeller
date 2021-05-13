package com.knightofzero;

import org.xml.sax.SAXException;

import javax.swing.*;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class App {
  /**
   *This is the start class for the program that keeps track of the point for the card game manielen
   * @author Jarne Amerlinck
   *
   */
  public static void main(String[] args) {
    System.out.println("WIP");
    try {
      Xml xmlFile = new Xml("dataFiles/TestFile.xml");
      xmlFile.getInformation("Jarne");
    } catch (ParserConfigurationException e) {
      e.printStackTrace();
    } catch (SAXException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }


    Points score = new Points(true);
    Gui frame = new Gui(score);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(600,300);
    frame.setVisible(true);



  }
}