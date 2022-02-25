package cz.upce.bank.eb.grids;

import cz.upce.bank.eb.entity.Adresy;
import mk.gridlib.builders.GridConfigBuilder;
import mk.gridlib.domain.grid.GridConfig;
import mk.gridlib.enums.CONTENTTYPE;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AdresyGrid {

    @Bean
    public GridConfig<Adresy> getAllAddresses() {
        return GridConfigBuilder.createGrid(Adresy.class, "addressId")
                .gridName("Addresses")
                .unlimitedRowsCount()
                .hiddenColumn("clientId").tableColumn("KLIENT_ID").contentType(CONTENTTYPE.TEXT).end()
                .hiddenColumn("addressId").tableColumn("ADRESA_ID").contentType(CONTENTTYPE.TEXT).end()
                .column("houseNumber").tableColumn("CISLO_POPISNE").label("Číslo popisné").contentType(CONTENTTYPE.TEXT).end()
                .column("street").tableColumn("ULICE").label("Ulice").contentType(CONTENTTYPE.TEXT).end()
                .column("town").tableColumn("MESTO").label("Město").contentType(CONTENTTYPE.TEXT).end()
                .column("postalCode").tableColumn("PSC").label("PSČ").contentType(CONTENTTYPE.TEXT).end()
                .column("countryCode").tableColumn("KOD_ZEME").label("Kód země").contentType(CONTENTTYPE.TEXT).end()
                .build();
    }
}