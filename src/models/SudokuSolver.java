package models;

public class SudokuSolver {

    private final int SIZE = 9;

    public boolean solve(int[][] board) {

        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {

                if (board[row][col] == 0) {

                    for (int k = 1; k <= SIZE; k++) {

                        if(isValid(board, row, col, k)) {
                            board[row][col] = k;
                            if (solve(board)) {
                                return true;
                            }
                            board[row][col] = 0;
                        }


                    }

                    return false;

                }

            }
        }

        return true;

    }

    public boolean hasUniqueSolution(int[][] board) {
        return countSolution(board, 0) == 1;
    }

    private int countSolution(int[][] board, int count) {
        // Se já achamos mais de uma solução
        System.out.println("countSolution: "+count);
        if (count > 1) return count;

        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                if (board[row][col] == 0) {
                    for (int num = 1; num <= SIZE; num++) {
                        if (isValid(board, row, col, num)) {
                            board[row][col] = num;
                            count = countSolution(board, count);
                            board[row][col] = 0;
                            // Interrompe cedo se já tiver mais de uma solução
                            if (count > 1) return count;
                        }
                    }
                    return count; // Nenhum número válido, retrocede
                }
            }
        }
        // Se não tem células vazias, encontramos uma solução
        return count + 1;
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
