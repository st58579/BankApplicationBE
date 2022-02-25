package cz.upce.bank.eb.service;

import cz.upce.bank.eb.dao.RecommendationsDao;
import cz.upce.bank.eb.entity.IdRequest;
import cz.upce.bank.eb.entity.Recommendation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Třída, která se stárá o provádění logiky spojenou s přání
 */
@Service
public class RecommendationsService {

    @Autowired
    RecommendationsDao recommendationsDao;

    /**
     * Vložení nového přání
     * @param newRecommendation
     * @return
     */

    public void createNewRecommendation(Recommendation newRecommendation) {
        recommendationsDao.createNewRecommendation(newRecommendation);
    }

    /**
     * Zpřístupnění všech přání
     * @return
     */
    public Recommendation[] getAllRecommendations() {
        return recommendationsDao.getAllRecommendations();
    }

    /**
     * Zamitnutí přání podle id
     * @param selectedIds
     */
    public void declineRecommendations(IdRequest selectedIds) {
        recommendationsDao.declineRecommendations(selectedIds);
    }

    /**
     * Potvrzení přání podle id
     * @param selectedIds
     */
    public void approveRecommendations(IdRequest selectedIds) {
        recommendationsDao.approveRecommendations(selectedIds);
    }
}
