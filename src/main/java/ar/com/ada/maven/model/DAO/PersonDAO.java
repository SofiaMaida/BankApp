package ar.com.ada.maven.model.DAO;

import ar.com.ada.maven.DBConnection;
import ar.com.ada.maven.model.DTO.PersonDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonDAO implements DAO<PersonDTO> {

    private Boolean willCloseConnection = true;

    public PersonDAO() {
    }

    public PersonDAO(Boolean willCloseConnection) {
        this.willCloseConnection = willCloseConnection;
    }

    public List<PersonDTO> findAll() {
        String sql = "SELECT * FROM person";
        List<PersonDTO> persons = new ArrayList<>();
        try {
            Connection conn = DBConnection.getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                PersonDTO person = new PersonDTO(rs.getInt("id"),
                        rs.getString("name"), rs.getString("lastName"),
                        rs.getInt("number_doc"));
                persons.add(person);
            }

            conn.close();
        } catch (SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            System.out.println("CONNECTION ERROR: " + e.getMessage());
        }
        return persons;
    }

    @Override
    public PersonDTO findById(Integer id) {
        String sql = "SELECT * FROM person WHERE id = ?";
        PersonDTO person = null;
        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            if (rs.next())
                person = new PersonDTO(rs.getInt("id"),rs.getString("name"),
                        rs.getString("lastName"),rs.getInt("number_doc"));
            if (willCloseConnection)
                conn.close();
        } catch (SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            System.out.println("CONNECTION ERROR: " + e.getMessage());
        }

        return person;
    }

    @Override
    public Boolean save(PersonDTO personDTO) {
        String sql = "INSERT INTO person (name, lastName, number_doc) VALUES (?,?,?)";
        int hasInsert = 0;
        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,personDTO.getName());
            ps.setString(2,personDTO.getLastName());
            ps.setInt(3,personDTO.getNumber_doc());
            hasInsert = ps.executeUpdate();
            conn.close();
        } catch (SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            System.out.println("CONNECTION ERROR: " + e.getMessage());
        }
        return hasInsert == 1;
    }

    @Override
    public Boolean update(PersonDTO personDTO, Integer id) {
        String sql = "UPDATE person SET name = ?, lastName = ?, number_doc = ? WHERE id = ?";
        int hasUpdate = 0;
        PersonDTO personDB = findById(id);
        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,personDTO.getName());
            ps.setString(2,personDTO.getLastName());
            ps.setInt(3,personDTO.getNumber_doc());
            if (!personDTO.getName().equals(personDB.getName()) && personDTO.getLastName().equals(personDB.getLastName())
                    && personDTO.getNumber_doc().equals(personDB.getNumber_doc()));
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
