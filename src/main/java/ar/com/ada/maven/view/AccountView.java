package ar.com.ada.maven.view;

import ar.com.ada.maven.utils.Keyboard;

import java.util.InputMismatchException;
import java.util.Scanner;

public class AccountView {

    private Scanner keyboard = Keyboard.getInstance();

    public String getNewTypeAccount() {
        System.out.println("Seleccione qué tipo de cuenta aspira: " +
                "| 1 | Cuenta corriente en pesos ARG" +
                "| 2 | Cuenta corriente en pesos USD" +
                "| 3 | Cuenta corriente en pesos EUR" +
                "| 4 | Cancelar");

        Scanner scanner = Keyboard.getInstance();
        scanner.nextLine();

        while (true) {
            try {

                String name = scanner.nextLine().trim();
                while (!name.matches("^[A-Za-záéíóúüÁÉÍÓÚÜ\\s]+$") && !name.isEmpty()) {
                    System.out.println("Error, debe ingresar un dato válido");
                    name = scanner.nextLine();

                }
                return name;
            } catch (InputMismatchException e) {
                System.out.println("Error, debe ingresar un dato válido");
            }
        }
    }

    public void accountAlreadyExists(String account) {
        System.out.println("Los datos ingresados ya corresponden a otro cliente");
        Keyboard.pressEnterToContinue();
    }

    public void showNewAccount(String account) {
        System.out.println("Se ha creado exitosamente su cuenta");
        Keyboard.pressEnterToContinue();
    }

    public void newAccountCanceled() {
        System.out.println("Se ha cancelado el proceso de guardado");
        Keyboard.pressEnterToContinue();
    }


}
