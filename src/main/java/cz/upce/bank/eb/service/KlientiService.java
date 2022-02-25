package cz.upce.bank.eb.service;


import cz.upce.bank.eb.dao.KlientiDao;
import cz.upce.bank.eb.entity.Klienti;
import cz.upce.bank.eb.entity.KlientiAdresy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Třída, která se stárá o provádění logiky spojenou s klienty
 */

@Service
public class KlientiService {

    @Autowired
    private KlientiDao klientiDao;

    /**
     * Zpřístupnění klienta podle id
     * @param clientId
     * @return
     */

    public Klienti getClientById(Integer clientId){
        return klientiDao.getClientById(clientId);
    }

    /**
     * Editace klienta
     * @param clientId
     * @param clientData
     * @return
     */

    public Klienti updateClient(Integer clientId, Klienti clientData){
        return klientiDao.updateClient(clientId, clientData);
    }

    /**
     * Zpřístupnění klientu, kteří bydlí na jedné adrese
     * @param addressId
     * @return
     */

    public KlientiAdresy[] getClientsOnAddress(Integer addressId) {
        return klientiDao.getClientsOnAddress(addressId);
    }

    /**
     * Editace klientu, kteří bydlí na jedne adrese
     * @param clientAddress
     * @return
     */

    public KlientiAdresy updateClientAddressState(KlientiAdresy clientAddress) {
        return klientiDao.updateClientAddressState(clientAddress);
    }
}
