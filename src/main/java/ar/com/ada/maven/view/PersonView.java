package ar.com.ada.maven.view;

import ar.com.ada.maven.model.DTO.DocumentationDTO;
import ar.com.ada.maven.model.DTO.PersonDTO;
import ar.com.ada.maven.utils.Ansi;
import ar.com.ada.maven.utils.Keyboard;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class PersonView {

    public int personMenuSelectOption() {
        System.out.println("\n+--------------------------------------------------+");
        System.out.println("\t\t  Bank - Rota :: Modulo Cliente");
        System.out.println("+--------------------------------------------------+\n");

        System.out.println("Seleccione una acción del menú:\n");
        System.out.println("| 1 | Registrar nuevo cliente");
        System.out.println("| 2 | Ver lista de clientes");
        System.out.println("| 3 | Editar cliente");
        System.out.println("| 4 | Eliminar cliente");
        System.out.println("| 5 | Regresar el menú principal");
        System.out.println("-------------------------\n");

        return Integer.valueOf(Keyboard.getInputInteger());
    }

    public HashMap<String, String> getNewClient(List<DocumentationDTO> typesDoc) {
        HashMap<String, String> data = new HashMap<>();

        System.out.println("Formulario para comenzar a ser cliente de Bank Rota");
        System.out.println("\nIngrese su nombre completo: ");
        data.put("name", Keyboard.getInputString());

        System.out.println("\nIngrese sus apellidos completos: ");
        data.put("lastName", Keyboard.getInputString());

        System.out.println("\nIngrese un tipo de documento: ");
        typesDoc.forEach(types ->
                System.out.println("\t|" + types.getId() + "\t| "+ types.getDocumentation_type()));
        data.put("documentation_type", Keyboard.getInputInteger());

        System.out.println("\nIngrese su número de documento: ");
        data.put("number_doc", Keyboard.getInputInteger());

        System.out.println("\nIngrese su número de celular: ");
        data.put("number_phone", Keyboard.getInputInteger());

        return data;
    }

    public void showNewClient(String name, String lastName, Integer numberDoc) {
        System.out.println("Los datos registrados son: \nNombre completo: " + name +
                "\nApellido: " + lastName +
                "\nNúmero de documento: " + numberDoc);
        Keyboard.pressEnterToContinue();
    }

    public void newClientCanceled() {
        System.out.println("Ha cancelado el ingreso");
        Keyboard.pressEnterToContinue();
    }

    public void clientAlreadyExists(String name, String lastName, Integer number_doc) {
        System.out.println("Los datos ingresados ya corresponden a otro cliente");
        Keyboard.pressEnterToContinue();
    }

    public String printPersonPerPage(List<PersonDTO> person, List<String> paginator, String optionEdithOrDelete, boolean showHeader) {
        if (showHeader) {
            System.out.println("\n+----------------------------------------------------------------+");
            System.out.println("\t  Bank - Rota :: Modulo Cliente :: Lista Clientes");
            System.out.println("+----------------------------------------------------------------+\n");
        }
        System.out.println("Los clientes son: ");
        person.forEach((personDTO) -> {
            System.out.println("\t|" + personDTO.getId() + "\t|\t" + personDTO.getName() + "\t|");
        });
        if (optionEdithOrDelete != null && !optionEdithOrDelete.isEmpty())
            paginator.set(paginator.size() - 2, optionEdithOrDelete);

        System.out.println("\n+----------------------------------------------------------------+");
        paginator.forEach(page -> System.out.print(page + " "));
        System.out.println("\n+----------------------------------------------------------------+\n");

        Scanner keyboard = Keyboard.getInstance();

        while (true) {
            try {
                System.out.print("? ");
                String name = keyboard.nextLine().trim();
                while (!name.matches("^[0-9IiAaSsUuEeqQ]+$") && !name.isEmpty()) {
                    MainView.chooseValidOption();
                    System.out.print("? ");
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
            case "[" + Ansi.CYAN + "E" + Ansi.RESET + "ditar]":
                action = "Editar";
                break;
            case "[" + Ansi.CYAN + "E" + Ansi.RESET + "liminar]":
                action = "Eliminar";
                break;
            case "[" + Ansi.CYAN + "E" + Ansi.RESET + "legir]":
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
        System.out.println("Se actualizará el siguiente dato: ");
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
        System.out.println("El cliente con el ID " + id + " no existe");
        System.out.println("[Seleccione un ID válido ó 0 para cancelar]:");
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
        System.out.println(person.getId() + "\t" + person.getName() + "\t" + person.getLastName() + "\t" + person.getNumber_doc());
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

    public void showDeletePerson(String name, String lastName, Integer number_doc) {
        System.out.println("El cliente " + name + lastName + " con numero de documento: " + number_doc + " se ha eliminado con éxito");
        Keyboard.pressEnterToContinue();
    }

    /*public void printAllPerson(List<PersonDTO> person) {
        System.out.println("*** LISTA DE CLIENTES ***");

        System.out.println("\t|\tID \t|CLIENTES|");
        System.out.println("\t|-------------------|");

        person.forEach((personDTO) -> {
            System.out.println("|\t" + personDTO.getId() + "\t|\t" + personDTO.getName() + "\t|\t" +
                    "\t|\t" + personDTO.getLastName() + "\t|\t" + personDTO.getNumber_doc());
        });
        Keyboard.pressEnterToContinue();
    }*/

}