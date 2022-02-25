package cz.upce.bank.eb.grids;

import cz.upce.bank.eb.entity.Dokumenty;
import cz.upce.bank.eb.entity.EbUser;
import mk.gridlib.builders.GridConfigBuilder;
import mk.gridlib.domain.grid.GridConfig;
import mk.gridlib.enums.CONTENTTYPE;
import mk.gridlib.enums.SORTORDER;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DokumentyGrid {

    private String backendUrl = "http://localhost:8080";

    @Bean
    public GridConfig<Dokumenty> getAllDocuments() {
        return GridConfigBuilder.createGrid(Dokumenty.class, "documentId")
                .gridName("Documents")
                .unlimitedRowsCount()
                .hiddenColumn("clientId").tableColumn("KLIENT_ID").contentType(CONTENTTYPE.TEXT).end()
                .column("created").tableColumn("DATUM_VZNIKU").label("Datum vzniku").contentType(CONTENTTYPE.TEXT).end()
                .column("name").tableColumn("NAZEV").label("Název").contentType(CONTENTTYPE.TEXT).end()
                .column("info").tableColumn("POPIS").label("Typ dokumentu").contentType(CONTENTTYPE.TEXT).end()
                .virtualColumn("url").fn(e -> ("/api/dokumenty/" + e.getDocumentId())).contentType(CONTENTTYPE.URL).label("Odkaz ke stažení").end()
                .sort("created", SORTORDER.DESC)
                .build();
    }
}