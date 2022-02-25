package cz.upce.bank.grid;


import mk.gridlib.dao.GridDataDao;
import mk.gridlib.main.GridSystem;
import mk.gridlib.service.interfaces.GridService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GridConfiguration {

    @Bean("gridService")
    public GridService gridDataService(GridDataDao gridDataDao) {
        return GridSystem.init(gridDataDao);
    }
}
