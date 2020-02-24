package ar.com.ada.maven.controller;

import ar.com.ada.maven.model.DAO.AccountDAO;
import ar.com.ada.maven.model.DAO.PersonDAO;
import ar.com.ada.maven.model.DAO.Type_accountDAO;
import ar.com.ada.maven.model.DTO.AccountDTO;
import ar.com.ada.maven.model.DTO.PersonDTO;
import ar.com.ada.maven.utils.Paginator;
import ar.com.ada.maven.view.AccountView;
import ar.com.ada.maven.view.MainView;
import ar.com.ada.maven.view.TypeAccountView;

import java.util.List;

public class AccountController {

    private static AccountView view = new AccountView();
    private static TypeAccountView typeAccountView = new TypeAccountView();
    private static PersonDAO personDAO = new PersonDAO();
   // private static TypeAccountController typeAccountController = new TypeAccountController();
    private static Type_accountDAO type_accountDAO = new Type_accountDAO(false);
    private static AccountDAO accountDAO = new AccountDAO(false);

    static void init() {
        boolean shouldGetOut = false;
        while (!shouldGetOut) {
            int option = view.accountMenuSelectOption();
            switch (option) {
                case 1:
                    createNewAccount();
                    break;
                case 2:
                    loginToAccount();
                    break;
                case 3:
                    deleteAccount();
                    break;
                case 4:
                    shouldGetOut = true;
                    break;
                default:
                    MainView.chooseValidOption();

            }
        }
    }

    public static void createNewAccount() {
        List<PersonDTO> person = personDAO.findAll();
        String newAccount = typeAccountView.getNewAccountARG();

        if (!newAccount.isEmpty()) {
            typeAccountView.choiceAccountId();                                                      //selecciona el usuario

            int accountId = PersonController.personListPerPage(Paginator.SELECT, false);            //enlista clientes

            if (accountId != 0) {
                AccountDTO byName = accountDAO.findByNumberAccount(newAccount);
                PersonDTO byId = personDAO.findById(accountId);

                AccountDTO accountDTO = new AccountDTO(newAccount, byId);

                if (byName != null && byName.equals(accountDTO)) {
                    typeAccountView.accountAlreadyExists(accountDTO.getNumber_account());
                } else {
                    Boolean isSaved = accountDAO.save(accountDTO);
                    if (isSaved)
                        typeAccountView.showNewAccount(accountDTO.getNumber_account());
                }
            } else {
                typeAccountView.newAccountCanceled();
            }
        } else {
            typeAccountView.newAccountCanceled();

        }



        /*//enlistar los clientes para seleccionar quien es y crear la cuenta
        List<PersonDTO> person = personDAO.findAll();
        //pide los numeros para la cuenta y los almacena en new accountARG NO DEBERIA PASAR DE UNA
        String newAccountARG = typeAccountView.getNewAccountARG(person);
        String numberAccount = "AR25 0064 0482 25 536398";

        //llamar al menu de opciones de typeAccountView
        //typeAccountView.typeAccountMenuSelectOption();

        if (!newAccountARG.isEmpty()) {
            //typeAccountView.choiceAccountId(person);
            int personId = PersonController.personListPerPage(Paginator.SELECT, false);

            if (personId != 0) {
                AccountDTO accountByNumberAccount = accountDAO.findByNumberAccount(newAccountARG);
                PersonDTO personById = personDAO.findById(personId);

                AccountDTO accountDTO = new AccountDTO(newAccountARG, personById);

                if (accountByNumberAccount != null && accountByNumberAccount.equals(accountDTO)) {
                    typeAccountView.accountAlreadyExists(accountDTO.getNumber_account());
                } else {
                    Boolean isSaved = accountDAO.save(accountDTO);
                    if (isSaved)
                        typeAccountView.showNewAccount(numberAccount + accountDTO.getNumber_account());
                }
            } else {
                typeAccountView.newAccountCanceled();
            }
        } else {
            typeAccountView.newAccountCanceled();
        }*/
    }

    private static void loginToAccount() {
        //List<AccountDTO> account;

        //view.printPersonPerPage(account, paginator);
        //findByDNI

    }

    private static int listAccountPerPage(String optionSelectDelete, boolean showHeader) {
        int limit = 3, currentPage = 0, totalAccount, totalPages, personIdSelected = 0;
        List<AccountDTO> account;
        List<String> paginator;
        boolean shouldGetOut = false;

        while (!shouldGetOut) {
            totalAccount = accountDAO.getTotalPersons();
            totalPages = (int) Math.ceil((double) totalAccount / limit);
            paginator = Paginator.buildPaginator(currentPage, totalPages);

            account = accountDAO.findAll(limit, currentPage * limit);
            String choice = view.printPersonPerPage(account, paginator, optionSelectDelete, showHeader);

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
                    if (optionSelectDelete != null) {
                        personIdSelected = view.personIdSelected(optionSelectDelete);
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

    private static AccountDTO getAccountToDelete(String optionDelete) {
        boolean hasExitWhile = false;
        AccountDTO accountToDelete = null;

        boolean actionInfo = Paginator.DELETE.equals(optionDelete);
        view.selectAccountIdToDeleteInfo(String.valueOf(actionInfo));

        int accountIdToDelete = listAccountPerPage(optionDelete, true);

        if (accountIdToDelete != 0) {
            while (!hasExitWhile) {
                accountToDelete = accountDAO.findById(accountIdToDelete);
                if (accountToDelete == null) {
                    view.accountNotExist(accountIdToDelete);
                    accountIdToDelete = view.personIdSelected(optionDelete);
                    hasExitWhile = (accountIdToDelete == 0);
                } else {
                    hasExitWhile = true;
                }
            }
        }

        return accountToDelete;
    }

    private static void deleteAccount() {
        AccountDTO accountToDelete = getAccountToDelete(Paginator.DELETE);

        if (accountToDelete != null) {
            boolean toDelete = view.getResponseToDelete(accountToDelete);
            if (toDelete) {
                Boolean isDelete = accountDAO.delete(accountToDelete.getId());
                if (isDelete)
                    view.showDeleteAccount(accountToDelete.getPerson());
            }
        } else {
            view.deleteAccountCanceled();
        }


    }
}



