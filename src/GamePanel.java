import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    CellView[] cells;

    public GamePanel(CellView[] cells) {
        this.cells = cells;

    }

    public void setCells(CellView[] cells) {
        this.cells = cells;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = 0; i < cells.length; i++) {
            cells[i].paintComponent(g);
        }
    }
}
