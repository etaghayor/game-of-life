import java.util.HashSet;
import java.util.ArrayList;
import java.util.Set;

public class Controller {
    private int n;
    private ArrayList<String> cells=new ArrayList<String>();
    private boolean res;

    public static void main(String args[]) {

        System.out.println("Test liste OK");
        Controller controller = new Controller();
        new View(controller);
    }

    public Cell[] startGameOfLife(Cell[] firstCells) {
        int n = firstCells.length;
        String s="";
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
            System.out.print(nextStateCells[i].getState());
        }
        res=checkcycle(cells,nextStateCells);
        System.out.println();
        addarraylist(nextStateCells,cells);
        return nextStateCells;
    }

    private void addarraylist(Cell[] cells,ArrayList<String> c){
        String s="";
        for (int j = 0; j < cells.length; j++) {
            s+=String.valueOf(cells[j].getState());
        }
        c.add(s);
    }

    private boolean checkcycle(ArrayList<String> cells,Cell [] c){
        String s="";
        for (int j = 0; j < c.length; j++) {
            s+=String.valueOf(c[j].getState());
        }
        for (int i = 0; i < cells.size(); i++) {
            if(s.equals(cells.get(i))){
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

    private boolean atLeastOneNeighbor(Cell[] cells, int i) {
        int n = cells.length - 1;
        if (i == 0) {
            return cells[n].getState() == 1 || cells[i + 1].getState() == 1;
        } else if (i == n) {
            return cells[0].getState() == 1 || cells[i - 1].getState() == 1;
        } else {
            return cells[i - 1].getState() == 1 || cells[i + 1].getState() == 1;
        }
    }

    private boolean exactlyOneNeighbor(Cell[] cells, int i) {
        int n = cells.length - 1;
        if (i == 0) {
            return (cells[n].getState() == 1 && cells[i + 1].getState() == 0)
                    || (cells[n].getState() == 0 && cells[i + 1].getState() == 1);
        } else if (i == n) {
            return (cells[0].getState() == 1 && cells[i - 1].getState() == 0)
                    || (cells[0].getState() == 0 && cells[i - 1].getState() == 1);
        } else {
            return (cells[i - 1].getState() == 1 && cells[i + 1].getState() == 0)
                    || (cells[i - 1].getState() == 0 && cells[i + 1].getState() == 1);
        }
    }

    private void printCells(ArrayList<Cell[]> cells){

    }

    private boolean checkIfexitsCycle (Cell[] cells,Cell[][] save){
        return true;
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public boolean getres(){
        return res;
    }
}