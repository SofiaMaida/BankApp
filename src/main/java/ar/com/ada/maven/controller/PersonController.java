package ar.com.ada.maven.controller;

import ar.com.ada.maven.model.DAO.DocumentationDAO;
import ar.com.ada.maven.model.DAO.PersonDAO;
import ar.com.ada.maven.model.DTO.DocumentationDTO;
import ar.com.ada.maven.model.DTO.PersonDTO;
import ar.com.ada.maven.utils.Ansi;
import ar.com.ada.maven.utils.Paginator;
import ar.com.ada.maven.view.MainView;
import ar.com.ada.maven.view.PersonView;

import java.util.HashMap;
import java.util.List;

public class PersonController {

    private static PersonView view = new PersonView();
    private static PersonDAO personDAO = new PersonDAO();
    private static DocumentationDAO docDAO = new DocumentationDAO();

    public static void init() {
        boolean shouldGetOut = false;

        while (!shouldGetOut) {
            int option = view.personMenuSelectOption();
            switch (option) {
                case 1:
                    createNewClient();
                    break;
                case 2:
                    personList();
                    break;
                case 3:
                    edithPerson();
                    break;
                case 4:
                    deletePerson();
                case 5:
                    shouldGetOut = true;
                default:
                    System.out.println("Opción no valida, vuelva a seleccionar");
            }
        }
    }

    public static void personList() {
        personListPerPage(null, true);
    }

    public static void createNewClient() {
        List<DocumentationDTO> typesDoc = docDAO.findAll();
        HashMap<String, String> client = view.getNewClient(typesDoc);
        if (!client.isEmpty()) {

            int numberDoc = Integer.parseInt(client.get("number_doc"));
            int typeDocID = Integer.parseInt(client.get("documentation_type"));
            // como mostrar los tipos de documento
            DocumentationDTO type = docDAO.findById(typeDocID);

            PersonDTO newPerson = new PersonDTO(client.get("name"), client.get("lastName"), numberDoc, type);

            PersonDTO byNumberDoc = personDAO.findByDni(numberDoc);
            if (byNumberDoc != null) {
                view.clientAlreadyExists(newPerson.getName(), newPerson.getLastName(), newPerson.getNumber_doc());
            } else {
                Boolean isSaved = personDAO.save(newPerson);
                if (isSaved) {
                    view.showNewClient(newPerson.getName(), newPerson.getLastName(), newPerson.getNumber_doc());
                } else {
                    view.updatePersonCanceled();
                }
            }
        } else {
            view.updatePersonCanceled();
            view.newClientCanceled();
        }
    }

    private static void edithPerson() {
        int personIdToEdith = personListPerPage(Paginator.EDITH, true);
        if (personIdToEdith != 0)
            editSelectedPerson(personIdToEdith);
        else
            view.updatePersonCanceled();
    }

    private static void deletePerson() {
        String optionDelete = "[" + Ansi.CYAN + "E" + Ansi.RESET + "liminar]";
        int personIdToDelete = personListPerPage(optionDelete, true);
        if (personIdToDelete != 0)
            deleteSelectedPerson(personIdToDelete);
        else
            view.updatePersonCanceled();
    }

    public static int personListPerPage(String optionSelectedEditOrDelete, boolean showHeader) {
        int limit = 3, currentPage = 0, totalPages, totalPersons, personIdSelected = 0;
        List<PersonDTO> person;
        Boolean shouldGetOut = false;
        List<String> paginator;

        while (!shouldGetOut) {
            totalPersons = personDAO.getTotalPersons();
            totalPages = (int) Math.ceil((double) totalPersons / limit);
            paginator = Paginator.buildPaginator(currentPage, totalPages);

            person = personDAO.findAll(limit, currentPage * limit);
            String choice = view.printPersonPerPage(person, paginator, optionSelectedEditOrDelete, showHeader);

            switch (choice) {
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
                case "e":
                case "E":
                    if (optionSelectedEditOrDelete != null) {
                        personIdSelected = view.personIdSelected(optionSelectedEditOrDelete);
                        shouldGetOut = true;
                    }
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
        return personIdSelected;
    }

    private static void editSelectedPerson(int id) {
        int edit = view.selectEditPerson();
        switch (edit) {
            case 1:
                editName(id);
                break;
            case 2:
                editLastName(id);
                break;
            case 3:
                editNumberDoc(id);
                break;
            default:
                System.out.println(Ansi.RED + "Seleccione una opcion valida" + Ansi.RESET);
        }
    }

    private static void editName(int id) {
        PersonDTO personById = personDAO.findById(id);
        if (personById != null) {
            view.getPersonToUpdate(personById);
            String nameToUpdate = view.nameToEdit(personById);

            if (!nameToUpdate.isEmpty()) {
                personDAO.findByName(nameToUpdate);
                personById.setName(nameToUpdate);

                Boolean isSaved = personDAO.update(personById, id);
                if (isSaved)
                    view.showUpdateData(personById.getName(), personById.getLastName(), personById.getNumber_doc());
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

    private static void editLastName(int id) {
        PersonDTO personById = personDAO.findById(id);
        if (personById != null) {
            view.getPersonToUpdate(personById);
            String lastNameToUpdate = view.lastNameToEdit(personById);

            if (!lastNameToUpdate.isEmpty()) {
                personDAO.findByName(lastNameToUpdate);
                personById.setLastName(lastNameToUpdate);

                Boolean isSaved = personDAO.update(personById, id);
                if (isSaved)
                    view.showUpdateData(personById.getName(), personById.getLastName(), personById.getNumber_doc());
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

    private static void editNumberDoc(int id) {
        PersonDTO personById = personDAO.findById(id);
        if (personById != null) {
            view.getPersonToUpdate(personById);
            Integer numberDocToUpdate = view.numberDocToEdit(personById);

            if (numberDocToUpdate != null) {
                personDAO.findById(numberDocToUpdate);
                personById.setNumber_doc(String.valueOf(numberDocToUpdate));

                Boolean isSaved = personDAO.update(personById, id);
                if (isSaved)
                    view.showUpdateData(personById.getName(), personById.getLastName(), personById.getNumber_doc());
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

    private static void deleteSelectedPerson(int id) {
        PersonDTO person = personDAO.findById(id);
        if (person != null) {
            Boolean nameDelete = view.getNameDelete(person);
            if (nameDelete) {
                Boolean isDelete = personDAO.delete(person.getId());
                if (isDelete)
                    view.showDeletePerson(person.getName(), person.getLastName(), person.getNumber_doc(), person.getDocument_type());
            } else {
                view.deletePersonCanceled();
            }
        }
    }
}


