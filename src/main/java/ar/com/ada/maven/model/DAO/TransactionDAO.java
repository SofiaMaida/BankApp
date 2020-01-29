package ar.com.ada.maven.model.DAO;

import ar.com.ada.maven.model.DBConnection;
import ar.com.ada.maven.model.DTO.MovementsDTO;
import ar.com.ada.maven.model.DTO.TransactionDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class TransactionDAO implements DAO<TransactionDTO> {

    private Boolean willCloseConnnection = true;
    public MovementsDAO movementsDAO = new MovementsDAO(false);
    public TransactionDAO(boolean b) {}
    public TransactionDAO(){}

    @Override
    public ArrayList<TransactionDTO> findAll() {
        String sql = "SELECT * FROM Transaction";
        ArrayList<TransactionDTO> transaction = new ArrayList<>();
        try {
            Connection connection = DBConnection.getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                MovementsDTO movements = movementsDAO.findById(rs.getInt("Movements_id"));
                TransactionDTO transactionDTO = new TransactionDTO(rs.getInt("id"), rs.getInt("amount"), movements);
                transaction.add(transactionDTO);
            }
            if (willCloseConnnection)
                connection.close();
        } catch (SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            System.out.println("CONNECTION ERROR FINDALL TRANSACTION: " + e.getMessage());
        }
        return transaction;
    }

    @Override
    public TransactionDTO findById(Integer id) {
        String sql = "SELECT * FROM Transaction WHERE id = ?";
        TransactionDTO transaction = null;
        try {
            Connection connection = DBConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                MovementsDTO movements = movementsDAO.findById(rs.getInt("Movements_id"));
                transaction = new TransactionDTO(rs.getInt("id"), rs.getInt("amount"), movements);
            }
            if (willCloseConnnection) connection.close();

        } catch (Exception e) {
            System.out.println("CONNECTION ERROR FINDBYID MOVEMENTS: " + e.getMessage());
        }
        return transaction;
    }

    @Override
    public Boolean save(TransactionDTO transactionDTO) {
        String sql = "INSERT INTO Transaction (amount, movements_id) values (?, ?)";
        int affectedRows = 0;
        try {
            Connection connection = DBConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, transactionDTO.getAmount());
            preparedStatement.setInt(2, transactionDTO.getMovements().getId());
            affectedRows = preparedStatement.executeUpdate();
            connection.close();
        } catch (Exception e) {
            System.out.println("CONNECTION ERROR SAVE TRANSACTION: " + e.getMessage());
        }
        return affectedRows == 1;
    }

    @Override
    public Boolean update(TransactionDTO transactionDTO, Integer id) {
        String sql = "UPDATE Transaction SET amount = ?, movements_id = ?";
        int hasUpdate = 0;
        TransactionDTO transactionDB = findById(id);
        try {
            Connection connection = DBConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, transactionDTO.getAmount());
            preparedStatement.setInt(2, transactionDTO.getMovements().getId());

            if (!(transactionDTO.getAmount().equals(transactionDB.getAmount()) &&
                    transactionDTO.getMovements().equals(transactionDB.getMovements())));
            hasUpdate = preparedStatement.executeUpdate();
            if (willCloseConnnection) connection.close();
        } catch (Exception e) {
            System.out.println("CONNECTION ERROR UPDATE TRANSACTION: " + e.getMessage());
        }
        return hasUpdate == 1;
    }

    @Override
    public Boolean delete(Integer id) {
        String sql = "DELETE FROM Transaction WHERE id = ?";
        int hasDelete = 0;
        try {
            Connection connection = DBConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            hasDelete = preparedStatement.executeUpdate();
        } catch (Exception e) {
            System.out.println("CONNECTION ERROR DELETE DELETE: " + e.getMessage());
        }
        return hasDelete == 1;
    }
}
