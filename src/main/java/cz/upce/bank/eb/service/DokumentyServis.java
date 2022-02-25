package cz.upce.bank.eb.service;

import cz.upce.bank.eb.dao.DokumentyDao;
import cz.upce.bank.eb.entity.Dokumenty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Třída, která se stárá o provádění logiky spojenou s dokumenty
 */

@Service
public class DokumentyServis {

    @Autowired
    private DokumentyDao dokumentyDao;

    public Dokumenty getDocumentContentById(Integer id){
        return dokumentyDao.getDokumentContenttById(id);
    }

    /**
     * Uložení nového dokumentu
     * @param name
     * @param clientIdString
     * @param typeIdString
     * @param file
     */

    @Transactional(rollbackFor = Exception.class)
    public void saveNewDocument(String name, String clientIdString, String typeIdString, byte[] file){
        Integer clientId = Integer.valueOf(clientIdString);
        Integer typeId  =Integer.valueOf(typeIdString);
        dokumentyDao.saveNewDocument(name, clientId, typeId, file);
    }

}
