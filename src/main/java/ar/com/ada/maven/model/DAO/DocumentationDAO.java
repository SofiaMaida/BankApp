package ar.com.ada.maven.model.DAO;

import ar.com.ada.maven.model.DTO.DocumentationDTO;

import java.util.List;

public class DocumentationDAO implements DAO<DocumentationDTO> {

    private Boolean willCloseConnection = true;

    public DocumentationDAO (){}

    public DocumentationDAO(Boolean willCloseConnection) {
        this.willCloseConnection = willCloseConnection;
    }

    public List<DocumentationDAO> findAll(){

    }
}
