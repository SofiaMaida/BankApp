package ar.com.ada.maven.view;

import ar.com.ada.maven.utils.Ansi;
import ar.com.ada.maven.utils.Keyboard;

public class MainView {

    public int mainMenuSelectOption() {

        System.out.println("--------------------------------------------------");
        System.out.println("\t** BIENVENIDOS A BANK ROTA **");
        System.out.println("--------------------------------------------------\n");
        System.out.println("Seleccione una opción: " +
                "\n| 1 | Cliente" +
                "\n| 2 | Cuentas" +
                "\n| 3 | Salir");

        return Integer.valueOf(Keyboard.getInputInteger());
    }

    public static void chooseValidOption() {
        System.out.println("Error, debe ingresar un dato válido");
        System.out.println(Ansi.RESET);
    }

    public static void invalidData() {
        System.out.println(Ansi.RED);
        System.out.println("Error, por favor ingrese datos validos.");
        System.out.println(Ansi.RESET);
    }
}
