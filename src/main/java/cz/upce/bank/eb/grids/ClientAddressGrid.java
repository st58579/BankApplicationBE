package cz.upce.bank.eb.grids;

import cz.upce.bank.eb.entity.KlientiAdresy;
import mk.gridlib.builders.GridConfigBuilder;
import mk.gridlib.domain.grid.GridConfig;
import mk.gridlib.enums.CONTENTTYPE;
import mk.gridlib.enums.SORTORDER;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClientAddressGrid {

    @Bean
    public GridConfig<KlientiAdresy> getAllClientAddresses() {
        return GridConfigBuilder.createGrid(KlientiAdresy.class, "clientId")
                .gridName("ClientAddresses")
                .unlimitedRowsCount()
                .hiddenColumn("clientId").tableColumn("KLIENTI_ID").contentType(CONTENTTYPE.TEXT).end()
                .hiddenColumn("addressId").tableColumn("ADRESY_ID").contentType(CONTENTTYPE.TEXT).end()
                .column("name").tableColumn("JMENO").label("Jméno").contentType(CONTENTTYPE.TEXT).end()
                .column("surname").tableColumn("PRIJMENI").label("Přijmení").contentType(CONTENTTYPE.TEXT).end()
                .column("phoneNumber").tableColumn("KONTAKTNI_CISLO").label("Kontaktní číslo").contentType(CONTENTTYPE.TEXT).end()
                .column("birthNumber").tableColumn("RODNE_CISLO").label("Rodné číslo").contentType(CONTENTTYPE.TEXT).end()
                .sort("name", SORTORDER.ASC)
                .build();
    }

}
