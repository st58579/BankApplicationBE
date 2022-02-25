package cz.upce.bank.eb.service;


import cz.upce.bank.eb.controller.StatisticsController;
import cz.upce.bank.eb.dao.StatisticsDao;
import cz.upce.bank.eb.entity.AccountStatistics;
import cz.upce.bank.eb.entity.ProfitInPeriodRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

/**
 * Třída, která se stárá o provádění logiky spojenou se statistikami
 */

@Service
public class StatisticsService {

    @Autowired
    StatisticsDao statisticsDao;

    /**
     * Zpřístupnění statustic
     * @return
     * @throws SQLException
     */

    public AccountStatistics getAccountStatistics() throws SQLException {
        return statisticsDao.getAccountStatistics();
    }

    /**
     * Suma všech prostředku na účtu
     * @return
     * @throws SQLException
     */

    public int getTotalBankCapital() throws SQLException {
        return statisticsDao.getTotalBankCapital();
    }

    /**
     * Výpočet příjmu za určité období
     * @param dateFrom
     * @param dateTo
     * @return
     */

    public int getProfitInPeriod(String dateFrom, String dateTo) {
        return statisticsDao.getProfitInPeriod(dateFrom, dateTo);
    }
}
