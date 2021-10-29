import javax.swing.*;
import java.awt.*;

public class CellView extends JComponent {
    public int x, y;
    public boolean alive = true;
    public int size = 50;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (alive)
            g.setColor(Color.GREEN);
        else
            g.setColor(Color.RED);
        g.fillOval(x, y, size, size);
    }
}
