package ar.com.ada.maven.view;

import ar.com.ada.maven.model.DTO.PersonDTO;
import ar.com.ada.maven.utils.Ansi;
import ar.com.ada.maven.utils.Keyboard;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class PersonView {

    public HashMap<String, String > getNewClient() {
        HashMap<String,String> data = new HashMap<>();
        Scanner scanner = Keyboard.getInstance();

        System.out.println("Formulario para comenzar a ser cliente de Bank Rota");
        System.out.println("\nIngrese su nombre completo: ");
        data.put("name", scanner.nextLine());

        System.out.println("\nIngrese sus apellidos Completos: ");
        data.put("lastName", scanner.nextLine());

        System.out.println("\nIngrese un tipo de documento: ");
        System.out.println("\nMarcar 1: DNI");
        System.out.println("\nMarcar 2: Pasaporte");
        System.out.println("\nMarcar 3: CUIL");
        System.out.println("\nMarcar 4: DNI Extranjero");
        data.put("documentation_type", scanner.nextLine());

        System.out.println("\nIngrese su número de documento: ");
        data.put("number_doc", scanner.nextLine());

        System.out.println("\nIngrese su número de celular: ");
        data.put("number_phone", scanner.nextLine());
        scanner.nextInt();


        //HAY QUE ARREGLAR EL TR CATCH - VLADIMIR DIJO QUE NOS AYUDARIA PARA VER COMO LEIA LO QUE ESCRIBIERA SCANNER
        /*while (true) {
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
        }*/

        return data;
    }

    public void showNewClient(String name, String lastName, Integer numberDoc) {
        System.out.println("Los datos ingresados son -> \nNombre completo: " + name +
                "\nApellido: " + lastName +
                "\nNúmero de documento: " + numberDoc);
        Keyboard.pressEnterToContinue();
    }

<<<<<<< HEAD
    public void updatePersonCanceled() {
        System.out.println("Ha cancelado la actualizacion del siguiente usuario \n");
        Keyboard.pressEnterToContinue();
    }

    public String printPersonPerPage(final List<PersonDTO> person, List<String> paginator, String optionEdithOrDelete, boolean showHeader) {
        if (showHeader)
            System.out.println("Los clientes son: ");
            person.forEach((PersonDTO personDTO) -> {
            System.out.println("\t|" + personDTO.getId() + "\t|\t" + personDTO.getName() + "\t|" +
=======
    public void newClientCanceled() {
        System.out.println("Ha cancelado el ingreso");
        Keyboard.pressEnterToContinue();
    }

    public void clientAlreadyExists() {
        System.out.println("Los datos ingresados ya corresponden a otro cliente");
        Keyboard.pressEnterToContinue();
    }

    public void printAllPerson(List<PersonDTO> person) {
        System.out.println("*** LISTA DE CLIENTES ***");

        System.out.println("\t|\tID \t|CLIENTES|");
        System.out.println("\t|-------------------|");

        person.forEach((personDTO) -> {
            System.out.println("|\t" + personDTO.getId() + "\t|\t" + personDTO.getName() + "\t|\t" +
                    "\t|\t" + personDTO.getLastName() + "\t|\t" + personDTO.getNumber_doc());
        });
        Keyboard.pressEnterToContinue();
    }

    public String printPersonPerPage(List<PersonDTO> person, List<String> paginator, boolean hasEdith) {
        System.out.println("Los clientes son: ");
        person.forEach((personDTO) -> {
            System.out.println("\t|" + personDTO.getId().toString() + "\t|\t" + personDTO.getName() + "\t|" +
>>>>>>> View
                    personDTO.getLastName() + "\t|" + personDTO.getNumber_doc());
        });
        System.out.println("\n+-----------------------------------------------------------+");
        String option = (hasEdith) ?
                "[" + Ansi.CYAN + "E" + Ansi.RESET + "ditar]" :
                "[" + Ansi.CYAN + "E" + Ansi.RESET + "liminar]";
        paginator.set(paginator.size() - 2, option);
        paginator.forEach((page) -> System.out.print(page + " "));
        System.out.println("\n+-----------------------------------------------------------+\n");

        Scanner keyboard = Keyboard.getInstance();

        while (true) {
            try {
                System.out.println("? ");
                String name = keyboard.nextLine().trim();
                while (!name.matches("^[0-9IiAaSsUuEe]+$") && !name.isEmpty()) {
                    System.out.println("Error, debe ingresar una opcion valida");
                    name = keyboard.nextLine();
                }
                return name;
            } catch (InputMismatchException e) {
                System.out.println("Error, debe ingresar una opcion valida");
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

    public static String getDataUpdate(PersonDTO personDTO) {
        System.out.println("Se actualizara el siguiente dato: ");
        System.out.println(personDTO.getId() + "\t" + personDTO.getName() + "\t" + personDTO.getLastName() + "\t" + personDTO.getNumber_doc());
        System.out.println("Ingrese el dato para actualizar \n[Para cancelar no ingrese datos y presione enter]: ");

        Scanner scanner = Keyboard.getInstance();
        scanner.nextLine();

        while (true) {
            try {
                String name = scanner.nextLine().trim();
                while (!name.matches("^[A-Za-záéíóúüÁÉÍÓÚÜ\\s]+$") && !name.isEmpty()) {
                    System.out.println("Error, debe ingresar un dato valido");
                    name = scanner.nextLine();
                }
                return name;
            } catch (InputMismatchException e) {
                System.out.println("Error, debe ingresar un dato válido");
                scanner.next();
            }
        }
    }

    public void personNotExist(int id) {
        System.out.println("El cliente no existe con el id" + id);
        Keyboard.pressEnterToContinue();
    }

    public void updatePersonCanceled() {
        System.out.println("Ha cancelado la actualización");
        Keyboard.pressEnterToContinue();
    }

    public void showUpdateData(String name, String lastName, Integer numberDoc) {
        System.out.println("Los datos se han actualizado con éxito" + Ansi.GREEN + "\nNombre: " + name +
                        Ansi.GREEN + "\nApellido: " + lastName + Ansi.GREEN + "\nNumero de documento: " + numberDoc);
        Keyboard.pressEnterToContinue();
    }

    public static Boolean getNameDelete(PersonDTO person) {
        System.out.println("Se eliminará el siguiente cliente: ");
        System.out.println(person.getName() + "\t" + person.getLastName() + "\t" + person.getNumber_doc());
        System.out.println("¿Está seguro que desea eliminar?");
        System.out.println("| 1 | Si");
        System.out.println("| 2 | No");

        Scanner scanner = Keyboard.getInstance();
        scanner.nextLine();

        while (true) {
            try {
                System.out.println("? ");
                String name = scanner.nextLine().trim();
                while (!name.matches("^[A-Za-záéíóúüÁÉÍÓÚÜ\\s]+$") && !name.isEmpty()) {
                    System.out.println("Error, debe ingresar un dato valido");
                    name = scanner.nextLine();
                }
                return "1".equals(name);
            } catch (InputMismatchException e) {
                System.out.println("Error, debe ingresar un dato valido");
                scanner.next();
            }
        }
    }

    public void deletePersonCanceled() {
        System.out.println("Se ha cancelado la eliminacion");
        Keyboard.pressEnterToContinue();
    }

    public void showDeletePerson(String name, String lastName, Integer numberDoc) {
        System.out.println("El cliente " + name + lastName + numberDoc + " se ha eliminado con éxito");
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
