import javax.swing.*;
import java.awt.event.*;


public class View extends JFrame {
    //Bouton
    Bouton niveau1; // 5 cellules
    Bouton niveau2; // 10 cellules
    Bouton niveau3; // 20 cellules

    //JPanel
    JPanel menu;

    Cell[] list;
    Controller controller;

    public View(Controller controller, Cell[] l) {
        this.setTitle("Jeu de la vie"); // Titre de la fenetre
        this.setSize(1080, 720); // Taille de la fenetre
        this.setVisible(true); // visible
        this.setResizable(true); // taille fixe
        setDefaultCloseOperation(EXIT_ON_CLOSE); // exit

        this.controller = controller;
        this.list = l;
        //JPanel boutons
        menu = new JPanel();
        niveau1 = new Bouton("5 cellules");
        niveau2 = new Bouton("10 cellules");
        niveau3 = new Bouton("20 cellules");
        menu.add(niveau1.getBouton());
        menu.add(niveau2.getBouton());
        menu.add(niveau3.getBouton());
        this.add(menu);
        this.validate();


        //Boutons
        niveau1.getBouton().addActionListener((ActionEvent e) -> {

            this.menu.setVisible(false);
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
        niveau2.getBouton().addActionListener((ActionEvent e) -> {
            System.out.println("test");
        });
        niveau3.getBouton().addActionListener((ActionEvent e) -> {
            System.out.println("test");
        });

    }
}