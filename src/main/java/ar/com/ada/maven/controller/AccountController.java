package ar.com.ada.maven.controller;

import ar.com.ada.maven.model.DAO.AccountDAO;
import ar.com.ada.maven.model.DAO.PersonDAO;
import ar.com.ada.maven.model.DAO.Type_accountDAO;
import ar.com.ada.maven.model.DTO.AccountDTO;
import ar.com.ada.maven.model.DTO.PersonDTO;
import ar.com.ada.maven.model.DTO.Type_accountDTO;
import ar.com.ada.maven.utils.Paginator;
import ar.com.ada.maven.view.AccountView;
import ar.com.ada.maven.view.MainView;
import ar.com.ada.maven.view.TypeAccountView;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import static java.lang.Integer.parseInt;

public class AccountController {

    private static AccountView view = new AccountView();
    private static TypeAccountView typeAccountView = new TypeAccountView();
    private static PersonDAO personDAO = new PersonDAO();
    private static Type_accountDAO type_accountDAO = new Type_accountDAO(false);
    private static AccountDAO accountDAO = new AccountDAO(false);
    private static Type_accountDTO Type_accountDTO = new Type_accountDTO(false);

    static void init() {
        boolean shouldGetOut = false;
        while (!shouldGetOut) {
            int option = view.accountMenuSelectOption();
            switch (option) {
                case 1:
                    listAllAccounts();
                    break;
                case 2:
                    createNewAccount();
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

    private static void listAllAccounts() {
        listAccountPerPage(null, true);
    }

    public static void createNewAccount() {
        List<PersonDTO> person = personDAO.findAll();
        Collection<Type_accountDTO> typeAccount = type_accountDAO.findAll();
        HashMap<String, String> account = view.getNewAccount(person, typeAccount);
        String numberAccountAR = "AR25 0064 0482 25 536398";

        if (!account.isEmpty()) {
            int personId = Integer.parseInt(account.get("person_id"));
            int typeAccountId = Integer.parseInt(account.get("type_account_id"));

            /*String option = typeAccountView.typeAccountSelectOption(typeAccount);
            switch (option) {
                case "1":
                    String numberAccountAR = "AR AR25 0064 0482 25 536398";
                    break;
                case "2":
                    String numberAccountEU = "EU AR25 0064 0482 25 536397";
                    break;
                case "3":
                    String numberAccountDO = "DO AR25 0064 0482 25 536396";
            }*/

            PersonDTO persons = personDAO.findById(personId);
            Type_accountDTO typeAccountDTO = type_accountDAO.findById(typeAccountId);

            AccountDTO newAccount = new AccountDTO(account.get("number_account"), persons, typeAccountDTO);
            AccountDTO byAccount = accountDAO.findByNumberAccount(String.valueOf(personId));

            if (byAccount != null) {
                typeAccountView.accountAlreadyExists(numberAccountAR + newAccount.getNumber_account());
            } else {
                Boolean isSaved = accountDAO.save(newAccount);
                if (isSaved) {
                    typeAccountView.showNewAccount(numberAccountAR + newAccount.getNumber_account() + " " + newAccount.getType_account());
                } else {
                    typeAccountView.newAccountCanceled();
                }
            }

        } else {
            typeAccountView.newAccountCanceled();
        }

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
                        int page = parseInt(choice);
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
                    view.showDeleteAccount(accountToDelete.getNumber_account());
            }
        } else {
            view.deleteAccountCanceled();
        }


    }
}



