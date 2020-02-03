package ar.com.ada.maven.model.DTO;

public class Type_accountDTO {

    private Integer id;
    private String type;
    private AccountDTO account;

    public Type_accountDTO(String accountNumber){}

    public Type_accountDTO(Integer id, String type){
        this.id = id;
        this.type=type;
    }

    public Type_accountDTO(Integer id, String type, AccountDTO account){
        this.id = id;
        this.type=type;
        this.account = account;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setAccount(AccountDTO account) {
        this.account = account;
    }

    public AccountDTO getAccount() {
        return account;
    }

    @Override
    public int hashCode() {
        return -884 * id.hashCode() + type.hashCode() + account.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Type_accountDTO that = (Type_accountDTO) obj;
        return id.equals(that.id) && type.equals(that.type) && account.equals(that.account);
    }

    @Override
    public String toString() {
        return "Tipo de cuenta: [-id: " +id + " - Tipo de cuenta: " + type + " -cuenta: " + account + "]";
    }
}
