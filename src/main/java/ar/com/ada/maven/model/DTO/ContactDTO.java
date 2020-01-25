package ar.com.ada.maven.model.DTO;

public class ContactDTO {

    private Integer id;
    private Integer number_phone;
    private PersonDTO person;

    public ContactDTO() {
    }

    public ContactDTO(Integer id, Integer number_phone) {
        this.id = id;
        this.number_phone = number_phone;
    }

    public ContactDTO(Integer id, Integer number_phone, PersonDTO person) {
        this.id = id;
        this.number_phone = number_phone;
        this.person = person;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setPerson(PersonDTO person) {
        this.person = person;
    }

    public PersonDTO getPerson() {
        return person;
    }

    public Integer getNumber_phone() {
        return number_phone;
    }

    public void setNumber_phone(Integer number_phone) {
        this.number_phone = number_phone;
    }

    @Override
    public int hashCode() {
        return -75 * id.hashCode() + number_phone.hashCode() + person.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass())return false;
        ContactDTO that = (ContactDTO) obj;
        return id.equals(that.id) && number_phone.equals(that.number_phone) && person.equals(that.person);
    }

    @Override
    public String toString() {
        return "Contacto: [-id: " +id+ " -numero: " +number_phone+ " -persona: " +person+ "]";
    }
}
