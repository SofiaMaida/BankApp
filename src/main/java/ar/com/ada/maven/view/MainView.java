package ar.com.ada.maven.view;

import ar.com.ada.maven.utils.Ansi;
import ar.com.ada.maven.utils.Keyboard;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MainView {

    public int mainMenuSelectOption() {

        System.out.println("------------------------------------");
        System.out.println(Ansi.PURPLE + "** BIENVENIDOS A BANK ROTA **" + Ansi.RESET);
        System.out.println("------------------------------------");
        System.out.println("Seleccione una opción: " +
                "\n| 1 | Clientes" +
                "\n| 2 | Cuentas" +
                "\n| 3 | Movimientos" +
                "\n| 4 | Salir");

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
