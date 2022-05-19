import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Fenetre extends JFrame {

    public Fenetre() {
        this.setSize(500,500);

        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JButton bouton = new JButton("clic moi :)");

        JPanel panneau = new JPanel();

        this.setContentPane(panneau);
        panneau.add(bouton);

        bouton.addActionListener(e -> System.out.println("clic"));

        this.setVisible(true);
    }
}
