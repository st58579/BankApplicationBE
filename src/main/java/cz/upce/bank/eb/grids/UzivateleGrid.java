package cz.upce.bank.eb.grids;

import cz.upce.bank.eb.entity.Klienti;
import cz.upce.bank.eb.entity.User;
import cz.upce.bank.eb.entity.UserRole;
import cz.upce.bank.eb.service.KlientiService;
import mk.gridlib.builders.GridConfigBuilder;
import mk.gridlib.domain.grid.GridConfig;
import mk.gridlib.enums.CONTENTTYPE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UzivateleGrid {

    @Autowired
    private KlientiService klientiService;

    @Bean
    public GridConfig<User> getUzivatelInfo() {
        return GridConfigBuilder.createGrid(User.class, "userId")
                .gridName("Users")
                .unlimitedRowsCount()
                .hiddenColumn("active").tableColumn("AKTIVNI").contentType(CONTENTTYPE.TEXT).end()
                .hiddenColumn("userId").tableColumn("ID").contentType(CONTENTTYPE.TEXT).end()
                .virtualColumn("role").fn(user -> {
                    if (user.getRole() == UserRole.USER){
                        return "Běžný uživatel";
                    }else {
                        return "Admin";
                    }
                }).label("Role").contentType(CONTENTTYPE.TEXT).end()
                .column("login").tableColumn("LOGIN").label("Login").contentType(CONTENTTYPE.TEXT).end()
                .virtualColumn("type").fn(user -> {
                    if (user.getClientId() == 0){
                        return "Účet správce";
                    }
                    Klienti klienti =  klientiService.getClientById(user.getClientId());
                    return "Klient: " + klienti.getName() + " " + klienti.getSurname();
                }).label("Typ účtu").contentType(CONTENTTYPE.TEXT).end()
                .column("registeredByLogin").tableColumn("REGISTER_BY_LOGIN").label("Byl vytvořen uživatelem")
                .contentType(CONTENTTYPE.TEXT).end()
                .virtualColumn("activeInfo").fn(user -> {
                    if (user.getActive().equals("1")){
                        return "Aktivní";
                    }
                    else {
                        return "Blokovaný";
                    }
                }).label("Stav").end()
                .build();
    }

}

