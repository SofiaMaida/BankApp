package ar.com.ada.maven.view;

import ar.com.ada.maven.model.DTO.MovementsDTO;
import ar.com.ada.maven.model.DTO.TransactionDTO;
import ar.com.ada.maven.model.DTO.Type_movementsDTO;
import ar.com.ada.maven.utils.Ansi;
import ar.com.ada.maven.utils.Keyboard;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class MovementView {

    private static TransactionDTO transaction = new TransactionDTO();

    public int movementsMenu(){
        System.out.println(" - BANK ROTA - Modulo Movimientos: \n");
        System.out.println(" -> Seleccione un opción del menú: " +
                "\n| 1 | ¿Hacer un movimiento nuevo?" +
                "\n| 2 | Consultar movimientos" +
                "\n| 3 | Salir. ");

        Scanner keyboard = Keyboard.getInstance();
        while (true) {
            try {
                int choice = keyboard.nextInt();
                keyboard.nextLine();
                return choice;
            } catch (InputMismatchException e) {
                System.out.println("Error, debe ingresar un dato valido");
                keyboard.nextLine();
            }
        }
    }

    public void printAllMovement(List<MovementsDTO> movements){
        System.out.println("*** LISTA DE MOVIMIENTOS ***");

        System.out.println("\t|\tID \t| TIPO DE MOVIMIENTO |\t| MONTO \t| FECHA | \t| CONCEPTO |");
        System.out.println("\t|-------------------|");

        movements.forEach((MovementsDTO) -> {
            System.out.println("\t|" + MovementsDTO.getId() + "\t|\t" + MovementsDTO.getType_movements() + "\t|"
                    + transaction.getAmount() + "\t|\t" + MovementsDTO.getMove_date() + "\t|\t" + MovementsDTO.getDescription()
                    +"\t|\t"); });

        Keyboard.pressEnterToContinue();

    }

    public void printMenuTypeMov(){}

    public void showNewMovements(){}

    public HashMap<String, String> getNewMovements(List<Type_movementsDTO> typeMov){
        HashMap<String,String> data = new HashMap<>();
        TransactionDTO transaction = new TransactionDTO();
        Scanner scanner = Keyboard.getInstance();

        System.out.println(" | Bienvenidos al creador de MOVIMIENTOS en tu actual cuenta |");
        System.out.println(" Para realizar un movimiento seleccione una de las siguientes opciones:\n");
        System.out.println(" (1) Débito - \n" +
                "(2) Crédito - \n");
        data.put("type_AccountDTO", Keyboard.getInputInteger());

        System.out.println("Ingrese el monto por realizar:\n ");
        //INSTANCIAR EL GET AMOUNT PARA GUARDARLO EN LA BASE DE DATOS
        data.put("Amount", Keyboard.getInputDouble());

        System.out.println("Ingresar la fecha:\n");
        data.put("move_date", scanner.nextLine());

        System.out.println("Agregar concepto del movimiento: \n");
        data.put("description", Keyboard.getInputString());

        System.out.println(Ansi.RED);
        System.out.println("ADVERTENCIA || Los movimientos no se pueden modicifar o eliminar ||");
        System.out.println(Ansi.RESET);
        return data;
    }

    public String printMovementsPerPage(List<MovementsDTO> movements, List<String> paginator, boolean showHeader) {
        if (showHeader) {
            System.out.println("\n+----------------------------------------------------------------+");
            System.out.println("\t  Bank - Rota :: Modulo Movimientos :: Lista de Movimientos");
            System.out.println("+----------------------------------------------------------------+\n");
        }

        System.out.println("Los primeros 7 Movimientos son: ");
        movements.forEach((MovementsDTO) -> {
            System.out.println("\t|" + MovementsDTO.getId() + "\t|\t" + MovementsDTO.getType_movements() + "\t|"
                    + transaction.getAmount() + "\t|\t" + MovementsDTO.getMove_date() + "\t|\t" + MovementsDTO.getDescription()
                    +"\t|\t"); });
        Scanner keyboard = Keyboard.getInstance();

        while (true){
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
}


