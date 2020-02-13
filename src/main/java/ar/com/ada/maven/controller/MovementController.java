package ar.com.ada.maven.controller;


import ar.com.ada.maven.model.DAO.MovementsDAO;
import ar.com.ada.maven.model.DTO.MovementsDTO;
import ar.com.ada.maven.view.MovementView;

import java.util.List;

public class MovementController {

    private static MovementView view = new MovementView();
    private static MovementsDAO movDAO = new MovementsDAO();

    public void init(){
        Boolean des = false;
        while (!des){
            int menu = view.movementsMenu();
            switch (menu){
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

    public void createNewMovement(){
        MovementView view = MovementController.view;
        int movToInsert = view.getNewMovements();
        if (!movToInsert == null){
            MovementsDTO newMov = new MovementsDTO(movToInsert);

        }
    }

    public void listAllMovements(){}

    private void movementsList(){
        List<MovementsDTO> movementsDTOList = movDAO.findAll();
        view.printAllMovements(movementsDTOList);
        try {
            System.out.println("|");
        }
    }
}
