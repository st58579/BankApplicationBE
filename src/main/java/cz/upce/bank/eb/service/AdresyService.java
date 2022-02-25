package cz.upce.bank.eb.service;

import cz.upce.bank.eb.dao.AdresyDao;
import cz.upce.bank.eb.entity.Adresy;
import cz.upce.bank.eb.entity.Klienti;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;

/**
 * Třída, která se stárá o provádění logiky spojenou s adresami
 */

@Service
public class AdresyService {

    @Autowired
    private AdresyDao adresyDao;

    /**
     * Vytvoření nové adresy
     * @param adresy
     * @return
     * @throws SQLException
     */

    @Transactional(rollbackFor = Exception.class)
    public Adresy createNewAddress(Adresy adresy) throws SQLException {
        int addId = adresyDao.createAddressAndBindToClient(adresy);
        adresy.setClientId(addId);
        return adresy;
    }

    /**
     * Zpřístupnění adresy
     * @param addressId
     * @return
     */

    public Adresy getAddressById(Integer addressId){
        return adresyDao.getAddressById(addressId);
    }

    /**
     * Editace adresy
     * @param addressId
     * @param addressData
     * @return
     */

    public Adresy updateAddress(Integer addressId, Adresy addressData) {
        return adresyDao.updateAddress(addressId, addressData);
    }
}
