package cz.upce.bank.eb.grids;

import cz.upce.bank.eb.entity.Ucty;
import mk.gridlib.builders.GridConfigBuilder;
import mk.gridlib.domain.grid.GridConfig;
import mk.gridlib.enums.CONTENTTYPE;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UctyGrid {

    @Bean
    public GridConfig<Ucty> getAllAccounts() {
        return GridConfigBuilder.createGrid(Ucty.class, "accountId")
                .gridName("Accounts")
                .unlimitedRowsCount()
                .column("accountNumber").tableColumn("CISLO_UCTU").label("Číslo účtu").contentType(CONTENTTYPE.TEXT).end()
                .column("remainder").tableColumn("ZUSTATEK").label("Aktuální zůstatek").contentType(CONTENTTYPE.TEXT).end()
                .virtualColumn("accountType").fn(ucty -> {
                    String type = ucty.getAccountType();
                    if (type.equals("B")){
                        return "Běžný";
                    }
                    else if (type.equals("U")){
                        return "Úvěrový";
                    }
                    else {
                        return "Spořící";
                    }
                }).label("Typ účtu").end()
                .hiddenColumn("accountId").tableColumn("UCET_ID").contentType(CONTENTTYPE.TEXT).end()
                .hiddenColumn("clientId").tableColumn("KLIENT_ID").contentType(CONTENTTYPE.TEXT).end()
                .column("state").tableColumn("POPIS").label("Stav účtu").contentType(CONTENTTYPE.TEXT).end()
                .build();
    }

    @Bean
    public GridConfig<Ucty> getAllAccountsWithNames() {
        return GridConfigBuilder.createGrid(Ucty.class, "accountId")
                .gridName("AccountsWithName")
                .unlimitedRowsCount()
                .column("accountNumber").tableColumn("CISLO_UCTU").label("Číslo účtu").contentType(CONTENTTYPE.TEXT).end()
                .column("remainder").tableColumn("ZUSTATEK").label("Aktuální zůstatek").contentType(CONTENTTYPE.TEXT).end()
                .virtualColumn("accountType").fn(ucty -> {
                    String type = ucty.getAccountType();
                    if (type.equals("B")){
                        return "Běžný";
                    }
                    else if (type.equals("U")){
                        return "Úvěrový";
                    }
                    else {
                        return "Spořící";
                    }
                }).label("Typ účtu").end()
                .hiddenColumn("accountId").tableColumn("UCET_ID").contentType(CONTENTTYPE.TEXT).end()
                .hiddenColumn("clientId").tableColumn("KLIENT_ID").contentType(CONTENTTYPE.TEXT).end()
                .column("state").tableColumn("POPIS").label("Stav účtu").contentType(CONTENTTYPE.TEXT).end()
                .column("ownerName").tableColumn("JMENO").label("Jméno majitele").contentType(CONTENTTYPE.TEXT).end()
                .column("ownerSurname").tableColumn("PRIJMENI").label("Příjmení majitele").contentType(CONTENTTYPE.TEXT).end()
                .build();
    }
}