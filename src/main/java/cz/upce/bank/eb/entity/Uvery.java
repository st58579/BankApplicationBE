package cz.upce.bank.eb.entity;

import org.springframework.jdbc.core.RowMapper;

import java.time.LocalDateTime;

public class Uvery {

    private int creditId;
    private int accountId;
    private LocalDateTime issueDate;
    private double remainder;
    private String typeInfo;
    private String timePeriodInfo;
    private int numOfTimePeriods;
    private double percentForTimePeriod;

    public static RowMapper<Uvery> getUveryMapper(){
        return (rs, rowNum) -> {
            Uvery uvery = new Uvery();
            uvery.setCreditId(rs.getInt("ID"));
            uvery.setAccountId(rs.getInt("UCTY_ID"));
            uvery.setRemainder(rs.getDouble("ZBYVAJICI_CASTKA"));
            uvery.setIssueDate(rs.getTimestamp("DATUM_POSKYTNUTI").toLocalDateTime());
            uvery.setTypeInfo(rs.getString("NAZEV"));
            uvery.setTimePeriodInfo(rs.getString("OBDOBI_POPIS"));
            uvery.setNumOfTimePeriods(rs.getInt("POCET_OBDOBI"));
            uvery.setPercentForTimePeriod(rs.getDouble("PROCENTO_ZA_CASOVE_OBDOBI"));
            return uvery;
        };
    }


    public int getCreditId() {
        return creditId;
    }

    public void setCreditId(int creditId) {
        this.creditId = creditId;
    }

    public String getTimePeriodInfo() {
        return timePeriodInfo;
    }

    public void setTimePeriodInfo(String timePeriodInfo) {
        this.timePeriodInfo = timePeriodInfo;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public LocalDateTime getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(LocalDateTime issueDate) {
        this.issueDate = issueDate;
    }

    public double getRemainder() {
        return remainder;
    }

    public void setRemainder(double remainder) {
        this.remainder = remainder;
    }

    public String getTypeInfo() {
        return typeInfo;
    }

    public void setTypeInfo(String typeInfo) {
        this.typeInfo = typeInfo;
    }

    public int getNumOfTimePeriods() {
        return numOfTimePeriods;
    }

    public void setNumOfTimePeriods(int numOfTimePeriods) {
        this.numOfTimePeriods = numOfTimePeriods;
    }

    public double getPercentForTimePeriod() {
        return percentForTimePeriod;
    }

    public void setPercentForTimePeriod(double percentForTimePeriod) {
        this.percentForTimePeriod = percentForTimePeriod;
    }
}
