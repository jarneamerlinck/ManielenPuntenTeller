package com.knightofzero;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class Gui extends JFrame {
    public JTextField currentScore;
    private JTextArea currentScoreText;
    private JButton addscore;
    private JButton newGame;
    private JFileChooser filefind;
    private JButton filebutton;

    /**
     *Makes the basis GUI for the program
     *
     * @param score een object van het type points
     */
    public Gui(Points score) {
        super("Manielen Punten Teller");

        setLayout(new FlowLayout());
        AddPoints addPoints = new AddPoints(score);
        addPoints.frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        addPoints.frame.pack();
        currentScore = new JTextField();

        addPoints.frame.setSize(300,100);
        addPoints.frame.setVisible(false);
        currentScoreText = new JTextArea("W-Z");
        currentScoreText.setEditable(false);
        add(currentScoreText,BorderLayout.NORTH);
        addscore = new JButton("+");
        add(addscore,BorderLayout.NORTH);
        addscore.hide();
        newGame= new JButton("New Game");
        add(newGame,BorderLayout.NORTH);
        newGame.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                filebutton = new JButton("Open");
                filefind = new JFileChooser();
                filefind.setCurrentDirectory(new java.io.File("src/"));
                filefind.setDialogTitle("Chose a supported XML file");
                filefind.setFileSelectionMode(JFileChooser.FILES_ONLY);
                if (filefind.showOpenDialog(filebutton)==JFileChooser.APPROVE_OPTION) {

                }
                score.xml(filefind.getSelectedFile().getAbsolutePath());
                String name[] = {JOptionPane.showInputDialog("W: First Player"),JOptionPane.showInputDialog("W: Second Player"),JOptionPane.showInputDialog("Z: First Player"),JOptionPane.showInputDialog("Z: Second Player")};
                System.out.println(name[0] + " en " + name[1] + " vs "+ name[2] + " en " + name[3]);
                try {
                    if (name[0]==null||name[0]==null||name[2]==null||name[3]==null) {
                        JOptionPane.showMessageDialog(rootPane, "There has been a name/names not entered");
                    }
                    else if (name[0].equalsIgnoreCase("")||name[1].equalsIgnoreCase("")||name[2].equalsIgnoreCase("")||name[3].equalsIgnoreCase("")) {
                        JOptionPane.showMessageDialog(rootPane, "There has been a name/names not entered");
                    }
                    else {
                        score.setNames_W(name[0],name[1]);
                        score.setNames_Z(name[2],name[3]);
                        JOptionPane.showConfirmDialog(rootPane, String.format("The teams are %s en %s vs. %s en %s", score.getNamesOfPlayersInWe()[0],score.getNamesOfPlayersInWe()[1],score.getNamesOfPlayersInThem()[0],score.getNamesOfPlayersInThem()[1]), "Continu is correct", JOptionPane.OK_CANCEL_OPTION);
                        currentScoreText.setText(String.format("%s en %s vs. %s en %s", score.getNamesOfPlayersInWe()[0],score.getNamesOfPlayersInWe()[1],score.getNamesOfPlayersInThem()[0],score.getNamesOfPlayersInThem()[1]));
                        newGame.hide();
                        remove(newGame);
                        score.newgame();
                        addscore.show();

                    }
                } catch (Exception e) {
                    System.out.println(e);
                    JOptionPane.showMessageDialog(rootPane, "There has been a name/names not entered");
                }


            }
        });

        currentScore.setText(String.format("Wins: %d-%d\n\nCurrent: %d-%d", score.getNumberOfWonGamesOfWe(),score.getNumberOfWonGamesOfThem(),score.getPointsOfWe(),score.getPointsOfThem()));
        add(currentScore,BorderLayout.CENTER);
        addscore.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                addPoints.frame.setLocationRelativeTo(rootPane);
                addPoints.frame.setVisible(true);
                currentScore.setText(String.format("Wins: %d-%d\n\nCurrent: %d-%d", score.getNumberOfWonGamesOfWe(),score.getNumberOfWonGamesOfThem(),score.getPointsOfWe(),score.getPointsOfThem()));

            }
        });
    }
}
