package cz.upce.bank.eb.grids;

import cz.upce.bank.eb.entity.Recommendation;
import mk.gridlib.builders.GridConfigBuilder;
import mk.gridlib.domain.grid.GridConfig;
import mk.gridlib.enums.CONTENTTYPE;
import mk.gridlib.enums.SORTORDER;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RecommendationsGrid {

    @Bean
    public GridConfig<Recommendation> getAllRecommendations() {
        return GridConfigBuilder.createGrid(Recommendation.class, "recommendationId")
                .gridName("Prani")
                .unlimitedRowsCount()
                .hiddenColumn("recommendationId").tableColumn("ID").contentType(CONTENTTYPE.TEXT).end()
                .column("subject").tableColumn("TEMA").label("Předmět").contentType(CONTENTTYPE.TEXT).end()
                .column("text").tableColumn("ZPRAVA").label("Zpráva").contentType(CONTENTTYPE.TEXT).end()
                .column("status").tableColumn("STATUS").label("Stav").contentType(CONTENTTYPE.TEXT).end()
                .sort("recommendationId", SORTORDER.DESC)
                .build();
    }
}
