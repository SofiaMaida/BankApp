package ar.com.ada.maven.model.DAO;

import ar.com.ada.maven.DBConnection;
import ar.com.ada.maven.model.DTO.AccountDTO;
import ar.com.ada.maven.model.DTO.DocumentationDTO;
import ar.com.ada.maven.model.DTO.PersonDTO;
import ar.com.ada.maven.model.DTO.Type_accountDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Type_accountDAO implements DAO<Type_accountDTO> {

    private Boolean willCloseConnection = true;
    private AccountDAO accoDAO = new AccountDAO(false);

    public Type_accountDAO(){}

    public Type_accountDAO(Boolean willCloseConnection){
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
            while(rs.next()){
                AccountDTO account = accoDAO.findById(rs.getInt("account_id"));
                Type_accountDTO type = new Type_accountDTO(rs.getInt("id"),
                        rs.getString("type"), account);
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
            if (rs.next()){
                AccountDTO account = accoDAO.findById(rs.getInt("account_id"));
                type = new Type_accountDTO(rs.getInt("id"), rs.getString("type"),
                        account);}
            if (willCloseConnection)
                conn.close();
        } catch (SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            System.out.println("CONNECTION ERROR: " + e.getMessage());
        }

        return type;
    }


    @Override
    public Boolean save(Type_accountDTO type_accountDTO) {
        String sql = "INSERT INTO type_account (type) VALUES (?)";
        int hasInsert = 0;
        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, type_accountDTO.getType());
            hasInsert = ps.executeUpdate();
            conn.close();
        } catch (SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            System.out.println("CONNECTION ERROR: " + e.getMessage());
        }
        return hasInsert == 1;
    }

    @Override
    public Boolean update(Type_accountDTO type_accountDTO, Integer id) {
        String sql = "UPDATE type_account SET type = ? WHERE id = ?";
        int hasUpdate = 0;
        Type_accountDTO typeDTO = findById(id);
        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,type_accountDTO.getType());
            ps.setInt(2, type_accountDTO.getAccount().getId());
            if (!type_accountDTO.getType().equals(typeDTO.getType())
                    && type_accountDTO.getAccount().equals(typeDTO.getAccount()));
            hasUpdate = ps.executeUpdate();
            conn.close();
        } catch (SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            System.out.println("CONNECTION ERROR: " + e.getMessage());
        }

        return hasUpdate ==1;
    }

    @Override
    public Boolean delete(Integer id) {
        String sql = "DELETE FROM type_account WHERE id = ?";
        int hasErased = 0;
        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,id);
            hasErased = ps.executeUpdate();
            conn.close();
        } catch (SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            System.out.println("CONNECTION ERROR: " + e.getMessage());
        }

        return hasErased == 1;
    }
}

