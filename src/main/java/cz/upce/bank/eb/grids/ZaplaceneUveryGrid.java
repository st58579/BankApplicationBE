package cz.upce.bank.eb.grids;

import cz.upce.bank.eb.entity.Uvery;
import cz.upce.bank.eb.entity.ZaplaceneUvery;
import mk.gridlib.builders.GridConfigBuilder;
import mk.gridlib.domain.grid.GridConfig;
import mk.gridlib.enums.CONTENTTYPE;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ZaplaceneUveryGrid {

    @Bean
    public GridConfig<ZaplaceneUvery> getZaplaceneUveryInfo() {
        return GridConfigBuilder.createGrid(ZaplaceneUvery.class, "creditId")
                .gridName("PayedCredits")
                .unlimitedRowsCount()
                .hiddenColumn("creditId").tableColumn("ID").contentType(CONTENTTYPE.TEXT).end()
                .hiddenColumn("accountId").tableColumn("UCTY_ID").contentType(CONTENTTYPE.TEXT).end()
                .virtualColumn("accountTypeId").fn(zaplaceneUvery -> {
                    int type = zaplaceneUvery.getAccountTypeId();
                    if (type == 1){
                        return "Spotřebitelský";
                    }
                    else if (type == 2){
                        return "Podnikatelský";
                    }
                    else {
                        return "Přečerpání";
                    }
                }).label("Typ účtu").end()
                .column("issueDate").tableColumn("DATUM_POSKYTNUTI").label("Datum poskytnuti").contentType(CONTENTTYPE.TEXT).end()
                .column("paymentDate").tableColumn("DATUM_ZAPLACENI").label("Datum zaplaceni").contentType(CONTENTTYPE.TEXT).end()
                .build();
    }
}
