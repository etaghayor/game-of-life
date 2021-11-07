import java.util.Random;


public class CellCircle {
    private int h, k;
    private final Random random = new Random();
    private final int radius;
    private final int n;
    private double angle;
    private Cell[] cells;

    public CellCircle(int radius, int n, int h, int k, Cell[] cells) {
        this.radius = radius;
        this.h = h;
        this.k = k;
        this.n = n;
        angle = 2 * Math.PI / n;
        this.setList(cells);
    }

    public CellView[] getCellViews() {
        CellView[] cellviews = new CellView[n];
        for (int i = 0; i < n; i++) {
            cellviews[i] = cells[i].cellView;
        }
        return cellviews;
    }

    public int getH() {
        return h;
    }

    public int getK() {
        return k;
    }

    public Cell[] getCells() {
        return cells;
    }

    public void setList(Cell[] cells) {
        this.cells = cells;
        int s = 250 / n;
        for (int i = 0; i < n; i++) {
            int xTmp = (int) (radius * Math.cos(angle * i) + h - s);
            int yTmp = (int) (radius * Math.sin(angle * i) + k - s);
            int size = 500 / n;
            cells[i].cellView.setX(xTmp);
            cells[i].cellView.setY(yTmp);
            cells[i].cellView.setAlive(cells[i].isAlive());
            cells[i].cellView.setRadius(size);
        }
    }
    
}

