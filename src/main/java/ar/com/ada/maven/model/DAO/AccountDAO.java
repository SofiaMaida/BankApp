package ar.com.ada.maven.model.DAO;

import ar.com.ada.maven.model.DBConnection;
import ar.com.ada.maven.model.DTO.AccountDTO;
import ar.com.ada.maven.model.DTO.PersonDTO;
import ar.com.ada.maven.model.DTO.Type_accountDTO;

import java.sql.*;
import java.util.ArrayList;

public class AccountDAO implements DAO<AccountDTO> {

    private Boolean willCloseConnection = true;
    public PersonDAO personDAO = new PersonDAO(false);
    public Type_accountDAO typeDAO = new Type_accountDAO(false);

    public AccountDAO(boolean b) {
    }

    public AccountDAO() {
    }

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
                Type_accountDTO type_account = typeDAO.findById(rs.getInt("type_account_id"));
                AccountDTO accountDTO = new AccountDTO(rs.getInt("id"), rs.getInt("number_account"), person, type_account);
                account.add(accountDTO);
            }
            if (willCloseConnection)
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
                Type_accountDTO typeDTO = typeDAO.findById(rs.getInt("type_account_id"));
                account = new AccountDTO(rs.getInt("id"), rs.getInt("number_account"), person, typeDTO);
            }
            if (willCloseConnection) connection.close();

        } catch (Exception e) {
            System.out.println("CONNECTION ERROR FINDBYID ACCOUNT: " + e.getMessage());
        }
        return account;
    }

    @Override
    public Boolean save(AccountDTO accountDTO) {
        String sql = "INSERT INTO Account (number_account, person_id, type_account_id) values (?, ?, ?)";
        int affectedRows = 0;
        try {
            Connection connection = DBConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, accountDTO.getNumber_account());
            preparedStatement.setInt(2, accountDTO.getPerson().getId());
            preparedStatement.setInt(3, accountDTO.getType_account().getId());
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
            preparedStatement.setInt(1, Integer.parseInt(accountDTO.getNumber_account()));
            preparedStatement.setInt(2, accountDTO.getPerson().getId());
            preparedStatement.setInt(3, accountDTO.getType_account().getId());

            if (!(accountDTO.getNumber_account().equals(accountDB.getNumber_account()) &&
                    accountDTO.getPerson().equals(accountDB.getPerson()) &&
                    accountDTO.getType_account().equals(accountDB.getType_account())));
            hasUpdate = preparedStatement.executeUpdate();
            if (willCloseConnection) connection.close();
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

    public int getTotalPersons() {
        String sql = "SELECT COUNT(*) AS total FROM Account";
        int total = 0;
        try {
            Connection connection = DBConnection.getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            if (rs.next()) total = rs.getInt("total");
            connection.close();
        } catch (SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            System.out.println("CONNECTION ERROR GET TOTAL ACCOUNT: " + e.getMessage());
        }

        return total;
    }

    public ArrayList<AccountDTO> findAll(int limit, int offset) {
        String sql = "SELECT * FROM account LIMIT ? OFFSET ?";
        ArrayList<AccountDTO> account = new ArrayList<>();
        try {
            Connection connection = DBConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, limit);
            preparedStatement.setInt(2, offset);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                PersonDTO person = personDAO.findById(rs.getInt("Person_id"));
                AccountDTO accountDTO = new AccountDTO(rs.getInt("id"), rs.getString("number_account"), person);
                account.add(accountDTO);
            }
            connection.close();
        } catch (SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            System.out.println("CONNECTION ERROR FINDALL ACCOUNT: " + e.getMessage());
        }
        return account;
    }

    public AccountDTO findByNumberAccount(String numberAccount) {
        String sql = "SELECT * FROM account WHERE number_account = ?";
        AccountDTO accountDTO = null;
        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, String.valueOf(accountDTO));
            ResultSet rs = ps.executeQuery();
            if (rs.next())
                accountDTO = new AccountDTO(rs.getInt("id"), rs.getString("number_account"));
            if (willCloseConnection)
                conn.close();
        } catch (SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            System.out.println("CONNECTION ERROR FINDBY NUMBER ACCOUNT: " + e.getMessage());
        }
        return accountDTO;
    }

}
