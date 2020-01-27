package ar.com.ada.maven.model.DTO;

import java.util.Objects;

public class BalanceDTO {

    private int id;
    private int balance;
    private AccountDTO account;

    public BalanceDTO(int id, int balance, AccountDTO account) {
        this.id = id;
        this.balance = balance;
        this.account = account;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public AccountDTO getAccount() {
        return account;
    }

    public void setAccount(AccountDTO account) {
        this.account = account;
    }

    @Override
    public String toString() {
        return "BalanceDTO{" +
                "id=" + id +
                ", balance=" + balance +
                ", account=" + account +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BalanceDTO that = (BalanceDTO) o;
        return id == that.id &&
                balance == that.balance &&
                Objects.equals(account, that.account);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, balance, account);
    }
}
