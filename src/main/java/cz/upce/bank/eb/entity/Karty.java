package cz.upce.bank.eb.entity;

import org.springframework.jdbc.core.RowMapper;

import java.time.LocalDate;

public class Karty {

    private int cardId;
    private String cardNumber;
    private int accountId;
    private LocalDate issueDate;
    private LocalDate expirationDate;
    private String ownerName;
    private String ownerSurname;
    private String state;

    public static RowMapper<Karty> getKartyMapper(){
        return (rs, rowNum) -> {
            Karty karty = new Karty();
            karty.setCardId(rs.getInt("ID"));
            karty.setCardNumber(String.valueOf(rs.getLong("CISLO_KARTY")));
            karty.setIssueDate(rs.getDate("DATUM_VYDANI").toLocalDate());
            karty.setExpirationDate(rs.getDate("DATUM_PLATNOSTI").toLocalDate());
            karty.setOwnerName(rs.getString("JMENO"));
            karty.setOwnerSurname(rs.getString("PRIJMENI"));
            karty.setState(rs.getString("POPIS"));
            karty.setAccountId(rs.getInt("UCET_ID"));
            return karty;
        };
    }


    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }


    public int getCardId() {
        return cardId;
    }

    public void setCardId(int cardId) {
        this.cardId = cardId;
    }


    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getOwnerSurname() {
        return ownerSurname;
    }

    public void setOwnerSurname(String ownerSurname) {
        this.ownerSurname = ownerSurname;
    }
}
