package cz.upce.bank.eb.entity;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

public class Transakce {

    private int transactionId;
    private int accountId;
    private double amount;
    private LocalDateTime transactionTime;
    private String transactionStateInfo;
    private String timePeriodInfo;
    private String errorInfo;

    public static RowMapper<Transakce> getTransakceMapper(){
        return (rs, rowNum) -> {
            Transakce transakce = new Transakce();
            transakce.setTransactionId(rs.getInt("ID"));
            transakce.setAccountId(rs.getInt("UCET_ID"));
            transakce.setAmount(rs.getDouble("CASTKA"));
            transakce.setTransactionTime(rs.getTimestamp("DATUM_TRANSAKCE").toLocalDateTime());
            transakce.setTransactionStateInfo(rs.getString("STAV_POPIS"));
            transakce.setTimePeriodInfo(rs.getString("OBDOBI_POPIS"));
            transakce.setErrorInfo(rs.getString("CHYBA_POPIS"));
            return transakce;
        };
    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDateTime getTransactionTime() {
        return transactionTime;
    }

    public void setTransactionTime(LocalDateTime transactionTime) {
        this.transactionTime = transactionTime;
    }

    public String getTransactionStateInfo() {
        return transactionStateInfo;
    }

    public void setTransactionStateInfo(String transactionStateInfo) {
        this.transactionStateInfo = transactionStateInfo;
    }

    public String getTimePeriodInfo() {
        return timePeriodInfo;
    }

    public void setTimePeriodInfo(String timePeriodInfo) {
        this.timePeriodInfo = timePeriodInfo;
    }

    public String getErrorInfo() {
        return errorInfo;
    }

    public void setErrorInfo(String errorInfo) {
        this.errorInfo = errorInfo;
    }
}
