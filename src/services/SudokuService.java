package services;

import enums.SudokuStatus;
import exceptions.InvalidMoveException;
import exceptions.NullException;
import models.Space;
import models.Sudoku;
import models.SudokuGenerator;
import models.SudokuSolver;

import java.util.List;

public class SudokuService {

    private Sudoku sudoku = new Sudoku();

    private SudokuGenerator sudokuGenerator = new SudokuGenerator();

    private SudokuSolver sudokuSolver = new SudokuSolver();

    public SudokuService() {}

    public Sudoku newSudoku(int clues) {

        sudoku = this.sudokuGenerator.generateSudoku(clues);

        return sudoku;
    }

    public Sudoku getSudoku() throws NullException {
        if (sudoku.getSudoku() == null) {
            throw new NullException("Sudoku is null");
        }
        return sudoku;
    }

    public Sudoku clearSudoku() throws NullPointerException {

        if (sudoku.getSudoku() == null) {
            throw new NullPointerException("Sudoku is null");
        }

        List<List<Space>> board =  this.sudoku.getSudoku().stream()
                .map(linha -> linha.stream()
                        .map(space -> {
                            if (!space.isFixed()) {
                                // cria um novo Space com o valor atual nulo
                                return new Space(space.isFixed(), space.getValueExpected());
                            }
                            // mantém o Space original
                            return space;
                        })
                        .toList()
                )
                .toList();


        sudoku.setSudoku(board);

        sudoku.setStatus(SudokuStatus.NON_STARTED);

       return sudoku;

    }

    public Sudoku putNumberSpace(Integer number, int[] pos) throws InvalidMoveException {

        if(this.sudoku.getSudoku().get(pos[0]).get(pos[1]).isFixed()){
           throw new InvalidMoveException("Não é possível alterar uma posição fixa");
        }

        if(this.sudoku.getSudoku().get(pos[0]).get(pos[1]).getValueActual() != null){
            throw new InvalidMoveException("Posição já preenchida");
        }


        this.sudoku.getSudoku().get(pos[0]).get(pos[1]).setValueActual(number);

        this.verifyStatus();

        return sudoku;

    }

    public Sudoku removeNumberSpace(int[] pos) throws InvalidMoveException {

        if(this.sudoku.getSudoku().get(pos[0]).get(pos[1]).isFixed()){
            throw new InvalidMoveException("Não é possível remove uma posição fixa");
        }

        this.sudoku.getSudoku().get(pos[0]).get(pos[1]).setValueActual(null);

        this.verifyStatus();

        return sudoku;

    }

    public SudokuStatus verifyStatus(){

        var isCompleted = sudoku.getSudoku().stream().allMatch(spaces -> {
            return spaces.stream().allMatch(
                    space -> space.getValueActual() != null
            );
        });

        this.sudoku.setStatus(isCompleted ? SudokuStatus.COMPLETE : SudokuStatus.INCOMPLETE);

        return sudoku.getStatus();
    }

    public SudokuStatus getSudokuStatus() {
        return sudoku.getStatus();
    }

    public SudokuStatus setSudokuStatus(SudokuStatus sudokuStatus) {
        this.sudoku.setStatus(sudokuStatus);
        return sudoku.getStatus();
    }


    public boolean existsErrosSudoku() {

        for(int i = 0; i < sudoku.getSudoku().size(); i++){
            for(int j = 0; j < sudoku.getSudoku().get(i).size(); j++){
                if(this.sudoku.getSudoku().get(i).get(j).getValueActual() != null && !this.isValidRules(new int[]{i,j},
                        this.sudoku.getSudoku().get(i).get(j).getValueActual())){
                            return true;
                }
            }
        }

        return false;
    }

    public boolean finalizeSudoku() {

        int [][] board = new int[9][9];

        for(int i = 0; i < sudoku.getSudoku().size(); i++){
            for(int j = 0; j < sudoku.getSudoku().get(i).size(); j++){
                board[i][j] = sudoku.getSudoku().get(i).get(j).getValueActual() == null ? 0 : sudoku.getSudoku().get(i).get(j).getValueActual();
            }
        }

        var isCompleted = sudoku.getSudoku().stream().allMatch(spaces -> {
            return spaces.stream().allMatch(
                    space -> space.getValueActual() != null
            );
        });

        if(isCompleted && !existsErrosSudoku()){
            this.setSudokuStatus(SudokuStatus.COMPLETE);
            return true;
        }

        return false;
    }


    public boolean isValidRules(int[] pos, Integer number) {

        boolean existsInRow = sudoku.getSudoku()
                .get(pos[0])
                .stream()
                .anyMatch(space -> space.getValueActual() != null
                        && number.equals(space.getValueActual())
                        && sudoku.getSudoku().get(pos[0]).indexOf(space) != pos[1]);

        // Verifica coluna
        boolean existsInCol = sudoku.getSudoku()
                .stream()
                .anyMatch(spaces -> spaces.get(pos[1]).getValueActual() != null
                        && number.equals(spaces.get(pos[1]).getValueActual())
                        && sudoku.getSudoku().indexOf(spaces) != pos[0]);

        // Verifica subgrade 3x3
        int startRow = (pos[0] / 3) * 3;
        int startCol = (pos[1] / 3) * 3;
        boolean existsInGrid = false;
        for (int i = startRow; i < startRow + 3; i++) {
            for (int j = startCol; j < startCol + 3; j++) {
                if ((i != pos[0] || j != pos[1]) // ignora a própria célula
                        && sudoku.getSudoku().get(i).get(j).getValueActual() != null
                        && number.equals(sudoku.getSudoku().get(i).get(j).getValueActual())) {
                    existsInGrid = true;
                    break;
                }
            }
        }

        return !existsInRow && !existsInCol && !existsInGrid;

    }


}
