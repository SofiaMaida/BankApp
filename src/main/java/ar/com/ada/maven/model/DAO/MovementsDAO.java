package ar.com.ada.maven.model.DAO;

import ar.com.ada.maven.model.DBConnection;
import ar.com.ada.maven.model.DTO.AccountDTO;
import ar.com.ada.maven.model.DTO.BalanceDTO;
import ar.com.ada.maven.model.DTO.MovementsDTO;
import ar.com.ada.maven.model.DTO.Type_movementsDTO;

import java.sql.*;
import java.util.ArrayList;


public class MovementsDAO implements DAO<MovementsDTO> {

    private Boolean willCloseConnection = true;
    public AccountDAO accountDAO = new AccountDAO(false);
    public BalanceDAO balanceDAO = new BalanceDAO(false);
    public Type_movementsDAO type_movementsDAO = new Type_movementsDAO(false);

    public MovementsDAO(boolean b) {}

    @Override
    public ArrayList<MovementsDTO> findAll() {
        String sql = "SELECT * FROM Movements";
        ArrayList<MovementsDTO> movements = new ArrayList<>();
        try {
            Connection connection = DBConnection.getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                AccountDTO account = accountDAO.findById(rs.getInt("Account_id"));
                BalanceDTO balance = balanceDAO.findById(rs.getInt("Balance_id"));
                Type_movementsDTO type_movements = type_movementsDAO.findById(rs.getInt("Type_movements_id"));
                MovementsDTO movementsDTO = new MovementsDTO(rs.getInt("id"), rs.getDate("move_date"), rs.getString("description"),
                        balance, account, type_movements);
                movements.add(movementsDTO);
            }
            if (willCloseConnection)
                connection.close();
        } catch (SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            System.out.println("CONNECTION ERROR FINDALL MOVEMENTS: " + e.getMessage());
        }
        return movements;
    }

    @Override
    public MovementsDTO findById(Integer id) {
        String sql = "SELECT * FROM Movements WHERE id = ?";
        MovementsDTO movements = null;
        try {
            Connection connection = DBConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                AccountDTO account = accountDAO.findById(rs.getInt("Account_id"));
                BalanceDTO balance = balanceDAO.findById(rs.getInt("Balance_id"));
                Type_movementsDTO type_movements = type_movementsDAO.findById(rs.getInt("Type_movements_id"));

                movements = new MovementsDTO(rs.getInt("id"), rs.getDate("move_date"),
                        rs.getString("description"), balance, account, type_movements);
            }
            if (willCloseConnection) connection.close();

        } catch (Exception e) {
            System.out.println("CONNECTION ERROR FINDBYID MOVEMENTS: " + e.getMessage());
        }
        return movements;
    }

    @Override
    public Boolean save(MovementsDTO movementsDTO) {
        String sql = "INSERT INTO Movements (move_date, description, account_id, balance_id, type_movements_id) values (?, ?, ?, ?, ?)";
        int affectedRows = 0;
        try {
            Connection connection = DBConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setDate(1, movementsDTO.getMove_date());
            preparedStatement.setString(2, movementsDTO.getDescription());
            preparedStatement.setInt(3, movementsDTO.getAccount().getId());
            preparedStatement.setInt(4, movementsDTO.getBalance().getId());
            preparedStatement.setInt(5, movementsDTO.getType_movements().getId());
            affectedRows = preparedStatement.executeUpdate();
            connection.close();
        } catch (Exception e) {
            System.out.println("CONNECTION ERROR SAVE MOVEMENTS: " + e.getMessage());
        }
        return affectedRows == 1;
    }

    @Override
    public Boolean update(MovementsDTO movementsDTO, Integer id) {
        String sql = "UPDATE Movements SET date_move = ?, description = ?, account_id = ?, balance_id = ?, type_movements_id = ?";
        int hasUpdate = 0;
        MovementsDTO movementsDB = findById(id);
        try {
            Connection connection = DBConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setDate(1, movementsDTO.getMove_date());
            preparedStatement.setString(2, movementsDTO.getDescription());
            preparedStatement.setInt(3, movementsDTO.getAccount().getId());
            preparedStatement.setInt(4, movementsDTO.getBalance().getId());
            preparedStatement.setInt(5, movementsDTO.getType_movements().getId());

            if (!(movementsDTO.getMove_date().equals(movementsDB.getMove_date()) &&
                    movementsDTO.getDescription().equals(movementsDB.getDescription()) &&
                    movementsDTO.getAccount().equals(movementsDB.getAccount()) &&
                    movementsDTO.getBalance().equals(movementsDB.getBalance()) &&
                    movementsDTO.getType_movements().equals(movementsDB.getType_movements())));
            hasUpdate = preparedStatement.executeUpdate();
            if (willCloseConnection) connection.close();
        } catch (Exception e) {
            System.out.println("CONNECTION ERROR UPDATE MOVEMENTS: " + e.getMessage());
        }
        return hasUpdate == 1;
    }

    @Override
    public Boolean delete(Integer id) {
        String sql = "DELETE FROM Movements WHERE id = ?";
        int hasDelete = 0;
        try {
            Connection connection = DBConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            hasDelete = preparedStatement.executeUpdate();
        } catch (Exception e) {
            System.out.println("CONNECTION ERROR DELETE MOVEMENTS: " + e.getMessage());
        }
        return hasDelete == 1;
    }
}
