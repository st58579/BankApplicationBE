package cz.upce.bank.eb.controller;

import cz.upce.bank.eb.entity.AccountStatistics;
import cz.upce.bank.eb.entity.ProfitInPeriodRequest;
import cz.upce.bank.eb.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

/**
 * Kontroler pro požadavky spojené se statistikami
 */

@RestController
@RequestMapping("/api/stats")
public class StatisticsController {

    @Autowired
    StatisticsService statisticsService;

    /**
     * Získání statistiky o účtech
     * @return
     * @throws SQLException
     */

    @GetMapping("/account")
    public @ResponseBody
    AccountStatistics getAccountStatistics() throws SQLException {
        return statisticsService.getAccountStatistics();
    }

    /**
     * Získání sumy všech prostředků banky
     * @return
     * @throws SQLException
     */

    @GetMapping("/capital")
    public @ResponseBody
    int getTotalBankCapital() throws SQLException {
        return statisticsService.getTotalBankCapital();
    }

    /**
     * Výpočet příjmu banky za určité období
     * @param dateFrom od
     * @param dateTo do
     * @return celkové přijmy za období
     */

    @GetMapping("/profit/{periodFrom}/{periodTo}")
    public @ResponseBody
    int getProfitInPeriod(@PathVariable(name = "periodFrom") String dateFrom, @PathVariable(name = "periodTo") String dateTo) {
        return statisticsService.getProfitInPeriod(dateFrom, dateTo);
    }

}
