package cz.upce.bank.eb.dao;

import cz.upce.bank.eb.entity.Karty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

/**
 * Třída má na starosti přístup k databázi pro KartyServis
 */


@Service
public class KartyDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * Insert pro vložení nové karty
     * @param accountId
     */

    public void newCard(Integer accountId){
        String query = "INSERT INTO KARTY (CISLO_KARTY, DATUM_VYDANI, DATUM_PLATNOSTI, BEZPECNOSTNI_KOD, UCET_ID, STAV_KARTY_ID) " +
                        "VALUES (S_KARTY_CISLO.NEXTVAL, SYSTIMESTAMP, add_months(SYSTIMESTAMP, 60), ?, ?, 1)";

        Random random = new Random();
        int securityCode = random.nextInt(900);


        jdbcTemplate.update(query, new Object[]{securityCode, accountId});
    }

    /**
     * Select pro zpřístupnění údajů o kartě podle id
     * @param cardId
     * @return
     */

    public Karty getCardById(Integer cardId){
        String query = "SELECT * FROM UDAJE_O_KARTACH WHERE ID = ?";
        List<Karty> foundCards  = jdbcTemplate.query(query, new Object[]{cardId}, Karty.getKartyMapper());
        if (foundCards.size() != 1){
            throw new DaoException("Card with ID " + cardId + " not found");
        }
        return foundCards.get(0);
    }


    /**
     * Update pro zmražení karty podle id
     * @param cardId
     */

    public void freezeCard(Integer cardId) {
        String query = "UPDATE KARTY SET STAV_KARTY_ID = 2 WHERE ID = ?";
        jdbcTemplate.update(query, new Object[]{cardId});
    }

    /**
     * Update pro rozmražení karty podle id
     * @param cardId
     */

    public void unfreezeCard(Integer cardId) {
        String query = "UPDATE KARTY SET STAV_KARTY_ID = 1 WHERE ID = ?";
        jdbcTemplate.update(query, new Object[]{cardId});
    }

    /**
     * Update pro blokování karty podle id
     * @param cardId
     */

    public void terminateCard(Integer cardId) {
        String query = "UPDATE KARTY SET STAV_KARTY_ID = 3 WHERE ID = ?";
        jdbcTemplate.update(query, new Object[]{cardId});
    }
}
