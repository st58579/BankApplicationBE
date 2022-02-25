package cz.upce.bank.eb.dao;

import cz.upce.bank.eb.entity.NewTransactionRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.CallableStatement;
import java.sql.Connection;

/**
 * Třída má na starosti přístup k databázi pro TransakceServis
 */

@Service
public class TransakceDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * Volání uložene procedury PROVED_TRANSAKCI pro zadání nové transakce
     * @param request
     * @throws Exception
     */

    public void addNewTransaction(NewTransactionRequest request) throws Exception{
        Connection connection = jdbcTemplate.getDataSource().getConnection();
        CallableStatement callableStatement = connection.prepareCall("{call PROVED_TRANSAKCI(?, ?, ?, ?)}");
        callableStatement.setLong(1, request.getFromAccountNumber());
        callableStatement.setLong(2, request.getToAccountNumber());
        callableStatement.setDouble(3, request.getAmount());
        callableStatement.setInt(4, request.getTimePeriodId());
        callableStatement.executeUpdate();

    }



}
