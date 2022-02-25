package mk.gridlib.domain.grid;

import mk.gridlib.domain.conditions.SortCondition;

import java.util.List;

public class GridSimpleConfig<T> {
    private Class<T> clazz;

    private String gridName;

    private String gridLabel;

    private List<GridColumnSimpleConfig> columns;

    private List<SortCondition> defaultSortConditions;

    private Integer defaultRowsCount;

    public Class<T> getClazz() {
        return clazz;
    }

    public void setClazz(Class<T> clazz) {
        this.clazz = clazz;
    }

    public String getGridName() {
        return gridName;
    }

    public void setGridName(String gridName) {
        this.gridName = gridName;
    }

    public List<GridColumnSimpleConfig> getColumns() {
        return columns;
    }

    public void setColumns(List<GridColumnSimpleConfig> columns) {
        this.columns = columns;
    }

    public List<SortCondition> getDefaultSortConditions() {
        return defaultSortConditions;
    }

    public void setDefaultSortConditions(List<SortCondition> defaultSortConditions) {
        this.defaultSortConditions = defaultSortConditions;
    }

    public String getGridLabel() {
        return gridLabel;
    }

    public void setGridLabel(String gridLabel) {
        this.gridLabel = gridLabel;
    }

    public Integer getDefaultRowsCount() {
        return defaultRowsCount;
    }

    public void setDefaultRowsCount(Integer defaultRowsCount) {
        this.defaultRowsCount = defaultRowsCount;
    }
}
