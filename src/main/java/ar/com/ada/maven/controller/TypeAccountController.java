package ar.com.ada.maven.controller;

import ar.com.ada.maven.model.DAO.Type_accountDAO;
import ar.com.ada.maven.model.DTO.Type_accountDTO;
import ar.com.ada.maven.view.MainView;
import ar.com.ada.maven.view.TypeAccountView;

import java.util.List;

/*public class TypeAccountController {

    private static TypeAccountView view = new TypeAccountView();
    private static Type_accountDAO type_accountDAO = new Type_accountDAO(false);

    public static void init() {
        boolean shouldGetOut = false;

        while (!shouldGetOut) {
            int option = view.typeAccountMenuSelectOption();

            switch (option) {
                //case 1:
                  //  createNewAccountARG();
                    //break;
                case 2:
                    createNewAccountUSD();
                    break;
                case 3:
                    createNewAccountEUR();
                    break;
                case 4:
                    shouldGetOut = true;
                    break;
                default:
                    MainView.chooseValidOption();

            }
        }

    }

    private static void createNewAccountARG() {
        String typeAccount = view.getNewAccountARG();
        String numberAccount = "AR25 0064 0482 25 536398";

        if (!(typeAccount == null)) {
            Type_accountDTO newTypeAccount = new Type_accountDTO(typeAccount);
            Type_accountDTO byAccount = type_accountDAO.findByNumberAccount(typeAccount);
            if (byAccount != null && byAccount.getType_account().equals(newTypeAccount.getType_account())) {
                view.accountAlreadyExists(newTypeAccount.getType_account());
            } else {
                Boolean isSaved = type_accountDAO.save(newTypeAccount);
                if (isSaved) {
                    //for (int i = 2125; i <= typeAccount; i+=3){
                    view.showNewAccount(numberAccount + newTypeAccount.getType_account());
                } else {
                    view.newAccountCanceled();
                }
            }
        }
    }

    private static void createNewAccountUSD() {
        String typeAccount = view.getNewAccountUSD();
        if (!(typeAccount == null)) {
            Type_accountDTO newTypeAccount = new Type_accountDTO(typeAccount);
            Type_accountDTO byAccount = type_accountDAO.findByNumberAccount(typeAccount);
            if (byAccount != null && byAccount.getType_account().equals(newTypeAccount.getType_account())) {
                view.accountAlreadyExists(newTypeAccount.getType_account());
            } else {
                Boolean isSaved = type_accountDAO.save(newTypeAccount);
                if (isSaved)
                    view.showNewAccount(newTypeAccount.getType_account());
            }
        } else {
            view.newAccountCanceled();
        }

    }

    private static void createNewAccountEUR() {
        String typeAccount = view.getNewAccountEUR();
        if (!(typeAccount == null)) {
            Type_accountDTO newTypeAccount = new Type_accountDTO(typeAccount);
            Type_accountDTO byAccount = type_accountDAO.findByNumberAccount(typeAccount);
            if (byAccount != null && byAccount.getType_account().equals(newTypeAccount.getType_account())) {
                view.accountAlreadyExists(newTypeAccount.getType_account());
            } else {
                Boolean isSaved = type_accountDAO.save(newTypeAccount);
                if (isSaved)
                    view.showNewAccount(newTypeAccount.getType_account());
            }
        } else {
            view.newAccountCanceled();
        }

    }

}*/
