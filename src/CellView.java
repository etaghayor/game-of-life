import javax.swing.*;
import java.awt.*;

public class CellView extends JComponent {
    private int x, y;
    private boolean alive = true;
    private int radius = 50;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (alive)
            g.setColor(Color.GREEN);
        else
            g.setColor(Color.RED);
        g.fillOval(x, y, radius, radius);
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public boolean isAlive() {
        return alive;
    }
}
