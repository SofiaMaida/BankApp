package ar.com.ada.maven.model.DTO;

import java.util.Objects;

public class Type_movementsDTO {

    private int id;
    private String type_move;

    public Type_movementsDTO() {
    }

    public Type_movementsDTO(int id, String type_move) {
        this.id = id;
        this.type_move = type_move;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType_move() {
        return type_move;
    }

    public void setType_move(String type_move) {
        this.type_move = type_move;
    }

    @Override
    public String toString() {
        return "Type_movements{" +
                "id=" + id +
                ", type_move='" + type_move + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Type_movementsDTO that = (Type_movementsDTO) o;
        return id == that.id &&
                Objects.equals(type_move, that.type_move);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type_move);
    }
}
