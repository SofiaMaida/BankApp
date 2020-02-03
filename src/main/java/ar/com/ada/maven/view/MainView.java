package ar.com.ada.maven.view;

import ar.com.ada.maven.utils.Ansi;
import ar.com.ada.maven.utils.Keyboard;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MainView {

    public int mainMenuSelectOption() {

        System.out.println("** BIENVENIDOS A BANK ROTA **\n");
        System.out.println("Seleccione una opción: " +
                "\n| 1 | ¿Desea ingresar un nuevo usuario?" +
                "\n| 2 | ¿Desea ingresar con su usuario?" +
                "\n| 3 | ¿Desea modificar algún dato ingresado?" +
                "\n| 4 | ¿Desea eliminar alguna cuenta a su nombre?" +
                "\n| 5 | ¿Desea eliminar su usuario?" +
                "\n| 6 | Salir");

        Scanner keyboard = Keyboard.getInstance();

        while (true) {
            try {
                int choice = keyboard.nextInt();
                keyboard.nextLine();
                return choice;
            } catch (InputMismatchException e) {
                System.out.println("Error, debe ingresar un dato valido");
                keyboard.nextLine();
            }
        }
    }
    public static void chooseValidOption() {
        System.out.println("Error, debe ingresar un dato válido");
        System.out.println(Ansi.RESET);
    }
}
