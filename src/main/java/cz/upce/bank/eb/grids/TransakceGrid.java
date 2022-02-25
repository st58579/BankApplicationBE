package cz.upce.bank.eb.grids;

import cz.upce.bank.eb.entity.Transakce;
import mk.gridlib.builders.GridConfigBuilder;
import mk.gridlib.domain.grid.GridConfig;
import mk.gridlib.enums.CONTENTTYPE;
import mk.gridlib.enums.SORTORDER;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TransakceGrid {

    @Bean
    public GridConfig<Transakce> getTransakceInfo() {
        return GridConfigBuilder.createGrid(Transakce.class, "transactionId")
                .gridName("Transactions")
                .unlimitedRowsCount()
                .hiddenColumn("transactionId").tableColumn("ID").contentType(CONTENTTYPE.TEXT).end()
                .hiddenColumn("accountId").tableColumn("UCET_ID").contentType(CONTENTTYPE.TEXT).end()
                /*.column("amount").tableColumn("CASTKA").label("Částka").contentType(CONTENTTYPE.TEXT).end()*/
                .virtualColumn("amount").fn(transakce -> transakce.getAmount() < 0 ? "- " + Math.abs(transakce.getAmount()) : transakce.getAmount())
                .label("Částka").end()
                .column("transactionTime").tableColumn("DATUM_TRANSAKCE").label("Čas transakce").contentType(CONTENTTYPE.TEXT).end()
                .column("transactionStateInfo").tableColumn("STAV_POPIS").label("Stav transakce").contentType(CONTENTTYPE.TEXT).end()
                .virtualColumn("periodInfo").fn(transakce -> {
                    String timePeriod = transakce.getTimePeriodInfo();
                    if (timePeriod == null){
                        return "Jednorázová";
                    }
                    else{
                        return timePeriod;
                    }
                }).label("Časové období").contentType(CONTENTTYPE.TEXT).end()
                .sort("transactionTime", SORTORDER.DESC)
                .build();
    }

}