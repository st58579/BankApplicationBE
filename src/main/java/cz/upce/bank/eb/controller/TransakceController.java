package cz.upce.bank.eb.controller;

import cz.upce.bank.eb.entity.NewTransactionRequest;
import cz.upce.bank.eb.service.TransakceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Kontroler pro požadavky spojené s transakcemi
 */

@RestController
@RequestMapping("/api/transakce")
@CrossOrigin(origins = "*")
public class TransakceController {

    @Autowired
    private TransakceService transakceService;

    /**
     * Provádění transakce
     * @param request údaje o transakci
     * @throws Exception
     */

    @PostMapping(value = "/novy")
    public void addNewTransaction(@RequestBody NewTransactionRequest request) throws Exception{
        transakceService.addNewTransaction(request);
    }

}
