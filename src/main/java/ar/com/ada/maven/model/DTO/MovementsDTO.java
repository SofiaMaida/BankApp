package ar.com.ada.maven.model.DTO;

import java.util.Date;
import java.util.Objects;

public class MovementsDTO {

    private int id;
    private Date move_date;
    private String description;
    private BalanceDTO balance_id;
    private AccountDTO account_id;
    private Type_movementsDTO type_movements_id;

    public MovementsDTO() {
    }

    public MovementsDTO(int id, Date move_date, String description, BalanceDTO balance_id, AccountDTO account_id, Type_movementsDTO type_movements_id) {
        this.id = id;
        this.move_date = move_date;
        this.description = description;
        this.balance_id = balance_id;
        this.account_id = account_id;
        this.type_movements_id = type_movements_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getMove_date() {
        return move_date;
    }

    public void setMove_date(Date move_date) {
        this.move_date = move_date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BalanceDTO getBalance_id() {
        return balance_id;
    }

    public void setBalance_id(BalanceDTO balance_id) {
        this.balance_id = balance_id;
    }

    public AccountDTO getAccount_id() {
        return account_id;
    }

    public void setAccount_id(AccountDTO account_id) {
        this.account_id = account_id;
    }

    public Type_movementsDTO getType_movements_id() {
        return type_movements_id;
    }

    public void setType_movements_id(Type_movementsDTO type_movements_id) {
        this.type_movements_id = type_movements_id;
    }

    @Override
    public String toString() {
        return "Movements{" +
                "id=" + id +
                ", move_date=" + move_date +
                ", description='" + description + '\'' +
                ", balance_id=" + balance_id +
                ", account_id=" + account_id +
                ", type_movements_id=" + type_movements_id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MovementsDTO movements = (MovementsDTO) o;
        return id == movements.id &&
                Objects.equals(move_date, movements.move_date) &&
                Objects.equals(description, movements.description) &&
                Objects.equals(balance_id, movements.balance_id) &&
                Objects.equals(account_id, movements.account_id) &&
                Objects.equals(type_movements_id, movements.type_movements_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, move_date, description, balance_id, account_id, type_movements_id);
    }
}
