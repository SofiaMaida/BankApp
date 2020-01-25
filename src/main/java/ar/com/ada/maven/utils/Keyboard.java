package ar.com.ada.maven.utils;

import java.io.IOException;
import java.util.Scanner;

public class Keyboard {

    private static Scanner keyboard;

    public Keyboard(){}

    public static Scanner getInstance(){
        if(keyboard == null){
            keyboard = new Scanner(System.in);
        }
        return keyboard;
    }

    public static void pressEnterToContinue(){
        System.out.println("- Presiona la tecla ENTER para continuar -");
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
