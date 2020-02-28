package ar.com.ada.maven.view;

import ar.com.ada.maven.utils.Ansi;
import ar.com.ada.maven.utils.Keyboard;

public class TypeAccountView {

    public void newAccountCanceled() {
        System.out.println("Ha cancelado el ingreso de una nueva cuenta\n");
        Keyboard.pressEnterToContinue();
    }


    public void accountAlreadyExists(String type) {
        System.out.println("Los numeros ingresados ya corresponden a otro cliente");
        Keyboard.pressEnterToContinue();
    }

    public void showNewAccount(String number) {
        System.out.println(Ansi.PURPLE + "Su numero de cuenta es: " + Ansi.RESET + number);
        System.out.println(Ansi.CYAN + "\t- ¡Muchas gracias por elegirnos! -\n" + Ansi.RESET);
        Keyboard.pressEnterToContinue();
    }

}
