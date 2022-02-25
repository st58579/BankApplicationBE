package cz.upce.bank.eb.service;

import cz.upce.bank.eb.dao.UctyDao;
import cz.upce.bank.eb.entity.NewAccountRequest;
import cz.upce.bank.eb.entity.Ucty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Třída, která se stárá o provádění logiky spojenou s účty
 */

@Service
public class UctyService {

    @Autowired
    private UctyDao uctyDao;

    /**
     * Zpřístupnění údaju o účte podle id
     * @param ucetId
     * @return
     */

    public Ucty getUcetById(Integer ucetId){
        Ucty foundAccount =  uctyDao.getUcetById(ucetId);
        if (foundAccount == null){
            throw new ServiceException("Account was not found");
        }
        return foundAccount;
    }

    /**
     * Terminace účtu
     * @param accountId
     * @throws Exception
     */

    public void terminateAccount(Integer accountId) throws Exception{
        uctyDao.terminateAccount(accountId);
    }

    /**
     * Zmražení účtu
     * @param accountId
     * @throws Exception
     */

    public void freezeAccount(Integer accountId) throws Exception{
        uctyDao.freezeAccount(accountId);
    }

    /**
     * Rozmražení účtu
     * @param accountId
     * @throws Exception
     */

    public void unfreezeAccount(Integer accountId) throws Exception{
        uctyDao.unfreezeAccount(accountId);
    }

    /**
     * Vytvoření nového účtu
     * @param account
     */

    public void createNewAccount(NewAccountRequest account){
        uctyDao.createNewAccount(account);
    }


}
