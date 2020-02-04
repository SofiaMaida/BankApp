package ar.com.ada.maven.view;

import ar.com.ada.maven.model.DTO.PersonDTO;
import ar.com.ada.maven.utils.Ansi;
import ar.com.ada.maven.utils.Keyboard;

import java.util.InputMismatchException;
import java.util.List;
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

    public String printPersonPerPage(final List<PersonDTO> person, List<String> paginator, String optionEdithOrDelete, boolean showHeader) {
        if (showHeader)
            System.out.println("Los clientes son: ");
            person.forEach((PersonDTO personDTO) -> {
            System.out.println("\t|" + personDTO.getId() + "\t|\t" + personDTO.getName() + "\t|" +
                    personDTO.getLastName() + "\t|" + personDTO.getNumber_doc());
        });
        if (optionEdithOrDelete != null && !optionEdithOrDelete.isEmpty())
            System.out.println("\n+-----------------------------------------------------------+");
        paginator.forEach((page) -> System.out.print(page + " "));
        System.out.println("\n+-----------------------------------------------------------+\n");

        Scanner keyboard = Keyboard.getInstance();

        while (true) {
            try {
                System.out.println("? ");
                String name = keyboard.nextLine().trim();
                while (!name.matches("^[0-9IiAaSsUuEe]+$") && !name.isEmpty()) {
                    MainView.chooseValidOption();
                    System.out.println("? ");
                    name = keyboard.nextLine();
                }
                return name;
            } catch (InputMismatchException e) {
                MainView.chooseValidOption();
                keyboard.next();
            }
        }
    }

    public int personIdSelected(String action) {
        switch (action) {
            case "[" + "E" + Ansi.RESET + "ditar]":
                action = "Editar";
                break;
            case "[" + "E" + Ansi.RESET + "liminar]":
                action = "Eliminar";
                break;
            case "[" + "E" + Ansi.RESET + "legir]":
                action = "Elegir";
                break;
        }

        System.out.println("Ingrese el numero de ID del cliente para " + action +
                " ó 0 para cancelar: \n");
        Scanner keyboard = Keyboard.getInstance();

        while (true) {
            try {
                System.out.println("? ");
                int choice = keyboard.nextInt();
                return choice;
            } catch (InputMismatchException e) {
                System.out.println("Error, debe ingresar un id valido");
                keyboard.next();
            }
        }
    }

    public void printAllPerson(List<PersonDTO> person) {
        System.out.println("*** LISTA DE CLIENTES ***");

        System.out.println("\t|\tID \t|CLIENTES|");
        System.out.println("\t|-------------------|");

        person.forEach((personDTO) -> {
            System.out.println("\t|\t" + personDTO.getId() + "\t|\t" + personDTO.getName() + "\t|" +
                    "\t|" + personDTO.getLastName() + "\t|" + personDTO.getNumber_doc());
        });
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
