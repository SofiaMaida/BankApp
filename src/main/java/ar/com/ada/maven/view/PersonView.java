package ar.com.ada.maven.view;
import ar.com.ada.maven.utils.Keyboard;

import java.util.InputMismatchException;

import java.util.Scanner;

public class PersonView {

    public String getNewClient() {
        System.out.println("Formulario para comenzar a ser cliente de Bank Rota");
        System.out.println("\nIngrese su nombre completo: ");
        Scanner scanner = Keyboard.getInstance();
        scanner.nextLine();

        System.out.println("\nIngrese su apellido: ");
        scanner = Keyboard.getInstance();
        scanner.nextLine();

        System.out.println("\nIngrese un tipo de documento: ");
        scanner = Keyboard.getInstance();
        scanner.nextLine();

        System.out.println("\nIngrese su número de documento: ");
        scanner = Keyboard.getInstance();
        scanner.nextInt();
        scanner.nextLine();

        System.out.println("\nIngrese su número de celular: ");
        scanner = Keyboard.getInstance();
        scanner.nextInt();
        scanner.nextLine();

        while (true) {
            try {
                String person = scanner.nextLine().trim();
                while (!person.matches("^[A-Za-záéíóúüÁÉÍÓÚÜ\\s]+$") && !person.isEmpty()) {
                    System.out.println("Error, debe ingresar un dato válido");
                    person = scanner.nextLine();
                }
                return person;
            } catch (InputMismatchException e) {
                System.out.println("Error, debe ingresar un dato válido");
            }
        }
    }

    public void clientAlreadyExists(String name, String lastName, Integer numberDoc) {
        System.out.println("Los datos ingresados ya corresponden a otro cliente");
        Keyboard.pressEnterToContinue();
    }

    public void showNewClient(String name, String lastName, Integer numberDoc) {
        System.out.println("Los datos ingresados son -> \nNombre completo: " + name +
                "\nApellido: " + lastName +
                "\nNúmero de documento: " + numberDoc);
        Keyboard.pressEnterToContinue();
    }

    /*public String getNewTypeAccount() {
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
    }*/

}
