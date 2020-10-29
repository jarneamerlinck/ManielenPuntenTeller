package com.knightofzero;

import java.util.Scanner;

public class Points {
    private Xml file;
    private int W_Points;
    private int Z_Points;
    private int W_Wins;
    private int Z_Wins;
    private String [] Names_W;
    private String [] Names_Z;
    private int Multiplier;
    /**
     * The constructor initialize the private varriables
     * @param StartNew Is this het beginning of the game
     */
    public Points(Boolean StartNew) {
        Names_W = new String[2];
        Names_Z = new String[2];
        if (StartNew) {
            W_Points = 0;
            Z_Points = 0;
            W_Wins = 0;
            Z_Wins = 0;
            Z_Wins = 0;
            this.file = file;
        }
        else {
            Scanner Input = new Scanner(System.in);
            System.out.println("Home team wins: ");
            W_Wins = Input.nextInt();
            System.out.println("Out team wins:");
            Z_Wins = Input.nextInt();
            System.out.println("What is the current game stand(home-Out): ");
            String current  = Input.next();
            String Score[] = current.split("-");
            W_Points = Integer.parseInt(Score[0]);
            Z_Points = Integer.parseInt(Score[1]);
            System.out.printf("\nThe wins score is %d-%d, in the current game the score is %d-%d\n",W_Wins,Z_Wins,W_Points,Z_Points);
            Input.close();

        }
        Multiplier=1;


    }

    /**
     *This is used to show the program where the '.xml' file is located
     * @param path the relative or absolute path to the '.xml' file
     */
    public void xml(String path) {
        file = new Xml(path);
        System.out.println(path);
    }

    /**
     * returns the current points for the first team on
     * @return this returns a value of the type int
     */
    public int getW_Points() {
        return W_Points;
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
    public int getZ_Points() {
        return Z_Points;
    }
    public int getW_Wins() {
        return W_Wins;
    }
    public int getZ_Wins() {
        return Z_Wins;
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
        return String.format("The wins score is %d-%d, in the current game the score is %d-%d\n",W_Wins,Z_Wins,W_Points,Z_Points);
    }
    /**
     * Returns the names of the players in the first team
     * @return an array of 2 elements of the String type
     */
    public String[] getNames_W() {
        return Names_W;
    }

    /**
     * Setting the names of the player in the first team
     * @param name1_W name of the first player
     * @param name2_W name of the second player
     */
    public void setNames_W(String name1_W,String name2_W) {
        Names_W[0] = name1_W;
        Names_W[1] = name2_W;
        file.setW_Player1(name1_W);
        file.setW_Player2(name2_W);
    }

    /**
     * Returns the names of the players in the second team
     * @return an array of 2 elements of the String type
     */
    public String[] getNames_Z() {
        return Names_Z;
    }
    /**
     * Setting the names of the player in the second team
     * @param name1_Z name of the first player
     * @param name2_Z name of the second player
     */
    public void setNames_Z(String name1_Z,String name2_Z) {
        Names_Z[0] = name1_Z;
        Names_Z[1] = name2_Z;
        file.setZ_Player1(name1_Z);
        file.setZ_Player2(name2_Z);

    }

    /**
     * The constructor sets all of the variables for the object back to the original initialized values
     */
    public void reset() {
        W_Points = 0;
        Z_Points = 0;
        W_Wins = 0;
        Z_Wins = 0;
        Multiplier=1;
        //System.out.println("All scores/wins removed");
    }

    /**
     *Makes a new game if all the required values were assigned.
     * To fulfill the requirements the methods setNames_W() and setNames_Z() have to be used before this method is addressed
     * @return boolean that is true if all the required values were assigned
     *
     */
    public boolean newgame() {
        if (Names_W[0]==null||Names_W[1]==null||Names_Z[0]==null||Names_W[1]==null) {
            return false;
        }
        else if (Names_W[0]==""||Names_W[1]==""||Names_Z[0]==""||Names_W[1]=="") {
            return false;
        }
        else {
            file.makegame();
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
        if (Multiplier==2&&multiplier != 4){//alles met vorige keer dubbel
            Multiplier*=multiplier;
        }
        else{//
            Multiplier=multiplier;
        }
        if (number==0){
            Multiplier=2;
        }
        else if (number==30){
            Multiplier*=2;
            System.out.println(Multiplier);
            number=number*Multiplier;
        }
        else {
            number=number*Multiplier;
            Multiplier=1;
        }
        if (W_Points==0&&Z_Points==0&&number==120){
            number=90;
        }
        //Na de punten herrekening
        int total = number + ((who.equalsIgnoreCase("w")) ? W_Points:Z_Points);
        if( who.equalsIgnoreCase("w")){
            W_Points=total;
        }
        else {
            Z_Points=total;
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
            file.saveboom(String.format("%d-%d", W_Points,Z_Points));
            W_Wins +=1;
            W_Points=0;
            Z_Points=0;
        }
        else if (Who.equalsIgnoreCase("z")) {
            file.saveboom(String.format("%d-%d", W_Points,Z_Points));
            Z_Wins +=1;
            W_Points=0;
            Z_Points=0;
        }
        else {
            System.out.println("Error");
        }
        file.updatingRanking();

    }
}
