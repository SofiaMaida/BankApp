package ar.com.ada.maven.controller;


import ar.com.ada.maven.model.DAO.*;
import ar.com.ada.maven.model.DTO.*;
import ar.com.ada.maven.utils.Paginator;
import ar.com.ada.maven.view.MainView;
import ar.com.ada.maven.view.MovementView;

import java.io.IOException;
import java.time.Instant;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class MovementController {

    private static MovementView view = new MovementView();
    private static MovementsDAO movDAO = new MovementsDAO();
    private static Type_movementsDAO typeMovDAO = new Type_movementsDAO(true);
    private static Type_accountDAO typeAccDAO = new Type_accountDAO(true);

    public static void init() {
        Boolean des = false;
        while (!des) {
            int menu = view.movementsMenu();
            switch (menu) {
                case 1:
                    createNewMovement();
                    break;
                case 2:
                    movementsList();
                    break;
                case 3:
                    listAllMovements();
                    break;
                default:
                    System.out.println("| DEBE ESCOGER UNA OPCIÓN VALIDA |");
                    System.out.println("");
            }
        }
    }

    public static void createNewMovement() {
        List<Type_movementsDTO> typeMov = (List<Type_movementsDTO>) typeMovDAO.findAll();
        HashMap<String, String> movement = view.getNewMovements(typeMov);
        if (!movement.isEmpty()){
            int typeMovID = Integer.parseInt(movement.get("type_movement"));
            Type_movementsDTO type = typeMovDAO.findById(typeMovID);
            Date now = Date.from(Instant.now());
            Double amount = Double.parseDouble(movement.get("amount"));


            MovementsDTO newMovement = new MovementsDTO(type, amount, now, movement.get("description"));
            Boolean isSaved = movDAO.save(newMovement);
            if (isSaved)
            view.showNewMovements(newMovement);
            else view.updateMovementCanceled();
        }
        view.updateMovementCanceled();
    }

    public static void listAllMovements() {
        List<MovementsDTO> movementsDTOList = movDAO.findAll();
        view.printAllMovement(movementsDTOList);
        try {
            System.out.println("| Presiona ENTER para regresar al menu |\n");
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void movementsList() {
        movementPerPage(true);
    }

    public static int movementPerPage(boolean showHeader) {
        int limit = 7, currentPage = 0, totalPages, totalMov, movementID = 0;
        List<MovementsDTO> movements = null;
        Boolean shouldGetOut = false;
        List<String> paginator;

        while (!shouldGetOut) {
            movements = movDAO.findAll(limit, currentPage * limit);
            totalMov = movDAO.getTotalMovements();
            totalPages = (int) Math.ceil((double) totalMov / limit);
            paginator = Paginator.buildPaginator(currentPage, totalPages);


            String choice = view.printMovementsPerPage(movements, paginator, showHeader);

            switch (choice){
                case "i":
                case "I":
                    currentPage = 0;
                    break;
                case "a":
                case "A":
                    if (currentPage > 0) currentPage--;
                    break;
                case "s":
                case "S":
                    if (currentPage + 1 < totalPages) currentPage++;
                    break;
                case "u":
                case "U":
                    currentPage = totalPages - 1;
                    break;
                case "q":
                case "Q":
                    shouldGetOut = true;
                    break;
                default:
                    if (choice.matches("^-?\\d+$")) {
                        int page = Integer.parseInt(choice);
                        if (page > 0 && page <= totalPages) currentPage = page - 1;
                    } else MainView.chooseValidOption();
            }
        }
        return movementID;
    }
}
