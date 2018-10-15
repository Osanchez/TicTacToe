package Model;

import java.util.Arrays;

public class Model {
    //board information
    private int rows = 3; //rows on the board
    private int columns = 3; //columns on the board
    private String[][] board; //initialize board with default size

    //board rules/state
    private String playerTurn; //X always goes first
    private boolean isWinState; //Game starts with no win state
    public String playerWinner;
    private int turnsLeft; //total number of possible moves on the board

    public Model() {

    }

    //ToString
    public String toString() {
        String board = Arrays.toString(this.board);
        return board;
    }

    //print board nicely
    public void printBoard() {
        for(int row = 0; row < this.rows; row++) {
            for(int column = 0; column < this.columns; column++) {
                if(!this.board[row][column].equals("")) {
                    System.out.print("[" + this.board[row][column] + "]");
                } else {
                   System.out.print("[ ]");
                }
            }
            System.out.println();
        }
    }

    //Getters and Setters
    public int getBoardSize() { //get board size
        return this.rows;
    }

    public int getTurnsLeft() {
        return this.turnsLeft;
    }

    public boolean getWinState() {
        return this.isWinState;
    }

    public int getMovesLeft() {
        return this.turnsLeft;
    }

    //sets board size
    public void setBoardSize(int size) { //set board size, maintain n x n shape.
        this.rows = size;
        this.columns = size;
    }

    public void setPlayerWiner(String winner) {
        this.playerWinner = winner;
    }

    //initialized a board to a given state
    public void setBoardState(String[][] board) {
        this.board = board;
    }

    //initialized board with deafualt values
    public void initializeBoard() {
        this.playerWinner = "";
        this.isWinState = false;
        this.playerTurn = "X";
        this.turnsLeft = this.rows * this.rows; //calculates area of board
        this.board = new String[this.rows][this.columns];
        //adds an empty string to every row and column
        for(int row = 0; row < this.rows; row++) {
            for(int column = 0; column < this.columns; column++) {
                this.board[row][column] = "";
            }
            System.out.println();
        }
    }

    //gets the current piece at a given row and column
    public String getPiece(int row, int column) {
        return this.board[row][column];
    }

    //sets a given player piece to given row and column
    public void setPiece(String player, int row, int column) {
        this.board[row][column] = player;
    }

    //gets the current players turn
    public String getPlayerTurn() {
        return this.playerTurn;
    }

    //Changes turn to next player
    public void endPlayerTurn() {
        if(this.playerTurn.equals("X")) {
            this.turnsLeft --;
            this.playerTurn = "O";
        } else {
            this.turnsLeft --;
            this.playerTurn = "X";
        }
    }

    //check board for win
    public boolean checkBoard() {
        if(this.isHorizontalWin()) {
            System.out.println("Horizontal Win!");
            return true;
        }
        else if(this.isVerticalWin()) {
            System.out.println("Vertical Win!");
            return true;
        }
         else if(this.isDiagonalWin()) {
            System.out.println("Diagonal Win!");
            return true;
        }
        else if(this.turnsLeft == 0) {
            System.out.println("Draw!");
            this.playerWinner = "Draw";
            return true;
        } else {
            return false;
        }
    }

    //returns winner of given column, row, or diagonal
    public boolean doesWinnerExists(int countX, int countO) {
        if(countX == this.rows) {
            this.isWinState = true;
            this.playerWinner = "X";
            return true;
        } else if(countO == this.rows) {
            this.isWinState = true;
            this.playerWinner = "O";
            return true;
        }
        return false;
    }

    //returns winner of game, player X, player O, or draw
    public String getWinner() {
        return this.playerWinner;
    }

    /*
    Win Checking
    NOTE: IDE is giving a false positive for duplicate code between each win check.
    For loops are reversed to check different directions.
     */

    public boolean isHorizontalWin() {
        //Horizontal Win check (Left - Right)
        int countX;
        int countO;
        for(int row = 0; row < this.rows; row++) {
            countX = 0;
            countO = 0;
            for(int column = 0; column < this.columns; column++) {
                if(this.board[row][column].equals("X")) {
                    countX++;
                }
                else if(this.board[row][column].equals("O")) {
                    countO++;
                }
                boolean winner = doesWinnerExists(countX, countO); //returns player if win, empty string if nothing
                if(winner) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isVerticalWin() {
        int countX;
        int countO;
        //Vertical Win Check (top - down)
        for(int column = 0; column < this.columns; column++) {
            countX = 0;
            countO = 0;
            for(int row = 0; row < this.rows; row++) {
                if(this.board[row][column].equals("X")) {
                    countX++;
                } else if(this.board[row][column].equals("O")) {
                    countO++;
                }
                boolean winner = doesWinnerExists(countX, countO); //returns player if win, empty string if nothing
                if(winner) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isDiagonalWin() {
        //(Top Left - Bottom Right)
        int countX = 0;
        int countO = 0;

        for(int row = 0; row < this.rows; row++) {
            if(this.board[row][row].equals("X")) {
                countX++;
            } else if(this.board[row][row].equals("O")) {
                countO++;
            }
        }
        //checks to see if there is a winner
        boolean winner = doesWinnerExists(countX, countO); //returns player if win, empty string if nothing
        if(winner) {
            return true;
        }

        //reset X and O piece count variables
        countX = 0;
        countO = 0;

        //indexes
        int x = 0;
        int y = this.rows - 1;

        ///(Top Right - Bottom Left)
        for(int row = 0; row < this.rows; row++) {
            if(this.board[x][y].equals("X")) {
                countX++;
            } else if(this.board[x][y].equals("O")) {
                countO++;
            } else { //there cannot be a winner if there is a gap in the diagonal direction
                return false;
            }
            x++;
            y--;
        }
        winner = doesWinnerExists(countX, countO); //returns player if win, empty string if nothing
        if(winner) {
            return true;
        }
        return false;
    }

    public static void main(String args[]) {

    }
}