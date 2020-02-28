package ar.com.ada.maven.model.DAO;

import ar.com.ada.maven.model.DBConnection;
import ar.com.ada.maven.model.DTO.DocumentationDTO;
import ar.com.ada.maven.model.DTO.PersonDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonDAO implements DAO<PersonDTO> {

    private Boolean willCloseConnection = true;
    private DocumentationDAO docDAO = new DocumentationDAO(false);
    private DocumentationDTO docDTO = new DocumentationDTO(false);

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
                DocumentationDTO type_doc = docDAO.findById(rs.getInt("documentation_id"));
                PersonDTO person = new PersonDTO(rs.getInt("id"),
                        rs.getString("name"), rs.getString("last_name"),
                        rs.getInt("number_doc"), type_doc);
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
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                DocumentationDTO type = docDAO.findById(rs.getInt("documentation_id"));
                person = new PersonDTO(rs.getInt("id"), rs.getString("name"),
                        rs.getString("last_name"), rs.getInt("number_doc"), type);
            }
            if (willCloseConnection)
                conn.close();
        } catch (SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            System.out.println("CONNECTION ERROR FINDBYID: " + e.getMessage());
        }

        return person;
    }

    @Override
    public Boolean save(PersonDTO personDTO) {
        String sql = "INSERT INTO person (name, last_name, number_doc, documentation_id) VALUES (?,?,?,?)";
        int hasInsert = 0;
        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, personDTO.getName());
            ps.setString(2, personDTO.getLastName());
            ps.setInt(3, personDTO.getNumber_doc());
            ps.setInt(4, personDTO.getDocument_type().getId());
            hasInsert = ps.executeUpdate();
            conn.close();
        } catch (SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            System.out.println("CONNECTION ERROR SAVE: " + e.getMessage());
        }
        return hasInsert == 1;
    }

    @Override
    public Boolean update(PersonDTO personDTO, Integer id) {
        String sql = "UPDATE person SET name = ?, last_name = ?, number_doc = ? WHERE id = ?";
        int hasUpdate = 0;
        PersonDTO personDB = findById(id);
        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, personDTO.getName());
            ps.setString(2, personDTO.getLastName());
            ps.setInt(3, personDTO.getNumber_doc());
            //ps.setString(4, String.valueOf(personDTO.getDocument_type()));
            ps.setInt(4, personDTO.getId());

            if (!personDTO.equals(personDB))
                hasUpdate = ps.executeUpdate();
            conn.close();
        } catch (SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            System.out.println("CONNECTION ERROR UPDATE: " + e.getMessage());
        }
        return hasUpdate == 1;
    }

    public Boolean delete(Integer id) {
        String sql = "DELETE FROM Person WHERE id = ?";
        int hasErased = 0;
        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            hasErased = ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("CONNECTION ERROR DELETE PERSON: " + e.getMessage());
        }

        return hasErased == 1;
    }

    public List<PersonDTO> findAll(int limit, int offset) {
        String sql = "SELECT * FROM Person LIMIT ? OFFSET ?";
        List<PersonDTO> person = new ArrayList<>();

        try {
            Connection connection = DBConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, limit);
            preparedStatement.setInt(2, offset);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                DocumentationDTO type_doc = docDAO.findById(rs.getInt("documentation_id"));
                PersonDTO personDTO = new PersonDTO(rs.getInt("id"), rs.getString("name"), rs.getString("last_name"),
                        rs.getInt("number_doc"), type_doc);
                person.add(personDTO);
            }
            connection.close();
        } catch (SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            System.out.println("CONNECTION ERROR FINDALL II PERSON: " + e.getMessage());
        }

        return person;
    }

    public int getTotalPersons() {
        String sql = "SELECT COUNT(*) AS total FROM Person";
        int total = 0;
        try {
            Connection connection = DBConnection.getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            if (rs.next()) total = rs.getInt("total");
            connection.close();
        } catch (SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            System.out.println("CONNECTION ERROR GET TOTAL PERSONS: " + e.getMessage());
        }

        return total;
    }

    public PersonDTO findByDni(Integer number_doc) {
        String sql = "SELECT * FROM person WHERE number_doc = ?";
        PersonDTO numberDni = null;
        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, number_doc);
            ResultSet rs = ps.executeQuery();
            if (rs.next())
                numberDni = new PersonDTO(rs.getInt("id"), rs.getInt("number_doc"));
            if (willCloseConnection)
                conn.close();

        } catch (SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            System.out.println("CONNECTION ERROR findbyDNI: " + e.getMessage());
        }
        return numberDni;
    }

    public PersonDTO findByName(String name) {
        String sql = "SELECT * FROM person WHERE name = ?";
        PersonDTO person = null;
        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            if (rs.next())
                person = new PersonDTO(rs.getInt("id"), rs.getString("name"), rs.getString("last_name"));
            if (willCloseConnection)
                conn.close();

        } catch (SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            System.out.println("CONNECTION ERROR findbyNAME: " + e.getMessage());
        }
        return person;
    }

}
