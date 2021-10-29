public class Controller {
    public static void main(String args[]) {
    	
        System.out.println("Test liste OK");
        Cell[] firstCells = new Cell[10];
        
        for (int i = 1; i < 10; i++) {
            firstCells[i] = new Cell(0);
        }
        
        firstCells[0] = new Cell(1);
        Controller controller = new Controller();
        
    	
    	/*
    	Cell[] firstCells = new Cell[8];
    	Controller controller = new Controller();
    	*/
    	
        new View(controller, firstCells);
    }

    public Cell[] startGameOfLife(Cell[] firstCells) {
        int n = firstCells.length;
        Cell[] nextStateCells = new Cell[n];
        for (int i = 0; i < n; i++) {
            if (firstCells[i].getState() == 1) {
                if (atLeastOneNeighbor(firstCells, i))
                    nextStateCells[i] = new Cell(0);
                else
                    nextStateCells[i] = new Cell(1);
            } else {
                if (exactlyOneNeighbor(firstCells, i))
                    nextStateCells[i] = new Cell(1);
                else
                    nextStateCells[i] = new Cell(0);

            }
        }
        return nextStateCells;
    }

    private boolean atLeastOneNeighbor(Cell[] cells, int i) {
        int n = cells.length - 1;
        if (i == 0) {
            return cells[n].getState() == 1 || cells[i + 1].getState() == 1;
        } else if (i == n) {
            return cells[0].getState() == 1 && cells[i - 1].getState() == 1;
        } else {
            return cells[i - 1].getState() == 1 && cells[i + 1].getState() == 1;
        }
    }

    private boolean exactlyOneNeighbor(Cell[] cells, int i) {
        int n = cells.length - 1;
        if (i == 0) {
            return (cells[n].getState() == 1 && cells[i + 1].getState() == 0) ||
                    (cells[n].getState() == 0 && cells[i + 1].getState() == 1);
        } else if (i == n) {
            return (cells[0].getState() == 1 && cells[i - 1].getState() == 0) ||
                    (cells[0].getState() == 0 && cells[i - 1].getState() == 1);
        } else {
            return (cells[i - 1].getState() == 1 && cells[i + 1].getState() == 0) ||
                    (cells[i - 1].getState() == 0 && cells[i + 1].getState() == 1);
        }
    }

}