package cz.upce.bank.eb.service;

import cz.upce.bank.eb.dao.UveryDao;
import cz.upce.bank.eb.entity.NewCreditRequest;
import cz.upce.bank.eb.entity.PayCreditRequest;
import cz.upce.bank.eb.entity.Uvery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

/**
 * Třída, která se stárá o provádění logiky spojenou s úvěry
 */

@Service
public class UveryService {
    
    @Autowired
    private UveryDao uveryDao;

    /**
     * Vytvoření nového úvěru
     * @param request
     */

    public void newCredit(NewCreditRequest request){
        uveryDao.newCredit(request);
    }

    /**
     * Zpřístupnění úvěru podle id
     * @param creditId
     * @return
     */

    public Uvery getUver(Integer creditId) {
        return uveryDao.getUver(creditId);
    }

    /**
     * Editace úvěru podle id
     * @param uverId
     * @param uverData
     * @return
     */

    public Uvery updateUver(Integer uverId, Uvery uverData) {
        return uveryDao.updateUver(uverId, uverData);
    }

    /**
     * Splácení úvěru
     * @param request
     * @throws SQLException
     */

    public void payUver(PayCreditRequest request) throws SQLException {
        uveryDao.payUver(request);
    }
}
