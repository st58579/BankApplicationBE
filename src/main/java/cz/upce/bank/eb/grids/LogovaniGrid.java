package cz.upce.bank.eb.grids;

import cz.upce.bank.eb.entity.Logovani;
import mk.gridlib.builders.GridConfigBuilder;
import mk.gridlib.domain.grid.GridConfig;
import mk.gridlib.enums.CONTENTTYPE;
import mk.gridlib.enums.SORTORDER;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LogovaniGrid {

    @Bean
    public GridConfig<Logovani> getAllLogs(){
        return GridConfigBuilder.createGrid(Logovani.class,"logId")
                .gridName("Logs")
                .unlimitedRowsCount()
                .hiddenColumn("logId").tableColumn("LOG_ID").contentType(CONTENTTYPE.TEXT).end()
                .column("logTime").tableColumn("DATUM").label("Čas logování").contentType(CONTENTTYPE.TEXT).end()
                .column("tableName").tableColumn("JMENO_TABULKY").label("Tabulka").contentType(CONTENTTYPE.TEXT).end()
                .column("query").tableColumn("DOTAZ").label("Dotaz").contentType(CONTENTTYPE.TEXT).end()
                .column("desc").tableColumn("POPIS").label("Popis dotazu").contentType(CONTENTTYPE.TEXT).end()
                .sort("logTime", SORTORDER.DESC)
                .build();
    }

}
