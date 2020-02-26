package ar.com.ada.maven.view;

import ar.com.ada.maven.model.DTO.Type_accountDTO;
import ar.com.ada.maven.utils.Ansi;
import ar.com.ada.maven.utils.Keyboard;

import java.util.List;

public class TypeAccountView {

    public String typeAccountMenuSelectOption(List<Type_accountDTO> typeAccount) {
        System.out.println("\n+--------------------------------------------------+");
        System.out.println("\t\t  Bank - Rota :: Modulo Tipo de cuenta");
        System.out.println("+--------------------------------------------------+\n");

        System.out.println("Seleccione qué tipo de cuenta desea: ");
        typeAccount.forEach(types ->
                System.out.println("\t|" + types.getId() + "\t|" + types.getType_account()));

        return String.valueOf(Keyboard.getInputInteger());
    }

    public void newAccountCanceled() {
        System.out.println("Ha cancelado el ingreso de una nueva cuenta\n");
        Keyboard.pressEnterToContinue();
    }

    public void choiceAccountId() {
        System.out.println("Seleccione de la siguiente lista su usuario:");
        Keyboard.pressEnterToContinue();
        /*System.out.println("Seleccione su usuario: ");
        person.forEach(personDTO ->
                System.out.println("\t|" + personDTO.getId() + "\t|\t"+ personDTO.getName() +
                        "\t|\t" + personDTO.getLastName() + "\t|\t" + personDTO.getNumber_doc()));
        Keyboard.pressEnterToContinue();
        return String.valueOf(Keyboard.getInputInteger());*/
    }

    public String getNewAccount() {
        System.out.println("Ingrese por favor al azar 4 digitos " +
                Ansi.RED + " \n[NO PODRAN SER NUMEROS CONSECUTIVOS, NI REPETIDOS]: " + Ansi.RESET);

        return String.valueOf(Keyboard.getInputInteger());
    }


    public void accountAlreadyExists(String type) {
        System.out.println("Los numeros ingresados ya corresponden a otro cliente");
        Keyboard.pressEnterToContinue();
    }

    public void showNewAccount(String type) {
        System.out.println("Su numero de cuenta es: " + type);
    }

}
