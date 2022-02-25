package mk.gridlib.dao;

import mk.gridlib.domain.grid.GridConfig;
import mk.gridlib.exceptions.GridException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GridConfigDaoImpl implements GridConfigDao {

    private final Map<String, GridConfig<?>> grids;

    public GridConfigDaoImpl() {
        this.grids = new HashMap<>();
    }

    public List<GridConfig<?>> getGrids() {
        return new ArrayList<>(this.grids.values());
    }

    public <T> void addGridConfig(GridConfig<T> config) {
        if (grids.containsKey(config.getGridName())) {
            throw GridException.nonUniqueName(config.getGridName());
        }
        this.grids.put(config.getGridName(), config);
    }

    public <T> GridConfig<T> getGridConfig(String name) {
        if (!this.grids.containsKey(name)) {
            throw GridException.gridNotExists(name);
        }
        return (GridConfig<T>) this.grids.get(name);
    }
}
