package cz.upce.bank.eb.entity;

import org.springframework.jdbc.core.RowMapper;

public class KlientiAdresy {

    private Integer clientId;
    private Integer addressId;

    private String name;
    private String surname;
    private String phoneNumber;
    private String birthNumber;
    private Integer active;

    public static RowMapper<KlientiAdresy> getClientAddressesMapper() {
        return (rs, rowNum) -> {
            KlientiAdresy clientAddresses = new KlientiAdresy();
            clientAddresses.setClientId(rs.getInt("KLIENTI_ID"));
            clientAddresses.setAddressId(rs.getInt("ADRESY_ID")); //вот в этом разница
            clientAddresses.setName(rs.getString("JMENO"));
            clientAddresses.setSurname(rs.getString("PRIJMENI"));
            clientAddresses.setBirthNumber(rs.getString("RODNE_CISLO"));
            clientAddresses.setPhoneNumber(rs.getString("KONTAKTNI_CISLO"));
            return clientAddresses;
        };
    }

    public static RowMapper<KlientiAdresy> getClientAddressStateMapper() {
        return (rs, rowNum) -> {
            KlientiAdresy client = new KlientiAdresy();
            client.setClientId(rs.getInt("KLIENTI_ID"));
            client.setAddressId(rs.getInt("ADRESY_ID")); //вот в этом разница
            client.setName(rs.getString("JMENO"));
            client.setSurname(rs.getString("PRIJMENI"));
            client.setBirthNumber(rs.getString("RODNE_CISLO"));
            client.setActive(rs.getInt("AKTIVNI"));
            return client;
        };
    }

    public KlientiAdresy() {
    }

    public Integer getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active = active;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
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
