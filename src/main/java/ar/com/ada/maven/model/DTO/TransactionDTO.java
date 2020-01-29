package ar.com.ada.maven.model.DTO;

import ar.com.ada.maven.model.DTO.MovementsDTO;

public class TransactionDTO {

    private int id;
    private int amount;
    private MovementsDTO movements;

    public TransactionDTO(int id, int amount, MovementsDTO movements) {
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

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public MovementsDTO getMovements() {
        return movements;
    }

    public void setMovements(MovementsDTO movements) {
        this.movements = movements;
    }
}
