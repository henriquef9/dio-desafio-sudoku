package controllers;

import enums.SudokuStatus;
import exceptions.InvalidMoveException;
import exceptions.NullException;
import models.Sudoku;
import services.SudokuService;

public class SudokuController {

    private SudokuService service = new SudokuService();

    public Sudoku createSudoku() {

        return this.service.newSudoku(36);

    }

    public Sudoku index() throws NullException {
        return this.service.getSudoku();
    }

    public Sudoku clearSudoku() throws NullPointerException {
        return this.service.clearSudoku();
    }

    public Sudoku put(int[] position, Integer number) throws InvalidMoveException {
        return this.service.putNumberSpace(number, position);
    }

    public Sudoku remove(int[] position) throws InvalidMoveException {
        return this.service.removeNumberSpace(position);
    }

    public SudokuStatus getStatus() {
        return this.service.getSudokuStatus();
    }

    public boolean existsError(){
        return this.service.existsErrosSudoku();
    }

    public boolean isValidPosition(int[] position, Integer number) {
        return this.service.isValidRules(position, number);
    }

    public boolean endGame(){
        return this.service.finalizeSudoku();
    }

}
