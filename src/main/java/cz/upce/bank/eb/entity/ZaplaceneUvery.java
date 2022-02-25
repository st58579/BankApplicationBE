package cz.upce.bank.eb.entity;

import org.springframework.jdbc.core.RowMapper;

import java.time.LocalDateTime;

public class ZaplaceneUvery {

    private int creditId;
    private int accountId;
    private int accountTypeId;
    private LocalDateTime paymentDate;
    private LocalDateTime issueDate;

    public static RowMapper<ZaplaceneUvery> getZaplaceneUveryMapper(){
        return (rs, rowNum) -> {
            ZaplaceneUvery uvery = new ZaplaceneUvery();
            uvery.setCreditId(rs.getInt("ID"));
            uvery.setAccountId(rs.getInt("UCTY_ID"));
            uvery.setAccountTypeId(rs.getInt("TYP_UVERU_ID"));
            uvery.setPaymentDate(rs.getTimestamp("DATUM_ZAPLACENI").toLocalDateTime());
            uvery.setIssueDate(rs.getTimestamp("DATUM_POSKYTNUTI").toLocalDateTime());
            return uvery;
        };
    }

    public int getCreditId() {
        return creditId;
    }

    public void setCreditId(int creditId) {
        this.creditId = creditId;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public int getAccountTypeId() {
        return accountTypeId;
    }

    public void setAccountTypeId(int accountTypeId) {
        this.accountTypeId = accountTypeId;
    }

    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDateTime paymentDate) {
        this.paymentDate = paymentDate;
    }

    public LocalDateTime getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(LocalDateTime issueDate) {
        this.issueDate = issueDate;
    }



}
