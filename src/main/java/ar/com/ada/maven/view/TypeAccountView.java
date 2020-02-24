package ar.com.ada.maven.view;

import ar.com.ada.maven.model.DTO.PersonDTO;
import ar.com.ada.maven.model.DTO.Type_accountDTO;
import ar.com.ada.maven.utils.Ansi;
import ar.com.ada.maven.utils.Keyboard;

import java.util.List;

public class TypeAccountView {

    public int typeAccountMenuSelectOption(List<Type_accountDTO> typeAccount) {
        System.out.println("\n+--------------------------------------------------+");
        System.out.println("\t\t  Bank - Rota :: Modulo Tipo de cuenta");
        System.out.println("+--------------------------------------------------+\n");

        System.out.println("Seleccione qué tipo de cuenta desea: ");
        typeAccount.forEach(types ->
                System.out.println("\t|" + types.getId() + "\t|" + types.getType_account()));
                //"\n| 1 | Cuenta corriente en pesos ARG" +
                //"\n| 2 | Cuenta corriente en pesos USD" +
                //"\n| 3 | Cuenta corriente en pesos EUR" +
                //"\n| 4 | Cancelar");

        return Integer.valueOf(Keyboard.getInputInteger());

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

    public String getNewAccountARG() {
        System.out.println("Ingrese por favor al azar 4 digitos " +
                Ansi.RED + " \n[NO PODRAN SER NUMEROS CONSECUTIVOS, NI REPETIDOS]: " + Ansi.RESET);

        return String.valueOf(Keyboard.getInputInteger());
    }

    public String getNewAccountUSD() {
        System.out.println("Ingrese por favor al azar 4 digitos " +
                Ansi.RED + " \n[NO PODRAN SER NUMEROS CONSECUTIVOS, NI REPETIDOS]: " + Ansi.RESET);

        String integer = Keyboard.getInputInteger();

        return integer;
    }

    public String getNewAccountEUR() {
        System.out.println("Ingrese por favor al azar 4 digitos " +
                Ansi.RED + " \n[NO PODRAN SER NUMEROS CONSECUTIVOS, NI REPETIDOS]: " + Ansi.RESET);

        String integer = Keyboard.getInputInteger();

        return integer;
    }

    public void accountAlreadyExists(String type) {
        System.out.println("Los numeros ingresados ya corresponden a otro cliente");
        Keyboard.pressEnterToContinue();
    }

    public void showNewAccount(String type) {
        System.out.println("Su numero de cuenta es: " + type);
    }

}
