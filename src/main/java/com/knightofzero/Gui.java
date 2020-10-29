package com.knightofzero;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class Gui extends JFrame {
    public JTextField currentScore;
    private JTextArea w;
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
        AddPoints ad = new AddPoints(score);
        ad.frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ad.frame.pack();
        currentScore = new JTextField();

        ad.frame.setSize(300,100);
        ad.frame.setVisible(false);
        w = new JTextArea("W-Z");
        w.setEditable(false);
        add(w,BorderLayout.NORTH);
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
                        JOptionPane.showConfirmDialog(rootPane, String.format("The teams are %s en %s vs. %s en %s", score.getNames_W()[0],score.getNames_W()[1],score.getNames_Z()[0],score.getNames_Z()[1]), "Continu is correct", JOptionPane.OK_CANCEL_OPTION);
                        w.setText(String.format("%s en %s vs. %s en %s", score.getNames_W()[0],score.getNames_W()[1],score.getNames_Z()[0],score.getNames_Z()[1]));
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

        currentScore.setText(String.format("Wins: %d-%d\n\nCurrent: %d-%d", score.getW_Wins(),score.getZ_Wins(),score.getW_Points(),score.getZ_Points()));
        add(currentScore,BorderLayout.CENTER);
        addscore.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                ad.frame.setLocationRelativeTo(rootPane);
                ad.frame.setVisible(true);
                currentScore.setText(String.format("Wins: %d-%d\n\nCurrent: %d-%d", score.getW_Wins(),score.getZ_Wins(),score.getW_Points(),score.getZ_Points()));

            }
        });
    }
}
