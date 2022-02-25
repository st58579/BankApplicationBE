package cz.upce.bank.eb.controller;

import cz.upce.bank.eb.entity.CardRequest;
import cz.upce.bank.eb.entity.Karty;
import cz.upce.bank.eb.entity.NewCardRequest;
import cz.upce.bank.eb.service.KartyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Kontroller pro požadavky spojené s kartami
 */

@RestController
@RequestMapping("/api/karty")
public class KartyController {

    @Autowired
    private KartyService kartyService;

    /**
     * Přidání nové karty
     * @param request údaje o nové kartě
     */

    @PostMapping("/nova")
    public void newCard(@RequestBody NewCardRequest request){
        kartyService.newCard(request.getAccountId());
    }

    /**
     * Získání údaju o kartě
     * @param cardId
     * @return
     */

    @GetMapping("/{cardId}")
    public @ResponseBody
    Karty getCard(@PathVariable("cardId") Integer cardId) {
        return kartyService.getCardById(cardId);
    }

    /**
     * Zmražení karty
     * @param request
     * @throws Exception
     */

    @PostMapping(value = "/zmrazit", consumes = "application/json")
    public void freezeCard(@RequestBody CardRequest request) throws Exception{
        kartyService.freezeCard(request.getCardId());
    }
    /**
     * Rozmražení karty
     * @param request
     * @throws Exception
     */

    @PostMapping(value = "/rozmrazit", consumes = "application/json")
    public void unfreezeCard(@RequestBody CardRequest request) throws Exception{
        kartyService.unfreezeCard(request.getCardId());
    }

    /**
     * Blokování karty
     * @param request
     * @throws Exception
     */

    @PostMapping(value = "/terminovat", consumes = "application/json")
    public void terminateCard(@RequestBody CardRequest request) throws Exception{
        kartyService.terminateCard(request.getCardId());
    }

}
