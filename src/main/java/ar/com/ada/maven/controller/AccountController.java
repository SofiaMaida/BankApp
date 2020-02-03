package ar.com.ada.maven.controller;

import ar.com.ada.maven.model.DAO.AccountDAO;
import ar.com.ada.maven.model.DAO.PersonDAO;
import ar.com.ada.maven.model.DAO.Type_accountDAO;
import ar.com.ada.maven.model.DTO.Type_accountDTO;
import ar.com.ada.maven.view.AccountView;
import ar.com.ada.maven.view.MainView;

public class AccountController {

    private static AccountView view = new AccountView();
    private static AccountDAO accountDAO = new AccountDAO(false);
    private static PersonDAO personDAO = new PersonDAO(false);
    private static Type_accountDAO type_accountDAO = new Type_accountDAO(false);

    static void init() {
        boolean shouldGetOut = false;
        while (!shouldGetOut) {
            String option = view.getNewTypeAccount();
            switch (option) {
                case "A":
                    createNewAccount();
                break;
                case "D":
                    shouldGetOut = true;
                    break;
                default:
                    MainView.chooseValidOption();

            }
        }
    }

    public static void createNewAccount() {
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

    }


}
