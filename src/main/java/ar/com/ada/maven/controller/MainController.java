package ar.com.ada.maven.controller;

import ar.com.ada.maven.view.MainView;

public class MainController {
    private static MainView view = new MainView();

    public static void run() {
        boolean shoulGetOut = false;

        while (!shoulGetOut) {
            int option = view.mainMenuSelectOption();
            switch (option) {
                case 1:
                    PersonController.init();
                    break;
                case 2:
                    AccountController.init();
                    break;
               // case 3:
                 //   MovementsController.init();
                   // break;
                case 4:
                    shoulGetOut = true;
                    break;
                default:
                    System.out.println("Opción no valida, vuelva a seleccionar");
            }
        }

    }
}
