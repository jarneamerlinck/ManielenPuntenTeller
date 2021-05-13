package com.knightofzero;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.Scanner;

public class Points {
    private Xml xmlFile;
    private int pointsOfWe;
    private int pointsOfThem;
    private int numberOfWonGamesOfWe;
    private int numberOfWonGamesOfThem;
    private String [] NamesOfPlayersInWe;
    private String [] NamesOfPlayersInThem;
    private int pointMultiplier;
    /**
     * The constructor initialize the private varriables
     * @param StartNew Is this het beginning of the game
     */
    public Points() {
        this(true);
    }
    public Points(Boolean StartNew) {
        NamesOfPlayersInWe = new String[2];
        NamesOfPlayersInThem = new String[2];
        if (StartNew) {
            pointsOfWe = 0;
            pointsOfThem = 0;
            numberOfWonGamesOfWe = 0;
            numberOfWonGamesOfThem = 0;
            numberOfWonGamesOfThem = 0;
            this.xmlFile = xmlFile;
        }
        else {
            Scanner Input = new Scanner(System.in);
            System.out.println("Home team wins: ");
            numberOfWonGamesOfWe = Input.nextInt();
            System.out.println("Out team wins:");
            numberOfWonGamesOfThem = Input.nextInt();
            System.out.println("What is the current game stand(home-Out): ");
            String current  = Input.next();
            String Score[] = current.split("-");
            pointsOfWe = Integer.parseInt(Score[0]);
            pointsOfThem = Integer.parseInt(Score[1]);
            System.out.printf("\nThe wins score is %d-%d, in the current game the score is %d-%d\n", numberOfWonGamesOfWe, numberOfWonGamesOfThem, pointsOfWe, pointsOfThem);
            Input.close();

        }
        pointMultiplier =1;


    }

