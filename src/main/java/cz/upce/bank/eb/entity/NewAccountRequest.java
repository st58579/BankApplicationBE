package cz.upce.bank.eb.entity;

public class NewAccountRequest {
    private Integer clientId;

    private String accountType;

    private Integer limit;

    private Double rate;

    private Integer timePeriodId;

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public Integer getTimePeriodId() {
        return timePeriodId;
    }

    public void setTimePeriodId(Integer timePeriodId) {
        this.timePeriodId = timePeriodId;
    }
}
