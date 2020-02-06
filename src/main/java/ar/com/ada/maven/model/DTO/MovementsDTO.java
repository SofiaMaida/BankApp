package ar.com.ada.maven.model.DTO;

import java.util.Date;
import java.util.Objects;

public class MovementsDTO {

    private int id;
    private Date move_date;
    private String description;
    private BalanceDTO balance;
    private AccountDTO account;
    private Type_movementsDTO type_movements;

    public MovementsDTO() {
    }

    public MovementsDTO(int id, Date move_date, String description, BalanceDTO balance_id, AccountDTO account_id, Type_movementsDTO type_movements_id) {
        this.id = id;
        this.move_date = move_date;
        this.description = description;
        this.balance = balance_id;
        this.account = account_id;
        this.type_movements = type_movements_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public java.sql.Date getMove_date() {
        return (java.sql.Date) move_date;
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

    public BalanceDTO getBalance() {
        return balance;
    }

    public void setBalance(BalanceDTO balance) {
        this.balance = balance;
    }

    public AccountDTO getAccount() {
        return account;
    }

    public void setAccount(AccountDTO account) {
        this.account = account;
    }

    public Type_movementsDTO getType_movements() {
        return type_movements;
    }

    public void setType_movements(Type_movementsDTO type_movements) {
        this.type_movements = type_movements;
    }

    @Override
    public String toString() {
        return "Movements{" +
                "id=" + id +
                ", move_date=" + move_date +
                ", description='" + description + '\'' +
                ", balance_id=" + balance +
                ", account_id=" + account +
                ", type_movements_id=" + type_movements +
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
                Objects.equals(balance, movements.balance) &&
                Objects.equals(account, movements.account) &&
                Objects.equals(type_movements, movements.type_movements);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, move_date, description, balance, account, type_movements);
    }
}
