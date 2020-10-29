package com.knightofzero;

import javax.swing.*;

public class App {
  /**
   *This is the start class for the program that keeps track of the point for the card game manielen
   * @author Jarne Amerlinck
   * @version 1.0
   */
  public static void main(String[] args) {
    System.out.println("WIP");

    Points score = new Points(true);
    Gui frame = new Gui(score);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(600,300);
    frame.setVisible(true);



  }
}