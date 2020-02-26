package ar.com.ada.maven.model.DTO;

public class AccountDTO {

    private Integer id;
    private String number_account;
    private PersonDTO person;
    private Type_accountDTO type_account;


    public AccountDTO(Integer id, String number_account, PersonDTO person, Type_accountDTO type_account){
        this.id = id;
        this.number_account = number_account;
        this.person = person;
        this.type_account = type_account;
    }

    public AccountDTO(int id, String number_account) {
        this.id = id;
        this.number_account = number_account;
    }

    public AccountDTO(String numberAccount, PersonDTO persons, Type_accountDTO typeAccountDTO) {
        this.number_account = numberAccount;
        this.person = persons;
        this.type_account = typeAccountDTO;
    }

    public AccountDTO(String numberAccount, PersonDTO persons) {
        this.number_account = numberAccount;
        this.person = persons;

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

    public String getNumber_account() {
        return number_account;
    }

    public void setNumber_account(String number_account) {
        this.number_account = number_account;
    }

    public Type_accountDTO getType_account() {
        return type_account;
    }

    public void setType_account(Type_accountDTO type_account) {
        this.type_account = type_account;
    }

    @Override
    public String toString() {
        return "Cuenta: [-id: " +id+ " -Numero de cuenta: " +number_account+ " -Persona" +person+ " -tipo de cuenta: " + type_account + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        AccountDTO that = (AccountDTO) obj;
        return id.equals(that.id) && number_account.equals(that.number_account) && person.equals(that.person) &&
                type_account.equals(that.type_account);
    }

    @Override
    public int hashCode() {
        return -123 * id.hashCode() + number_account.hashCode() + person.hashCode() + type_account.hashCode();
    }
}
