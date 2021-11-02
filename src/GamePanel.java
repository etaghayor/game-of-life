import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GamePanel extends JPanel {
    CellCircle cells;

    public GamePanel(CellCircle cells) {
        this.cells = cells;
        this.setBackground(Color.BLACK);
        initMouseListener();
    }


    private void initMouseListener() {
        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int x = e.getX(), y = e.getY();
                for (Cell cell : cells.getCells()) {
                    CellView view = cell.cellView;
                    if (inArea(x, y, view.getX(), view.getY(), view.getRadius())) {
                        cell.setLive(!cell.isAlive());
                        repaint();
                        revalidate();
                        break;
                    }
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
    }

    public void setCells(CellCircle cells) {
        this.cells = cells;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        CellView[] views = cells.getCellViews();
        for (CellView view : views) {
            view.paintComponent(g);
        }
    }


    private boolean inArea(int x, int y, int targetX, int targetY, int radius) {
        return Math.sqrt((x - targetX) * (x - targetX) + (y - targetY) * (y - targetY)) <= radius;
    }

}
