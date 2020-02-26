package ar.com.ada.maven.view;

import ar.com.ada.maven.model.DTO.AccountDTO;
import ar.com.ada.maven.model.DTO.PersonDTO;
import ar.com.ada.maven.model.DTO.Type_accountDTO;
import ar.com.ada.maven.utils.Ansi;
import ar.com.ada.maven.utils.Keyboard;
import ar.com.ada.maven.utils.Paginator;

import java.util.*;

public class AccountView {

    private Scanner keyboard = Keyboard.getInstance();

    public int accountMenuSelectOption() {
        System.out.println("\n+--------------------------------------------------+");
        System.out.println("\t\t  Bank - Rota :: Modulo de Cuenta");
        System.out.println("+--------------------------------------------------+\n");

        System.out.println("Seleccione una acción del menú: " +
                "\n| 1 | Listar las cuentas" +
                "\n| 2 | Crear una nueva cuenta" +
                "\n| 3 | Eliminar una cuenta" +
                "\n| 4 | Salir - Menú principal");

        return Integer.valueOf(Keyboard.getInputInteger());

    }

    public String printPersonPerPage(List<AccountDTO> accounts, List<String> paginator, String optionEdithOrDelete, boolean showHeader) {
        if (showHeader) {
            System.out.println("\n+----------------------------------------------------------------+");
            System.out.println("\t  Bank - Rota :: Modulo de Cuentas :: Lista de Cuentas");
            System.out.println("+----------------------------------------------------------------+\n");
        }
        System.out.println("|\tID\t|\tCUENTAS");
        accounts.forEach(account -> {
                    System.out.println("|\t" + account.getId() + "\t|\t" + account.getNumber_account() + "\t|\t" +
                            account.getPerson() + "\t|\t" + account.getType_account() + "\t|");
                }
        );
        if (optionEdithOrDelete != null && !optionEdithOrDelete.isEmpty())
            paginator.set(paginator.size() - 2, optionEdithOrDelete);

        System.out.println("\n+----------------------------------------------------------------+");
        paginator.forEach(page -> System.out.print(page + " "));
        System.out.println("\n+----------------------------------------------------------------+\n");

        Scanner keyboard = Keyboard.getInstance();

        return String.valueOf(Keyboard.getInputString());

    }

    public Integer personIdSelected(String actionOption) {
        switch (actionOption) {
            case Paginator.DELETE:
                actionOption = "Eliminar";
                break;
            case Paginator.SELECT:
                actionOption = "Elegir";
                break;
        }
        System.out.println("Ingrese el numero de ID del cliente para " + actionOption + " ó 0 para cancelar: \n");

        return Integer.valueOf(Keyboard.getInputInteger());
    }

    public static void selectAccountIdToDeleteInfo(String actions) {
        System.out.println("De la siguiente lista de cuentas, seleccione el id para eliminar");
        Keyboard.pressEnterToContinue();
    }

    public boolean getResponseToDelete(AccountDTO account) {
        System.out.println(Ansi.RED + "¡ADVERTENCIA! SI LA CUENTA POSEE MOVIMIENTOS NO PODRÁ SER ELIMINADA" + Ansi.RESET);

        System.out.println("¿Esta seguro que desea eliminarlo?");
        System.out.println("| 1 | Si");
        System.out.println("| 2 | No");

        return Boolean.valueOf(Keyboard.getInputInteger());
    }

    public void showDeleteAccount(String person) {
        System.out.println(Ansi.GREEN + "La cuenta se ha eliminado exitosamente" + Ansi.RESET);
        Keyboard.pressEnterToContinue();
    }

    public void deleteAccountCanceled() {
        System.out.println(Ansi.RED + "Ha cancelado la elimincacion de la cuenta" + Ansi.RESET);
        Keyboard.pressEnterToContinue();
    }

    public void accountNotExist(int id) {
        System.out.println("No existe una cuenta con el id " + id + " asociado");
        System.out.println("Seleccione un ID valido ó 0 para cancelar");
    }

    public HashMap<String, String> getNewAccount(List<PersonDTO> person, Collection<Type_accountDTO> typeAccount) {
        HashMap<String, String> data = new HashMap<>();

        System.out.println("\nIngrese por favor al azar 4 digitos " +
                Ansi.RED + " \n[NO PODRAN SER NUMEROS CONSECUTIVOS, NI REPETIDOS]: " + Ansi.RESET);
        data.put("number_account", Keyboard.getInputInteger());

        System.out.println("\nSeleccione su usuario: ");
        person.forEach(persons ->
                System.out.println("\t|" + persons.getId() + "\t|\t" + persons.getName() + "\t|\t" + persons.getLastName() +
                        "\t|\t" + persons.getNumber_doc()));
        data.put("person_id", Keyboard.getInputInteger());

        System.out.println("\nSeleccione el tipo de cuenta que desea: ");
        typeAccount.forEach(types ->
                System.out.println("|\t" + types.getId() + "\t|\t" + types.getType_account()));
        data.put("type_account_id", Keyboard.getInputInteger());

        return data;
    }
}

