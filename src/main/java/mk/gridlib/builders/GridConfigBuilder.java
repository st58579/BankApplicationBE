package mk.gridlib.builders;

import mk.gridlib.builders.gridcolumn.GridClassColumnBuilder;
import mk.gridlib.builders.gridcolumn.GridColumnBuilder;
import mk.gridlib.builders.gridcolumn.GridHiddenColumnBuilder;
import mk.gridlib.builders.gridcolumn.GridVirtualColumnBuilder;
import mk.gridlib.domain.conditions.SearchCondition;
import mk.gridlib.domain.conditions.SortCondition;
import mk.gridlib.domain.grid.GridColumnConfig;
import mk.gridlib.domain.grid.GridConfig;
import mk.gridlib.enums.SEARCHTYPE;
import mk.gridlib.enums.SORTORDER;
import mk.gridlib.exceptions.GridException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GridConfigBuilder<T> {

    private Map<String, GridColumnConfig<T>> columns;

    private GridConfig<T> gridConfig;

    private GridConfigBuilder(Class<T> clazz, String idField) {
        this.gridConfig = new GridConfig<>(clazz, idField);
        this.gridConfig.setGridName(clazz.getSimpleName());
        this.gridConfig.setGridLabel(clazz.getSimpleName());
        this.columns = new HashMap<>();
    }

    public static <T> GridConfigBuilder<T> createGrid(Class<T> clazz, String idField) {
        return new GridConfigBuilder<T>(clazz, idField);
    }

    public GridConfigBuilder<T> gridName(String name) {
        this.gridConfig.setGridName(name);
        return this;
    }

    public GridConfigBuilder<T> label(String label) {
        this.gridConfig.setGridLabel(label);
        return this;
    }

    public GridConfigBuilder<T> label(String language, String label) {
        this.gridConfig.getLabels().put(language, label);
        return this;
    }

    public GridColumnBuilder<T> column(String name) {
        return new GridClassColumnBuilder<>(name, this);
    }

    public GridVirtualColumnBuilder<T> virtualColumn(String name) {
        return new GridVirtualColumnBuilder<>(name, this);
    }

    public GridHiddenColumnBuilder<T> hiddenColumn(String name) {
        return new GridHiddenColumnBuilder<>(name, this);
    }

    public GridConfigBuilder<T> rowsCount(int rowsCount) {
        if (rowsCount < 1) {
            throw GridException.badRowsCount();
        }
        this.gridConfig.setDefaultRowsCount(rowsCount);
        return this;
    }

    public GridConfigBuilder<T> unlimitedRowsCount() {
        this.gridConfig.setDefaultRowsCount(Integer.MAX_VALUE);
        return this;
    }

    public GridConfigBuilder<T> filter(String field, SEARCHTYPE searchType, Object value) {
        SearchCondition searchCondition = new SearchCondition(searchType, field,value, value);
        this.gridConfig.getSearchConditions().add(searchCondition);
        return this;
    }

    public GridConfigBuilder<T> filter(String field, SEARCHTYPE searchType, Object value1, Object value2) {
        SearchCondition searchCondition = new SearchCondition(searchType, field,value1, value2);
        this.gridConfig.getSearchConditions().add(searchCondition);
        return this;
    }

    public GridConfigBuilder<T> sort(String fieldName, SORTORDER sortorder) {
        int conditionOrder = gridConfig.getDefaultSortConditions().size();
        this.gridConfig.getDefaultSortConditions().add(new SortCondition(fieldName, sortorder, conditionOrder));
        return this;
    }

    public GridConfig<T> build() {
        gridConfig.setColumns(new ArrayList<>(this.columns.values()));
        return gridConfig;
    }

    public Map<String, GridColumnConfig<T>> getColumns() {
        return columns;
    }
}
