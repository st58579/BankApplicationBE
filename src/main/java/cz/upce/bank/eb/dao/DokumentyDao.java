package cz.upce.bank.eb.dao;

import cz.upce.bank.eb.entity.Dokumenty;
import cz.upce.bank.eb.entity.Klienti;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.util.List;

/**
 * Třída má na starosti přístup k databázi pro DokumentyServis
 */

@Service
public class DokumentyDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * Zpřístupnění obsahu dokumentu podle id
     * @param clientId
     * @return
     */

    public Dokumenty getDokumentContenttById(Integer clientId){
        String query = "SELECT * FROM UDAJE_O_DOKUMENTECH WHERE ID = ?";
        List<Dokumenty> foundClients  = jdbcTemplate.query(query, new Object[]{clientId}, Dokumenty.getDokumentyMapper());
        if (foundClients.size() != 1){
            return null;
        }
        return foundClients.get(0);
    }

    /**
     * Insert pro vložení nového dokumentu
     * @param name
     * @param clientId
     * @param typeId
     * @param file
     */

    public void saveNewDocument(String name, Integer clientId, Integer typeId,  byte[] file){
        String sql = "INSERT INTO DOKUMENTY (OBSAH, NAZEV, KLIENT_ID, TYP_DOKUMENTU_ID)" +
                " VALUES (?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection
                    .prepareStatement(sql, new String[] { "ID" });
            ps.setBytes(1, file);
            ps.setString(2, name);
            ps.setInt(3, clientId);
            ps.setInt(4, typeId);
            return ps;
        }, keyHolder);

        int generatedId = keyHolder.getKey().intValue();
    }


}
