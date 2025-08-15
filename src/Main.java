import views.screens.Menu;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {


        Menu menu = new Menu();

        int option = menu.viewMenu();

        while (option != 7) {

            switch (option) {
                case 1:
                    menu.newGame();
                    break;
                case 2:
                    menu.restart();
                    break;
                case 3:
                    menu.insertNumber();
                    break;
                case 4:
                    menu.removeNumber();
                    break;
                case 5:
                    menu.verifyStatus();
                    break;
                case 6:
                    menu.viewBoard();
                    break;
            }

            option = menu.viewMenu();

        }

    }
}