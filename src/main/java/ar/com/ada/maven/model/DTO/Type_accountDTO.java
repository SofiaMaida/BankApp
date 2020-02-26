package ar.com.ada.maven.model.DTO;

public class Type_accountDTO {

    private Integer id;
    private String type_account;

    public Type_accountDTO(Integer id, String type) {
        this.id = id;
        this.type_account = type;
    }

    public Type_accountDTO(String type) {
        this.type_account = type;
    }

    public Type_accountDTO(boolean b) {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType_account() {
        return type_account;
    }

    public void setType_account(String type_account) {
        this.type_account = type_account;
    }


    @Override
    public int hashCode() {
        return -884 * id.hashCode() + type_account.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Type_accountDTO that = (Type_accountDTO) obj;
        return id.equals(that.id) && type_account.equals(that.type_account);
    }

    @Override
    public String toString() {
        return "Tipo de cuenta: [-id: " + id + " - Tipo de cuenta: " + type_account + "]";
    }
}
