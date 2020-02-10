package ar.com.ada.maven.controller;

import ar.com.ada.maven.model.DAO.PersonDAO;
import ar.com.ada.maven.model.DTO.PersonDTO;
import ar.com.ada.maven.utils.Ansi;
import ar.com.ada.maven.utils.Paginator;
import ar.com.ada.maven.view.PersonView;

import java.util.ArrayList;
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
                    break;
                case "c":
                    edithPerson();
                    break;
                case "d":
                    deletePerson();
                case "f":
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
        } else {
            view.newClientCanceled();
        }
    }

    public static void personList() {
        List<PersonDTO> person = personDAO.findAll();
        view.printAllPerson(person);
    }

    private static void edithPerson() {
        int personIdToEdith = personListPerPage(true);
        if (personIdToEdith != 0)
            editSelectedPerson(personIdToEdith);
        else
            view.updatePersonCanceled();
    }

    private static void editSelectedPerson(int id) {
        PersonDTO person = personDAO.findById(id);
        if (person != null) {
            String nameToUpdate = view.getDataUpdate(person);

            if (!nameToUpdate.isEmpty()) {
                personDAO.findByDni(nameToUpdate);
                person.setNumber_doc(nameToUpdate);
                Boolean isSaved = personDAO.update(person, id);
                if (isSaved)
                    view.showUpdateData(person.getName(), person.getLastName(), person.getNumber_doc());
            } else
                view.updatePersonCanceled();
        } else {
            view.personNotExist(id);
            int personIdSelected = view.personIdSelected("Editar");
            if (personIdSelected != 0) {
                editSelectedPerson(personIdSelected);
            } else
                view.updatePersonCanceled();
        }
    }

    private static void deletePerson() {
        int personIdToDelete = personListPerPage(false);
        if (personIdToDelete != 0)
            deleteSelectedPerson(personIdToDelete);
        else
            view.updatePersonCanceled();
    }

    private static void deleteSelectedPerson(int id) {
        PersonDTO person = personDAO.findById(id);
        if (person != null) {
            Boolean nameDelete = view.getNameDelete(person);
            if (nameDelete) {
                Boolean isDelete = personDAO.delete(id);
                if (isDelete)
                    view.showDeletePerson(person.getName(), person.getLastName(), person.getNumber_doc());
            } else
                view.deletePersonCanceled();
        } else {
            view.personNotExist(id);
            int personIdSelected = view.personIdSelected("Eliminar");
            if (personIdSelected != 0) {
                deleteSelectedPerson(personIdSelected);
            } else
                view.deletePersonCanceled();
        }

    }

    private static int personListPerPage(boolean hasEdith) {
        int limit = 3, currentPage = 0, numberPersons, totalPages;
        List<PersonDTO> person;
        List<String> paginator;
        Boolean shouldGetOut = false;

        while (!shouldGetOut) {
            numberPersons = personDAO.getTotalPersons();
            totalPages = (int) Math.ceil((double) numberPersons / limit);
            paginator = buildPaginator(currentPage, totalPages);

            person = personDAO.findAll(limit, currentPage * limit);
            String choice = view.printPersonPerPage(person, paginator, hasEdith);

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
                    String action = (hasEdith) ? "Editar" : "Eliminar";
                    return view.personIdSelected(action);
                case "q":
                case "Q":
                    shouldGetOut = true;
                    break;
                default:
                    if (choice.matches("^-?\\d+$")) {
                        int page = Integer.parseInt(choice);
                        if (page > 0 && page <= totalPages) currentPage = page - 1;
                    } else
                        System.out.println("Error, debe ingresar una opcion valida");
            }
        }
        return 0;
    }

    private static List<String> buildPaginator(int currentPage, int totalPages) {
        List<String> pages = new ArrayList<>();
        pages.add("[" + Ansi.CYAN + "I" + Ansi.RESET + "nicio]");
        pages.add("[" + Ansi.CYAN + "A" + Ansi.RESET + "nterior]");

        for (int i = 1; i <= totalPages; i++) {
            if (i == currentPage + 1)
                pages.add(Ansi.YELLOW + "[" + i + "]" + Ansi.RESET);
            else
                pages.add("[" + i + "]");
        }

        pages.add("[" + Ansi.CYAN + "S" + Ansi.RESET + "iguiente]");
        pages.add("[" + Ansi.CYAN + "U" + Ansi.RESET + "ltimo]");
        pages.add("");
        pages.add("[" + Ansi.CYAN + "Q" + Ansi.RESET + " para salir]");

        return pages;
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
}