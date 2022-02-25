package mk.gridlib.dao;

import mk.gridlib.domain.grid.GridConfig;

import java.util.List;

public interface GridConfigDao {
    List<GridConfig<?>> getGrids();

    <T> void addGridConfig(GridConfig<T> config);

    <T> GridConfig<T> getGridConfig(String name);
}
