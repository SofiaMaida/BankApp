package ar.com.ada.maven.utils;

import ar.com.ada.maven.view.MainView;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Keyboard {

    private static Scanner keyboard;

    public Keyboard() {
    }

    public static Scanner getInstance() {
        if (keyboard == null) {
            keyboard = new Scanner(System.in);
        }
        return keyboard;
    }

    public static void pressEnterToContinue() {
        System.out.println("- Presiona la tecla ENTER para continuar -");
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getInputString() {
        Scanner keyboard = getInstance();
        while (true) {
            try {
                System.out.print("? ");
                String txt = keyboard.nextLine().trim();
                while (!txt.matches("^[A-Za-záéíóúüÁÉÍÓÚÜ\\s]+$") && !txt.isEmpty()) {
                    MainView.invalidData();
                    txt = keyboard.nextLine();
                }
                return txt;
            } catch (InputMismatchException e) {
                MainView.invalidData();
                keyboard.next();
            }
        }
    }

    public static String getInputInteger() {
        Scanner keyboard = getInstance();
        while (true) {
            try {
                System.out.print("? ");
                String txt = keyboard.nextLine().trim();
                while (!txt.matches("^[0-9]+$") && !txt.isEmpty()) {
                    MainView.invalidData();
                    txt = keyboard.nextLine();
                }
                return txt;
            } catch (InputMismatchException e) {
                MainView.invalidData();
                keyboard.next();
            }
        }

    }

}
