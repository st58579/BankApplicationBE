package cz.upce.bank.eb.entity;

public class NewTransactionRequest {

    private Long fromAccountNumber;
    private Long toAccountNumber;
    private Double amount;
    private Integer timePeriodId;

    public Long getFromAccountNumber() {
        return fromAccountNumber;
    }

    public void setFromAccountNumber(Long fromAccountNumber) {
        this.fromAccountNumber = fromAccountNumber;
    }

    public Long getToAccountNumber() {
        return toAccountNumber;
    }

    public void setToAccountNumber(Long toAccountNumber) {
        this.toAccountNumber = toAccountNumber;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Integer getTimePeriodId() {
        return timePeriodId;
    }

    public void setTimePeriodId(Integer timePeriodId) {
        this.timePeriodId = timePeriodId;
    }
}
