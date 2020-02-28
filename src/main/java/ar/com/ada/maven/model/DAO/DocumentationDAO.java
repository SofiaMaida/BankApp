package ar.com.ada.maven.model.DAO;

import ar.com.ada.maven.model.DBConnection;
import ar.com.ada.maven.model.DTO.DocumentationDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DocumentationDAO implements DAO<DocumentationDTO> {

    private Boolean willCloseConnection = true;


    public DocumentationDAO (){}

    public DocumentationDAO(Boolean willCloseConnection) {
        this.willCloseConnection = willCloseConnection;
    }

    public List<DocumentationDTO> findAll(){
        String sql = "SELECT * FROM documentation";
        List<DocumentationDTO> docs = new ArrayList<>();
        try {
            Connection conn = DBConnection.getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                DocumentationDTO doc = new DocumentationDTO(rs.getInt("id"),
                        rs.getString("documentation_type"));
                docs.add(doc);
            }

            conn.close();
        } catch (SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            System.out.println("CONNECTION ERROR: " + e.getMessage());
        }
        return docs;
    }

    public DocumentationDTO findById(Integer id) {
        String sql = "SELECT * FROM documentation WHERE id = ?";
        DocumentationDTO docum = null;
        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
            docum = new DocumentationDTO(rs.getInt("id"), rs.getString("documentation_type"));
            }
            if (willCloseConnection)
                conn.close();
        } catch (SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            System.out.println("CONNECTION ERROR: " + e.getMessage());
        }

        return docum;
    }

    @Override
    public Boolean save(DocumentationDTO documentationDTO) {
        String sql = "INSERT INTO documentation (documentation_type) VALUES (?)";
        int hasInsert = 0;
        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, documentationDTO.getDocumentation_type());
            hasInsert = ps.executeUpdate();
            conn.close();
        } catch (SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            System.out.println("CONNECTION ERROR: " + e.getMessage());
        }
        return hasInsert == 1;
    }

    @Override
    public Boolean update(DocumentationDTO documentationDTO, Integer id) {
        String sql = "UPDATE person SET documentation_type = ? WHERE id = ?";
        int hasUpdate = 0;
        DocumentationDTO docDB = findById(id);
        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,documentationDTO.getDocumentation_type());
            if (!documentationDTO.getDocumentation_type().equals(docDB.getDocumentation_type()));
            hasUpdate = ps.executeUpdate();
            conn.close();
        } catch (SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            System.out.println("CONNECTION ERROR: " + e.getMessage());
        }

        return hasUpdate ==1;
    }

    @Override
    public Boolean delete(Integer id) {
        String sql = "DELETE FROM documentation WHERE id = ?";
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
