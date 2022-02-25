package cz.upce.bank.eb.grids;

import cz.upce.bank.eb.entity.EbUser;
import mk.gridlib.builders.GridConfigBuilder;
import mk.gridlib.domain.grid.GridConfig;
import mk.gridlib.enums.CONTENTTYPE;
import mk.gridlib.enums.SORTORDER;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class EbUserGrid {

    @Bean
    public GridConfig<EbUser> getAllUsers() {
        return GridConfigBuilder.createGrid(EbUser.class, "userId")
                .gridName("AllEbUsers")
                .unlimitedRowsCount()
                .column("username").tableColumn("USER_NAME").label("User name").contentType(CONTENTTYPE.TEXT).end()
                .column("lastName").tableColumn("LAST_NAME").label("Last name").contentType(CONTENTTYPE.TEXT).end()
                .column("firstName").tableColumn("FIRST_NAME").label("First name").contentType(CONTENTTYPE.TEXT).end()
                .virtualColumn("fullName").fn(e -> e.getFirstName() + " " + e.getLastName()).contentType(CONTENTTYPE.TEXT).end()
                .sort("lastName", SORTORDER.ASC)
                .build();
    }
}
