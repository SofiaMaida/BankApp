package ar.com.ada.maven.controller;

import ar.com.ada.maven.model.DAO.PersonDAO;
import ar.com.ada.maven.model.DAO.Type_accountDAO;
import ar.com.ada.maven.model.DTO.PersonDTO;
import ar.com.ada.maven.model.DTO.Type_accountDTO;
import ar.com.ada.maven.view.PersonView;

public class PersonController {

    private static PersonView view = new PersonView();
    private static PersonDAO personDAO = new PersonDAO();
    // private static Type_accountDAO type_accountDAO = new Type_accountDAO();

    public static void init() {
        boolean shouldGetOut = false;

        while (!shouldGetOut) {
            String option = view.getNewClient();
            switch (option) {
                case "a":
                    createNewClient();
                    break;
                //case "b":
                //  createNewAccount();
                //break;
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
