package cz.upce.bank.eb.controller;

import cz.upce.bank.eb.entity.Adresy;
import cz.upce.bank.eb.entity.Klienti;
import cz.upce.bank.eb.service.AdresyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

/**
 * Kontroler pro požadavky spojené s adresou
 */

@RestController
@RequestMapping("/api/adresy")
@CrossOrigin(origins = "*")
public class AdresyController {

    @Autowired
    private AdresyService adresyService;

    /**
     * Přidání nové adresy
     * @param newAddress údaje o adrese
     * @return POJO s údaji o adrese
     * @throws SQLException
     */

    @PostMapping(value = "/novy")
    public Adresy addNewAdrress(@RequestBody Adresy newAddress) throws SQLException {
        return adresyService.createNewAddress(newAddress);
    }

    /**
     * Ziskaní údajů o adrese
     * @param addressId
     * @return POJO s údaji o adrese
     */

    @GetMapping("/{addressId}")
    public @ResponseBody
    Adresy getAddress(@PathVariable("addressId") Integer addressId){
        return adresyService.getAddressById(addressId);
    }

    /**
     * Změna údaju o adrese
     * @param addressId
     * @param addressData nové údaje
     * @return POJO s údaji o adrese
     */

    @PutMapping("/{addressId}")
    public @ResponseBody
    Adresy updateAddress(@PathVariable("addressId") Integer addressId, @RequestBody Adresy addressData){
        return adresyService.updateAddress(addressId, addressData);
    }

}
