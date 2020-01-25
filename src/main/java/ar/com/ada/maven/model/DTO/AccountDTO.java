package ar.com.ada.maven.model.DTO;

public class AccountDTO {

    private Integer id, number_account;
    private PersonDTO person;

    public AccountDTO(){
    }

    public AccountDTO(Integer id, Integer number_account){
        this.id = id;
        this.number_account = number_account;
    }

    public AccountDTO(Integer id, Integer number_account, PersonDTO person){
        this.id = id;
        this.number_account = number_account;
        this.person = person;
    }

    public PersonDTO getPerson() {
        return person;
    }

    public void setPerson(PersonDTO person) {
        this.person = person;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public Integer getNumber_account() {
        return number_account;
    }

    public void setNumber_account(Integer number_account) {
        this.number_account = number_account;
    }

    @Override
    public String toString() {
        return "Cuenta: [-id: " +id+ " -Numero de cuenta: " +number_account+ " -Persona" +person+ "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        AccountDTO that = (AccountDTO) obj;
        return id.equals(that.id) && number_account.equals(that.number_account) && person.equals(that.person);
    }

    @Override
    public int hashCode() {
        return -123 * id.hashCode() + number_account.hashCode() + person.hashCode();
    }
}
