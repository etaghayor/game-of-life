import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.*;

public class View extends JFrame {
	// Bouton
	Bouton jeu; // jeu

	// JPanel / JLabel
	JPanel menu;
	JPanel jouer;
	JPanel entre;
	JTextField nombre;
	Cell[] list;
	Controller controller;


	public View(Controller controller, Cell[] l) {
		this.setTitle("Jeu de la vie"); // Titre de la fenetre
		this.setSize(400, 600); // Taille de la fenetre
		this.setVisible(true); // visible
		this.setResizable(true); // taille fixe
		setDefaultCloseOperation(EXIT_ON_CLOSE); // exit
		setLocationRelativeTo(null);

		this.controller = controller;
		this.list = l;
		// JPanel boutons / show
		menu = new JPanel(new GridLayout(4, 0));
		jouer = new JPanel();
		entre = new JPanel();
		jeu = new Bouton("jouer");
		menu.add(jouer);
		JLabel title = new JLabel("Choisissez le nombre de cellule :");
		title.setForeground(Color.white);
		title.setHorizontalAlignment(JLabel.CENTER);
		menu.add(title);
		jouer.add(jeu.getBouton());
		jouer.setBackground(Color.BLACK);
		entre.setBackground(Color.BLACK);
		menu.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		menu.setBackground(Color.BLACK);
		JTextField nombre = new JTextField();
		nombre.setPreferredSize(new Dimension(150, 25));
		entre.add(nombre);
		menu.add(entre);
		menu.add(jouer);
		this.add(menu);
		this.validate();

		// Boutons
		jeu.getBouton().addActionListener((ActionEvent e) -> {
			// VERIFICATION N INT POUR LE NOMBRE DE CELLULES
			if (!isInt(nombre.getText())) {
				return;
			}
			// SI N EST UN INT ALORS ON CREER N CELLULES
			//setExtendedState(JFrame.MAXIMIZED_BOTH);
			setLocationRelativeTo(null);
			setSize(1600, 900);
			
			// CODE
			this.menu.setVisible(false);
			this.jouer.setVisible(false);
			CellCircle cells = new CellCircle(300, list.length, 1080 / 2, 720 / 2, list);
			GamePanel gamePanel = new GamePanel(cells.getCellViews());
			this.add(gamePanel);
			this.repaint();
			this.revalidate();
			Thread t = new Thread(() -> {
				while (true) {
					CellCircle tmp = new CellCircle(300, list.length, 1080 / 2, 720 / 2, list);
					gamePanel.setCells(tmp.getCellViews());
					gamePanel.repaint();
					gamePanel.revalidate();
					this.repaint();
					this.revalidate();
					list = controller.startGameOfLife(list);
					try {
						Thread.sleep(800);
					} catch (InterruptedException ex) {
						ex.printStackTrace();
					}

				}
			});
			t.start();
		});
	}

	public static boolean isInt(String str) {
		try {
			@SuppressWarnings("unused")
			int x = Integer.parseInt(str);
			return true; // String is an Integer
		} catch (NumberFormatException e) {
			return false; // String is not an Integer
		}
	}
}