package ar.com.ada.maven.controller;

import ar.com.ada.maven.model.DAO.PersonDAO;
import ar.com.ada.maven.model.DTO.PersonDTO;
import ar.com.ada.maven.utils.Paginator;
import ar.com.ada.maven.view.MainView;
import ar.com.ada.maven.view.PersonView;

import java.util.List;

public class PersonController {

    private static PersonView view = new PersonView();
    private static PersonDAO personDAO = new PersonDAO();

    public static void init() {
        boolean shouldGetOut = false;

        while (!shouldGetOut) {
            String option = view.getNewClient();
            switch (option) {
                case "a":
                    createNewClient();
                    break;
                case "b":
                    personList();
                case "d":
                    shouldGetOut = true;
                default:
                    System.out.println("Opción no valida, vuelva a seleccionar");
            }
        }
    }

    public static void createNewClient() {
        String clientName = view.getNewClient();
        if (!clientName.isEmpty()) {
            PersonDTO newPerson = new PersonDTO(clientName);
            PersonDTO byName = personDAO.findById(1);
            PersonDTO byLastName = personDAO.findById(1);
            PersonDTO byNumberDoc = personDAO.findById(1);

            if (byName != null && byName.getName().equals(newPerson.getName()) &&
                    byLastName != null && byLastName.getLastName().equals(newPerson.getLastName()) &&
                    byNumberDoc != null && byNumberDoc.getNumber_doc().equals(newPerson.getNumber_doc())) {
                view.clientAlreadyExists(newPerson.getName(), newPerson.getLastName(), newPerson.getNumber_doc());
            } else {
                Boolean isSaved = personDAO.save(newPerson);
                if (isSaved)
                    view.showNewClient(newPerson.getName(), newPerson.getLastName(), newPerson.getNumber_doc());
            }
        }
    }

    public static void personList() {
        int i = personListPerPage(null, true);
        List<PersonDTO> person = personDAO.findAll();
        view.printAllPerson(person);
    }

    public static int personListPerPage(String optionSelectedEdithOrDelete, boolean showHeader) {
        int limit = 3, currentPage = 0, totalPages, totalPersons, personIdSelected = 0;
        List<PersonDTO> person = null;
        Boolean shouldGetOut = false;
        List<String> paginator;

        while (!shouldGetOut) {
            totalPersons = personDAO.getTotalPersons();
            totalPages = (int) Math.ceil((double) totalPersons / limit);
            paginator = Paginator.buildPaginator(currentPage, totalPages);

            String choice = view.printPersonPerPage(person, paginator, optionSelectedEdithOrDelete, showHeader);
            switch (choice) {
                case "i":
                case "I":
                    currentPage = 0;
                    break;
                case "a":
                case "A":
                    if (currentPage > 0)
                        currentPage--;
                    break;
                case "s":
                case "S":
                    if (currentPage + 1 < totalPages)
                        currentPage++;
                    break;
                case "u":
                case "U":
                    currentPage = totalPages - 1;
                    break;
                case "e":
                case "E":
                    boolean shouldtGetOut;
                    if (optionSelectedEdithOrDelete != null) {
                        personIdSelected = view.personIdSelected(optionSelectedEdithOrDelete);
                        shouldtGetOut = true;
                    }
                case "q":
                case "Q":
                    shouldtGetOut = true;
                    break;
                default:
                    if (choice.matches("^-?\\d+$")) {
                        int page = Integer.parseInt(choice);
                        if (page > 0 && page <= totalPages) currentPage = page - 1;
                    } else MainView.chooseValidOption();
            }
        }
        return personIdSelected;
    }

}

    /*public static void createNewAccount() {
        String accountNumber = view.getNewTypeAccount();
        if (!accountNumber.isEmpty()) {
            Type_accountDTO newAccount = new Type_accountDTO(accountNumber);
            Type_accountDTO byAccount = type_accountDAO.findById(1);
            if (byAccount != null && byAccount.getType().equals(newAccount.getType())) {
                view.accountAlreadyExists(newAccount.getType());
            } else {
                Boolean isSaved = type_accountDAO.save(newAccount);
                if (isSaved)
                    view.showNewAccount(newAccount.getType());
            }
        } else {
            view.newAccountCanceled();
        }

    }*/