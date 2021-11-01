public class Cell {
    private int state;
    public CellView cellView;

    public Cell(int state) {
        this.state = state;
        this.cellView = new CellView();
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return String.valueOf(getState());
    }
}
