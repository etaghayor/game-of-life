import java.util.ArrayList;

public class Controller {
    private int n;
    private ArrayList<String> cells = new ArrayList<String>();
    private boolean cycleFound;
    private View view;

    public static void main(String args[]) {

        System.out.println("Test liste OK");
        Controller controller = new Controller();
        controller.view = new View(controller);
    }

    public Cell[] startGameOfLife(Cell[] firstCells) {
        int n = firstCells.length;
        System.out.println(cellsToString(firstCells));
        Cell[] nextStateCells = new Cell[n];
        for (int i = 0; i < n; i++) {
            if (firstCells[i].isAlive()) {
                if (atLeastOneNeighbor(firstCells, i))
                    nextStateCells[i] = new Cell(false);
                else
                    nextStateCells[i] = new Cell(true);
            } else {
                if (exactlyOneNeighbor(firstCells, i))
                    nextStateCells[i] = new Cell(true);
                else
                    nextStateCells[i] = new Cell(false);
            }
        }
        cycleFound = checkCycle(cells, nextStateCells);
        addCellToList(cells, nextStateCells);
        return nextStateCells;
    }

    private void addCellToList(ArrayList<String> cellStrings, Cell[] cells) {
        cellStrings.add(cellsToString(cells));
    }

    private boolean checkCycle(ArrayList<String> cellStrings, Cell[] cells) {
        String s = cellsToString(cells);
        for (String cell : cellStrings) {
            if (s.equals(cell)) {
                System.out.println(cellsToString(cells));
                return true;
            }
        }
        return false;
        /*ça marche aussi (à vous de choisir)
        Set<String> c=new HashSet<String>();
        c.addAll(cells);
        if(c.size()<cells.size()){
            return true;
        }
        return false;
         */

    }

    private String cellsToString(Cell[] cells) {
        StringBuilder s = new StringBuilder();
        for (Cell c : cells)
            s.append(c);
        return s.toString();
    }

    private boolean atLeastOneNeighbor(Cell[] cells, int i) {
        int n = cells.length - 1;
        if (i == 0) {
            return cells[n].isAlive() || cells[i + 1].isAlive();
        } else if (i == n) {
            return cells[0].isAlive() || cells[i - 1].isAlive();
        } else {
            return cells[i - 1].isAlive() || cells[i + 1].isAlive();
        }
    }

    private boolean exactlyOneNeighbor(Cell[] cells, int i) {
        int n = cells.length - 1;
        if (i == 0) {
            return (cells[n].isAlive() && !cells[i + 1].isAlive())
                    || (!cells[n].isAlive() && cells[i + 1].isAlive());
        } else if (i == n) {
            return (cells[0].isAlive() && !cells[i - 1].isAlive())
                    || (!cells[0].isAlive() && cells[i - 1].isAlive());
        } else {
            return (cells[i - 1].isAlive() && !cells[i + 1].isAlive())
                    || (!cells[i - 1].isAlive() && cells[i + 1].isAlive());
        }
    }

    public void reset() {
        this.cells.clear();
        this.n = 0;
        this.cycleFound = false;
        view.setVisible(false);
        view.dispose();
        view = new View(this);
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public boolean isCycleFound() {
        return cycleFound;
    }
}