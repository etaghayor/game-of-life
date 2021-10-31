import javax.swing.*;
import javax.swing.text.html.ImageView;

import java.awt.*;
import java.awt.event.*;

public class View extends JFrame {
    // Bouton
    Bouton jeu; // jeu

    final static int WIDTH = 650, HEIGHT = 650;
    // JPanel / JLabel
    JPanel menu;
    JPanel jouer;
    JPanel entre;
    JTextField nombre;
    Cell[] list;
    Controller controller;

    public View(Controller controller) {
        this.setTitle("Jeu de la vie"); // Titre de la fenetre
        this.setSize(400, 600); // Taille de la fenetre
        this.setVisible(true); // visible
        this.setResizable(true); // taille fixe
        setDefaultCloseOperation(EXIT_ON_CLOSE); // exit
        setLocationRelativeTo(null);
        //icon
        ImageIcon logo=new ImageIcon("index.jpeg");
        this.setIconImage(logo.getImage());
        this.controller = controller;
//        this.list = l;
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
        nombre = new JTextField();
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

            controller.setN(Integer.parseInt(nombre.getText()));

            Cell[] firstCells = new Cell[controller.getN()];

            for (int i = 1; i < controller.getN(); i++) {
                firstCells[i] = new Cell(0);
            }

            firstCells[0] = new Cell(1);
            this.list = firstCells;
            // setExtendedState(JFrame.MAXIMIZED_BOTH);
            setLocationRelativeTo(null);
            setSize(WIDTH, HEIGHT);
            // CODE
            this.menu.setVisible(false);
            this.jouer.setVisible(false);
            CellCircle cells = new CellCircle((int) (HEIGHT / 3), list.length, (int) (WIDTH / 2.3),
                    (int) (HEIGHT / 2.3), list);
            GamePanel gamePanel = new GamePanel(cells.getCellViews());
            gamePanel.setBackground(Color.BLACK);
            this.add(gamePanel);
            this.repaint();
            this.revalidate();
            Thread t = new Thread(() -> {
                while (true) {
                    CellCircle tmp = new CellCircle((int) (HEIGHT / 3), list.length, (int) (WIDTH / 2.3),
                            (int) (HEIGHT / 2.3), list);
                    gamePanel.setCells(tmp.getCellViews());
                    gamePanel.repaint();
                    gamePanel.revalidate();
                    this.repaint();
                    this.revalidate();

                    //JOptionPane
                    int x=-1;
                    String [] option ={"choose a new configuration","Quit"};
                    list = controller.startGameOfLife(list);
                    JLabel j=new JLabel("Cycle detected",SwingConstants.CENTER);
                    j.setFont(new Font("Serif", Font.BOLD, 20));
                    if(controller.getres()==true){
                        //JOptionPane.showOptionDialog(null,"cycle detected",JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,option);
                        x =JOptionPane.showOptionDialog(null, j,
                                "",
                                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, option,option[0]);
                    }
                    if(x==1){
                        System.exit(0);
                    }else if(x==0){
                        JOptionPane.getRootFrame().dispose();
                        /*
                        this.remove(gamePanel);
                        this.add(menu);
                        this.repaint();
                        this.revalidate();
                         */
                    }else{

                    }
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