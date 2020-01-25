package ar.com.ada.maven.model.DAO;

import ar.com.ada.maven.model.DBConnection;
import ar.com.ada.maven.model.DTO.AccountDTO;
import ar.com.ada.maven.model.DTO.BalanceDTO;

import java.sql.*;
import java.util.ArrayList;

public class BalanceDAO implements DAO<BalanceDTO> {

    private Boolean willCloseConnection = true;
    public AccountDAO accountDAO = new AccountDAO(false);
    public BalanceDAO(boolean b) {}
    public BalanceDAO(){}

    @Override
    public ArrayList<BalanceDTO> findAll() {
        String sql = "SELECT * FROM Balance";
        ArrayList<BalanceDTO> balance = new ArrayList<>();
        try {
            Connection connection = DBConnection.getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                AccountDTO account = accountDAO.findById(rs.getInt("Account_id"));
                BalanceDTO balanceDTO = new BalanceDTO(rs.getInt("id"), rs.getDouble("balance"), account);
                balance.add(balanceDTO);
            }
            if (willCloseConnection)
                connection.close();
        } catch (SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            System.out.println("CONNECTION ERROR FINDALL BALANCE: " + e.getMessage());
        }
        return balance;
    }

    @Override
    public BalanceDTO findById(Integer id) {
        String sql = "SELECT * FROM Balance WHERE id = ?";
        BalanceDTO balance = null;
        try {
            Connection connection = DBConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                AccountDTO account = accountDAO.findById(rs.getInt("Account_id"));
                balance = new BalanceDTO(rs.getInt("id"), rs.getDouble("balance"), account);
            }
            if (willCloseConnection) connection.close();

        } catch (Exception e) {
            System.out.println("CONNECTION ERROR FINDBYID BALANCE: " + e.getMessage());
        }
        return balance;
    }

    @Override
    public Boolean save(BalanceDTO balanceDTO) {
        String sql = "INSERT INTO Balance (balance, account_id) values (?, ?)";
        int affectedRows = 0;
        try {
            Connection connection = DBConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setDouble(1, balanceDTO.getBalance());
            preparedStatement.setInt(2, balanceDTO.getAccount().getId());
            affectedRows = preparedStatement.executeUpdate();
            connection.close();
        } catch (Exception e) {
            System.out.println("CONNECTION ERROR SAVE ACCOUNT: " + e.getMessage());
        }
        return affectedRows == 1;
    }

    @Override
    public Boolean update(BalanceDTO balanceDTO, Integer id) {
        String sql = "UPDATE Balance SET balance = ?, account_id = ?";
        int hasUpdate = 0;
        BalanceDTO balanceDB = findById(id);
        try {
            Connection connection = DBConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setDouble(1, balanceDTO.getBalance());
            preparedStatement.setInt(2, balanceDTO.getAccount().getId());

            if (!(balanceDTO.getBalance().equals(balanceDB.getBalance()) &&
                    balanceDTO.getAccount().equals(balanceDB.getAccount())));
            hasUpdate = preparedStatement.executeUpdate();
            if (willCloseConnection) connection.close();
        } catch (Exception e) {
            System.out.println("CONNECTION ERROR UPDATE BALANCE: " + e.getMessage());
        }
        return hasUpdate == 1;
    }

    @Override
    public Boolean delete(Integer id) {
        String sql = "DELETE FROM Balance WHERE id = ?";
        int hasDelete = 0;
        try {
            Connection connection = DBConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            hasDelete = preparedStatement.executeUpdate();
        } catch (Exception e) {
            System.out.println("CONNECTION ERROR DELETE BALANCE: " + e.getMessage());
        }
        return hasDelete == 1;
    }
}
