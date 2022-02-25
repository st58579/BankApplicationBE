package cz.upce.bank.eb.grids;

import cz.upce.bank.eb.entity.Uvery;
import mk.gridlib.builders.GridConfigBuilder;
import mk.gridlib.domain.grid.GridConfig;
import mk.gridlib.enums.CONTENTTYPE;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UveryGrid {

    @Bean
    public GridConfig<Uvery> getUveryInfo() {
        return GridConfigBuilder.createGrid(Uvery.class, "creditId")
                .gridName("Credits")
                .unlimitedRowsCount()

                .hiddenColumn("creditId").tableColumn("ID").contentType(CONTENTTYPE.TEXT).end()
                .hiddenColumn("accountId").tableColumn("UCTY_ID").contentType(CONTENTTYPE.TEXT).end()
                .column("numOfTimePeriods").tableColumn("POCET_OBDOBI").label("Počet období").contentType(CONTENTTYPE.TEXT).end()
                .column("timePeriodInfo").tableColumn("OBDOBI_POPIS").label("Časová jednotka").contentType(CONTENTTYPE.TEXT).end()
                /*.column("percentForTimePeriod").tableColumn("PROCENTO_ZA_CASOVE_OBDOBI").label("Úrok").contentType(CONTENTTYPE.TEXT).end()*/
                .virtualColumn("percentForTimePeriod").fn(uvery -> uvery.getPercentForTimePeriod() + " %").label("Úrok").contentType(CONTENTTYPE.TEXT).end()
                .column("remainder").tableColumn("ZBYVAJICI_CASTKA").label("Zbyvající částka").contentType(CONTENTTYPE.TEXT).end()
                .column("issueDate").tableColumn("DATUM_POSKYTNUTI").label("Datum poskytnuti").contentType(CONTENTTYPE.TEXT).end()
                .column("typeInfo").tableColumn("NAZEV").label("Typ úvěru").contentType(CONTENTTYPE.TEXT).end()
                .build();
    }

}