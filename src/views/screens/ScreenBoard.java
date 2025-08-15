package views.screens;

import models.Sudoku;

public class ScreenBoard {

    public static void viewBoard(Sudoku sudoku){

        System.out.println("=================== Tabuleiro =================== ");

        sudoku.getSudoku().forEach(spaces -> {
            System.out.print("| ");
            spaces.forEach(space -> {
                System.out.print((space.getValueActual() == null ? " " : space.getValueActual()) + " |");
            });
            System.out.println();
        });

    }

}
