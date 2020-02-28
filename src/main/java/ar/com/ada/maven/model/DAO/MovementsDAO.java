package ar.com.ada.maven.model.DAO;

import ar.com.ada.maven.model.DBConnection;
import ar.com.ada.maven.model.DTO.AccountDTO;
import ar.com.ada.maven.model.DTO.MovementsDTO;
import ar.com.ada.maven.model.DTO.Type_movementsDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class MovementsDAO implements DAO<MovementsDTO> {

    private Boolean willCloseConnection = true;
    public AccountDAO accountDAO = new AccountDAO(false);
    public Type_movementsDAO type_movementsDAO = new Type_movementsDAO(false);

    public MovementsDAO(boolean b) {}
    public MovementsDAO(){}

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
                Type_movementsDTO type_movements = type_movementsDAO.findById(rs.getInt("Type_movements_id"));
                MovementsDTO movementsDTO = new MovementsDTO(rs.getInt("id"), rs.getDate("move_date"), rs.getString("description"),
                        account, type_movements, rs.getDouble("amount"));
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
                Type_movementsDTO type_movements = type_movementsDAO.findById(rs.getInt("Type_movements_id"));

                movements = new MovementsDTO(rs.getInt("id"), rs.getDate("move_date"), rs.getString("description"),
                        account, type_movements, rs.getDouble("amount"));
            }
            if (willCloseConnection) connection.close();

        } catch (Exception e) {
            System.out.println("CONNECTION ERROR FINDBYID MOVEMENTS: " + e.getMessage());
        }
        return movements;
    }

    @Override
    public Boolean save(MovementsDTO movementsDTO) {
        String sql = "INSERT INTO Movements (move_date, description, account_id type_movements_id) values ( ?, ?, ?, ?)";
        int affectedRows = 0;
        try {
            Connection connection = DBConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            Date sqlDate = new Date(movementsDTO.getMove_date().getTime());
            preparedStatement.setDate(1, sqlDate);
            preparedStatement.setDouble(2, movementsDTO.getAmount());
            preparedStatement.setString(3, movementsDTO.getDescription());
            preparedStatement.setInt(4, movementsDTO.getAccount().getId());
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
        String sql = "UPDATE Movements SET date_move = ?, description = ?, account_id = ?, type_movements_id = ?";
        int hasUpdate = 0;
        MovementsDTO movementsDB = findById(id);
        try {
            Connection connection = DBConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            Date sqlDate = new Date(movementsDTO.getMove_date().getTime());
            preparedStatement.setDate(1, sqlDate);
            preparedStatement.setDouble(2, movementsDTO.getAmount());
            preparedStatement.setString(3, movementsDTO.getDescription());
            preparedStatement.setInt(4, movementsDTO.getAccount().getId());
            preparedStatement.setInt(5, movementsDTO.getType_movements().getId());

            if (!(movementsDTO.getMove_date().equals(movementsDB.getMove_date()) &&
                    movementsDTO.getAmount().equals(movementsDB.getAmount()) &&
                    movementsDTO.getDescription().equals(movementsDB.getDescription()) &&
                    movementsDTO.getAccount().equals(movementsDB.getAccount()) &&
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

    public int getTotalMovements() {
        String sql = "SELECT COUNT(*) AS total FROM movements";
        int total = 0;
        try {
            Connection connection = DBConnection.getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            if (rs.next())  total = rs.getInt("total");
            connection.close();
        } catch (SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            System.out.println("CONNECTION ERROR GET TOTAL PERSONS: " + e.getMessage());
        }

        return total;
    }

    public List<MovementsDTO> findAll(int limit, int offset) {
        String sql = "SELECT * FROM Person LIMIT ? OFFSET ?";
        List<MovementsDTO> movements = new ArrayList<>();

        try {
            Connection connection = DBConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, limit);
            preparedStatement.setInt(2, offset);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Type_movementsDTO type_mov = type_movementsDAO.findById(rs.getInt("type_movements_id"));
                AccountDTO account = accountDAO.findById(rs.getInt("account_id"));
                MovementsDTO movDTO = new MovementsDTO(rs.getInt("id"), rs.getDate("move_date"), rs.getString("description"),
                        account, type_mov, rs.getDouble("amount"));
                movements.add(movDTO);
            }
            connection.close();
        } catch (SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            System.out.println("CONNECTION ERROR FINDALL II PERSON: " + e.getMessage());
        }

        return movements ;
    }

}