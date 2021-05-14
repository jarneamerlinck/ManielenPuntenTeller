package com.knightofzero;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AddPoints {
    public JFrame frame;
    private JRadioButton W;
    private JRadioButton Z;
    private JTextField scorefield;
    private JRadioButton mee;
    private JRadioButton tegen;
    private JButton submit;
    private ButtonGroup whoGroup;
    private int number;

    /**
     * Makes the GUI appear and functional
     * @param score object of the type score
     */
    public AddPoints(Points score) {
        frame=new JFrame("Add score");
        frame.setLayout(new FlowLayout());
        W  = new JRadioButton("W",true);
        Z = new JRadioButton("Z",false);
        whoGroup = new ButtonGroup();
        whoGroup.add(W);
        whoGroup.add(Z);
        scorefield = new JTextField(24);
        frame.add(scorefield);
        scorefield.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                pointsChecker(score);
            }
        });
        frame.add(W);
        frame.add(Z);
        mee = new JRadioButton("mee");
        frame.add(mee);
        tegen = new JRadioButton("tegen");
        frame.add(tegen);
        tegen.setEnabled(false);
        mee.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                if(mee.isSelected()){
                    tegen.setEnabled(true);
                }
                else{
                    tegen.setSelected(false);
                    tegen.setEnabled(false);

                }

            }
        });
        submit = new JButton("Submit");
        frame.add(submit);
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                pointsChecker(score);
            }
        });
    }
    /**
     * Private method that checks if the input has a correct value
     * The correct value is a int beteen 0 and 30 limits included
     * @param score is a String with a int inside
     */
    private void pointsChecker(Points score) {
        boolean correct_Form=true;;
        try {
            number = Integer.parseInt(scorefield.getText());

        } catch (Exception e) {
            reset(true);
            JOptionPane.showMessageDialog(frame, "Not a correct input");
            reset(false);
            correct_Form=false;


        }
        if (number <0) {
            reset(false);
            JOptionPane.showMessageDialog(frame, "Score can't be lower than 0");
        }
        else if (number >30) {
            reset(false);
            JOptionPane.showMessageDialog(frame, "Score can't be greater than 30");

        }
        else if (correct_Form){
            SetResults setResults = new SetResults();
            setResults.setMee(mee.isSelected());
            setResults.setTegen(tegen.isSelected());
            setResults.setWho((W.isSelected()) ? "w":"z");
            setResults.setScore(number);
            score.increasePoints(setResults);

            /*
            if(W.isSelected()) {

                score.increasePointsOfWe(Number,mee.isSelected(),tegen.isSelected());
            }
            else if(Z.isSelected()) {
                score.increasePointsOfThem(Number,mee.isSelected(),tegen.isSelected());
            }

            */
            mee.setSelected(false);
            tegen.setSelected(false);
            reset(true);
            JOptionPane.showMessageDialog(frame, score.toString());


        }
    }
    private void reset(boolean close){
        scorefield.setText("");
        tegen.setEnabled(false);
        if (close)frame.hide();
        else frame.show();
    }
}
