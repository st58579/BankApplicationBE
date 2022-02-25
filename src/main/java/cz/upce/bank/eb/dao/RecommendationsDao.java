package cz.upce.bank.eb.dao;

import cz.upce.bank.eb.entity.IdRequest;
import cz.upce.bank.eb.entity.Recommendation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecommendationsDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * Insert pro vložení nového přání
     * @param newRecommendation
     */
    public void createNewRecommendation(Recommendation newRecommendation) {
        String query = "INSERT INTO PRANI (TEMA, ZPRAVA) VALUES (?, ?)";
        jdbcTemplate.update(query, new Object[] {newRecommendation.getSubject(), newRecommendation.getText()});
    }

    /**
     * Select pro zpřístupnění všech přání
     * @return
     */
    public Recommendation[] getAllRecommendations() {
        String query = "SELECT * FROM PRANI";
        List<Recommendation> recommendations = jdbcTemplate.query(query, new Object[]{},Recommendation.getRecommendationMapper());
        if (recommendations.size() < 1) {
            throw new DaoException("Recommendations were not found");
        }
        Recommendation[] recommendationsArr = new Recommendation[recommendations.size()];
        recommendations.toArray(recommendationsArr);
        return recommendationsArr;
    }

    /**
     * Update pro zamitnutí přání podle id
     */
    public void declineRecommendations(IdRequest selectedIds) {
        String query = "UPDATE PRANI SET STATUS = ? WHERE ID = ?";
        for (int i = 0; i < selectedIds.getIds().length; i++){
            jdbcTemplate.update(query, new Object[] {"Zamítnuto", selectedIds.getIds()[i]});
        }
    }

    /**
     * Update pro potvrzení přání podle id
     */
    public void approveRecommendations(IdRequest selectedIds) {
        String query = "UPDATE PRANI SET STATUS = ? WHERE ID = ?";
        for (int i = 0; i < selectedIds.getIds().length; i++){
            jdbcTemplate.update(query, new Object[] {"Přijato", selectedIds.getIds()[i]});
        }
    }
}
