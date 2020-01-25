package ar.com.ada.maven.model.DAO;

import ar.com.ada.maven.model.DBConnection;
import ar.com.ada.maven.model.DTO.Type_movementsDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class Type_movementsDAO implements DAO<Type_movementsDTO> {

    private Boolean willCloseConnection = true;

    public Type_movementsDAO(Boolean willCloseConnection) {
        this.willCloseConnection = willCloseConnection;
    }

        @Override
    public Collection<Type_movementsDTO> findAll() {
        String sql = "SELECT * FROM Type_movements";
        ArrayList<Type_movementsDTO> type_movements = new ArrayList<>();
        try {
            Connection connection = DBConnection.getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                Type_movementsDTO type_movementsDTO = new Type_movementsDTO(rs.getInt("id"), rs.getString("type_move"));
                type_movements.add(type_movementsDTO);
            }
            if (willCloseConnection)
                connection.close();
        } catch (SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            System.out.println("CONNECTION ERROR FINDALL TYPE_MOVE: " + e.getMessage());
        }

        return type_movements;
    }

    @Override
    public Type_movementsDTO findById(Integer id) {
        String sql = "SELECT* FROM Type_movements WHERE id = ?";
        Type_movementsDTO type_movements = null;
        try {
            Connection connection = DBConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next())
                type_movements = new Type_movementsDTO(rs.getInt("id"), rs.getString("type_move"));
            if (willCloseConnection)
                connection.close();
        } catch (Exception e) {
            System.out.println("CONNECTION ERROR FINDBYID TYPE_MOVE: " + e.getMessage());
        }
        return type_movements;
    }

    @Override
    public Boolean save(Type_movementsDTO type_movementsDTO) {
        String sql = "INSERT INTO type_movements (type_move) values (?)";
        int hasInsert = 0;
        try {
            Connection connection = DBConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, type_movementsDTO.getType_move());
            hasInsert = preparedStatement.executeUpdate();
            if (willCloseConnection)
                connection.close();
        } catch (Exception e) {
            System.out.println("CONNECTION ERROR SAVE TYPE_MOVE: " + e.getMessage());
        }
        return hasInsert == 1;
    }

    @Override
    public Boolean update(Type_movementsDTO type_movementsDTO, Integer id) {
        String sql = "UPDATE Type_movements SET type_move ? WHERE id = ?";
        int hasUpdate = 0;
        Type_movementsDTO type_movementsDB = findById(id);

        try {
            Connection connection = DBConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, type_movementsDTO.getType_move());
            preparedStatement.setInt(2, id);

            if (!type_movementsDTO.getType_move().equals(type_movementsDB.getType_move()))
                hasUpdate = preparedStatement.executeUpdate();
        } catch (SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            System.out.println("CONNECTION ERROR UPDATE TYPEMOVE: " + e.getMessage());
        }
        return hasUpdate == 1;
    }

    @Override
    public Boolean delete(Integer id) {
        String sql = "DELETE * FROM Type_movements WHERE type_move = ?";
        int hasDelete = 0;
        try {
            Connection connection = DBConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);

            hasDelete = preparedStatement.executeUpdate();
        } catch (Exception e) {
            System.out.println("CONNECTION ERROR DELETE TYPEMOVE: " + e.getMessage());
        }
        return hasDelete == 1;
    }
}
