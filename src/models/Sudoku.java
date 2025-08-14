package models;

import enums.SudokuStatus;

import java.util.*;

public class Sudoku {

    private List<List<Space>> sudoku;
    private SudokuStatus status;


    public Sudoku() {
        this.status = SudokuStatus.NON_STARTED;
    }

    public List<List<Space>> getSudoku() {
        return sudoku;
    }

    public void setSudoku(List<List<Space>> sudoku) {
        this.sudoku = sudoku;
    }

    public SudokuStatus getStatus() {
        return status;
    }

    public void setStatus(SudokuStatus status) {
        this.status = status;
    }
}
