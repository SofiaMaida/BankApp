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
                "\n| 3 | ¿Desea realizar un movimiento?" +
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
