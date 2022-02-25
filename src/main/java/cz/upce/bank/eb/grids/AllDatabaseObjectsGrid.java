package cz.upce.bank.eb.grids;

import cz.upce.bank.eb.entity.Adresy;
import cz.upce.bank.eb.entity.DatabaseObject;
import mk.gridlib.builders.GridConfigBuilder;
import mk.gridlib.domain.grid.GridConfig;
import mk.gridlib.enums.CONTENTTYPE;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AllDatabaseObjectsGrid {

    @Bean
    public GridConfig<DatabaseObject> getAllDatabaseObjects(){
        return GridConfigBuilder.createGrid(DatabaseObject.class, "objectName")
                .gridName("AllObjects")
                .unlimitedRowsCount()
                .column("objectName").tableColumn("OBJECT_NAME").label("Název objektu").contentType(CONTENTTYPE.TEXT).end()
                .column("objectType").tableColumn("OBJECT_TYPE").label("Typ objektu").contentType(CONTENTTYPE.TEXT).end()
                .build();
    }

}
