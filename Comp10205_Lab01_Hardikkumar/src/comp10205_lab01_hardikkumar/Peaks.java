package comp10205_lab01_hardikkumar;

/*
 * Peaks class to create array of Peak number value
 * settig value to row and columns as 
 * well as getting value of row and columns.
 */
/**
 *
 * @author hardik
 */
public class Peaks {

    int row;
    int col;

    /**
     * default Constructor of peak class
     */
    public Peaks() {

    }

    /**
     * parameterized Constructor
     *
     * @param row
     * @param col
     */
    public Peaks(int row, int col) {
        this.row = row;
        this.col = col;
    }

    /**
     * Getting Rows
     *
     * @return row
     */
    public int getRow() {
        return row;
    }

    /**
     * Getting columns
     *
     * @return col
     */
    public int getCol() {
        return col;
    }

    /**
     * setting row 
     *
     * @param row
     */
    public void setRow(int row) {
        this.row = row;
    }

    /**
     * setting columns 
     *
     * @param col
     */
    public void setCol(int col) {
        this.col = col;
    }
}
