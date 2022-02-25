package cz.upce.bank.eb.dao;

import cz.upce.bank.eb.entity.Klienti;
import cz.upce.bank.eb.entity.NewAccountRequest;
import cz.upce.bank.eb.entity.Ucty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

/**
 * Třída má na starosti přístup k databázi pro UctyServis
 */


@Service
public class UctyDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * Select pro zpřístupnění údajů o účte podle id
     * @param clientId
     * @return
     */

    public Ucty getUcetById(Integer clientId){
        String query = "SELECT * FROM UDAJE_O_UCTECH WHERE UCET_ID = ?";
        List<Ucty> foundClients  = jdbcTemplate.query(query, new Object[]{clientId}, Ucty.getUctyMapper());
        if (foundClients.size() != 1){
           return null;
        }
        return foundClients.get(0);
    }

    /**
     * Update pro zmrážení účtu podle id
     * @param accountId
     * @return
     */

    public void freezeAccount(Integer accountId) throws Exception{
        Connection connection = jdbcTemplate.getDataSource().getConnection();
        CallableStatement callableStatement = connection.prepareCall("{call ZMRAZ_UCET(?)}");
        callableStatement.setInt(1, accountId);
        callableStatement.executeUpdate();
    }

    /**
     * Update pro blokování účtu podle id
     * @param accountId
     * @return
     */

    public void terminateAccount(Integer accountId) throws Exception{
        Connection connection = jdbcTemplate.getDataSource().getConnection();
        CallableStatement callableStatement = connection.prepareCall("{call TERMINUJ_UCET(?)}");
        callableStatement.setInt(1, accountId);
        callableStatement.executeUpdate();
    }

    /**
     * Update pro rozzmrážení účtu podle id
     * @param accountId
     * @return
     */

    public void unfreezeAccount(Integer accountId) throws Exception{
        Connection connection = jdbcTemplate.getDataSource().getConnection();
        CallableStatement callableStatement = connection.prepareCall("{call ROZMRAZ_UCET(?)}");
        callableStatement.setInt(1, accountId);
        callableStatement.executeUpdate();;
    }

    /**
     * Vytvořní nového účtu
     * @param account
     */

    public void createNewAccount(NewAccountRequest account){
        if (account.getAccountType().equals("U")){
            createCreditAccount(account);
        }
        else if (account.getAccountType().equals("S")){
            createSavingsAccount(account);
        }
        else {
            createNormalAccount(account);
        }
    }

    /**
     * Insert pro vložení nového běžného účtu
     * @param accountRequest
     */

    public void createNormalAccount(NewAccountRequest accountRequest){
        String query = "insert into UCTY (DATUM_ZALOZENI, ZUSTATEK, TYP_UCTU, KLIENT_ID, STAV_UCTU_ID)\n" +
                "values (current_timestamp, 0, 'B', ?, 1)";

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection
                    .prepareStatement(query, new String[] { "ID" });
            ps.setInt(1, accountRequest.getClientId());
            return ps;
        });

    }

    /**
     * Insert pro vložení nového kreditního účtu
     * @param accountRequest
     */

    public void createCreditAccount(NewAccountRequest accountRequest){
        String query = "insert into UCTY (DATUM_ZALOZENI, ZUSTATEK, TYP_UCTU, KLIENT_ID, STAV_UCTU_ID, HRANICE_CERPANI)\n" +
                "values (current_timestamp, 0, 'U', ?, 1, ?)";

        Integer limit = accountRequest.getLimit();
        if (limit > 0){
            throw new DaoException("Limit can not be positive number");
        }

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection
                    .prepareStatement(query, new String[] { "ID" });
            ps.setInt(1, accountRequest.getClientId());
            ps.setInt(2, limit);
            return ps;
        });
    }

    /**
     * Insert pro vložení nového spořícíhp účtu
     * @param accountRequest
     */

    public void createSavingsAccount(NewAccountRequest accountRequest){
        String query = "insert into UCTY (DATUM_ZALOZENI, ZUSTATEK, TYP_UCTU, KLIENT_ID, STAV_UCTU_ID, UROKOVA_SAZBA, CASOVA_OBDOBI_ID)\n" +
                "values (current_timestamp, 0, 'S', ?, 1, ?, ?)";

        Double rate = accountRequest.getRate();
        if (rate < 0){
            throw new DaoException("Rate can not be negative number");
        }

        Integer timePeriodId = accountRequest.getTimePeriodId();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection
                    .prepareStatement(query, new String[] { "ID" });
            ps.setInt(1, accountRequest.getClientId());
            ps.setDouble(2, rate);
            ps.setInt(3, timePeriodId);
            return ps;
        });
    }



}
