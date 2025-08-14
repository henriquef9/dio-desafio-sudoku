package models;

import java.util.*;

public class SudokuGenerator {

    private Random rand = new Random();
    private SudokuSolver solver = new SudokuSolver();

    public void generateSudoku(int clues) {

        while(true) {

            int[][] sudoku = this.generateBoard();

            int[][] puzzle = this.generatePuzzle(sudoku, clues);

            int[][] copy = puzzle;
            if(solver.hasUniqueSolution(copy)) {

                break;
            }

        }

    }


    public int[][] generateBoard() {

        int[][] board = new int[9][9];
        fillBoard(board,0,0);
        return board;

    }

    private int[][] generatePuzzle(int[][] board, int clues) {

        int[][] puzzle = new int[9][9];
        List<int[]> cells  = new ArrayList<>();

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                cells.add(new int[]{i, j});
            }
        }

        Collections.shuffle(cells, rand);

        for (int i = 0; i < clues; i++) {

            int[] pos = cells.get(i);

            puzzle[pos[0]][pos[1]] = board[pos[0]][pos[1]];

        }

        return puzzle;

    }

    private int[][] deepCopy(int[][] board) {
        int[][] copy = new int[9][9];
        for (int i = 0; i < 9; i++) copy[i] = board[i].clone();
        return copy;
    }


    private boolean fillBoard(int[][] board, int row, int col) {

        if(col == 9){

            col = 0;
            row++;

            if(row == 9){
                return true;
            }

        }

        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);

        Collections.shuffle(numbers, rand);

        for (var number : numbers) {

            if(isValid(board, row, col, number)) {

                board[row][col] = number;
                if(fillBoard(board, row, col + 1)) {
                    return true;
                }

                board[row][col] = 0;
            }


        }

        return false;
    }


    private boolean isValid(int[][] board, int row, int col, int number) {

        for (int i = 0; i < 9; i++) {
            if(board[row][i] == number){
                return false;
            }
        }

        for (int i = 0; i < 9; i++) {
            if(board[i][col] == number){
                return false;
            }
        }

        int boxRowStart = row - (row % 3);
        int boxColStart = col - (col % 3);

        for (int i = boxRowStart; i < boxRowStart + 3; i++) {
            for (int j = boxColStart; j < boxColStart + 3; j++) {
                if(board[i][j] == number){
                    return false;
                }
            }
        }

        return true;

    }


}
