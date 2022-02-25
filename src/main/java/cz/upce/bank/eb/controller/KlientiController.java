package cz.upce.bank.eb.controller;

import cz.upce.bank.eb.entity.Klienti;
import cz.upce.bank.eb.entity.User;
import cz.upce.bank.eb.entity.UserRole;
import cz.upce.bank.eb.entity.KlientiAdresy;
import cz.upce.bank.eb.service.KlientiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

/**
 * Kontroler pro požadavky spojené s klientem bankovnictví
 */

@RestController
@RequestMapping("/api/klienti")
public class KlientiController {

    @Autowired
    private KlientiService klientiService;

    /**
     * Ziskaní údaju o klientě
     * @param clientId
     * @return
     */

    @GetMapping("/{clientId}")
    public @ResponseBody
    Klienti getClient(@PathVariable("clientId") Integer clientId)
    {
        try{
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (user == null || (user.getRole() == UserRole.USER && user.getClientId() != clientId)){
                throw new AccessProhibitedException("Access denied");
            }

            return klientiService.getClientById(clientId);
        }catch (Exception e){
            throw new AccessProhibitedException("Access denied");
        }
    }

    /**
     * Ziskání seznamu klientů, které bydly na jedné adrese
     * @param addressId
     * @return Pole klientu na adrese
     */

    @GetMapping("/batch/{addressId}")
    public @ResponseBody
    KlientiAdresy[] getClientsOnAddress(@PathVariable("addressId") Integer addressId) {
        return klientiService.getClientsOnAddress(addressId);
    }

    /**
     * Změna údajů o adrese kliemta
     * @param clientAddress
     * @return
     */

    @PutMapping("/addressState")
    public @ResponseBody
    KlientiAdresy updateClientAddressState(@RequestBody KlientiAdresy clientAddress){
        return klientiService.updateClientAddressState(clientAddress);
    }

    /**
     * Změna údajů klienta
     * @param clientId
     * @param clientData
     * @return
     */

    @PutMapping("/{clientId}")
    public @ResponseBody
    Klienti updateClient(@PathVariable("clientId") Integer clientId, @RequestBody Klienti clientData){
        return klientiService.updateClient(clientId, clientData);
    }
}