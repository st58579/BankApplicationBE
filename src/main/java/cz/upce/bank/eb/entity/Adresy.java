package cz.upce.bank.eb.entity;

import org.springframework.jdbc.core.RowMapper;

public class Adresy {

    private int addressId;

    private int clientId;

    private String houseNumber;

    private String street;

    private String town;

    private String postalCode;

    private String countryCode;

    private Integer state;

    public static RowMapper<Adresy> getAdresyMapper(){
        return (rs, rowNum) -> {
            Adresy adresy = new Adresy();
            adresy.setAddressId(rs.getInt("ID"));
            adresy.setHouseNumber(rs.getString("CISLO_POPISNE"));
            adresy.setStreet(rs.getString("ULICE"));
            adresy.setTown(rs.getString("MESTO"));
            adresy.setPostalCode(rs.getString("PSC"));
            adresy.setCountryCode(rs.getString("KOD_ZEME"));
            return adresy;
        };
    }

    public static RowMapper<Adresy> getAdresyViewMapper(){
        return (rs, rowNum) -> {
            Adresy adresy = new Adresy();
            adresy.setAddressId(rs.getInt("ADRESA_ID"));
            adresy.setClientId(rs.getInt("KLIENT_ID"));
            adresy.setHouseNumber(rs.getString("CISLO_POPISNE"));
            adresy.setStreet(rs.getString("ULICE"));
            adresy.setTown(rs.getString("MESTO"));
            adresy.setPostalCode(rs.getString("PSC"));
            adresy.setCountryCode(rs.getString("KOD_ZEME"));
            return adresy;
        };
    }

    public static RowMapper<Adresy> getKlientyAdresyMapper() {
        return (rs, rowNum) -> {
            Adresy adresy = new Adresy();
            adresy.setAddressId(rs.getInt("ADRESY_ID"));
            adresy.setClientId(rs.getInt("KLIENTI_ID"));
            adresy.setState(rs.getInt("AKTIVNI"));
            return adresy;
        };
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }


}
