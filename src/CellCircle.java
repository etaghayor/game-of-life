import java.util.Random;


public class CellCircle {
    private int h, k;
    private final Random random = new Random();
    private final int radius;
    private final int n;
    private Cell[] cells;

    public CellCircle(int radius, int n, int h, int k, Cell[] cells) {
        this.radius = radius;
        this.h = h;
        this.k = k;
        this.n = n;
        double angle = 2*Math.PI / n;
        for (int i = 0; i < n; i++) {
            cells[i].cellView.x = (int) (radius * Math.cos(angle * i) + h);
            cells[i].cellView.y = (int) (radius * Math.sin(angle * i) + k);
            cells[i].cellView.alive = cells[i].getState() == 1;
            cells[i].cellView.size = 700/n;
        }
        this.cells = cells;
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
}

