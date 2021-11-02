public class Cell {
    private boolean alive;
    public CellView cellView;

    public Cell(boolean state) {
        this.alive = state;
        this.cellView = new CellView();
    }

    public boolean isAlive() {
        return alive;
    }

    public void setLive(boolean alive) {
        this.alive = alive;
        this.cellView.setAlive(alive);
    }

    @Override
    public String toString() {
        return isAlive() ? "1" : "0";
    }
}
