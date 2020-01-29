package ar.com.ada.maven.model.DAO;

import ar.com.ada.maven.model.DBConnection;
import ar.com.ada.maven.model.DTO.AccountDTO;
import ar.com.ada.maven.model.DTO.PersonDTO;

import java.sql.*;
import java.util.ArrayList;

public class AccountDAO implements DAO<AccountDTO> {

    private Boolean willCloseConnnection = true;
    public PersonDAO personDAO = new PersonDAO(false);
    public AccountDAO(boolean b) {}
    public AccountDAO(){}

    @Override
    public ArrayList<AccountDTO> findAll() {
        String sql = "SELECT * FROM Account";
        ArrayList<AccountDTO> account = new ArrayList<>();
        try {
            Connection connection = DBConnection.getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                PersonDTO person = personDAO.findById(rs.getInt("Person_id"));
                AccountDTO accountDTO = new AccountDTO(rs.getInt("id"), rs.getInt("number_account"), person);
                account.add(accountDTO);
            }
            if (willCloseConnnection)
                connection.close();
        } catch (SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            System.out.println("CONNECTION ERROR FINDALL ACCOUNT: " + e.getMessage());
        }
        return account;
    }

    @Override
    public AccountDTO findById(Integer id) {
        String sql = "SELECT * FROM Account WHERE id = ?";
        AccountDTO account = null;
        try {
            Connection connection = DBConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                PersonDTO person = personDAO.findById(rs.getInt("Person_id"));
                account = new AccountDTO(rs.getInt("id"), rs.getInt("number_account"), person);
            }
            if (willCloseConnnection) connection.close();

        } catch (Exception e) {
            System.out.println("CONNECTION ERROR FINDBYID ACCOUNT: " + e.getMessage());
        }
        return account;
    }

    @Override
    public Boolean save(AccountDTO accountDTO) {
        String sql = "INSERT INTO Account (number_account, person_id) values (?, ?)";
        int affectedRows = 0;
        try {
            Connection connection = DBConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, accountDTO.getNumber_account());
            preparedStatement.setInt(2, accountDTO.getPerson().getId());
            affectedRows = preparedStatement.executeUpdate();
            connection.close();
        } catch (Exception e) {
            System.out.println("CONNECTION ERROR SAVE ACCOUNT: " + e.getMessage());
        }
        return affectedRows == 1;
    }

    @Override
    public Boolean update(AccountDTO accountDTO, Integer id) {
        String sql = "UPDATE Account SET number_account = ?, person_id = ?";
        int hasUpdate = 0;
        AccountDTO accountDB = findById(id);
        try {
            Connection connection = DBConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, accountDTO.getNumber_account());
            preparedStatement.setInt(2, accountDTO.getPerson().getId());

            if (!(accountDTO.getNumber_account().equals(accountDB.getNumber_account()) &&
                    accountDTO.getPerson().equals(accountDB.getPerson())));
            hasUpdate = preparedStatement.executeUpdate();
            if (willCloseConnnection) connection.close();
        } catch (Exception e) {
            System.out.println("CONNECTION ERROR UPDATE ACCOUNT: " + e.getMessage());
        }
        return hasUpdate == 1;
    }

    @Override
    public Boolean delete(Integer id) {
        String sql = "DELETE FROM Account WHERE id = ?";
        int hasDelete = 0;
        try {
            Connection connection = DBConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            hasDelete = preparedStatement.executeUpdate();
        } catch (Exception e) {
            System.out.println("CONNECTION ERROR DELETE ACCOUNT: " + e.getMessage());
        }
        return hasDelete == 1;
    }
}
