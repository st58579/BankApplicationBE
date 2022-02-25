package cz.upce.bank.eb.entity;

import org.springframework.jdbc.core.RowMapper;

public class Ucty {

    private int accountId;
    private int clientId;
    private String accountNumber;
    private String state;
    private String accountType;
    private double remainder;
    private Double limit;
    private Double rate;
    private String timePeriod;
    private String ownerName;
    private String ownerSurname;

    public static RowMapper<Ucty> getUctyMapper() {
        return (rs, rowNum) -> {
            Ucty ucty = new Ucty();
            ucty.setAccountId(rs.getInt("UCET_ID"));
            ucty.setClientId(rs.getInt("KLIENT_ID"));
            ucty.setAccountNumber(rs.getString("CISLO_UCTU"));
            ucty.setState(rs.getString("STAV_POPIS"));
            ucty.setRemainder(rs.getDouble("ZUSTATEK"));
            ucty.setAccountType(rs.getString("TYP_UCTU"));
            ucty.setLimit(rs.getDouble("HRANICE_CERPANI"));
            ucty.setRate(rs.getDouble("UROKOVA_SAZBA"));
            ucty.setTimePeriod(rs.getString("OBDOBI_POPIS"));
            ucty.setOwnerName(rs.getString("JMENO"));
            ucty.setOwnerSurname(rs.getString("PRIJMENI"));
            return ucty;
        };
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public double getRemainder() {
        return remainder;
    }

    public void setRemainder(double remainder) {
        this.remainder = remainder;
    }

    public Double getLimit() {
        return limit;
    }

    public void setLimit(Double limit) {
        this.limit = limit;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public String getTimePeriod() {
        return timePeriod;
    }

    public void setTimePeriod(String timePeriod) {
        this.timePeriod = timePeriod;
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