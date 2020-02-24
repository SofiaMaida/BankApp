package ar.com.ada.maven.model.DAO;

import ar.com.ada.maven.model.DBConnection;
import ar.com.ada.maven.model.DTO.Type_accountDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Type_accountDAO implements DAO<Type_accountDTO> {

    private Boolean willCloseConnection = true;

    public Type_accountDAO() {
    }

    public Type_accountDAO(Boolean willCloseConnection) {
        this.willCloseConnection = willCloseConnection;
    }

    @Override
    public Collection<Type_accountDTO> findAll() {
        String sql = "SELECT * FROM type_account";
        List<Type_accountDTO> types = new ArrayList<>();
        try {
            Connection conn = DBConnection.getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {

                Type_accountDTO type = new Type_accountDTO(rs.getInt("id"),
                        rs.getString("type_account"));
                types.add(type);
            }

            conn.close();
        } catch (SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            System.out.println("CONNECTION ERROR: " + e.getMessage());
        }
        return types;
    }

    @Override
    public Type_accountDTO findById(Integer id) {
        String sql = "SELECT * FROM type_account WHERE id = ?";
        Type_accountDTO type = null;
        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {

                type = new Type_accountDTO(rs.getInt("id"), rs.getString("type_account"));
            }
            if (willCloseConnection)
                conn.close();
        } catch (SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            System.out.println("CONNECTION ERROR: " + e.getMessage());
        }

        return type;
    }


    @Override
    public Boolean save(Type_accountDTO type_accountDTO) {
        String sql = "INSERT INTO type_account (type) VALUES (Cuenta corriente en pesos ARG, Cuenta corriente en pesos USD, Cuenta corriente en pesos EUR)";
        int hasInsert = 0;
        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, "Cuenta corriente en pesos ARG");
            ps.setString(2, "Cuenta corriente en pesos USD");
            ps.setString(3, "Cuenta corriente en pesos EUR");
            hasInsert = ps.executeUpdate();
            conn.close();
        } catch (SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            System.out.println("CONNECTION ERROR: " + e.getMessage());
        }
        return hasInsert == 1;
    }

    @Override
    public Boolean update(Type_accountDTO type_accountDTO, Integer id) {
        String sql = "UPDATE type_account SET type_account = ? WHERE id = ?";
        int hasUpdate = 0;
        Type_accountDTO typeDTO = findById(id);
        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, type_accountDTO.getType_account());
            if (!type_accountDTO.getType_account().equals(typeDTO.getType_account())) ;
            hasUpdate = ps.executeUpdate();
            conn.close();
        } catch (SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            System.out.println("CONNECTION ERROR: " + e.getMessage());
        }

        return hasUpdate == 1;
    }

    @Override
    public Boolean delete(Integer id) {
        String sql = "DELETE FROM type_account WHERE id = ?";
        int hasErased = 0;
        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            hasErased = ps.executeUpdate();
            conn.close();
        } catch (SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            System.out.println("CONNECTION ERROR: " + e.getMessage());
        }

        return hasErased == 1;
    }

    public Type_accountDTO findByNumberAccount(String type) {
        String sql = "SELECT * FROM type_account WHERE type_account = ?";
        Type_accountDTO numberAccount = null;
        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, type);
            ResultSet rs = ps.executeQuery();
            if (rs.next())
                numberAccount = new Type_accountDTO(rs.getInt("id"), rs.getString("type_account"));
            if (willCloseConnection)
                conn.close();
        } catch (SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            System.out.println("CONNECTION ERROR: " + e.getMessage());
        }
        return numberAccount;
    }
}



