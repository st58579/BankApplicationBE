package cz.upce.bank.eb.controller;

import cz.upce.bank.eb.entity.IdRequest;
import cz.upce.bank.eb.entity.Recommendation;
import cz.upce.bank.eb.service.RecommendationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

/**
 * Kontroler pro požadavky spojené s přání
 */


@RestController
@RequestMapping("/api/prani")
@CrossOrigin(origins = "*")
public class RecommendationsController {

    @Autowired
    RecommendationsService recommendationsService;

    @PostMapping(value = "/nove")
    public void addNewRecommendation(@RequestBody Recommendation newRecommendation) throws SQLException {
        recommendationsService.createNewRecommendation(newRecommendation);
    }

    @GetMapping("/get")
    public @ResponseBody
    Recommendation[] getAllRecommendations(){
        return recommendationsService.getAllRecommendations();
    }

    @PostMapping(value = "decline")
    public void declineRecommendations(@RequestBody IdRequest selectedIds){
        recommendationsService.declineRecommendations(selectedIds);
    }

    @PostMapping(value = "approve")
    public void approveRecommendations(@RequestBody IdRequest selectedIds) {
        recommendationsService.approveRecommendations(selectedIds);
    }
}
