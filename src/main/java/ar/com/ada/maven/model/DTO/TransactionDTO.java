package ar.com.ada.maven.model.DTO;

import ar.com.ada.maven.model.DTO.MovementsDTO;

import java.util.Objects;

public class TransactionDTO {

    private int id;
    private Double amount;
    private MovementsDTO movements;

    public TransactionDTO(int id, Double amount, MovementsDTO movements) {
        this.id = id;
        this.amount = amount;
        this.movements = movements;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public MovementsDTO getMovements() {
        return movements;
    }

    public void setMovements(MovementsDTO movements) {
        this.movements = movements;
    }

    @Override
    public String toString() {
        return "TransactionDTO{" +
                "id=" + id +
                ", amount=" + amount +
                ", movements=" + movements +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransactionDTO that = (TransactionDTO) o;
        return id == that.id &&
                Objects.equals(amount, that.amount) &&
                Objects.equals(movements, that.movements);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, amount, movements);
    }
}
