package mk.gridlib.service.interfaces;

import mk.gridlib.domain.grid.GridConfig;

import java.util.List;

public interface GridConfigService {

    void initGrids();

    List<GridConfig<?>> getGrids();

    <T> void addGridConfig(GridConfig<T> config);

    <T> GridConfig<T> getGridConfig(String name);
}
