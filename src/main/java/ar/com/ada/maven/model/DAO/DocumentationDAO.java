package ar.com.ada.maven.model.DAO;

import ar.com.ada.maven.DBConnection;
import ar.com.ada.maven.model.DTO.DocumentationDTO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
            while
        } catch (SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            System.out.println("CONNECTION ERROR: " + e.getMessage());
        }
    }
}
