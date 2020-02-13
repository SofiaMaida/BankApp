package ar.com.ada.maven.view;

import ar.com.ada.maven.model.DTO.TransactionDTO;
import ar.com.ada.maven.utils.Ansi;
import ar.com.ada.maven.utils.Keyboard;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MovementView {

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

    public void printAllMovements(){

    }

    public void printMenuTypeMov(){}

    public void showNewMovements(){}

    public void getNewMovements(){
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
        }
    }

