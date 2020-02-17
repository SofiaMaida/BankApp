package ar.com.ada.maven.model.DTO;

import java.util.Objects;

public class PersonDTO {

    private Integer id;
    private String name, lastName;
    private int number_doc;
    private DocumentationDTO document_type;

    public PersonDTO(String name, String clientName, int number_doc){}

    public PersonDTO(Integer id, String name){
        this.id = id;
        this.name = name;
    }

    public PersonDTO(Integer id, int number_doc) {
        this.id = id;
        this.number_doc = number_doc;

    }

    public PersonDTO(Integer id, String name, String lastName){
        this.id = id;
        this.name = name;
        this.lastName = lastName;
    }

    public PersonDTO(Integer id, String name, String lastName, int number_doc, DocumentationDTO document_type){
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.number_doc = number_doc;
    }

    public PersonDTO(String name, String lastName, int number_doc, DocumentationDTO document_type){
        this.name = name;
        this.lastName = lastName;
        this.number_doc = number_doc;
        this.document_type = document_type;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setNumber_doc(int number_doc) {
        this.number_doc = number_doc;
    }

    public int getNumber_doc() {
        return number_doc;
    }

    public DocumentationDTO getDocument_type() {
        return document_type;
    }

    public void setDocument_type(DocumentationDTO document_type) {
        this.document_type = document_type;
    }

    @Override
    public String toString() {
        return "PERSON: [-id " + id + " - nombre: " + name + " - apellido: " + lastName + " - DNI: " + number_doc + "Tipo de documento: "
        + document_type + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonDTO personDTO = (PersonDTO) o;
        return number_doc == personDTO.number_doc &&
                Objects.equals(id, personDTO.id) &&
                Objects.equals(name, personDTO.name) &&
                Objects.equals(lastName, personDTO.lastName) &&
                Objects.equals(document_type, personDTO.document_type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, lastName, number_doc, document_type);
    }
}

