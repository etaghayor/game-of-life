import javax.swing.JButton;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Bouton extends JButton {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/*
	 * Cette classe permet de ne pas a avoir a configurer a chaque fois les boutons
	 */
	JButton b;

	public Bouton(String s) {
		b = new JButton(s);
		b.setBackground(Color.CYAN); // couleur fond
		b.setForeground(Color.BLACK);// Couleur ecriture
		b.setPreferredSize(new Dimension(100, 50));
		b.setMaximumSize(new Dimension(100, 50));
		b.setMinimumSize(new Dimension(100, 50));
		b.setFocusPainted(false);
	}

	public JButton getBouton() { // Permet de recuperer le bouton a utilisé
		return this.b;
	}
}