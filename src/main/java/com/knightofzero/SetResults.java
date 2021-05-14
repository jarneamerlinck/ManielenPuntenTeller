package com.knightofzero;

public class SetResults {
    private boolean mee;
    private boolean tegen;
    private String who;
    private int score;

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public SetResults() {
    }

    public boolean isMee() {
        return mee;
    }

    public void setMee(boolean mee) {
        this.mee = mee;
    }

    public boolean isTegen() {
        return tegen;
    }

    public void setTegen(boolean tegen) {
        this.tegen = tegen;
    }

    public String getWho() {
        return who;
    }

    public void setWho(String who) {
        this.who = who;
    }
}
