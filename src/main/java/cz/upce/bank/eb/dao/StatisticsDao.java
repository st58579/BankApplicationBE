package cz.upce.bank.eb.dao;

import cz.upce.bank.eb.controller.StatisticsController;
import cz.upce.bank.eb.entity.AccountStatistics;
import cz.upce.bank.eb.entity.ProfitInPeriodRequest;
import oracle.jdbc.OracleTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Třída má na starosti přístup k databázi pro StatisticsServis
 */

@Service
public class StatisticsDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * Volání uložene procedury UCET_STATS pro zpřístupnění statistik
     * @return
     * @throws SQLException
     */

    public AccountStatistics getAccountStatistics() throws SQLException {
        AccountStatistics as = new AccountStatistics();
        try(Connection connection = jdbcTemplate.getDataSource().getConnection()) {
            CallableStatement proc = connection.prepareCall("{ call UCET_STATS(?,?,?,?,?,?) }");
            proc.registerOutParameter(1, OracleTypes.INTEGER);
            proc.registerOutParameter(2, OracleTypes.INTEGER);
            proc.registerOutParameter(3, OracleTypes.INTEGER);
            proc.registerOutParameter(4, OracleTypes.DOUBLE);
            proc.registerOutParameter(5, OracleTypes.DOUBLE);
            proc.registerOutParameter(6, OracleTypes.DOUBLE);
            proc.execute();

            as.setSimpleAccCount(proc.getInt(1));
            as.setSavingAccCount(proc.getInt(2));
            as.setCreditAccCount(proc.getInt(3));
            as.setAvgCreditPerAcc(proc.getDouble(4));
            as.setAvgCardPerAcc(proc.getDouble(5));
            as.setAvgAccPerClient(proc.getDouble(6));
            proc.close();
        }
        return as;
    }

    /**
     * Volání uložene procedury TOTAL_CAPITAL pro zpřístupnění statistik
     * @return
     * @throws SQLException
     */

    public int getTotalBankCapital() throws SQLException {
        int total = 0;
        try(Connection connection = jdbcTemplate.getDataSource().getConnection()) {
            CallableStatement proc = connection.prepareCall("{ ? = call TOTAL_CAPITAL() }");
            proc.registerOutParameter(1, OracleTypes.INTEGER);
            proc.execute();
            total = proc.getInt(1);
            proc.close();
        }
        return total;
    }

    /**
     * Volání uložene procedury CALCULATE_PROFIT_IN_PERIOD pro zpřístupnění statistik
     * @return
     * @throws SQLException
     */

    public int getProfitInPeriod(String dateFrom, String dateTo) {
        int profit = 0;
        try(Connection connection = jdbcTemplate.getDataSource().getConnection()) {
            CallableStatement proc = connection.prepareCall("{ ? = call CALCULATE_PROFIT_IN_PERIOD(?,?) }");
            proc.registerOutParameter(1, OracleTypes.INTEGER);
            proc.setString(2, dateFrom);
            proc.setString(3, dateTo);
            proc.execute();
            profit = proc.getInt(1);
            proc.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return profit;
    }
}
