package cz.upce.bank.eb.controller;

import cz.upce.bank.eb.entity.NewCreditRequest;
import cz.upce.bank.eb.entity.PayCreditRequest;
import cz.upce.bank.eb.entity.Uvery;
import cz.upce.bank.eb.service.UveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
@RequestMapping("/api/uvery")
@CrossOrigin(origins = "*")
public class UveryController {

    @Autowired
    UveryService uveryService;

    /**
     * Zpřísupnění údajů o úvěru
     * @param creditId
     * @return
     */

    @GetMapping("/{creditId}")
    public @ResponseBody
    Uvery getUver(@PathVariable("creditId") Integer creditId) { return uveryService.getUver(creditId); }

    /**
     * Editace úvěru
     * @param uverId
     * @param uverData
     * @return
     */

    @PutMapping("/{creditId}")
    public @ResponseBody
    Uvery updateUver(@PathVariable("creditId") Integer uverId, @RequestBody Uvery uverData){
        return uveryService.updateUver(uverId, uverData);
    }

    /**
     * Pŕidání nového úvěru
     * @param request
     */

    @PostMapping("/novy")
    public void newCredit(@RequestBody NewCreditRequest request){
        uveryService.newCredit(request);
    }

    /**
     * Splácení úvěru
     * @param request
     * @throws SQLException
     */

    @PostMapping(value="/platba")
    public void payUver(@RequestBody PayCreditRequest request) throws SQLException {
        uveryService.payUver(request);
    }
}
