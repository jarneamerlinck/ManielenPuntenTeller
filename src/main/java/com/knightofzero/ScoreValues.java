package com.knightofzero;

public enum ScoreValues {
    MAX_MULTIPLIER(4), SCOREMAXINPUT(30),SCOREMAXSTARTGAME(90), SCOREALLCARDS(60);
    private int score;

    public int getScore() {return score;}

    ScoreValues(int score) {
        this.score = score;
    }
}
