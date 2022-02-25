package cz.upce.bank.eb.grids;

import cz.upce.bank.eb.entity.Karty;
import mk.gridlib.builders.GridConfigBuilder;
import mk.gridlib.domain.grid.GridConfig;
import mk.gridlib.enums.CONTENTTYPE;
import mk.gridlib.enums.SORTORDER;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KartyGrid {

    @Bean
    public GridConfig<Karty> getCardInfo() {
        return GridConfigBuilder.createGrid(Karty.class, "cardId")
                .gridName("Cards")
                .unlimitedRowsCount()
                .hiddenColumn("cardId").tableColumn("ID").label("ID").contentType(CONTENTTYPE.TEXT).end()
                .column("cardNumber").tableColumn("CISLO_KARTY").label("Číslo karty").contentType(CONTENTTYPE.TEXT).end()
                .column("issueDate").tableColumn("DATUM_VYDANI").label("Datum vydání").contentType(CONTENTTYPE.TEXT).end()
                .column("expirationDate").tableColumn("DATUM_PLATNOSTI").label("Datum platnosti").contentType(CONTENTTYPE.TEXT).end()
                .column("ownerName").tableColumn("JMENO").label("Client name").contentType(CONTENTTYPE.TEXT).end()
                .column("ownerSurname").tableColumn("PRIJMENI").label("Last name").contentType(CONTENTTYPE.TEXT).end()
                .sort("name", SORTORDER.ASC)
                .build();
    }

    @Bean
    public GridConfig<Karty> getAccountCardInfo() {
        return GridConfigBuilder.createGrid(Karty.class, "cardId")
                .gridName("AccountCards")
                .unlimitedRowsCount()
                .hiddenColumn("cardId").tableColumn("ID").contentType(CONTENTTYPE.TEXT).end()
                .hiddenColumn("accountId").tableColumn("UCET_ID").contentType(CONTENTTYPE.TEXT).end()
                .column("cardNumber").tableColumn("CISLO_KARTY").label("Číslo karty").contentType(CONTENTTYPE.TEXT).end()
                .column("issueDate").tableColumn("DATUM_VYDANI").label("Datum vydání").contentType(CONTENTTYPE.TEXT).end()
                .column("expirationDate").tableColumn("DATUM_PLATNOSTI").label("Datum platnosti").contentType(CONTENTTYPE.TEXT).end()
                .column("state").tableColumn("POPIS").label("Stav karty").contentType(CONTENTTYPE.TEXT).end()
                .build();
    }
}