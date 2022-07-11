package MainApp;

import java.util.Scanner;

public class ConsoleUI {

    public static void main(String[] args) {
        Scanner myInput = new Scanner( System.in );

        try {
            MockDataLoader.loadData();
            MenuItem menuItem;

            do {
                System.out.println("******** welcome to evolutionary shifts ********");
                System.out.println("1. show all roles");
                System.out.println("2. show all employees");
                System.out.println("3. show arrangement properties");
                System.out.println("4. show employees preferences");
                System.out.println("5. run evolution algorithm");
                System.out.println("6. show best solution");
                System.out.println("7. exit");

                menuItem = MenuItem.values()[myInput.nextInt() - 1];
                RequestHandler.handleSelection(menuItem);

            } while(menuItem != MenuItem.Exit);

        } catch (Exception e) {
            System.out.println("Invalid selection");
        }
    }
}
