package cz.upce.bank.eb.service;

import cz.upce.bank.eb.dao.KartyDao;
import cz.upce.bank.eb.entity.Karty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Třída, která se stárá o provádění logiky spojenou s kartami
 */

@Service
public class KartyService {

    @Autowired
    private KartyDao kartyDao;

    public Karty getCardById(Integer cardId) {
        return kartyDao.getCardById(cardId);
    }

    /**
     * Nová karta
     * @param accountId
     */

    public void newCard(Integer accountId){
        kartyDao.newCard(accountId);
    }

    /**
     * Zmražení karty
     * @param cardId
     */

    public void freezeCard(Integer cardId) {
        kartyDao.freezeCard(cardId);
    }

    /**
     * Rozmražení karty
     * @param cardId
     */

    public void unfreezeCard(Integer cardId) {
        kartyDao.unfreezeCard(cardId);
    }

    /**
     * Blokování karty
     * @param cardId
     */

    public void terminateCard(Integer cardId) {
        kartyDao.terminateCard(cardId);
    }
}
