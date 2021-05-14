package com.knightofzero;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.Scanner;

import static com.knightofzero.ScoreValues.*;

public class Points {
    private Xml xmlFile;
    private int pointsOfWe;
    private int pointsOfThem;
    private int numberOfWonGamesOfWe;
    private int numberOfWonGamesOfThem;
    private String [] NamesOfPlayersInWe;
    private String [] NamesOfPlayersInThem;
    private int pointMultiplier;
    private Scanner input;

    public Points() {
        this(true);
    }
    public Points(Boolean startNew) {
        giveAllVariablesStartValues();
        if (!startNew) {
            getStartValuesFromInput();
            printCurrentGame();
        }
    }

    private void getStartValuesFromInput() {
        input = new Scanner(System.in);
        numberOfWonGamesOfWe = getInputInt("Home team wins: ");
        numberOfWonGamesOfThem = getInputInt("Out team wins: ");
        String score[] = getInputScore("What is the current game stand(home-Out): ");
        pointsOfWe = Integer.parseInt(score[0]);
        pointsOfThem = Integer.parseInt(score[1]);
        input.close();
    }

    private void printCurrentGame() {
        System.out.printf("\nThe wins score is %d-%d, in the current game the score is %d-%d\n", numberOfWonGamesOfWe, numberOfWonGamesOfThem, pointsOfWe, pointsOfThem);
    }

    private int getInputInt(String question) {
        System.out.println(question);
        return input.nextInt();
    }
    private String[] getInputScore(String question) {
        System.out.println(question);
        return input.next().split("-");
    }

    private void giveAllVariablesStartValues() {
        NamesOfPlayersInWe = new String[2];
        NamesOfPlayersInThem = new String[2];
        xmlFile = null;

        pointsOfWe = 0;
        pointsOfThem = 0;
        numberOfWonGamesOfWe = 0;
        numberOfWonGamesOfThem = 0;
        pointMultiplier = 1;
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

    public void increasePoints(SetResults setResults) {
        addPoints(setResults);
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


    private void addPoints(SetResults setResults) {
        if (isCorrectMultiplierBooleans(setResults.isMee(), setResults.isTegen())) {
            int score = calculateScore(setResults);
            addScoreToPoints(score, setResults.getWho());
        }
    }
    private int calculateScoreRuleMaxAtStartGame(int score, boolean mee){
        if (isMaxScoreFromStartGame(score, mee)){
            pointMultiplier = 1;
            return SCOREMAXSTARTGAME.getScore();
        }
        return score;
    }
    private int calculateRuleScoreNull(int score, SetResults setResults){
        if(isScoreNull(score)){
            pointMultiplier *= 2;
        }
        else if (isMultiplierBeforeGameMax()){
            pointMultiplier = 1;
            return score * 4;
        }
        else if(isScoreMaxAndNotMee(score, setResults.isMee())){
            return calculateScoreWithMultiplier(score, setResults.isTegen());
        }
        return score;
    }
    private int calculateScore(SetResults setResults) {
        int score = calculateScoreRuleMaxAtStartGame(setResults.getScore(), setResults.isMee());
        score = calculateRuleScoreNull(score, setResults);
        if (isScoreMax(score)){
            return getScoreIfScoreMax(score, setResults.isMee(), setResults.isTegen());
        }
        return score;
    }

    private boolean isScoreMax(int score) {
        return score == SCOREMAXINPUT.getScore();
    }

    private int getScoreIfScoreMax(int score, boolean mee, boolean tegen) {
        return (mee) ? calculateScoreWithMultiplier(score *2, tegen) : 60;
    }

    private boolean isScoreMaxAndNotMee(int score, boolean mee) {
        return mee&&score!= SCOREMAXINPUT.getScore();
    }

    private int calculateScoreWithMultiplier(int score, boolean tegen) {
        int currentMultiplier = pointMultiplier*2;
        if(tegen){
            currentMultiplier = MAX_MULTIPLIER.getScore();
        }
        score *= currentMultiplier;
        return score;
    }

    private boolean isScoreNull(int score) {
        return score == 0;
    }

    private boolean isMultiplierBeforeGameMax() {
        return pointMultiplier == MAX_MULTIPLIER.getScore();
    }

    private boolean isMaxScoreFromStartGame(int score, boolean mee) {
        return pointsOfWe == 0 && pointsOfThem == 0 && isScoreMax(score) && mee;
    }


    private boolean isCorrectMultiplierBooleans(boolean mee, boolean tegen) {
        return !(!mee&&tegen);
    }


    private void addScoreToPoints(int score, String who) {
        int total = score + ((who.equalsIgnoreCase("w")) ? pointsOfWe : pointsOfThem);
        if( who.equalsIgnoreCase("w")){
            pointsOfWe = total;
        }
        else {
            pointsOfThem = total;
        }
        if (total>=101) {
            EndCurrentGame(who);
        }
    }




    /**
     * Private method that adds 1 to the counter of the won games for both teams
     * @param Who
     */
    private void EndCurrentGame(String Who) {
        if (Who.equalsIgnoreCase("w")) {
            SaveBoom();
            numberOfWonGamesOfWe +=1;
        }
        else if (Who.equalsIgnoreCase("z")) {
            SaveBoom();
            numberOfWonGamesOfThem +=1;
        }
        else {
            System.out.println("Error");
        }
        xmlFile.updatingRanking();

    }

    private void SaveBoom() {
        xmlFile.saveboom(String.format("%d-%d", pointsOfWe, pointsOfThem));
        pointsOfWe =0;
        pointsOfThem =0;
    }

    /**
     * Returns a current stand of the games
     * @return value of the type String
     */
    @Override
    public String toString() {
        return String.format("The wins score is %d-%d, in the current game the score is %d-%d\n", numberOfWonGamesOfWe, numberOfWonGamesOfThem, pointsOfWe, pointsOfThem);
    }


}
