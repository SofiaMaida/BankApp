package ar.com.ada.maven.view;

import ar.com.ada.maven.model.DTO.AccountDTO;
import ar.com.ada.maven.model.DTO.PersonDTO;
import ar.com.ada.maven.utils.Ansi;
import ar.com.ada.maven.utils.Keyboard;
import ar.com.ada.maven.utils.Paginator;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class AccountView {

    private Scanner keyboard = Keyboard.getInstance();

    public int accountMenuSelectOption() {
        System.out.println("\n+--------------------------------------------------+");
        System.out.println("\t\t  Bank - Rota :: Modulo de Cuenta");
        System.out.println("+--------------------------------------------------+\n");

        System.out.println("Seleccione una acción del menú: " +
                "\n| 1 | Crear una nueva cuenta" +
                "\n| 2 | Ingresar a sus movimientos" +
                "\n| 3 | Eliminar una cuenta" +
                "\n| 4 | Salir - Menú principal");

        return Integer.valueOf(Keyboard.getInputInteger());

    }

    public String printPersonPerPage(List<AccountDTO> accounts, List<String> paginator, String optionDelete, boolean showHeader) {
        if (showHeader) {
            System.out.println("\n+----------------------------------------------------------------+");
            System.out.println("\t  Bank - Rota :: Modulo de Cuentas :: Lista de Cuentas");
            System.out.println("+----------------------------------------------------------------+\n");
        }
        System.out.println("\t|\tID\t|CUENTAS|");
        accounts.forEach(account -> {
            System.out.println(account.getId() + account.getNumber_account());
            //System.out.println(account.getPerson().getId() + account.getPerson().getName() + account.getPerson().getLastName(),
              //              account.getType_account());

                }
        );
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

        System.out.println("¿Esta seguro que desea eliminarlo?\n");
        System.out.println("\n| 1 | Si");
        System.out.println("\n| 2 | No");

        keyboard.nextLine();

       return Boolean.valueOf(Keyboard.getInputInteger());
    }

    public void showDeleteAccount(PersonDTO person) {
        System.out.println("La cuenta se ha eliminado exitosamente");
        Keyboard.pressEnterToContinue();
    }

    public void deleteAccountCanceled() {
        System.out.println("Ha cancelado la elimincacion de la cuenta");
        Keyboard.pressEnterToContinue();
    }

    public void accountNotExist(int id) {
        System.out.println("No existe una cuenta con el id " + id + " asociado");
        System.out.println("Seleccione un ID valido ó 0 para cancelar");
    }
}

