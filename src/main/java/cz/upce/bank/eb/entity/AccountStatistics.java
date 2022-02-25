package cz.upce.bank.eb.entity;

public class AccountStatistics {

    public int  simpleAccCount;
    public int savingAccCount;
    public int creditAccCount;
    public double avgCreditPerAcc;
    public double avgCardPerAcc;
    public double avgAccPerClient;

    public int getSimpleAccCount() {
        return simpleAccCount;
    }

    public void setSimpleAccCount(int simpleAccCount) {
        this.simpleAccCount = simpleAccCount;
    }

    public int getSavingAccCount() {
        return savingAccCount;
    }

    public void setSavingAccCount(int savingAccCount) {
        this.savingAccCount = savingAccCount;
    }

    public int getCreditAccCount() {
        return creditAccCount;
    }

    public void setCreditAccCount(int creditAccCount) {
        this.creditAccCount = creditAccCount;
    }

    public double getAvgCreditPerAcc() {
        return avgCreditPerAcc;
    }

    public void setAvgCreditPerAcc(double avgCreditPerAcc) {
        this.avgCreditPerAcc = avgCreditPerAcc;
    }

    public double getAvgCardPerAcc() {
        return avgCardPerAcc;
    }

    public void setAvgCardPerAcc(double avgCardPerAcc) {
        this.avgCardPerAcc = avgCardPerAcc;
    }

    public double getAvgAccPerClient() {
        return avgAccPerClient;
    }

    public void setAvgAccPerClient(double avgAccPerClient) {
        this.avgAccPerClient = avgAccPerClient;
    }
}
