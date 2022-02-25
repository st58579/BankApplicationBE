package cz.upce.bank.eb.service;

import cz.upce.bank.eb.dao.TransakceDao;
import cz.upce.bank.eb.entity.NewTransactionRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Třída, která se stárá o provádění logiky spojenou s transakcemi
 */

@Service
public class TransakceService {

    @Autowired
    private TransakceDao transakceDao;

    /**
     * Přidání nové transakce
     * @param request
     * @throws Exception
     */

    public void addNewTransaction(NewTransactionRequest request) throws Exception{
        transakceDao.addNewTransaction(request);
    }

}
