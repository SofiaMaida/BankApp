package ar.com.ada.maven.model.DAO;

import ar.com.ada.maven.DBConnection;
import ar.com.ada.maven.model.DTO.ContactDTO;
import ar.com.ada.maven.model.DTO.DocumentationDTO;
import ar.com.ada.maven.model.DTO.PersonDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContactDAO implements DAO<ContactDTO> {

    private Boolean willCloseConnection = true;
    private PersonDAO perDAO = new PersonDAO(false);

    public ContactDAO(){}

    public ContactDAO(Boolean willCloseConnection){
        this.willCloseConnection = willCloseConnection;
    }

    public List<ContactDTO> findAll(){
        String sql = "SELECT * FROM contact";
        List<ContactDTO> contacts = new ArrayList<>();
        try {
            Connection conn = DBConnection.getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                PersonDTO person = perDAO.findById(rs.getInt("contact_id"));
                ContactDTO contact = new ContactDTO(rs.getInt("id"),
                        rs.getInt("number_type"), person);
                contacts.add(contact);
            }

            conn.close();
        } catch (SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            System.out.println("CONNECTION ERROR: " + e.getMessage());
        }
        return contacts;
    }

    @Override
    public ContactDTO findById(Integer id) {
        String sql = "SELECT * FROM person WHERE id = ?";
        ContactDTO  contDTO = null;
        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                PersonDTO person = perDAO.findById(rs.getInt("person_id"));
                contDTO = new ContactDTO(rs.getInt("id"), person);
            }
            if (willCloseConnection)
                conn.close();
        } catch (SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            System.out.println("CONNECTION ERROR: " + e.getMessage());
        }

        return contDTO;
    }

    @Override
    public Boolean save(ContactDTO contactDTO) {
        String sql = "INSERT INTO person (number_phone) VALUES (?)";
        int hasInsert = 0;
        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, contactDTO.getNumber_phone());
            hasInsert = ps.executeUpdate();
            conn.close();
        } catch (SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            System.out.println("CONNECTION ERROR: " + e.getMessage());
        }
        return hasInsert == 1;
    }

    @Override
    public Boolean update(ContactDTO contactDTO, Integer id) {
        String sql = "UPDATE person SET number_phone = ? WHERE id = ?";
        int hasUpdate = 0;
        ContactDTO contDB = findById(id);
        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,contactDTO.getNumber_phone());
            if (!contactDTO.getNumber_phone().equals(contDB.getNumber_phone()));
            hasUpdate = ps.executeUpdate();
            conn.close();
        } catch (SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            System.out.println("CONNECTION ERROR: " + e.getMessage());
        }

        return hasUpdate ==1;
    }

    @Override
    public Boolean delete(Integer id) {
        String sql = "DELETE FROM person WHERE id = ?";
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