    /**
     *This is used to show the program where the '.xml' file is located
     * @param path the relative or absolute path to the '.xml' file
     */
    public void xml(String path) {
        try {
            xmlFile = new Xml(path);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(path);
    }

    /**
     * returns the current points for the first team on
     * @return this returns a value of the type int
     */
    public int getPointsOfWe() {
        return pointsOfWe;
    }
    /**
     * adding points to the first team
     * @param adding an int value that needs to be added
     * @param mee a boolean that multiplied by 2
     * @param tegen a boolean that multiplied by 2
     */
    public void addW_Points(int adding,boolean mee,boolean tegen) {

        addPoints(adding,mee,tegen,"W");
    }
    /**
     * returns the current points for the second team on
     * @return this returns a value of the type int
     */
    public int getPointsOfThem() {
        return pointsOfThem;
    }
    public int getNumberOfWonGamesOfWe() {
        return numberOfWonGamesOfWe;
    }
    public int getNumberOfWonGamesOfThem() {
        return numberOfWonGamesOfThem;
    }
    /**
     * adding points to the second team
     * @param adding an int value that needs to be added
     * @param mee a boolean that multiplied by 2
     * @param tegen a boolean that multiplied by 2
     */
    public void addZ_Points(int adding,boolean mee,boolean tegen) {

        addPoints(adding,mee,tegen,"Z");
    }
    /**
     * Returns a current stand of the games
     * @return value of the type String
     */
    @Override
    public String toString() {
        return String.format("The wins score is %d-%d, in the current game the score is %d-%d\n", numberOfWonGamesOfWe, numberOfWonGamesOfThem, pointsOfWe, pointsOfThem);
    }
    /**
     * Returns the names of the players in the first team
     * @return an array of 2 elements of the String type
     */
    public String[] getNamesOfPlayersInWe() {
        return NamesOfPlayersInWe;
    }

    /**
     * Setting the names of the player in the first team
     * @param name1_W name of the first player
     * @param name2_W name of the second player
     */
    public void setNames_W(String name1_W,String name2_W) {
        NamesOfPlayersInWe[0] = name1_W;
        NamesOfPlayersInWe[1] = name2_W;
        xmlFile.setW_Player1(name1_W);
        xmlFile.setW_Player2(name2_W);
    }

    /**
     * Returns the names of the players in the second team
     * @return an array of 2 elements of the String type
     */
    public String[] getNamesOfPlayersInThem() {
        return NamesOfPlayersInThem;
    }
    /**
     * Setting the names of the player in the second team
     * @param name1_Z name of the first player
     * @param name2_Z name of the second player
     */
    public void setNames_Z(String name1_Z,String name2_Z) {
        NamesOfPlayersInThem[0] = name1_Z;
        NamesOfPlayersInThem[1] = name2_Z;
        xmlFile.setZ_Player1(name1_Z);
        xmlFile.setZ_Player2(name2_Z);

    }

    /**
     * The constructor sets all of the variables for the object back to the original initialized values
     */
    public void reset() {
        pointsOfWe = 0;
        pointsOfThem = 0;
        numberOfWonGamesOfWe = 0;
        numberOfWonGamesOfThem = 0;
        pointMultiplier =1;
        //System.out.println("All scores/wins removed");
    }

    /**
     *Makes a new game if all the required values were assigned.
     * To fulfill the requirements the methods setNames_W() and setNames_Z() have to be used before this method is addressed
     * @return boolean that is true if all the required values were assigned
     *
     */
    public boolean newgame() {
        if (NamesOfPlayersInWe[0]==null|| NamesOfPlayersInWe[1]==null|| NamesOfPlayersInThem[0]==null|| NamesOfPlayersInWe[1]==null) {
            return false;
        }
        else if (NamesOfPlayersInWe[0]==""|| NamesOfPlayersInWe[1]==""|| NamesOfPlayersInThem[0]==""|| NamesOfPlayersInWe[1]=="") {
            return false;
        }
        else {
            xmlFile.makegame();
            return true;
        }

    }

    /**
     * This method is private and used for adding and writing to the .xml file.
     * This method also decides is the 'boom' has ended
     * @param number a value of the type int
     * @param mee a boolean that multiplied by 2
     * @param tegen a boolean that multiplied by 2
     * @param who a String that indicated who has won the points
     */
    private void addPoints(int number,boolean mee,boolean tegen,String who){
        int multiplier=1;
        if (mee&&!tegen){
            multiplier=2;
        }
        else if (mee&&tegen){
            multiplier=4;
        }
        countPoints(number,multiplier,who);
    }
    private void countPoints(int number,int multiplier,String who) {
        //TODO updating
        //Multiplier=multiplier;s
        if (pointMultiplier ==2&&multiplier != 4){//alles met vorige keer dubbel
            pointMultiplier *=multiplier;
        }
        else{//
            pointMultiplier =multiplier;
        }
        if (number==0){
            pointMultiplier =2;
        }
        else if (number==30){
            pointMultiplier *=2;
            System.out.println(pointMultiplier);
            number=number* pointMultiplier;
        }
        else {
            number=number* pointMultiplier;
            pointMultiplier =1;
        }
        if (pointsOfWe ==0&& pointsOfThem ==0&&number==120){
            number=90;
        }
        //Na de punten herrekening
        int total = number + ((who.equalsIgnoreCase("w")) ? pointsOfWe : pointsOfThem);
        if( who.equalsIgnoreCase("w")){
            pointsOfWe =total;
        }
        else {
            pointsOfThem =total;
        }
        if (total>=101) {
            GameEnd(who);
        }
    }

    /**
     * print an error
     */
    private void error() {
        System.out.println("Error");

    }

    /**
     * Private method that adds 1 to the counter of the won games for both teams
     * @param Who
     */
    private void GameEnd(String Who) {
        if (Who.equalsIgnoreCase("w")) {
            xmlFile.saveboom(String.format("%d-%d", pointsOfWe, pointsOfThem));
            numberOfWonGamesOfWe +=1;
            pointsOfWe =0;
            pointsOfThem =0;
        }
        else if (Who.equalsIgnoreCase("z")) {
            xmlFile.saveboom(String.format("%d-%d", pointsOfWe, pointsOfThem));
            numberOfWonGamesOfThem +=1;
            pointsOfWe =0;
            pointsOfThem =0;
        }
        else {
            System.out.println("Error");
        }
        xmlFile.updatingRanking();

    }
}
