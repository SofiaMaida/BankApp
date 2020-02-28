package ar.com.ada.maven.model.DTO;

import java.util.Date;
import java.util.Objects;

public class MovementsDTO {

    private int id;
    private Date move_date;
    private String description;
    private AccountDTO account;
    private Type_movementsDTO type_movements;
    private Double amount;

    public MovementsDTO(int id, Date move_date, String description, AccountDTO account, Type_movementsDTO type_movements, Double amount) {
        this.id = id;
        this.move_date = move_date;
        this.description = description;
        this.account = account;
        this.type_movements = type_movements;
        this.amount = amount;
    }

    public MovementsDTO(Date now, String description, AccountDTO accountDTO, Type_movementsDTO type, Double amount) {
        this.move_date = now;
        this.description = description;
        this.account = accountDTO;
        this.type_movements = type;
        this.amount = amount;
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

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "MovementsDTO{" +
                "id=" + id +
                ", move_date=" + move_date +
                ", description='" + description + '\'' +
                ", account=" + account +
                ", type_movements=" + type_movements +
                ", amount=" + amount +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MovementsDTO that = (MovementsDTO) o;
        return id == that.id &&
                Objects.equals(move_date, that.move_date) &&
                Objects.equals(description, that.description) &&
                Objects.equals(account, that.account) &&
                Objects.equals(type_movements, that.type_movements) &&
                Objects.equals(amount, that.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, move_date, description, account, type_movements, amount);
    }
}
