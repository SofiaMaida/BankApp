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
        System.out.println(Ansi.PURPLE + "\t\t  Bank - Rota :: Modulo Cliente" + Ansi.RESET);
        System.out.println("+--------------------------------------------------+\n");

        System.out.println("Seleccione una acción del menú:\n");
        System.out.println("| 1 | Registrar nuevo cliente");
        System.out.println("| 2 | Ver lista de clientes");
        System.out.println("| 3 | Editar cliente");
        System.out.println("| 4 | Eliminar cliente");
        System.out.println("| 5 | Regresar el menú principal");
        System.out.println("----------------------------------------------------\n");

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
        System.out.println("\n------------------------------------------");
        System.out.println("Los datos registrados son: ");
        System.out.println("-------------------------------------------");
        System.out.println(Ansi.GREEN + "\n*Nombre completo: " + Ansi.RESET + name);
        System.out.println(Ansi.GREEN + "\n*Apellido: " + Ansi.RESET + lastName);
        System.out.println(Ansi.GREEN + "\n*Número de documento: " + Ansi.RESET + numberDoc);

        Keyboard.pressEnterToContinue();
    }

    public void newClientCanceled() {
        System.out.println(Ansi.RED + "Ha cancelado el ingreso" + Ansi.RESET);
        Keyboard.pressEnterToContinue();
    }

    public void clientAlreadyExists(String name, String lastName, Integer number_doc) {
        System.out.println("Los datos ingresados ya corresponden a otro cliente");
        Keyboard.pressEnterToContinue();
    }

    public String printPersonPerPage(List<PersonDTO> person, List<String> paginator, String optionEdithOrDelete, boolean showHeader) {
        if (showHeader) {
            System.out.println("\n+----------------------------------------------------------------+");
            System.out.println(Ansi.PURPLE + "\t  Bank - Rota :: Modulo Cliente :: Lista Clientes" + Ansi.RESET);
            System.out.println("+----------------------------------------------------------------+\n");
        }
        System.out.println("Los clientes son: ");
        person.forEach((personDTO) -> {
            System.out.println("|\t" + personDTO.getId() + "\t|\t" + personDTO.getName() + "\t|\t" +
                    personDTO.getLastName() + "\t|\t" + personDTO.getNumber_doc());
        });
        if (optionEdithOrDelete != null && !optionEdithOrDelete.isEmpty())
            paginator.set(paginator.size() - 2, optionEdithOrDelete);

        System.out.println("\n+----------------------------------------------------------------+");
        paginator.forEach(page -> System.out.print(page + " "));
        System.out.println("\n+----------------------------------------------------------------+\n");

        Scanner keyboard = Keyboard.getInstance();

        return String.valueOf(Keyboard.getInputString());
    }

    public Integer personIdSelected(String action) {
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

        return Integer.valueOf(Keyboard.getInputInteger());
    }

    public static void getPersonToUpdate(PersonDTO personDTO) {
        System.out.println("Se actualizará el siguiente usuario: ");
        System.out.println("|\t" + personDTO.getId() + "\t|\t" + personDTO.getName() + "\t|\t" + personDTO.getLastName() +
                "\t|\t" + personDTO.getDocument_type() + "\t|\t" + personDTO.getNumber_doc());

    }

    public static String nameToEdit(PersonDTO personDTO) {
        System.out.println("\nIngrese el nombre del usuario que desea actualizar " +
                Ansi.RED + "\n[Para cancelar no ingrese datos y presione enter]: " + Ansi.RESET);

        Scanner keyboard = Keyboard.getInstance();
        keyboard.nextLine();
        return String.valueOf(Keyboard.getInputString());
    }

    public static String lastNameToEdit(PersonDTO personDTO) {
        System.out.println("\nIngrese el apellido del usuario que desea actualizar: " +
                Ansi.RED + "\n[Para cancelar no ingrese datos y presione enter]: " + Ansi.RESET);

        Scanner keyboard = Keyboard.getInstance();
        keyboard.nextLine();
        return String.valueOf(Keyboard.getInputString());
    }

    public static Integer numberDocToEdit(PersonDTO personDTO) {
        System.out.println("\nIngrese el numero de documento del usuario que desea actualizar: " +
                Ansi.RED + "\n[Para cancelar no ingrese datos y presione enter]: " + Ansi.RESET);

        Scanner keyboard = Keyboard.getInstance();
        keyboard.nextLine();
        return Integer.valueOf(Keyboard.getInputInteger());
    }

    public void personNotExist(int id) {
        System.out.println("El cliente con el ID " + id + " no existe");
        System.out.println("[Seleccione un ID válido ó 0 para cancelar]:");
        Keyboard.pressEnterToContinue();
    }

    public void updatePersonCanceled() {
        System.out.println(Ansi.RED + "Ha cancelado la actualización" + Ansi.RESET);
        Keyboard.pressEnterToContinue();
    }

    public void showUpdateData(String name, String lastName, Integer numberDoc) {
        System.out.println("Los datos se han actualizado con éxito" +
                Ansi.PURPLE + "\nNombre: " + Ansi.RESET + name +
                Ansi.PURPLE + "\nApellido: " + Ansi.RESET + lastName +
                Ansi.PURPLE + "\nNumero de documento: " + Ansi.RESET + numberDoc);
        Keyboard.pressEnterToContinue();
    }

    public static Boolean getNameDelete(PersonDTO person) {
        System.out.println("Se eliminará el siguiente cliente: ");
        System.out.println(Ansi.PURPLE +"|\t" + person.getId() + "\t|\t" + person.getName() + "\t|\t" +
                person.getLastName() + "\t|\t" + person.getNumber_doc() + "\t|" + Ansi.RESET);
        System.out.println("\n¿Está seguro que desea eliminar?");
        System.out.println("| 1 | Si");
        System.out.println("| 2 | No");

        Scanner keyboard = Keyboard.getInstance();
        keyboard.nextLine();

        while (true) {
            try {
                System.out.print("? ");
                String name = keyboard.nextLine().trim();
                while (!name.matches("^[1-2]+$") && !name.isEmpty()) {
                    System.out.println("Error, debe ingresar una opcion valida");
                    name = keyboard.nextLine();
                }
                return "1".equals(name);
            } catch (InputMismatchException e) {
                System.out.println("Error, debe ingresar una opcion valida");
                keyboard.next();
            }
        }
    }

    public void deletePersonCanceled() {
        System.out.println(Ansi.RED +"Se ha cancelado la eliminación" + Ansi.RESET);
        Keyboard.pressEnterToContinue();
    }

    public void showDeletePerson(String name, String lastName, Integer number_doc, DocumentationDTO document_type) {
        System.out.println(Ansi.GREEN + "El cliente " + name + " " + lastName + " con numero de documento: " +
                number_doc + " se ha eliminado con éxito" + Ansi.RESET);
        Keyboard.pressEnterToContinue();
    }

    public static int selectEditPerson() {
        System.out.println("Seleccione que desea editar: \n");
        System.out.println("| 1 | Nombre");
        System.out.println("| 2 | Apellido");
        System.out.println("| 3 | Numero de documento");

        return Integer.valueOf(Keyboard.getInputInteger());

    }

}
