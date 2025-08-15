package views.screens;

import controllers.SudokuController;
import exceptions.InvalidMoveException;
import exceptions.NullException;
import models.Sudoku;

import java.util.Scanner;

public class Menu {


    private Scanner sc;

    private SudokuController sudukoController;

    private Sudoku sudoku;

    public Menu() {
        sc = new Scanner(System.in);
        sudukoController = new SudokuController();
    }

    public int viewMenu() {

        try {
            sudoku = this.sudukoController.index();
        } catch (NullException e) {
            sudoku = this.sudukoController.createSudoku();
        }

       if(!this.sudukoController.endGame()){
           System.out.println("================= Menu =================");
           System.out.println("1. Novo Jogo");
           System.out.println("2. Reiniciar Jogo");
           System.out.println("3. Inserir numero no tabuleiro");
           System.out.println("4. Remover numero no tabuleiro");
           System.out.println("5. Verificar status do Jogo");
           System.out.println("6. Vizualizar sudoku");
           System.out.println("7. Sair");

           int op = sc.nextInt();

           return op;
       }else{
           System.out.println("Jogo Finalizado!");
           System.out.println("Você Ganhou!");
           System.out.println("================= Menu =================");
           System.out.println("1. Novo Jogo");
           System.out.println("7. Sair");

           int op = sc.nextInt();

           return op;
       }

    }

    public void newGame(){

        sudoku = this.sudukoController.createSudoku();

        System.out.println("Novo Jogo");

        ScreenBoard.viewBoard(sudoku);


    }

    public void restart(){

        try {
            sudoku = this.sudukoController.clearSudoku();
        }catch (NullPointerException e){
            System.out.println("Inicie um jogo primeiro");
        }

        System.out.println("Jogo reiniciado");

        ScreenBoard.viewBoard(sudoku);

    }

    public void insertNumber(){

        Integer number;
        int[] position = new int[2];

        System.out.println("Digite um numero: ");
        number = sc.nextInt();
        System.out.println("Digite a posicao.");
        System.out.println("Digite linha: ");
        position[0] = sc.nextInt();
        System.out.println("Digite coluna: ");
        position[1] = sc.nextInt();

        try {
            sudoku = this.sudukoController.put(position, number);
            System.out.println("Numero inserido com sucesso!");
            ScreenBoard.viewBoard(sudoku);

            if(!this.sudukoController.isValidPosition(position, number)){
                System.out.println("Número inserido infringe as regras do Sudoku!");
            }

        } catch (InvalidMoveException e) {
            System.out.println(e.getMessage());
        }

    }

    public void removeNumber(){

        int[] position = new int[2];

        System.out.println("Digite a posicao.");
        System.out.println("Digite linha: ");
        position[0] = sc.nextInt();
        System.out.println("Digite coluna: ");
        position[1] = sc.nextInt();

        try {
            sudoku = this.sudukoController.remove(position);
            System.out.println("Numero removido com sucesso!");
            ScreenBoard.viewBoard(sudoku);
        } catch (InvalidMoveException e) {
            System.out.println(e.getMessage());
        }


    }

    public void verifyStatus(){

        System.out.println("================= Status ===============");

        System.out.println("Status do jogo: "+this.sudukoController.getStatus().getLabel());
        if(this.sudukoController.existsError()){
            System.out.println("Contém erros");
        }

    }

    public void viewBoard(){
        System.out.println("================= Vizualizar sudoku ===============");
        ScreenBoard.viewBoard(sudoku);
    }

}
