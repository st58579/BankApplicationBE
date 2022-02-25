package cz.upce.bank.eb.entity;

import org.springframework.jdbc.core.RowMapper;

public class Klienti {

    private Integer clientId;

    private String name;

    private String surname;

    private String phoneNumber;

    private String birthNumber;

    public static RowMapper<Klienti> getClientMapper() {
        return (rs, rowNum) -> {
            Klienti client = new Klienti();
            client.setClientId(rs.getInt("ID"));
            client.setName(rs.getString("JMENO"));
            client.setSurname(rs.getString("PRIJMENI"));
            client.setBirthNumber(rs.getString("RODNE_CISLO"));
            client.setPhoneNumber(rs.getString("KONTAKTNI_CISLO"));
            return client;
        };
    }

    public Klienti() {
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getBirthNumber() {
        return birthNumber;
    }

    public void setBirthNumber(String birthNumber) {
        this.birthNumber = birthNumber;
    }
}
