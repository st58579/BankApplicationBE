package cz.upce.bank.eb.dao;

import cz.upce.bank.eb.entity.Adresy;
import oracle.jdbc.OracleTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.*;
import java.util.List;

/**
 * Třída má na starosti přístup k databázi pro AdresyServis
 */

@Service
public class AdresyDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * Volání uložené procedury VLOZ_ADRESU
     * Procedura je v databázi
     * @param adresy
     * @return
     * @throws SQLException
     */

    public int createAddressAndBindToClient(Adresy adresy) throws SQLException {
        Connection connection = jdbcTemplate.getDataSource().getConnection();
        CallableStatement proc = connection.prepareCall("{ ? = call VLOZ_ADRESU(?,?,?,?,?,?) }");
        proc.registerOutParameter(1, OracleTypes.INTEGER);
        proc.setInt(2, adresy.getClientId());
        proc.setString(3, adresy.getHouseNumber());
        proc.setString(4, adresy.getStreet());
        proc.setString(5, adresy.getTown());
        proc.setString(6, adresy.getPostalCode());
        proc.setString(7, adresy.getCountryCode());
        proc.execute();
        int result = proc.getInt(1);
        proc.close();
        return result;
    }

    /**
     * Select pro vyhledávaní adresy podle id
     * @param addressId
     * @return
     */

    public Adresy getAddressById(Integer addressId){
        String query = "SELECT * FROM ADRESY WHERE ID = ?";
        List<Adresy> foundAddresses  = jdbcTemplate.query(query, new Object[]{addressId}, Adresy.getAdresyMapper());
        if (foundAddresses.size() != 1){
            throw new DaoException("Address with ID " + addressId + " not found");
        }
        return foundAddresses.get(0);
    }

    /**
     * Změna údajů adresy
     * @param addressId
     * @param modifiedAddressData
     * @return
     */

    @Transactional(rollbackFor = Exception.class)
    public Adresy updateAddress(Integer addressId, Adresy modifiedAddressData) {
        String query = "UPDATE ADRESY SET CISLO_POPISNE = ?, ULICE = ?, MESTO = ?, PSC = ?, KOD_ZEME = ?\n" +
                "WHERE ID = ?";
        jdbcTemplate.update(query,new Object[] {modifiedAddressData.getHouseNumber(), modifiedAddressData.getStreet(),
        modifiedAddressData.getTown(), modifiedAddressData.getPostalCode(), modifiedAddressData.getCountryCode(), modifiedAddressData.getAddressId()});
        return modifiedAddressData;
    }
}
