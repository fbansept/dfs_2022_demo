package edu.fbansept;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Main extends JFrame {

    public Main() {
        this.setSize(500,500);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JButton bouton = new JButton("Clic moi !");
        JButton bouton2 = new JButton("Me clic pas");

        bouton.addActionListener(e -> System.out.println("Tu m'as cliqué :)"));
        bouton2.addActionListener(e -> System.out.println("Tu m'as cliqué :("));


        JPanel panneau = new JPanel();
        panneau.add(bouton);
        panneau.add(bouton2);

        this.setContentPane(panneau);

        this.setVisible(true);
    }

    public static void main(String[] args) {
	    new Main();
    }


}
